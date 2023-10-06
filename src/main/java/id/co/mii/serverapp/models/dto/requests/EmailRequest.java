package id.co.mii.serverapp.models.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailRequest {
    @NotNull
    @Email
    private String to;
    @NotBlank
    private String subject;
    @NotBlank
    private String text;
    private String attachment;
}
