package com.volleyservice.to;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TeamTO {
    private Long id;
    private List<PlayerTO> players;
    private Integer points;
}
