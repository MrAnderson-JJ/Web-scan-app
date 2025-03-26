package com.scan_app.output_service.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scan_app.output_service.dto.FilterScansDto;
import com.scan_app.output_service.entity.*;
import com.scan_app.output_service.repository.HostRepository;
import com.scan_app.output_service.repository.NmapCustomRepositoryImpl;
import com.scan_app.output_service.repository.NmapRepository;
import com.scan_app.output_service.repository.PortRepository;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScanService {

    @Autowired
    private NmapRepository nmaprunRepository;
    @Autowired
    private HostRepository hostRepository;
    @Autowired
    private PortRepository portRepository;

    private final XmlToJsonService xmlToJsonService;

    public Nmaprun saveScan(String filePathXml) throws Exception {
        // Convert XML to JSON
        String jsonOutput = xmlToJsonService.jsonConvert(filePathXml);

        System.out.println(jsonOutput);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("before map");
        Nmaprun nmaprun = objectMapper.readValue(jsonOutput, Nmaprun.class);
        nmaprun.set_id(new ObjectId().toString());

        Host host = nmaprun.getHost();
        host.setId(new ObjectId().toString());
        host.setNmapRunRefId(nmaprun.get_id());

        System.out.println(nmaprun.getScanner());
        System.out.println("here");
        Ports ports = new Ports();
        System.out.println("asdasdsdaasd");
        if (host.getPorts() != null) {
            System.out.println("in if");
            ports = host.getPorts();
            ports.setId(new ObjectId().toString());
            ports.setHostRefId(host.getId());
        }
        System.out.println("after ports");
        System.out.println("id: " + nmaprun.get_id());
        nmaprunRepository.save(nmaprun);
        hostRepository.save(host);
        if (host.getPorts() != null) {
            portRepository.save(ports);
        }
        return nmaprun;
    }

    public Nmaprun saveScanJson(String json) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
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

    public List<PortDto> getPortsByScanId(String id) {
        List<Host> hosts = hostRepository.findByNmapRunRefId(id);
        if (hosts.isEmpty()) {
            throw new NoSuchElementException("No hosts found for scan ID: " + id);
        }

        List<String> hostIds = hosts.stream()
                .map(Host::getId)
                .collect(Collectors.toList());

        if (portRepository.findByHostRefIdIn(hostIds) == null) {
            return null;
        }
        List<Port> ports = portRepository.findByHostRefIdIn(hostIds).getPort();

        System.out.println("output: " + ports.getFirst().getPortid());
        return ports.stream().map(this::mapToPorts).toList();
    }

    public List<HostDto> getQuickScanByScanId(String id) {
        Nmaprun nmaprun = getScanById(id);
        Host host = nmaprun.getHost();
        List<Host> hosts = new ArrayList<>();
        hosts.add(host);
        if (hosts.isEmpty()) {
            throw new NoSuchElementException("No hosts found for scan ID: " + id);
        }

        List<PortDto> portDtos = host.getPorts().getPort().stream().map(this::mapToPorts).toList();

        List<HostDto> result = new ArrayList<>();
        for (Host hostn:
             hosts) {
            HostDto hostDto = mapToHost(hostn, portDtos);
            result.add(hostDto);
        }

        return result;
    }

    public PingDto getPingByScanId(String id) {
        List<Host> hosts = hostRepository.findByNmapRunRefId(id);
        Nmaprun nmaprun = getScanById(id);

        List<HostDto> result = new ArrayList<>();
        for (Host host:
                hosts) {
            HostDto hostDto = mapToHost(host, null);
            result.add(hostDto);
        }

        return PingDto.builder()
                .host(result.getFirst())
                .elapsed(nmaprun.getRunstats().getFinished().getElapsed())
                .build();
    }

    public List<HostDto> getIntenseScanByScanId(String id) {
        List<Host> hosts = hostRepository.findByNmapRunRefId(id);
        if (hosts.isEmpty()) {
            throw new NoSuchElementException("No hosts found for scan ID: " + id);
        }

        List<PortDto> portDtos = getPortsByScanId(id);

        List<HostDto> result = new ArrayList<>();
        for (Host host:
                hosts) {
            HostDto hostDto = mapToHost(host, portDtos);
            result.add(hostDto);
        }

        return result;
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
                .service(port.getService().getName())
                .build();
    }

    public AddressDto mapToAddress(Host host) {
        return AddressDto.builder()
                .addr(host.getAddress().getAddr())
                .addrType(host.getAddress().getAddrtype())
                .build();
    }

    public HostStatusDto mapToHostStatus(Host host) {
        return HostStatusDto.builder()
                .reason(host.getStatus().getReason())
                .reasonTtl(host.getStatus().getReasonTtl())
                .state(host.getStatus().getState())
                .build();
    }

    public HostDto mapToHost(Host host, List<PortDto> portsDto) {
        return HostDto.builder()
                .hostStatusDto(mapToHostStatus(host))
                .address(mapToAddress(host))
                .ports(portsDto)
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