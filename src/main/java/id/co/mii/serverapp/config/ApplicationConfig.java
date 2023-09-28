package id.co.mii.serverapp.config;

import id.co.mii.serverapp.models.Country;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    public Class<Country> countryClass() {
        return Country.class;
    }
}
