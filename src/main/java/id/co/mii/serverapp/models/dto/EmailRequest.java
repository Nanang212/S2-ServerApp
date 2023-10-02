package id.co.mii.serverapp.models.dto;

public class EmailRequest {
    private String to;
    private String subject;
    private String text;
    private String attachment;
    public EmailRequest() {
    }
    public EmailRequest(String to, String subject, String text, String attachment) {
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.attachment = attachment;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getAttachment() {
        return attachment;
    }
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
    @Override
    public String toString() {
        return "EmailRequest [to=" + to + ", subject=" + subject + ", text=" + text + ", attachment=" + attachment
                + "]";
    }

    
}
