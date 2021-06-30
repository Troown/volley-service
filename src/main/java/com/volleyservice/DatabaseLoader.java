package com.volleyservice;


import com.volleyservice.entity.Player;
import com.volleyservice.repository.PlayerRepository;
import com.volleyservice.repository.TeamRepository;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@NoArgsConstructor
@Component
public class DatabaseLoader {
    @Bean
    CommandLineRunner init(PlayerRepository playerRepo, TeamRepository teamRepo) {
        return args -> {
            playerRepo.saveAll(List.of(
                    new Player("Kuba", "Zdybek"),
                    new Player("Daniel", "Pliński"),
                    new Player("Dominik", "Witczak"),
                    new Player("Mateusz", "Kańczok")));
        };
    }

    public String getMethodResponse() {
        return "{\n" +
                "  \"_embedded\": {\n" +
                "    \"playerTOList\": [\n" +
                "      {\n" +
                "        \"name\": \"Mateusz\",\n" +
                "        \"surname\": \"Kańczok\",\n" +
                "        \"rankingPoints\": 0,\n" +
                "        \"adult\": true,\n" +
                "        \"_links\": {\n" +
                "          \"self\": {\n" +
                "            \"href\": \"http://localhost/players/1\"\n" +
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
                "}";
    }
    public String postRequestBody() {
        return "{\n" +
                "  \"name\": \"Mateusz\",\n" +
                "  \"surname\": \"Kańczok\",\n" +
                "  \"dateOfBirth\": \"1993-06-03\"\n" +
                "}";
    }
    public String postResponseBody() {
        return "{\n" +
                "  \"name\": \"Mateusz\",\n" +
                "  \"surname\": \"Kańczok\",\n" +
                "  \"rankingPoints\": 0,\n" +
                "  \"adult\": true,\n" +
                "  \"_links\": {\n" +
                "    \"self\": {\n" +
                "      \"href\": \"http://localhost/players/1\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }

    public String postWithNull() {
        return"\"{\\n\" +\n" +
                "                \"  \\\"name\\\": \\null,\\n\" +\n" +
                "                \"  \\\"surname\\\": \\\"Anderson\\\",\\n\" +\n" +
                "                \"  \\\"dateOfBirth\\\": \\\"1991-01-01\\\"\\n\" +\n" +
                "                \"}\"";
    }

}
