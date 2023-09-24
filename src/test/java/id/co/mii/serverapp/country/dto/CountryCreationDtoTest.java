package id.co.mii.serverapp.country.dto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CountryCreationDtoTest {
    @Autowired
    private Validator validator;

    @Test
    void testValidate() {
        CountryCreationDto countryCreationDto = new CountryCreationDto(null, null, null);
        Set<ConstraintViolation<CountryCreationDto>> constraintViolations = validator.validate(countryCreationDto);
        assertEquals(3, constraintViolations.size());

        countryCreationDto = new CountryCreationDto(-1, null, null);
        constraintViolations = validator.validate(countryCreationDto);
        assertEquals(3, constraintViolations.size());

        countryCreationDto = new CountryCreationDto(1, "", null);
        constraintViolations = validator.validate(countryCreationDto);
        assertEquals(2, constraintViolations.size());

        countryCreationDto = new CountryCreationDto(1, "I", null);
        constraintViolations = validator.validate(countryCreationDto);
        assertEquals(2, constraintViolations.size());

        countryCreationDto = new CountryCreationDto(1, "ID", null);
        constraintViolations = validator.validate(countryCreationDto);
        assertEquals(1, constraintViolations.size());

        countryCreationDto = new CountryCreationDto(1, "ID", "");
        constraintViolations = validator.validate(countryCreationDto);
        assertEquals(1, constraintViolations.size());

        countryCreationDto = new CountryCreationDto(1, "ID", "Indonesia Indonesia Indonesia Indonesia Indonesia Indonesia Indonesia Indonesia");
        constraintViolations = validator.validate(countryCreationDto);
        assertEquals(1, constraintViolations.size());

        countryCreationDto = new CountryCreationDto(1, "ID", "Indonesia");
        constraintViolations = validator.validate(countryCreationDto);
        assertTrue(constraintViolations.isEmpty());
    }
}