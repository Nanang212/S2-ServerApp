package id.co.mii.serverapp.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.repositories.UserRepository;
import id.co.mii.serverapp.utils.Dummy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserRepository userRepository;

    private List<User> users;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        users = userRepository.saveAll(Dummy.createUser());
    }

    @Test
    void testGetAllShouldReturnListOfUsers() throws Exception {
        mockMvc.perform(
                get("/user")                     
        )
                .andExpect(status().isOk())
                .andDo(result -> {
                    List<User> actualUsers = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<User>>() {});
                    assertNotNull(actualUsers);
                   assertFalse(actualUsers.isEmpty());
                    assertEquals(users.size(), actualUsers.size());
                });
    }

    @Test
    void testGetBbyIdShouldReturnUser() throws Exception{
        User expectedUser = users.get(0);

        mockMvc
            .perform(get("/user/" + expectedUser.getId()))
            .andExpectAll(status().isOk())
            .andDo(result -> {
                User actualUsers = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<User>() {
                    
                });

                 assertNotNull(actualUsers);
                 assertEquals(expectedUser.getId(),  actualUsers.getId());
                 assertEquals(expectedUser.getUsername(), actualUsers.getUsername());

            });
    }

    @Test
    void testGetByIdShouldReturErrorIfDataNotFound () throws Exception{
        mockMvc
            .perform(get("/user/" + 999999))
            .andExpectAll(status().isNotFound());
    }

    @Test 
    void testCreateShouldReturnUser() throws Exception {
         User excpectedUser = new User();
         excpectedUser.setUsername("test");
         excpectedUser.setPassword("rahasia");


        // User excpectedUser = users.get(0);


        mockMvc
            .perform(
                post("/user")
                .accept(MediaType.APPLICATION_JSON)
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(excpectedUser))
            )
            .andExpectAll(
                       status().isOk()
                      
                   )
                   .andDo(result -> {
                    User actualUser = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<User>() {
                        
                    } );

                    assertNotNull(actualUser);
                    
                    assertEquals(excpectedUser.getUsername(), actualUser.getUsername());
                    assertEquals(excpectedUser.getPassword(), actualUser.getPassword());
                   });
              }


    // @Test
    // void testgetByIdShouldReturnUserVersi2() throws Exception {
    //      User excpectedUser = users.get(0);
         


    //      when(userRepository.findById(excpectedUser.getId())).thenReturn(Optional.of(excpectedUser));         mockMvc
    //         .perform(get("/user/{id}", excpectedUser.getId()))
    //         .andExpectAll(
    //             status().isOk(),
    //             jsonPath("$.id").exists(),
    //             jsonPath("$.username").value(excpectedUser.getUsername()),
    //             jsonPath("$.password").value(excpectedUser.getPassword())
    //         ). andDo(print());
    // }
}