package com.volleyservice.mapper;

import com.volleyservice.entity.Tournament;
import com.volleyservice.to.TeamTO;
import com.volleyservice.to.TournamentTO;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-16T14:36:59+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Oracle Corporation)"
)
public class TournamentMapperMSImpl implements TournamentMapperMS {

    @Override
    public TournamentTO mapsToDTO(Tournament tournament) {
        if ( tournament == null ) {
            return null;
        }

        Long id = null;
        String tournamentName = null;
        String city = null;

        id = tournament.getId();
        tournamentName = tournament.getTournamentName();
        city = tournament.getCity();

        List<TeamTO> teams = null;

        TournamentTO tournamentTO = new TournamentTO( id, tournamentName, city, teams );

        return tournamentTO;
    }
}
