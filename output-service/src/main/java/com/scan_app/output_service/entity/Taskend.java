
package com.scan_app.output_service.entity;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "extrainfo",
    "task",
    "time"
})
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("jsonschema2pojo")
public class Taskend {

    @JsonProperty("extrainfo")
    private String extrainfo;
    @JsonProperty("task")
    private String task;
    @JsonProperty("time")
    private String time;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("extrainfo")
    public String getExtrainfo() {
        return extrainfo;
    }

    @JsonProperty("extrainfo")
    public void setExtrainfo(String extrainfo) {
        this.extrainfo = extrainfo;
    }

    @JsonProperty("task")
    public String getTask() {
        return task;
    }

    @JsonProperty("task")
    public void setTask(String task) {
        this.task = task;
    }

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
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
