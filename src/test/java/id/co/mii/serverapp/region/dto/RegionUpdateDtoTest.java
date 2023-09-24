package id.co.mii.serverapp.region.dto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RegionUpdateDtoTest {
    @Autowired
    private Validator validator;

    @Test
    void testValidate() {
        RegionUpdateDto regionUpdateDto = new RegionUpdateDto("");
        Set<ConstraintViolation<RegionUpdateDto>> constraintViolations = validator.validate(regionUpdateDto);
        assertFalse(constraintViolations.isEmpty());

        regionUpdateDto = new RegionUpdateDto("Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia");
        constraintViolations = validator.validate(regionUpdateDto);
        assertFalse(constraintViolations.isEmpty());

        regionUpdateDto = new RegionUpdateDto("Asia");
        constraintViolations = validator.validate(regionUpdateDto);
        assertTrue(constraintViolations.isEmpty());
    }
}