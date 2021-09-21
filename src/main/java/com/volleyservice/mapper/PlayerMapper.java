package com.volleyservice.mapper;


import com.volleyservice.myUtils.DateUtil;
import com.volleyservice.entity.Player;
import com.volleyservice.to.PlayerRequestTO;
import com.volleyservice.to.PlayerTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;

@RequiredArgsConstructor
@Service
public class PlayerMapper {

    public Player mapsToEntity(PlayerRequestTO playerRequestTO) {
        return new Player(playerRequestTO.getName(), playerRequestTO.getSurname(), playerRequestTO.getDateOfBirth());
    }

    public PlayerTO mapsToTO (Player player) {

        return PlayerTO.builder()
                .id(player.getId())
                .name(player.getName())
                .surname(player.getSurname())
                .rankingPoints(player.getRankingPoints())
                .isAdult(DateUtil.isAdult(player.getDateOfBirth(), LocalDate.now()))
                .build();
    }
}
