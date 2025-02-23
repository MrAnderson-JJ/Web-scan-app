
package com.scan_app.output_service.entity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "product",
        "version",
        "extrainfo",
        "ostype",
        "method",
        "conf",
        "cpe"
})
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("jsonschema2pojo")
public class Service {

    @JsonProperty("name")
    private String name;
    @JsonProperty("product")
    private String product;
    @JsonProperty("version")
    private String version;
    @JsonProperty("extrainfo")
    private String extrainfo;
    @JsonProperty("ostype")
    private String ostype;
    @JsonProperty("method")
    private String method;
    @JsonProperty("conf")
    private String conf;
    @JsonProperty("cpe")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<String> cpe;
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

    @JsonProperty("product")
    public String getProduct() {
        return product;
    }

    @JsonProperty("product")
    public void setProduct(String product) {
        this.product = product;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("extrainfo")
    public String getExtrainfo() {
        return extrainfo;
    }

    @JsonProperty("extrainfo")
    public void setExtrainfo(String extrainfo) {
        this.extrainfo = extrainfo;
    }

    @JsonProperty("ostype")
    public String getOstype() {
        return ostype;
    }

    @JsonProperty("ostype")
    public void setOstype(String ostype) {
        this.ostype = ostype;
    }

    @JsonProperty("method")
    public String getMethod() {
        return method;
    }

    @JsonProperty("method")
    public void setMethod(String method) {
        this.method = method;
    }

    @JsonProperty("conf")
    public String getConf() {
        return conf;
    }

    @JsonProperty("conf")
    public void setConf(String conf) {
        this.conf = conf;
    }

    public List<String> getCpe() {
        return cpe;
    }

    public void setCpe(List<String> cpe) {
        this.cpe = cpe;
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