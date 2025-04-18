
package com.scan_app.output_service.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "endtime",
        "starttime",
        "address",
        "hostnames",
        "ports",
        "status",
        "os",
        "uptime",
        "distance",
        "tcpsequence",
        "ipidsequence",
        "tcptssequence",
        "trace",
        "times"
})
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("jsonschema2pojo")
@Document(collection = "hosts")
public class Host {

    @Id
    private String id;
    private String nmapRunRefId;
    @DBRef // References nmaprun collection
    private Nmaprun nmaprun;
    @JsonProperty("starttime")
    private Integer starttime;
    @JsonProperty("endtime")
    private Integer endtime;
    @JsonProperty("status")
    private Status status;
    @JsonProperty("address")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Address> address = new ArrayList<>();;
    @JsonProperty("hostnames")
    private Hostnames hostnames;
    @JsonProperty("ports")
    private Ports ports;
    @JsonProperty("os")
    private Os os;
    @JsonProperty("uptime")
    private Uptime uptime;
    @JsonProperty("distance")
    private Distance distance;
    @JsonProperty("tcpsequence")
    private Tcpsequence tcpsequence;
    @JsonProperty("ipidsequence")
    private Ipidsequence ipidsequence;
    @JsonProperty("tcptssequence")
    private Tcptssequence tcptssequence;
    @JsonProperty("trace")
    private Trace trace;
    @JsonProperty("times")
    private Times times;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNmapRunRefId() {
        return nmapRunRefId;
    }

    public void setNmapRunRefId(String nmapRunRefId) {
        this.nmapRunRefId = nmapRunRefId;
    }

    @JsonProperty("starttime")
    public Integer getStarttime() {
        return starttime;
    }

    @JsonProperty("starttime")
    public void setStarttime(Integer starttime) {
        this.starttime = starttime;
    }

    @JsonProperty("endtime")
    public Integer getEndtime() {
        return endtime;
    }

    @JsonProperty("endtime")
    public void setEndtime(Integer endtime) {
        this.endtime = endtime;
    }

    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Status status) {
        this.status = status;
    }

    @JsonProperty("address")
    public List<Address> getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(List<Address> address) {
        this.address = address;
    }

    @JsonProperty("hostnames")
    public Hostnames getHostnames() {
        return hostnames;
    }

    @JsonProperty("hostnames")
    public void setHostnames(Hostnames hostnames) {
        this.hostnames = hostnames;
    }

    @JsonProperty("ports")
    public Ports getPorts() {
        return ports;
    }

    @JsonProperty("ports")
    public void setPorts(Ports ports) {
        this.ports = ports;
    }

    @JsonProperty("os")
    public Os getOs() {
        return os;
    }

    @JsonProperty("os")
    public void setOs(Os os) {
        this.os = os;
    }

    @JsonProperty("uptime")
    public Uptime getUptime() {
        return uptime;
    }

    @JsonProperty("uptime")
    public void setUptime(Uptime uptime) {
        this.uptime = uptime;
    }

    @JsonProperty("distance")
    public Distance getDistance() {
        return distance;
    }

    @JsonProperty("distance")
    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    @JsonProperty("tcpsequence")
    public Tcpsequence getTcpsequence() {
        return tcpsequence;
    }

    @JsonProperty("tcpsequence")
    public void setTcpsequence(Tcpsequence tcpsequence) {
        this.tcpsequence = tcpsequence;
    }

    @JsonProperty("ipidsequence")
    public Ipidsequence getIpidsequence() {
        return ipidsequence;
    }

    @JsonProperty("ipidsequence")
    public void setIpidsequence(Ipidsequence ipidsequence) {
        this.ipidsequence = ipidsequence;
    }

    @JsonProperty("tcptssequence")
    public Tcptssequence getTcptssequence() {
        return tcptssequence;
    }

    @JsonProperty("tcptssequence")
    public void setTcptssequence(Tcptssequence tcptssequence) {
        this.tcptssequence = tcptssequence;
    }

    @JsonProperty("trace")
    public Trace getTrace() {
        return trace;
    }

    @JsonProperty("trace")
    public void setTrace(Trace trace) {
        this.trace = trace;
    }

    @JsonProperty("times")
    public Times getTimes() {
        return times;
    }

    @JsonProperty("times")
    public void setTimes(Times times) {
        this.times = times;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
