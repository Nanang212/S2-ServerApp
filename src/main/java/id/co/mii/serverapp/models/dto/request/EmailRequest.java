package id.co.mii.serverapp.models.dto.request;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {
    // private String nameSender;
    private String to;
    private String subject;
    private String text;
    private String attachment;
    Map<String, Object> properties;
}
