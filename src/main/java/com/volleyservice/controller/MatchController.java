package com.volleyservice.controller;

import com.volleyservice.mapper.MatchMapper;
import com.volleyservice.mapper.SetMapper;
import com.volleyservice.service.MatchService;
import com.volleyservice.to.ListOfSetsRequestTO;
import com.volleyservice.to.MatchTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tournaments/{tournamentId}/matches")
public class MatchController {

    private final MatchService matchService;
    private final MatchMapper matchMapper;

    @GetMapping("/")
    ResponseEntity<List<MatchTO>> findAllInTournament(@PathVariable long tournamentId) {
        return ResponseEntity.ok(matchService.findAlInTournament(tournamentId).stream()
                .map(matchMapper::mapsToTO).collect(toList()));
    }

    @GetMapping("/{matchNumber}")
    ResponseEntity<MatchTO> findOneInTournament(
            @PathVariable long tournamentId,
            @PathVariable Integer matchNumber) {
        return ResponseEntity.ok(
                matchMapper.mapsToTO(matchService.findByMatchNumber(tournamentId, matchNumber)));
    }

}
