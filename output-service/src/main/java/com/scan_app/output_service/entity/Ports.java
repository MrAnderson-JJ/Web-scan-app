
package com.scan_app.output_service.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "extraports",
    "port"
})
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("jsonschema2pojo")
@Document(collection = "ports")
public class Ports {

    @Id
    private String id;
    private String hostRefId;
    private Host host;
    @JsonProperty("extraports")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Extraports> extraports = new ArrayList<>();
    @JsonProperty("port")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Port> port = new ArrayList<>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHostRefId() {
        return hostRefId;
    }

    public void setHostRefId(String hostRefId) {
        this.hostRefId = hostRefId;
    }

    @JsonProperty("extraports")
    public List<Extraports> getExtraports() {
        return extraports;
    }

    @JsonProperty("extraports")
    public void setExtraports(List<Extraports> extraports) {
        this.extraports = extraports;
    }

    @JsonProperty("port")
    public List<Port> getPort() {
        return port;
    }

    @JsonProperty("port")
    public void setPort(List<Port> port) {
        this.port = port;
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
