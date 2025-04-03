
package com.scan_app.output_service.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "address",
    "hostnames",
    "status"
})
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("jsonschema2pojo")
public class Hosthint {

    @JsonProperty("address")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Address__1> address = new ArrayList<>();
    @JsonProperty("hostnames")
    private Hostnames__1 hostnames;
    @JsonProperty("status")
    private Status__1 status;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("address")
    public List<Address__1> getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(List<Address__1> address) {
        this.address = address;
    }

    @JsonProperty("hostnames")
    public Hostnames__1 getHostnames() {
        return hostnames;
    }

    @JsonProperty("hostnames")
    public void setHostnames(Hostnames__1 hostnames) {
        this.hostnames = hostnames;
    }

    @JsonProperty("status")
    public Status__1 getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Status__1 status) {
        this.status = status;
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
