package co.com.mercadolibre.xmen.controller;

import co.com.mercadolibre.xmen.TestUtil;
import co.com.mercadolibre.xmen.model.Dna;
import co.com.mercadolibre.xmen.service.implementation.MutantService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MutantApiRest.class)
@AutoConfigureMockMvc
@EnableWebMvc
public class MutantApiRestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MutantService mutantService;

    @Test
    public void invalidDnaTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/public/mutant")
                .accept(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(null))
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void isMutantTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/public/mutant")
                .accept(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(Dna.builder().dna(new String[]{"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"}).build()))
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatus());
    }

    @Test
    public void isNotMutantTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/public/mutant")
                .accept(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(Dna.builder().dna(new String[]{"ATGCGA","CAGTGC","TTATGC","AGAAGG","CCCCTA","TCACTG"}).build()))
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
