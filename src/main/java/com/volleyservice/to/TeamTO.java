package com.volleyservice.to;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamTO {
    String player1;
    String player2;
    Integer points;
}
