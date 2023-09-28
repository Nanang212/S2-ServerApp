package id.co.mii.serverapp.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.models.dto.request.RegionRequest;
import id.co.mii.serverapp.repositories.CountryRepository;
import id.co.mii.serverapp.repositories.RegionRepository;
import id.co.mii.serverapp.utils.DataDummy;
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
        RegionRequest regionRequest = new RegionRequest("");
        mockMvc
            .perform(
                post("/region")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(regionRequest))
            )
            .andExpectAll(status().isBadRequest());
    }

    @Test
    void testCreateRegionShouldReturnErrorIfDataIsDuplicate() throws Exception {
        RegionRequest regionRequest = new RegionRequest("Asia");
        mockMvc
            .perform(
                post("/region")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(regionRequest))
            )
            .andExpectAll(status().isConflict());
    }

    @Test
    void testCreateRegionShouldReturnRegion() throws Exception {
        RegionRequest regionRequest = new RegionRequest("Pangea");

        mockMvc
            .perform(
                post("/region")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(regionRequest))
            )
            .andExpectAll(status().isCreated())
            .andDo(result -> {
                Region actualRegion = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<Region>() {
                });

                assertNotNull(actualRegion);
                assertEquals(regionRequest.getName(), actualRegion.getName());
            });
    }

    @Test
    void testGetAllShouldReturnListOfRegions() throws Exception {
        mockMvc
            .perform(get("/regions"))
            .andExpectAll(status().isOk())
            .andDo(result -> {
                List<Region> actualRegions = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Region>>() {
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
                Region actualRegion = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<Region>() {
                });

                assertNotNull(actualRegion);
                assertEquals(expectedRegion.getId(), actualRegion.getId());
                assertEquals(expectedRegion.getName(), actualRegion.getName());
            });
    }

    @Test
    void testUpdateRegionShouldReturnErrorIfDataNotFound() throws Exception {
        RegionRequest request = new RegionRequest("Pangea");

        mockMvc
            .perform(
                put("/region/" + 999999)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            )
            .andExpectAll(status().isNotFound());
    }

    @Test
    void testUpdateRegionShouldReturnRegion() throws Exception {
        Region expectedRegion = regions.get(0);
        expectedRegion.setName("Pangea");
        RegionRequest countryUpdateDto = new RegionRequest(expectedRegion.getName());

        mockMvc
            .perform(
                put("/region/" + expectedRegion.getId())
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(countryUpdateDto))
            )
            .andExpectAll(status().isOk())
            .andDo(result -> {
                Region actualRegion = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<Region>() {
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
                Region actualRegion = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<Region>() {
                });

                assertNotNull(actualRegion);
                assertEquals(expectedRegion.getId(), actualRegion.getId());
                assertEquals(expectedRegion.getName(), actualRegion.getName());
            });
    }
}