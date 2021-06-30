package com.volleyservice;

import com.volleyservice.repository.PlayerRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {VolleyServiceApplication.class})
@WebAppConfiguration(value = "")
public class PlayerControllerIntegrationTest {


    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PlayerRepository playerRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @AfterEach
    public void cleanUp() {
        playerRepository.deleteAll();
    }


    @Test
    public void givenWac_whenServletContext_thenItProvidesPlayerController() {

        final ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("playerController"));
        assertNotNull(webApplicationContext.getBean("databaseLoader"));
    }

    @Test
    public void addPlayerAndCompareResponseBodyGetAllPlayersAndCompareResponseBody() throws Exception {
        DatabaseLoader dl = new DatabaseLoader();

        this.mockMvc.perform(MockMvcRequestBuilders.post("/players").contentType(MediaType.APPLICATION_JSON).
                content(dl.postRequestBody())).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/hal+json"))
                .andExpect(MockMvcResultMatchers.content().json(dl.postResponseBody()));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/players"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/hal+json"))
                .andExpect(MockMvcResultMatchers.content().json(dl.getMethodResponse()));
    }

    @Test
    public void addPlayerWithNullValueAndCheckResponseIsBadRequest() throws Exception {
        DatabaseLoader dl = new DatabaseLoader();
        this.mockMvc.perform(MockMvcRequestBuilders.post("/players")
                .contentType(MediaType.APPLICATION_JSON).content(dl.postWithNull())).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Disabled
    @Test
    public void addPlayerAndUpdateHisDateOfBirthAndRankingPointsUsingPatchMethod() throws Exception {
        DatabaseLoader dl = new DatabaseLoader();

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/players").contentType(MediaType.APPLICATION_JSON)
                .content(dl.postRequestBody())).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/hal+json"))
                .andReturn();

        var content = result.getResponse().getContentAsString();
        JSONObject jsonResponse = new JSONObject(content);

        assertThat(jsonResponse.get("name")).isEqualTo("Mateusz");


        this.mockMvc.perform(MockMvcRequestBuilders.patch("/players/1")
                .contentType("application/json-patch+json").content("[\n" +
                        "    { \"op\": \"replace\", \"path\": \"/dateOfBirth\", \"value\": \"1992-06-09\" },\n" +
                        "    { \"op\": \"replace\", \"path\": \"/rankingPoints\", \"value\": \"330\" }\n" +
                        "]")).andExpect(MockMvcResultMatchers.status().isOk());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/players/1").content("{\n" +
                "  \"name\": \"Mateusz\",\n" +
                "  \"surname\": \"Kańczok\",\n" +
                "  \"rankingPoints\": 330,\n" +
                "  \"adult\": true,\n" +
                "  \"_links\": {\n" +
                "    \"self\": {\n" +
                "      \"href\": \"http://localhost:8080/players/1\"\n" +
                "    },\n" +
                "    \"players\": {\n" +
                "      \"href\": \"http://localhost:8080/players\"\n" +
                "    }\n" +
                "  }\n" +
                "}")).andExpect(MockMvcResultMatchers.status().isOk());

    }
}
