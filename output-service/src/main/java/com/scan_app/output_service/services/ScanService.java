package com.scan_app.output_service.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scan_app.output_service.dto.FilterScansDto;
import com.scan_app.output_service.entity.*;
import com.scan_app.output_service.repository.NmapRepository;
import com.scan_app.output_service.dto.scan.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScanService {

    @Autowired
    private NmapRepository nmaprunRepository;

    public Nmaprun saveScanJson(String json) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        Nmaprun nmaprun = objectMapper.readValue(json, Nmaprun.class);
        nmaprun.set_id(new ObjectId().toString());

        nmaprunRepository.save(nmaprun);
        return nmaprun;
    }

    public Nmaprun getScanById(String id){
        if (nmaprunRepository.findById(id).isPresent()) {
            Nmaprun nmaprun = nmaprunRepository.findById(id).get();
            return nmaprun;
        } else {
            throw new NoSuchElementException("No scan found with ID: " + id);
        }
    }

    public List<HostDto> getQuickScanByScanId(String id) {
        Nmaprun nmaprun = getScanById(id);
        //Host host = nmaprun.getHost();
        List<Host> hosts = nmaprun.getHost();
        //hosts.add(host);
        if (hosts.isEmpty()) {
            throw new NoSuchElementException("No hosts found for scan ID: " + id);
        }

        List<HostDto> result = new ArrayList<>();
        for (Host hostn:
             hosts) {
            HostDto hostDto = mapToHost(hostn);
            result.add(hostDto);
        }

        return result;
    }

    public PingDto getPingByScanId(String id) {
        Nmaprun nmaprun = getScanById(id);
        List<HostDto> hosts = getQuickScanByScanId(id);

        return PingDto.builder()
                .host(hosts.get(0))
                .elapsed(nmaprun.getRunstats().getFinished().getElapsed())
                .build();
    }

    public NmapRunDto getNmapRunByScanId(String id) {
        List<HostDto> hosts = getQuickScanByScanId(id);
        NmapRunDto nmapRunDto = new NmapRunDto(hosts);

        return nmapRunDto;
    }

    public boolean deleteScans(List<String> scanIds) {
        nmaprunRepository.deleteAllById(scanIds);
        return nmaprunRepository.findAllById(scanIds).isEmpty();
    }

    public PortDto mapToPorts(Port port) {
        return PortDto.builder()
                .portId(Integer.valueOf(port.getPortid()))
                .protocol(port.getProtocol())
                .state(port.getState().getState())
                .service(Optional.ofNullable(port.getService())
                        .map(com.scan_app.output_service.entity.Service::getName)
                        .orElse(null))
                .build();

    }

    public AddressDto mapToAddress(Host host) {
        Address address = host.getAddress().stream()
                .filter(a -> "ipv4".equalsIgnoreCase(a.getAddrtype()))
                .findFirst()
                .orElse(null);
        return AddressDto.builder()
                .addr(address.getAddr())
                .addrType(address.getAddrtype())
                .build();
    }

    public HostStatusDto mapToHostStatus(Host host) {
        return HostStatusDto.builder()
                .reason(host.getStatus().getReason())
                .reasonTtl(host.getStatus().getReasonTtl())
                .state(host.getStatus().getState())
                .build();
    }

    public HostDto mapToHost(Host host) {
        return HostDto.builder()
                .hostStatusDto(mapToHostStatus(host))
                .address(mapToAddress(host))
                //.ports(portsDto)
                .ports(Optional.ofNullable(host.getPorts()).map(ports -> ports.getPort().stream().map(this::mapToPorts).toList()).orElse(null))
                .trace(Optional.ofNullable(host.getTrace()).map(this::mapToTrace).orElse(null))
                .os(Optional.ofNullable(host.getOs()).map(this::mapToOs).orElse(null))
                .build();
    }

    public HopDto mapToHop(Hop hop) {
        return HopDto.builder()
                .ip(hop.getIpaddr())
                .rtt(hop.getRtt())
                .ttl(hop.getTtl())
                .host(hop.getHost())
                .build();
    }

    public TraceDto mapToTrace(Trace trace) {
        return TraceDto.builder()
                .proto(trace.getProto())
                .port(trace.getPort())
                .hop(trace.getHop().stream().map(this::mapToHop).toList())
                .build();
    }

    public OsClassDto mapToOsClass(Osclass osclass) {
        return OsClassDto.builder()
                .cpe(osclass.getCpe())
                .type(osclass.getType())
                .vendor(osclass.getVendor())
                .osfamily(osclass.getOsfamily())
                .accuracy(osclass.getAccuracy())
                .build();
    }

    public OsMatchDto mapToOsMatch(Osmatch osmatch) {
        return OsMatchDto.builder()
                .name(osmatch.getName())
                .accuracy(osmatch.getAccuracy())
                .line(osmatch.getLine())
                .osclass(osmatch.getOsclass().stream().map(this::mapToOsClass).toList())
                .build();
    }

    public OsDto mapToOs(Os os) {
        return OsDto.builder()
                .osMatch(os.getOsmatch().stream().map(this::mapToOsMatch).toList())
                .build();
    }

    public List<String> filterScans(FilterScansDto filterScansDto){
        System.out.println("second");
        List<Nmaprun> nmapruns = nmaprunRepository.filterScans(filterScansDto.getScanIds(), filterScansDto.getPort(), filterScansDto.getMaxDistance());
        System.out.println("k");
        System.out.println("third");
        return nmapruns.stream().map(Nmaprun::get_id).toList();
    }
}