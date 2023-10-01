package id.co.mii.serverapp.models;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    private String to;
    private String from;
    private String subject;
    private String template;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Map<String, Object> properties;
}
