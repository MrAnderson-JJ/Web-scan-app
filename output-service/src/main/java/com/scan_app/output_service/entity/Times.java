
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
    private Integer rttvar;
    @JsonProperty("srtt")
    private Integer srtt;
    @JsonProperty("to")
    private Integer to;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("rttvar")
    public Integer getRttvar() {
        return rttvar;
    }

    @JsonProperty("rttvar")
    public void setRttvar(Integer rttvar) {
        this.rttvar = rttvar;
    }

    @JsonProperty("srtt")
    public Integer getSrtt() {
        return srtt;
    }

    @JsonProperty("srtt")
    public void setSrtt(Integer srtt) {
        this.srtt = srtt;
    }

    @JsonProperty("to")
    public Integer getTo() {
        return to;
    }

    @JsonProperty("to")
    public void setTo(Integer to) {
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
