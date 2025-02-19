
package com.scan_app.output_service.entity;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "endtime",
    "starttime",
    "address",
    "hostnames",
    "ports",
    "status",
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
    @JsonProperty("endtime")
    private Integer endtime;
    @JsonProperty("starttime")
    private Integer starttime;
    @JsonProperty("address")
    private Address address;
    @JsonProperty("hostnames")
    private Hostnames hostnames;
    @JsonProperty("ports")
    @Transient // Do not save ports directly here
    private Ports ports;
    @JsonProperty("status")
    private Status status;
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

    @JsonProperty("endtime")
    public Integer getEndtime() {
        return endtime;
    }

    @JsonProperty("endtime")
    public void setEndtime(Integer endtime) {
        this.endtime = endtime;
    }

    @JsonProperty("starttime")
    public Integer getStarttime() {
        return starttime;
    }

    @JsonProperty("starttime")
    public void setStarttime(Integer starttime) {
        this.starttime = starttime;
    }

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Address address) {
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

    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Status status) {
        this.status = status;
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
