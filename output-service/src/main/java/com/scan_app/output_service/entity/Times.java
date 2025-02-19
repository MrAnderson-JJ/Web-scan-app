
package com.scan_app.output_service.entity;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "rttvar",
    "srtt",
    "to"
})
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("jsonschema2pojo")
public class Times {

    @JsonProperty("rttvar")
    private String rttvar;
    @JsonProperty("srtt")
    private String srtt;
    @JsonProperty("to")
    private String to;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("rttvar")
    public String getRttvar() {
        return rttvar;
    }

    @JsonProperty("rttvar")
    public void setRttvar(String rttvar) {
        this.rttvar = rttvar;
    }

    @JsonProperty("srtt")
    public String getSrtt() {
        return srtt;
    }

    @JsonProperty("srtt")
    public void setSrtt(String srtt) {
        this.srtt = srtt;
    }

    @JsonProperty("to")
    public String getTo() {
        return to;
    }

    @JsonProperty("to")
    public void setTo(String to) {
        this.to = to;
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
