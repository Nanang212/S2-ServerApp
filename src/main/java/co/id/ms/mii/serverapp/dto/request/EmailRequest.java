package co.id.ms.mii.serverapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailRequest {
    private String to;
    private String subject;
    private String name;
    private String text;
    private String attachment;
}
