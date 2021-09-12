package com.volleyservice.mapper;

import com.volleyservice.entity.Tournament;
import com.volleyservice.to.TournamentRequestTO;
import com.volleyservice.to.TournamentTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TournamentMapper {

    private final TeamMapper teamMapper;

    public Tournament mapsToEntity(TournamentRequestTO tournamentRequestTO) {
        return new Tournament(
                tournamentRequestTO.getTournamentName(),
                tournamentRequestTO.getCity());
    }

    public TournamentTO mapsToTO(Tournament tournament) {

        return new TournamentTO(
                tournament.getId(),
                tournament.getTournamentName(),
                tournament.getCity(),
                tournament.getRegisteredTeams()
                        .stream().map(teamMapper::mapsToTO).collect(Collectors.toList()));
    }
}



