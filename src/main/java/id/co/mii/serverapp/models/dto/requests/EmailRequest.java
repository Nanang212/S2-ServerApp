package id.co.mii.serverapp.models.dto.requests;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailRequest {
    
    private String to;
    private String from;
    private String subject;
    private String template;
    private Map<String, Object> properties;
}
