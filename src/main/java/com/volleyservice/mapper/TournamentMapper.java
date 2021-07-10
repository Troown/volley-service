package com.volleyservice.mapper;

import com.volleyservice.entity.Tournament;
import com.volleyservice.to.TournamentRequestTO;
import com.volleyservice.to.TournamentTO;
import com.volleyservice.to.TournamentTeamRequestTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Service
public class TournamentMapper {

    public Tournament mapsToEntity(TournamentRequestTO tournamentRequestTO) {
        return new Tournament(tournamentRequestTO.getTournamentName());
    }

    public TournamentTO mapsToTO(Tournament tournament) {
        return new TournamentTO(tournament.getTournamentName(), tournament.getRegisteredTeams());
    }

//    public Player mapsToEntity(PlayerRequestTO playerRequestTO) {
//        return new Player(playerRequestTO.getName(), playerRequestTO.getSurname(), playerRequestTO.getDateOfBirth());
//    }
//
//    public PlayerTO mapsToTO (Player player) {
//        return new PlayerTO(player.getName(), player.getSurname(), player.getRankingPoints(),
//                isAdult(player.getDateOfBirth()));
//    }
//
//    private boolean isAdult(LocalDate dateOfBirth) {
//        return (Period.between(dateOfBirth, LocalDate.now()).getYears()) >= 18;
//    }
}



