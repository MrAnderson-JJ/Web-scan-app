
package com.scan_app.output_service.entity;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "seconds",
    "lastboot"
})
@Generated("jsonschema2pojo")
public class Uptime {

    @JsonProperty("seconds")
    private String seconds;
    @JsonProperty("lastboot")
    private String lastboot;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("seconds")
    public String getSeconds() {
        return seconds;
    }

    @JsonProperty("seconds")
    public void setSeconds(String seconds) {
        this.seconds = seconds;
    }

    @JsonProperty("lastboot")
    public String getLastboot() {
        return lastboot;
    }

    @JsonProperty("lastboot")
    public void setLastboot(String lastboot) {
        this.lastboot = lastboot;
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
