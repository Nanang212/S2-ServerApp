package id.co.mii.serverapp.region;

import id.co.mii.serverapp.country.CountryRepository;
import id.co.mii.serverapp.region.dto.RegionCreationDto;
import id.co.mii.serverapp.region.dto.RegionDto;
import id.co.mii.serverapp.region.dto.RegionUpdateDto;
import id.co.mii.serverapp.util.DataDummy;
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
class RegionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private ObjectMapper objectMapper;
    private List<Region> regions;

    @BeforeEach
    @Transactional
    void setUp() {
        regionRepository.deleteAll();
        regions = DataDummy.createRegions();
        regionRepository.saveAll(regions);
        regions = regionRepository.findAll();
        countryRepository.saveAll(DataDummy.createCountries(regions));
    }

    @Test
    void testCreateRegionShouldReturnErrorIfDataIsNotValid() throws Exception {
        RegionCreationDto regionCreationDto = new RegionCreationDto("");
        mockMvc
            .perform(
                post("/region")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(regionCreationDto))
            )
            .andExpectAll(status().isBadRequest());
    }

    @Test
    void testCreateRegionShouldReturnErrorIfDataIsDuplicate() throws Exception {
        RegionCreationDto regionCreationDto = new RegionCreationDto("Asia");
        mockMvc
            .perform(
                post("/region")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(regionCreationDto))
            )
            .andExpectAll(status().isConflict());
    }

    @Test
    void testCreateRegionShouldReturnRegion() throws Exception {
        RegionCreationDto regionCreationDto = new RegionCreationDto("Pangea");

        mockMvc
            .perform(
                post("/region")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(regionCreationDto))
            )
            .andExpectAll(status().isCreated())
            .andDo(result -> {
                RegionDto actualRegion = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<RegionDto>() {
                });

                assertNotNull(actualRegion);
                assertEquals(regionCreationDto.getName(), actualRegion.getName());
                assertNotNull(actualRegion.getCountries());
            });
    }

    @Test
    void testGetAllShouldReturnListOfRegions() throws Exception {
        mockMvc
            .perform(get("/regions"))
            .andExpectAll(status().isOk())
            .andDo(result -> {
                List<RegionDto> actualRegions = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<RegionDto>>() {
                });

                assertNotNull(actualRegions);
                assertFalse(actualRegions.isEmpty());
                assertEquals(this.regions.size(), actualRegions.size());
            });
    }

    @Test
    void testGetByIdShouldReturnErrorIfDataNotFound() throws Exception {
        mockMvc
            .perform(get("/region/" + 999999999))
            .andExpectAll(status().isNotFound());
    }

    @Test
    void testGetByIdShouldReturnRegion() throws Exception {
        Region expectedRegion = regions.get(0);

        mockMvc
            .perform(get("/region/" + expectedRegion.getId()))
            .andExpectAll(status().isOk())
            .andDo(result -> {
                RegionDto actualRegion = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<RegionDto>() {
                });

                assertNotNull(actualRegion);
                assertEquals(expectedRegion.getId(), actualRegion.getId());
                assertEquals(expectedRegion.getName(), actualRegion.getName());
            });
    }

    @Test
    void testUpdateRegionShouldReturnErrorIfDataNotFound() throws Exception {
        RegionUpdateDto countryUpdateDto = new RegionUpdateDto("Pangea");

        mockMvc
            .perform(
                put("/region/" + 999999)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(countryUpdateDto))
            )
            .andExpectAll(status().isNotFound());
    }

    @Test
    void testUpdateRegionShouldReturnRegion() throws Exception {
        Region expectedRegion = regions.get(0);
        expectedRegion.setName("Pangea");
        RegionUpdateDto countryUpdateDto = new RegionUpdateDto(expectedRegion.getName());

        mockMvc
            .perform(
                put("/region/" + expectedRegion.getId())
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(countryUpdateDto))
            )
            .andExpectAll(status().isOk())
            .andDo(result -> {
                RegionDto actualRegion = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<RegionDto>() {
                });

                assertNotNull(actualRegion);
                assertEquals(expectedRegion.getId(), actualRegion.getId());
                assertEquals(expectedRegion.getName(), actualRegion.getName());
            });
    }

    @Test
    void testDeleteRegionShouldReturnErrorIfDataNotFound() throws Exception {
        mockMvc
            .perform(
                delete("/region/" + 999999)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpectAll(status().isNotFound());
    }

    @Test
    void testDeleteRegionShouldReturnRegion() throws Exception {
        Region expectedRegion = regions.get(0);

        mockMvc
            .perform(
                delete("/region/" + expectedRegion.getId())
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpectAll(status().isOk())
            .andDo(result -> {
                RegionDto actualRegion = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<RegionDto>() {
                });

                assertNotNull(actualRegion);
                assertEquals(expectedRegion.getId(), actualRegion.getId());
                assertEquals(expectedRegion.getName(), actualRegion.getName());
            });
    }
}