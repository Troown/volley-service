package com.volleyservice.to;

import com.volleyservice.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TournamentTO {
    private Long id;
    private String tournamentName;
    private List<TeamTO> teams;
}
