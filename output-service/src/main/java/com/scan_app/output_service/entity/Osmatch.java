
package com.scan_app.output_service.entity;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "accuracy",
    "line",
    "osclass"
})
@Generated("jsonschema2pojo")
public class Osmatch {

    @JsonProperty("name")
    private String name;
    @JsonProperty("accuracy")
    private String accuracy;
    @JsonProperty("line")
    private String line;
    @JsonProperty("osclass")
    private Osclass osclass;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("accuracy")
    public String getAccuracy() {
        return accuracy;
    }

    @JsonProperty("accuracy")
    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    @JsonProperty("line")
    public String getLine() {
        return line;
    }

    @JsonProperty("line")
    public void setLine(String line) {
        this.line = line;
    }

    @JsonProperty("osclass")
    public Osclass getOsclass() {
        return osclass;
    }

    @JsonProperty("osclass")
    public void setOsclass(Osclass osclass) {
        this.osclass = osclass;
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
