
package com.scan_app.output_service.entity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "args",
    "scanner",
    "start",
    "startstr",
    "version",
    "xmloutputversion",
    "debugging",
    "host",
    "hosthint",
    "runstats",
    "scaninfo",
    "taskbegin",
    "taskend",
    "verbose"
})
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("jsonschema2pojo")
@Document(collection = "nmaprun")
public class Nmaprun {

    @Id
    private String _id;
    @JsonProperty("args")
    private String args;
    @JsonProperty("scanner")
    private String scanner;
    @JsonProperty("start")
    private String start;
    @JsonProperty("startstr")
    private String startstr;
    @JsonProperty("version")
    private String version;
    @JsonProperty("xmloutputversion")
    private String xmloutputversion;
    @JsonProperty("debugging")
    private Debugging debugging;
    @JsonProperty("host")
    @Transient
    private Host host;
    @JsonProperty("hosthint")
    private Hosthint hosthint;
    @JsonProperty("runstats")
    private Runstats runstats;
    @JsonProperty("scaninfo")
    private Scaninfo scaninfo;
    @JsonProperty("taskbegin")
    private List<Taskbegin> taskbegin;
    @JsonProperty("taskend")
    private List<Taskend> taskend;
    @JsonProperty("verbose")
    private Verbose verbose;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public String get_id() {
        return _id;
    }

    public void set_id(String id) {
        this._id = id;
    }

    @JsonProperty("args")
    public String getArgs() {
        return args;
    }

    @JsonProperty("args")
    public void setArgs(String args) {
        this.args = args;
    }

    @JsonProperty("scanner")
    public String getScanner() {
        return scanner;
    }

    @JsonProperty("scanner")
    public void setScanner(String scanner) {
        this.scanner = scanner;
    }

    @JsonProperty("start")
    public String getStart() {
        return start;
    }

    @JsonProperty("start")
    public void setStart(String start) {
        this.start = start;
    }

    @JsonProperty("startstr")
    public String getStartstr() {
        return startstr;
    }

    @JsonProperty("startstr")
    public void setStartstr(String startstr) {
        this.startstr = startstr;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("xmloutputversion")
    public String getXmloutputversion() {
        return xmloutputversion;
    }

    @JsonProperty("xmloutputversion")
    public void setXmloutputversion(String xmloutputversion) {
        this.xmloutputversion = xmloutputversion;
    }

    @JsonProperty("debugging")
    public Debugging getDebugging() {
        return debugging;
    }

    @JsonProperty("debugging")
    public void setDebugging(Debugging debugging) {
        this.debugging = debugging;
    }

    @JsonProperty("host")
    public Host getHost() {
        return host;
    }

    @JsonProperty("host")
    public void setHost(Host host) {
        this.host = host;
    }

    @JsonProperty("hosthint")
    public Hosthint getHosthint() {
        return hosthint;
    }

    @JsonProperty("hosthint")
    public void setHosthint(Hosthint hosthint) {
        this.hosthint = hosthint;
    }

    @JsonProperty("runstats")
    public Runstats getRunstats() {
        return runstats;
    }

    @JsonProperty("runstats")
    public void setRunstats(Runstats runstats) {
        this.runstats = runstats;
    }

    @JsonProperty("scaninfo")
    public Scaninfo getScaninfo() {
        return scaninfo;
    }

    @JsonProperty("scaninfo")
    public void setScaninfo(Scaninfo scaninfo) {
        this.scaninfo = scaninfo;
    }

    @JsonProperty("taskbegin")
    public List<Taskbegin> getTaskbegin() {
        return taskbegin;
    }

    @JsonProperty("taskbegin")
    public void setTaskbegin(List<Taskbegin> taskbegin) {
        this.taskbegin = taskbegin;
    }

    @JsonProperty("taskend")
    public List<Taskend> getTaskend() {
        return taskend;
    }

    @JsonProperty("taskend")
    public void setTaskend(List<Taskend> taskend) {
        this.taskend = taskend;
    }

    @JsonProperty("verbose")
    public Verbose getVerbose() {
        return verbose;
    }

    @JsonProperty("verbose")
    public void setVerbose(Verbose verbose) {
        this.verbose = verbose;
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
