package br.com.cryslefundes.adopet.api.controller;

import br.com.cryslefundes.adopet.api.core.dto.pet.PetDTO;
import br.com.cryslefundes.adopet.api.core.dto.pet.RegisterPetDTO;
import br.com.cryslefundes.adopet.api.core.dto.pet.UpdatePetDTO;
import br.com.cryslefundes.adopet.api.core.entity.Pet;
import br.com.cryslefundes.adopet.api.core.enums.PetType;
import br.com.cryslefundes.adopet.api.service.PetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class PetControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private PetService service;
    @Autowired
    private JacksonTester<RegisterPetDTO> registerDTOJson;
    @Autowired
    private JacksonTester<UpdatePetDTO> updateDTOJson;

    @Test
    @DisplayName("Should return status code 201 (created) when someone request register a pet with method POST")
    void shouldReturnStatusCode201() throws Exception {
        var registerPetDTO = new RegisterPetDTO(
                "thor",
                "chihuahua",
                5,
                5.8f,
                PetType.DOG
        );

        Pet pet = new Pet(registerPetDTO);
        given(service.registerPet(registerPetDTO)).willReturn(new PetDTO(pet));

        var response = mvc.perform(
                post("/pets")
                        .content(registerDTOJson.write(registerPetDTO).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(201);
    }

    @Test
    @DisplayName("Should return status code 200 (ok) when someone request show an specific pet with method GET")
    void shouldReturnStatusCode200CaseOne() throws Exception {
        var response = mvc.perform(
                get("/pets/{id}", UUID.randomUUID())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    @DisplayName("Should return status code 200 (ok) when someone request show all pets with method GET")
    void shouldReturnStatusCode200CaseTwo() throws Exception {
        var response = mvc.perform(
                get("/pets")
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    @DisplayName("Should return status code 200 (ok) when someone request update a pet with method PUT")
    void shouldReturnStatusCode200CaseThree() throws Exception {
        var updatePetDTO = new UpdatePetDTO(UUID.randomUUID(), "Belinha", null, null, null, null);

        var response = mvc.perform(
                put("/pets")
                        .content(updateDTOJson.write(updatePetDTO).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
    }

}