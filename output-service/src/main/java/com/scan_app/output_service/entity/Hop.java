
package com.scan_app.output_service.entity;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ttl",
    "ipaddr",
    "rtt",
    "host"
})
@Generated("jsonschema2pojo")
public class Hop {

    @JsonProperty("ttl")
    private String ttl;
    @JsonProperty("ipaddr")
    private String ipaddr;
    @JsonProperty("rtt")
    private String rtt;
    @JsonProperty("host")
    private String host;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("ttl")
    public String getTtl() {
        return ttl;
    }

    @JsonProperty("ttl")
    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    @JsonProperty("ipaddr")
    public String getIpaddr() {
        return ipaddr;
    }

    @JsonProperty("ipaddr")
    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    @JsonProperty("rtt")
    public String getRtt() {
        return rtt;
    }

    @JsonProperty("rtt")
    public void setRtt(String rtt) {
        this.rtt = rtt;
    }

    @JsonProperty("host")
    public String getHost() {
        return host;
    }

    @JsonProperty("host")
    public void setHost(String host) {
        this.host = host;
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
