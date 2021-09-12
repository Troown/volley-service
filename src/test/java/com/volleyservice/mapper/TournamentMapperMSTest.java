package com.volleyservice.mapper;

import com.volleyservice.entity.Tournament;
import com.volleyservice.to.TournamentTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class TournamentMapperMSTest {
    @Disabled
    @Test
    void mapsToDTO() {
        TournamentMapperMS mapper = Mappers.getMapper(TournamentMapperMS.class);
        Tournament tournament = new Tournament("My CUP", "MyCity");
//        when
        TournamentTO tournamentTO = mapper.mapsToDTO(tournament);
//        then
        Assertions.assertThat(tournamentTO.getTournamentName()).isEqualTo("My CUP");
        Assertions.assertThat(tournamentTO.getCity()).isEqualTo("MyCity");
    }
}