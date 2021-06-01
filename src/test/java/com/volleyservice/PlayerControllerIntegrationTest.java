package com.volleyservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { VolleyServiceApplication.class })
@WebAppConfiguration(value = "")
@ActiveProfiles("test")

public class PlayerControllerIntegrationTest {


    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
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
        this.mockMvc.perform(MockMvcRequestBuilders.post("/players").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "  \"name\": \"Mat\",\n" +
                "  \"surname\": \"Anderson\",\n" +
                "  \"number\": 1,\n" +
                "  \"teamName\": \"USA\",\n" +
                "  \"dateOfBirth\": \"1991-01-01\"\n" +
                "}")).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/hal+json"))
                .andExpect(MockMvcResultMatchers.content().json("{\n" +
                        "  \"name\": \"Mat\",\n" +
                        "  \"surname\": \"Anderson\",\n" +
                        "  \"number\": 1,\n" +
                        "  \"teamName\": \"USA\",\n" +
                        "  \"adult\": true,\n" +
                        "  \"_links\": {\n" +
                        "    \"self\": {\n" +
                        "      \"href\": \"http://localhost/players/1\"\n" +//need to change in all links, instead of: "href": "http://localhost:8080/players" as it is in swagger
                        "    }\n" +
                        "  }\n" +
                        "}"));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/players"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/hal+json"))
                .andExpect(MockMvcResultMatchers.content().json("{\n" +
                        "  \"_embedded\": {\n" +
                        "    \"playerTOList\": [\n" +
                        "      {\n" +
                        "        \"name\": \"Mat\",\n" +
                        "        \"surname\": \"Anderson\",\n" +
                        "        \"number\": 1,\n" +
                        "        \"teamName\": \"USA\",\n" +
                        "        \"adult\": true,\n" +
                        "        \"_links\": {\n" +
                        "          \"self\": {\n" +
                        "            \"href\": \"http://localhost/players/1\"\n" +
                        "          },\n" +
                        "          \"players\": {\n" +
                        "            \"href\": \"http://localhost/players\"\n" +
                        "          }\n" +
                        "        }\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  \"_links\": {\n" +
                        "    \"self\": {\n" +
                        "      \"href\": \"http://localhost/players\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}"));


    }

    @Test
    public void addPlayerWithNullValueAndCheckResponseIsBadRequest  () throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/players")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "  \"name\": \null,\n" +
                "  \"surname\": \"Anderson\",\n" +
                "  \"number\": 1,\n" +
                "  \"teamName\": \"USA\",\n" +
                "  \"dateOfBirth\": \"1991-01-01\"\n" +
                "}")).andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

}