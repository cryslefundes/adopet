package br.com.cryslefundes.adopet.api.controller;

import br.com.cryslefundes.adopet.api.core.dto.adoption.AdoptionDTO;
import br.com.cryslefundes.adopet.api.core.dto.adoption.ApprovedAdoptionDTO;
import br.com.cryslefundes.adopet.api.core.dto.adoption.DeniedAdoptionDTO;
import br.com.cryslefundes.adopet.api.core.dto.adoption.RequestedAdoptionDTO;
import br.com.cryslefundes.adopet.api.core.entity.Adoption;
import br.com.cryslefundes.adopet.api.core.entity.Pet;
import br.com.cryslefundes.adopet.api.core.entity.Tutor;
import br.com.cryslefundes.adopet.api.service.AdoptionService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AdoptionControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private AdoptionService service;
    @Autowired
    private JacksonTester<ApprovedAdoptionDTO> approvedAdoptionDTOJSON;
    @Autowired
    private JacksonTester<DeniedAdoptionDTO> deniedAdoptionDTOJSON;
    @Autowired
    private JacksonTester<RequestedAdoptionDTO> requestedAdoptionDTOJSON;

    @Test
    @DisplayName("Should return status code 201 (created) when someone request an adoption")
    void shouldReturnStatusCode201() throws Exception {
        var requestAdoptionDTO = new RequestedAdoptionDTO(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "I want a dog to protect my house."
        );
        Adoption adoption = new Adoption(new Pet(), new Tutor(), requestAdoptionDTO.purpose());

        given(service.requestAdoption(requestAdoptionDTO)).willReturn(new AdoptionDTO(adoption));

        var response = mvc.perform(
                post("/adoptions")
                        .content(requestedAdoptionDTOJSON.write(requestAdoptionDTO).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(201);
    }

    @Test
    @DisplayName("Should return status code 200 (ok) when some admin approve the requested adoption")
    void shouldReturnStatusCode200CaseOne() throws Exception {
        var approvedAdoptioDTO = new ApprovedAdoptionDTO(UUID.randomUUID());
        var response = mvc.perform(
                put("/adoptions/approve")
                        .content(approvedAdoptionDTOJSON.write(approvedAdoptioDTO).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    @DisplayName("Should return status code 200 (ok) when some admin denied the requested adoption")
    void shouldReturnStatusCode200CaseTwo() throws Exception {
        var deniedAdoptioDTO = new DeniedAdoptionDTO(UUID.randomUUID(), "Don't use a dog for this purpose.");
        var response = mvc.perform(
                put("/adoptions/disapprove")
                        .content(deniedAdoptionDTOJSON.write(deniedAdoptioDTO).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
    }

}