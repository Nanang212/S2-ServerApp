package id.co.mii.serverapp.config;

import java.nio.charset.StandardCharsets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class ThymeleafTemplateConfig {

    //Inisiasi object
    @Bean //(sifatnya singleton)
    public SpringTemplateEngine springTemplateEngine() {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.addTemplateResolver(emailTemplateResolver());
        return springTemplateEngine;
    }

    // konfigurasi template engine 
    public ClassLoaderTemplateResolver emailTemplateResolver() {
        ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
        emailTemplateResolver.setPrefix("/templates/"); // Prefix --> tempat direktori template email (welcome-email.html)
        emailTemplateResolver.setSuffix(".html"); // Suffix --> ekstensi ke HTML 
        emailTemplateResolver.setTemplateMode(TemplateMode.HTML); // ngasi tau kalo file mode dokumen html
        emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name()); // konversi html ke UTF-8 (encoding character)
        emailTemplateResolver.setCacheable(false); // menyimpan cache template html
        return emailTemplateResolver;
    }
}
