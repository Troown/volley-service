package com.volleyservice.mapper;

import com.volleyservice.entity.Tournament;
import com.volleyservice.to.TournamentTO;
import org.mapstruct.Mapper;

@Mapper
public interface TournamentMapperMS {
    TournamentTO mapsToDTO(Tournament tournament);
}
