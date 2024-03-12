package br.com.cryslefundes.adopet.api.controller;

import br.com.cryslefundes.adopet.api.core.dto.AddressDTO;
import br.com.cryslefundes.adopet.api.core.dto.tutors.RegisterTutorDTO;
import br.com.cryslefundes.adopet.api.core.dto.tutors.TutorDTO;
import br.com.cryslefundes.adopet.api.core.dto.tutors.UpdateTutorDTO;
import br.com.cryslefundes.adopet.api.core.entity.Tutor;
import br.com.cryslefundes.adopet.api.service.TutorService;
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

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TutorControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private TutorService service;
    @Autowired
    private JacksonTester<RegisterTutorDTO> registerDTOJson;
    @Autowired
    private JacksonTester<UpdateTutorDTO> updateTutorDTOJson;

    @Test
    @DisplayName("Should return status code 201 (created) when someone request register a tutor with method POST")
    void shouldReturnStatusCode201() throws Exception {
        var registerTutorDTO = new RegisterTutorDTO(
                "crystian",
                "21984125307",
                "crystian.tutor@dev.com",
                "143.123.111-99",
                new AddressDTO(
                        "rua random",
                        30,
                        null,
                        "Rio de janeiro",
                        "Rio de janeiro",
                        "21212321"
                )
        );
        Tutor tutor = new Tutor(registerTutorDTO);
        given(service.registerTutor(registerTutorDTO)).willReturn(new TutorDTO(tutor));

        var response = mvc.perform(
                post("/tutors")
                        .content(registerDTOJson.write(registerTutorDTO).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(201);
    }

    @Test
    @DisplayName("Should return status code 200 (ok) when someone request show an specific tutor with method GET")
    void shouldReturnStatusCode200CaseOne() throws Exception {
        var response = mvc.perform(
                get("/tutors/{id}", UUID.randomUUID())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    @DisplayName("Should return status code 200 (ok) when someone request show all tutors with method GET")
    void shouldReturnStatusCode200CaseTwo() throws Exception {
        var response = mvc.perform(
                get("/tutors")
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
    }


    @Test
    @DisplayName("Should return status code 200 (ok) when someone request an update tutor with method PUT")
    void shouldReturnStatusCode200CaseThree() throws Exception {
        var updateTutorDTO = new UpdateTutorDTO(UUID.randomUUID(), "crystian lefundes", null, null, null, null);

        var response = mvc.perform(
                put("/tutors")
                        .content(updateTutorDTOJson.write(updateTutorDTO).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
    }
}

