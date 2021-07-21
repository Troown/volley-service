package com.volleyservice.mapper;

import com.volleyservice.entity.Tournament;
import com.volleyservice.service.TeamService;
import com.volleyservice.to.TeamRequestTO;
import com.volleyservice.to.TournamentRequestTO;
import com.volleyservice.to.TournamentTO;
import com.volleyservice.to.TournamentTeamRequestTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class TournamentMapper {

    private final TeamMapper teamMapper;

    public Tournament mapsTournamentRequestTOToEntity(TournamentRequestTO tournamentRequestTO) {
        return new Tournament(tournamentRequestTO.getTournamentName());
    }

    public TournamentTO mapsToTO(Tournament tournament) {

        return new TournamentTO(tournament.getId(), tournament.getTournamentName(),
                tournament.getRegisteredTeams()
                        .stream().map(teamMapper::mapsToTO).collect(Collectors.toList()));
    }
}



