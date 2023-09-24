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
class RegionCreationDtoTest {
    @Autowired
    private Validator validator;

    @Test
    void testValidate() {
        RegionCreationDto regionCreationDto = new RegionCreationDto("");
        Set<ConstraintViolation<RegionCreationDto>> constraintViolations = validator.validate(regionCreationDto);
        assertFalse(constraintViolations.isEmpty());

        regionCreationDto = new RegionCreationDto("Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia Asia");
        constraintViolations = validator.validate(regionCreationDto);
        assertFalse(constraintViolations.isEmpty());

        regionCreationDto = new RegionCreationDto("Asia");
        constraintViolations = validator.validate(regionCreationDto);
        assertTrue(constraintViolations.isEmpty());
    }
}