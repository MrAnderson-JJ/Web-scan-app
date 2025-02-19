
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
    "state",
    "extrareasons"
})
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("jsonschema2pojo")
public class Extraports {

    @JsonProperty("count")
    private String count;
    @JsonProperty("state")
    private String state;
    @JsonProperty("extrareasons")
    private Extrareasons extrareasons;
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

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("extrareasons")
    public Extrareasons getExtrareasons() {
        return extrareasons;
    }

    @JsonProperty("extrareasons")
    public void setExtrareasons(Extrareasons extrareasons) {
        this.extrareasons = extrareasons;
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
