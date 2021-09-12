package com.volleyservice.to;

import com.volleyservice.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
@AllArgsConstructor
@Value
public class TournamentTO {
    Long id;
    String tournamentName;
    String city;
    List<TeamTO> teams;
}
