
package com.scan_app.output_service.entity;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "state",
    "proto",
    "portid"
})
@Generated("jsonschema2pojo")
public class Portused {

    @JsonProperty("state")
    private String state;
    @JsonProperty("proto")
    private String proto;
    @JsonProperty("portid")
    private String portid;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("proto")
    public String getProto() {
        return proto;
    }

    @JsonProperty("proto")
    public void setProto(String proto) {
        this.proto = proto;
    }

    @JsonProperty("portid")
    public String getPortid() {
        return portid;
    }

    @JsonProperty("portid")
    public void setPortid(String portid) {
        this.portid = portid;
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
