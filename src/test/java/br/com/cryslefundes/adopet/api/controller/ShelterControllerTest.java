package br.com.cryslefundes.adopet.api.controller;

import br.com.cryslefundes.adopet.api.core.dto.AddressDTO;
import br.com.cryslefundes.adopet.api.core.dto.shelter.RegisterShelterDTO;
import br.com.cryslefundes.adopet.api.core.dto.shelter.ShelterDTO;
import br.com.cryslefundes.adopet.api.core.dto.shelter.UpdateShelterDTO;

import br.com.cryslefundes.adopet.api.core.entity.Address;
import br.com.cryslefundes.adopet.api.core.entity.Shelter;
import br.com.cryslefundes.adopet.api.service.ShelterService;
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
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ShelterControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ShelterService service;
    @Autowired
    private JacksonTester<RegisterShelterDTO> registerDTOToJson;
    @Autowired
    private JacksonTester<UpdateShelterDTO> updateDTOToJson;

    @Test
    @DisplayName("Should return status code 201 (created) when someone request register a shelter")
    void shouldReturnStatusCode201() throws Exception {
        RegisterShelterDTO registerDTO = new RegisterShelterDTO(
                "Abrigo Animal Feliz",
                "11986134375",
                "abrigo_feliz@admin.com",
                new AddressDTO(
                        "rua da felicidade",
                        42,
                        null,
                        "São Paulo",
                        "São Paulo",
                        "11311314"
                ));
        Shelter shelter = new Shelter(registerDTO);
        given(service.registerShelter(registerDTO)).willReturn(new ShelterDTO(shelter));

        var response = mvc.perform(
                        post("/shelters")
                                .content(registerDTOToJson.write(registerDTO).getJson())
                                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(201);
    }

    @Test
    @DisplayName("Should return status code 200 (ok) when someone request get a shelter")
    void shouldReturnStatusCode200CaseOne() throws Exception {
        var response = mvc.perform(
                get("/shelters/{id}", UUID.randomUUID())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    @DisplayName("Should return status code 200 (ok) when someone request an update a shelter")
    void shouldReturnStatusCode200CaseTwo() throws Exception {
        UpdateShelterDTO updateDTO = new UpdateShelterDTO(
                UUID.randomUUID(),
                null,
                "11907436258",
                null,
                null
        );

        var response = mvc.perform(
                put("/shelters")
                        .content(updateDTOToJson.write(updateDTO).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    @DisplayName("Should return status code 400 (bad request) when the exception MethodArgumentNotValid it is intercepted")
    void shouldReturnBadRequest() throws Exception {
        RegisterShelterDTO registerDTO = new RegisterShelterDTO(
                "Abrigo Animal Feliz",
                null,
                "abrigo_feliz@admin.com",
                null
              );

        var response = mvc.perform(
                post("/shelters")
                        .content(registerDTOToJson.write(registerDTO).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(400);
    }

}