package com.volleyservice.mapper;


import com.volleyservice.DataProvider.MyClock;
import com.volleyservice.entity.Player;
import com.volleyservice.to.PlayerRequestTO;
import com.volleyservice.to.PlayerTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;

@RequiredArgsConstructor
@Service
public class PlayerMapper {
    private final MyClock myClock;

    public Player mapsToEntity(PlayerRequestTO playerRequestTO) {
        return new Player(playerRequestTO.getName(), playerRequestTO.getSurname(), playerRequestTO.getDateOfBirth());
    }

    public PlayerTO mapsToTO (Player player) {

        return PlayerTO.builder()
                .id(player.getId())
                .name(player.getName())
                .surname(player.getSurname())
                .rankingPoints(player.getRankingPoints())
                .isAdult(isAdult(player.getDateOfBirth()))
                .build();
    }

    private boolean isAdult(LocalDate dateOfBirth) {
        return (Period.between(dateOfBirth, LocalDate.now(myClock.getClock())).getYears()) >= 18;
    }
}
