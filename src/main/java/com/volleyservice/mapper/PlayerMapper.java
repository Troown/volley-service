package com.volleyservice.mapper;


import com.volleyservice.entity.Player;
import com.volleyservice.to.PlayerRequestTO;
import com.volleyservice.to.PlayerTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class PlayerMapper {

    public Player mapsToEntity(PlayerRequestTO playerRequestTO) {
        return new Player(playerRequestTO.getName(), playerRequestTO.getSurname(), playerRequestTO.getDateOfBirth());
    }

    public PlayerTO mapsToTO (Player player) {
        return new PlayerTO(player.getName(), player.getSurname(), player.getRankingPoints(),
                isAdult(player.getDateOfBirth()));
    }

    private boolean isAdult(LocalDate dateOfBirth) {
        return (Period.between(dateOfBirth, LocalDate.now()).getYears()) >= 18;
    }
}
