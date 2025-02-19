
package com.scan_app.output_service.entity;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "addr",
    "addrtype"
})
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("jsonschema2pojo")
public class Address {

    @JsonProperty("addr")
    private String addr;
    @JsonProperty("addrtype")
    private String addrtype;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("addr")
    public String getAddr() {
        return addr;
    }

    @JsonProperty("addr")
    public void setAddr(String addr) {
        this.addr = addr;
    }

    @JsonProperty("addrtype")
    public String getAddrtype() {
        return addrtype;
    }

    @JsonProperty("addrtype")    public void setAddrtype(String addrtype) {
        this.addrtype = addrtype;
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
