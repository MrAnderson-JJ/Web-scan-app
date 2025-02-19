
package com.scan_app.output_service.entity;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "class",
    "values"
})
@Generated("jsonschema2pojo")
public class Tcptssequence {

    @JsonProperty("class")
    private String _class;
    @JsonProperty("values")
    private String values;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("class")
    public String getClass_() {
        return _class;
    }

    @JsonProperty("class")
    public void setClass_(String _class) {
        this._class = _class;
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
