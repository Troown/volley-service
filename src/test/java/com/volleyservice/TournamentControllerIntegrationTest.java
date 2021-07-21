package com.volleyservice;

import com.volleyservice.entity.Team;
import com.volleyservice.entity.Tournament;
import com.volleyservice.repository.PlayerRepository;
import com.volleyservice.repository.TournamentRepository;
import jdk.jfr.Enabled;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {VolleyServiceApplication.class})
@WebAppConfiguration(value = "")
public class TournamentControllerIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TournamentRepository tournamentRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @SneakyThrows
    @Test
    public void addTournamentToDB() {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/tournaments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tournamentName\": \"Augustow CUP\"}"))
                .andExpect(MockMvcResultMatchers.content().contentType("application/hal+json"))
                .andReturn();
        var content = result.getResponse().getContentAsString();

        JSONObject jsonResponse = new JSONObject(content);

        assertThat(jsonResponse.get("tournamentName")).isEqualTo("Augustow CUP");

    }


}

