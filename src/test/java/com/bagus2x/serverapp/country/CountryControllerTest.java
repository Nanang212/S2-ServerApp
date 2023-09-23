package com.bagus2x.serverapp.country;

import com.bagus2x.serverapp.country.dto.CountryCreationDto;
import com.bagus2x.serverapp.country.dto.CountryDto;
import com.bagus2x.serverapp.country.dto.CountryUpdateDto;
import com.bagus2x.serverapp.region.Region;
import com.bagus2x.serverapp.region.RegionRepository;
import com.bagus2x.serverapp.util.DataDummy;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CountryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private ObjectMapper objectMapper;
    private List<Region> regions;
    private List<Country> countries;

    @BeforeEach
    @Transactional
    void setUp() {
        regionRepository.deleteAll();
        regions = DataDummy.createRegions();
        regions = regionRepository.saveAll(regions);
        countries = countryRepository.saveAll(DataDummy.createCountries(regions));
    }

    @Test
    void testCreateCountryShouldReturnErrorIfDataIsNotValid() throws Exception {
        CountryCreationDto countryCreationDto = new CountryCreationDto(regions.get(0).getId(), "i", "Indonesia");
        mockMvc
            .perform(
                post("/country")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(countryCreationDto))
            )
            .andExpectAll(status().isBadRequest());
    }

    @Test
    void testCreateCountryShouldReturnErrorIfDataIsDuplicate() throws Exception {
        CountryCreationDto countryCreationDto = new CountryCreationDto(regions.get(0).getId(), "ID", "Indonesia");
        mockMvc
            .perform(
                post("/country")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(countryCreationDto))
            )
            .andExpectAll(status().isConflict());
    }

    @Test
    void testCreateCountryShouldReturnCountry() throws Exception {
        Region expectedRegion = regions.get(2);
        CountryCreationDto countryCreationDto = new CountryCreationDto(expectedRegion.getId(), "zb", "Zimbabwe");

        mockMvc
            .perform(
                post("/country")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(countryCreationDto))
            )
            .andExpectAll(status().isCreated())
            .andDo(result -> {
                CountryDto actualCountry = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<CountryDto>() {
                });

                assertNotNull(actualCountry);
                assertEquals(countryCreationDto.getCode().toUpperCase(), actualCountry.getCode());
                assertEquals(countryCreationDto.getName(), actualCountry.getName());
                assertNotNull(actualCountry.getRegion());
                assertEquals(countryCreationDto.getRegionId(), actualCountry.getRegion().getId());
            });
    }

    @Test
    void testGetAllShouldReturnListOfCountries() throws Exception {
        mockMvc
            .perform(get("/countries"))
            .andExpectAll(status().isOk())
            .andDo(result -> {
                List<CountryDto> actualCountries = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<CountryDto>>() {
                });

                assertNotNull(actualCountries);
                assertFalse(actualCountries.isEmpty());
                assertEquals(this.countries.size(), actualCountries.size());
            });
    }

    @Test
    void testGetByIdShouldReturnErrorIfDataNotFound() throws Exception {
        mockMvc
            .perform(get("/country/" + 999999999))
            .andExpectAll(status().isNotFound());
    }

    @Test
    void testGetByIdShouldReturnCountry() throws Exception {
        Country expectedCountry = countries.get(0);

        mockMvc
            .perform(get("/country/" + expectedCountry.getId()))
            .andExpectAll(status().isOk())
            .andDo(result -> {
                CountryDto actualCountry = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<CountryDto>() {
                });

                assertNotNull(actualCountry);
                assertEquals(expectedCountry.getId(), actualCountry.getId());
                assertEquals(expectedCountry.getCode(), actualCountry.getCode());
                assertEquals(expectedCountry.getName(), actualCountry.getName());
                assertEquals(expectedCountry.getRegion().getId(), actualCountry.getRegion().getId());
                assertEquals(expectedCountry.getRegion().getName(), actualCountry.getRegion().getName());
            });
    }

    @Test
    void testUpdateCountryShouldReturnErrorIfDataNotFound() throws Exception {
        CountryUpdateDto countryUpdateDto = new CountryUpdateDto(1, "IN", "India");

        mockMvc
            .perform(
                put("/country/" + 999999)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(countryUpdateDto))
            )
            .andExpectAll(status().isNotFound());
    }

    @Test
    void testUpdateCountryShouldReturnCountry() throws Exception {
        Country expectedCountry = countries.get(0);
        expectedCountry.setName("India");
        CountryUpdateDto countryUpdateDto = new CountryUpdateDto(expectedCountry.getRegion().getId(), expectedCountry.getCode(), expectedCountry.getName());

        mockMvc
            .perform(
                put("/country/" + expectedCountry.getId())
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(countryUpdateDto))
            )
            .andExpectAll(status().isOk())
            .andDo(result -> {
                CountryDto actualCountry = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<CountryDto>() {
                });

                assertNotNull(actualCountry);
                assertEquals(expectedCountry.getId(), actualCountry.getId());
                assertEquals(expectedCountry.getCode(), actualCountry.getCode());
                assertEquals(expectedCountry.getName(), actualCountry.getName());
                assertEquals(expectedCountry.getRegion().getId(), actualCountry.getRegion().getId());
                assertEquals(expectedCountry.getRegion().getName(), actualCountry.getRegion().getName());
            });
    }

    @Test
    void testDeleteCountryShouldReturnErrorIfDataNotFound() throws Exception {
        mockMvc
            .perform(
                delete("/country/" + 999999)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpectAll(status().isNotFound());
    }

    @Test
    void testDeleteCountryShouldReturnCountry() throws Exception {
        Country expectedCountry = countries.get(0);

        mockMvc
            .perform(
                delete("/country/" + expectedCountry.getId())
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpectAll(status().isOk())
            .andDo(result -> {
                CountryDto actualCountry = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<CountryDto>() {
                });

                assertNotNull(actualCountry);
                assertEquals(expectedCountry.getId(), actualCountry.getId());
                assertEquals(expectedCountry.getCode(), actualCountry.getCode());
                assertEquals(expectedCountry.getName(), actualCountry.getName());
                assertEquals(expectedCountry.getRegion().getId(), actualCountry.getRegion().getId());
                assertEquals(expectedCountry.getRegion().getName(), actualCountry.getRegion().getName());
            });
    }
}