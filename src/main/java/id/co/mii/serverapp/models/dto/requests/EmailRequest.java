package id.co.mii.serverapp.models.dto.requests;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {
    
    private String to;
    private String subject;
    private String text;
    private String nameSended;
    private String attachment;
    private Map<String, Object> properties;
}
