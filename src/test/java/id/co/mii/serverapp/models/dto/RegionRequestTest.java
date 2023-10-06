package id.co.mii.serverapp.models.dto;

import id.co.mii.serverapp.models.dto.requests.RegionRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RegionRequestTest {
    @Autowired
    private Validator validator;

    @Test
    void testValidate() {
        RegionRequest regionRequest = new RegionRequest("");
        Set<ConstraintViolation<RegionRequest>> constraintViolations = validator.validate(regionRequest);
        assertFalse(constraintViolations.isEmpty());

        regionRequest = new RegionRequest("Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia");
        constraintViolations = validator.validate(regionRequest);
        assertFalse(constraintViolations.isEmpty());

        regionRequest = new RegionRequest("Asia");
        constraintViolations = validator.validate(regionRequest);
        assertTrue(constraintViolations.isEmpty());
    }
}