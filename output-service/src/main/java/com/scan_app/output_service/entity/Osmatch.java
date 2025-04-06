
package com.scan_app.output_service.entity;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Osclass> osclass = new ArrayList<>();
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
    public List<Osclass> getOsclass() {
        return osclass;
    }

    @JsonProperty("osclass")
    public void setOsclass(List<Osclass> osclass) {
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
