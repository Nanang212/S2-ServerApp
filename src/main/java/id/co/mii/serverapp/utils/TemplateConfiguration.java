package id.co.mii.serverapp.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.nio.charset.StandardCharsets;

@Configuration
public class TemplateConfiguration {

    @Bean
    public SpringTemplateEngine springTemplateEngine(ClassLoaderTemplateResolver classLoaderTemplateResolver) {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.addTemplateResolver(classLoaderTemplateResolver);

        return springTemplateEngine;
    }

    @Bean
    public ClassLoaderTemplateResolver classLoaderTemplateResolver() {
        ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
        emailTemplateResolver.setPrefix("/templates/");
        emailTemplateResolver.setSuffix(".html");
        emailTemplateResolver.setTemplateMode(TemplateMode.HTML);
        emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        emailTemplateResolver.setCacheable(false);

        return emailTemplateResolver;
    }
}
