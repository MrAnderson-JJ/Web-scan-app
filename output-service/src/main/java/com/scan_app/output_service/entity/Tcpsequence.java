
package com.scan_app.output_service.entity;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "index",
    "difficulty",
    "values"
})
@Generated("jsonschema2pojo")
public class Tcpsequence {

    @JsonProperty("index")
    private String index;
    @JsonProperty("difficulty")
    private String difficulty;
    @JsonProperty("values")
    private String values;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("index")
    public String getIndex() {
        return index;
    }

    @JsonProperty("index")
    public void setIndex(String index) {
        this.index = index;
    }

    @JsonProperty("difficulty")
    public String getDifficulty() {
        return difficulty;
    }

    @JsonProperty("difficulty")
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @JsonProperty("values")
    public String getValues() {
        return values;
    }

    @JsonProperty("values")
    public void setValues(String values) {
        this.values = values;
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
