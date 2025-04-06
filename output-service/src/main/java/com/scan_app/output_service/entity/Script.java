
package com.scan_app.output_service.entity;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "output",
    "table"
})
@Generated("jsonschema2pojo")
public class Script {

    @JsonProperty("id")
    private String id;
    @JsonProperty("output")
    private String output;
    @JsonProperty("table")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Table> table = new ArrayList<>();
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

    @JsonProperty("table")
    public List<Table> getTable() {
        return table;
    }

    @JsonProperty("table")
    public void setTable(List<Table> table) {
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
