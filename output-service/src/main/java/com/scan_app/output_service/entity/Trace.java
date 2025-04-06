
package com.scan_app.output_service.entity;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "port",
    "proto",
    "hop"
})
@Generated("jsonschema2pojo")
public class Trace {

    @JsonProperty("port")
    private String port;
    @JsonProperty("proto")
    private String proto;
    @JsonProperty("hop")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Hop> hop = new ArrayList<>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("port")
    public String getPort() {
        return port;
    }

    @JsonProperty("port")
    public void setPort(String port) {
        this.port = port;
    }

    @JsonProperty("proto")
    public String getProto() {
        return proto;
    }

    @JsonProperty("proto")
    public void setProto(String proto) {
        this.proto = proto;
    }

    @JsonProperty("hop")
    public List<Hop> getHop() {
        return hop;
    }

    @JsonProperty("hop")
    public void setHop(List<Hop> hop) {
        this.hop = hop;
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
