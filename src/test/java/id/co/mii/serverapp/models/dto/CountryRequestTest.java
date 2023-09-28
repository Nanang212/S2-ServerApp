package id.co.mii.serverapp.models.dto;

import id.co.mii.serverapp.models.dto.request.CountryRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CountryRequestTest {
    @Autowired
    private Validator validator;

    @Test
    void testValidate() {
        CountryRequest countryRequest = new CountryRequest(null, null, null);
        Set<ConstraintViolation<CountryRequest>> constraintViolations = validator.validate(countryRequest);
        assertEquals(3, constraintViolations.size());

        countryRequest = new CountryRequest(-1, null, null);
        constraintViolations = validator.validate(countryRequest);
        assertEquals(3, constraintViolations.size());

        countryRequest = new CountryRequest(1, "", null);
        constraintViolations = validator.validate(countryRequest);
        assertEquals(2, constraintViolations.size());

        countryRequest = new CountryRequest(1, "I", null);
        constraintViolations = validator.validate(countryRequest);
        assertEquals(2, constraintViolations.size());

        countryRequest = new CountryRequest(1, "ID", null);
        constraintViolations = validator.validate(countryRequest);
        assertEquals(1, constraintViolations.size());

        countryRequest = new CountryRequest(1, "ID", "");
        constraintViolations = validator.validate(countryRequest);
        assertEquals(1, constraintViolations.size());

        countryRequest = new CountryRequest(1, "ID", "Indonesia Indonesia Indonesia Indonesia Indonesia Indonesia Indonesia Indonesia");
        constraintViolations = validator.validate(countryRequest);
        assertEquals(1, constraintViolations.size());

        countryRequest = new CountryRequest(1, "ID", "Indonesia");
        constraintViolations = validator.validate(countryRequest);
        assertTrue(constraintViolations.isEmpty());
    }
}