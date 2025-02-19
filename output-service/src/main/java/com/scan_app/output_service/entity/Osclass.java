
package com.scan_app.output_service.entity;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "vendor",
    "osfamily",
    "accuracy",
    "cpe"
})
@Generated("jsonschema2pojo")
public class Osclass {

    @JsonProperty("type")
    private String type;
    @JsonProperty("vendor")
    private String vendor;
    @JsonProperty("osfamily")
    private String osfamily;
    @JsonProperty("accuracy")
    private String accuracy;
    @JsonProperty("cpe")
    private String cpe;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("vendor")
    public String getVendor() {
        return vendor;
    }

    @JsonProperty("vendor")
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @JsonProperty("osfamily")
    public String getOsfamily() {
        return osfamily;
    }

    @JsonProperty("osfamily")
    public void setOsfamily(String osfamily) {
        this.osfamily = osfamily;
    }

    @JsonProperty("accuracy")
    public String getAccuracy() {
        return accuracy;
    }

    @JsonProperty("accuracy")
    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    @JsonProperty("cpe")
    public String getCpe() {
        return cpe;
    }

    @JsonProperty("cpe")
    public void setCpe(String cpe) {
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
