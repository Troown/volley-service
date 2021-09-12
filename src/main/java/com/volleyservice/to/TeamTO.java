package com.volleyservice.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
@AllArgsConstructor
@Value
public class TeamTO {
    Long id;
    List<PlayerTO> players;
    Integer points;
}
