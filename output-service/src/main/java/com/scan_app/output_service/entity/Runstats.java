
package com.scan_app.output_service.entity;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "finished",
    "hosts"
})
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("jsonschema2pojo")
public class Runstats {

    @JsonProperty("finished")
    private Finished finished;
    @JsonProperty("hosts")
    private Hosts hosts;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("finished")
    public Finished getFinished() {
        return finished;
    }

    @JsonProperty("finished")
    public void setFinished(Finished finished) {
        this.finished = finished;
    }

    @JsonProperty("hosts")
    public Hosts getHosts() {
        return hosts;
    }

    @JsonProperty("hosts")
    public void setHosts(Hosts hosts) {
        this.hosts = hosts;
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
