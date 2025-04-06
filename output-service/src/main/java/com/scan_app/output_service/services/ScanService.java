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
            return nmaprunRepository.findById(id).get();
        } else {
            throw new NoSuchElementException("No scan found with ID: " + id);
        }
    }

    public List<HostDto> getQuickScanByScanId(String id) {
        Nmaprun nmaprun = getScanById(id);
        List<Host> hosts = nmaprun.getHost();
        if (hosts.isEmpty()) {
            return new ArrayList<>();
        }

        List<HostDto> result = new ArrayList<>();
        for (Host hostn:
             hosts) {
            HostDto hostDto = mapToHost(hostn);
            result.add(hostDto);
        }

        return result;
    }

    public NmapRunDto getNmapRunByScanId(String id) {
        List<HostDto> hosts = getQuickScanByScanId(id);

        return new NmapRunDto(hosts);
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
        List<Nmaprun> nmapruns = nmaprunRepository.filterScans(filterScansDto.getScanIds(), filterScansDto.getPort(), filterScansDto.getMaxDistance());
        if (!nmapruns.isEmpty()) {
            // Filter by maxOpenPorts
            if (filterScansDto.getMaxOpenPorts() != null) {
                List<Nmaprun> temp = new ArrayList<>();
                for (Nmaprun nmaprun : nmapruns) {
                    if (nmaprun.getHost() == null) continue;

                    for (Host host : nmaprun.getHost()) {
                        if (host.getPorts() == null || host.getPorts().getPort() == null) continue;

                        if (host.getPorts().getPort().size() <= filterScansDto.getMaxOpenPorts()) {
                            temp.add(nmaprun);
                            break;
                        }
                    }
                }
                nmapruns = temp;
            }

            // Filter by oneHost
            if (Boolean.TRUE.equals(filterScansDto.getOneHost())) {
                List<Nmaprun> temp = new ArrayList<>();
                for (Nmaprun nmaprun : nmapruns) {
                    if (nmaprun.getHost().size() == 1) {
                        System.out.println(nmaprun.getHost().size());
                        temp.add(nmaprun);
                    }
                }
                nmapruns = temp;
            }
        }
        return nmapruns.stream().map(Nmaprun::get_id).toList();
    }
}