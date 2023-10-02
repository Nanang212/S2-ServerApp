package id.co.mii.serverapp.config;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class ThymeleafTemplateConfig {
    
    @Bean
    public SpringTemplateEngine springTemplateEngine(){
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.addTemplateResolver(eTemplateResolver());
        return springTemplateEngine;
    }

    public ClassLoaderTemplateResolver eTemplateResolver(){
        ClassLoaderTemplateResolver eTemplateResolver = new ClassLoaderTemplateResolver();
        eTemplateResolver.setPrefix("/templates/");
        eTemplateResolver.setSuffix(".html");
        eTemplateResolver.setTemplateMode(TemplateMode.HTML);
        eTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        eTemplateResolver.setCacheable(false);
        return eTemplateResolver;
    }
}
