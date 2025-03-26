
package com.scan_app.output_service.entity;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "elapsed",
    "exit",
    "summary",
    "time",
    "timestr"
})
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("jsonschema2pojo")
public class Finished {

    @JsonProperty("elapsed")
    private String elapsed;
    @JsonProperty("exit")
    private String exit;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("time")
    private Long time;
    @JsonProperty("timestr")
    private String timestr;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("elapsed")
    public String getElapsed() {
        return elapsed;
    }

    @JsonProperty("elapsed")
    public void setElapsed(String elapsed) {
        this.elapsed = elapsed;
    }

    @JsonProperty("exit")
    public String getExit() {
        return exit;
    }

    @JsonProperty("exit")
    public void setExit(String exit) {
        this.exit = exit;
    }

    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @JsonProperty("time")
    public Long getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(Long time) {
        this.time = time;
    }

    @JsonProperty("timestr")
    public String getTimestr() {
        return timestr;
    }

    @JsonProperty("timestr")
    public void setTimestr(String timestr) {
        this.timestr = timestr;
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
