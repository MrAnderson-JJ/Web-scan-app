
package com.scan_app.output_service.entity;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "count",
    "ports",
    "proto",
    "reason"
})
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("jsonschema2pojo")
public class Extrareasons {

    @JsonProperty("count")
    private String count;
    @JsonProperty("ports")
    private String ports;
    @JsonProperty("proto")
    private String proto;
    @JsonProperty("reason")
    private String reason;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("count")
    public String getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(String count) {
        this.count = count;
    }

    @JsonProperty("ports")
    public String getPorts() {
        return ports;
    }

    @JsonProperty("ports")
    public void setPorts(String ports) {
        this.ports = ports;
    }

    @JsonProperty("proto")
    public String getProto() {
        return proto;
    }

    @JsonProperty("proto")
    public void setProto(String proto) {
        this.proto = proto;
    }

    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }

    @JsonProperty("reason")
    public void setReason(String reason) {
        this.reason = reason;
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
