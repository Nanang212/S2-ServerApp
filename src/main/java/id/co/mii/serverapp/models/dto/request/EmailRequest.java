package id.co.mii.serverapp.models.dto.request;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class EmailRequest {
    private String to;
    private String subject;
    private String text;
    private String name;
    private String attachment;
    private String uuid;
}
