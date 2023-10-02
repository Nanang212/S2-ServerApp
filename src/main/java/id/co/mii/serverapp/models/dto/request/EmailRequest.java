package id.co.mii.serverapp.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailRequest {
    private String nameSender;
    private String to;
    private String subject;
    private String text;
    private String attachment;
}
