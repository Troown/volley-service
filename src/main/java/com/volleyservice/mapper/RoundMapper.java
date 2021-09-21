package com.volleyservice.mapper;

import com.volleyservice.entity.Round;
import com.volleyservice.to.RoundTO;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class RoundMapper {
    private final MatchMapper matchMapper;

    public RoundTO mapToTO(@NotNull Round round) {
        return new RoundTO(
                round.getId(),
                round.getRoundNumber(),
                round.getPhase(),
                round.getMatches().stream().map(matchMapper::mapsToTO).collect(Collectors.toList())
        );
    }

}
