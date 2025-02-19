
package com.scan_app.output_service.entity;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "portused",
    "osmatch"
})
@Generated("jsonschema2pojo")
public class Os {

    @JsonProperty("portused")
    private List<Portused> portused;
    @JsonProperty("osmatch")
    private List<Osmatch> osmatch;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("portused")
    public List<Portused> getPortused() {
        return portused;
    }

    @JsonProperty("portused")
    public void setPortused(List<Portused> portused) {
        this.portused = portused;
    }

    @JsonProperty("osmatch")
    public List<Osmatch> getOsmatch() {
        return osmatch;
    }

    @JsonProperty("osmatch")
    public void setOsmatch(List<Osmatch> osmatch) {
        this.osmatch = osmatch;
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
