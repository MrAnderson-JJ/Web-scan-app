
package com.scan_app.output_service.entity;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "output",
    "elem",
    "table"
})
@Generated("jsonschema2pojo")
public class Script {

    @JsonProperty("id")
    private String id;
    @JsonProperty("output")
    private String output;
    @JsonProperty("elem")
    private String elem;
    @JsonProperty("table")
    private Table table;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("output")
    public String getOutput() {
        return output;
    }

    @JsonProperty("output")
    public void setOutput(String output) {
        this.output = output;
    }

    @JsonProperty("elem")
    public String getElem() {
        return elem;
    }

    @JsonProperty("elem")
    public void setElem(String elem) {
        this.elem = elem;
    }

    @JsonProperty("table")
    public Table getTable() {
        return table;
    }

    @JsonProperty("table")
    public void setTable(Table table) {
        this.table = table;
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
