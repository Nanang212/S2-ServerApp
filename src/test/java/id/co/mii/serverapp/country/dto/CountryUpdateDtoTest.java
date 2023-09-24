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
class CountryUpdateDtoTest {
    @Autowired
    private Validator validator;

    @Test
    void testValidate() {
        CountryUpdateDto countryUpdateDto = new CountryUpdateDto(null, null, null);
        Set<ConstraintViolation<CountryUpdateDto>> constraintViolations = validator.validate(countryUpdateDto);
        assertEquals(3, constraintViolations.size());

        countryUpdateDto = new CountryUpdateDto(-1, null, null);
        constraintViolations = validator.validate(countryUpdateDto);
        assertEquals(3, constraintViolations.size());

        countryUpdateDto = new CountryUpdateDto(1, "", null);
        constraintViolations = validator.validate(countryUpdateDto);
        assertEquals(2, constraintViolations.size());

        countryUpdateDto = new CountryUpdateDto(1, "I", null);
        constraintViolations = validator.validate(countryUpdateDto);
        assertEquals(2, constraintViolations.size());

        countryUpdateDto = new CountryUpdateDto(1, "ID", null);
        constraintViolations = validator.validate(countryUpdateDto);
        assertEquals(1, constraintViolations.size());

        countryUpdateDto = new CountryUpdateDto(1, "ID", "");
        constraintViolations = validator.validate(countryUpdateDto);
        assertEquals(1, constraintViolations.size());

        countryUpdateDto = new CountryUpdateDto(1, "ID", "Indonesia Indonesia Indonesia Indonesia Indonesia Indonesia Indonesia Indonesia");
        constraintViolations = validator.validate(countryUpdateDto);
        assertEquals(1, constraintViolations.size());

        countryUpdateDto = new CountryUpdateDto(1, "ID", "Indonesia");
        constraintViolations = validator.validate(countryUpdateDto);
        assertTrue(constraintViolations.isEmpty());
    }
}