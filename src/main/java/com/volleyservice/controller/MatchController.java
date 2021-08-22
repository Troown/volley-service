package com.volleyservice.controller;

import com.volleyservice.mapper.MatchMapper;
import com.volleyservice.mapper.MatchSetMapper;
import com.volleyservice.service.MatchService;
import com.volleyservice.to.ListOfSetsRequestTO;
import com.volleyservice.to.MatchTO;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tournaments/{tournamentId}/matches")
public class MatchController {

    private final MatchService matchService;
    private final MatchMapper matchMapper;
    private final MatchSetMapper matchSetMapper;

    @GetMapping("/")
    ResponseEntity<CollectionModel<EntityModel<MatchTO>>> findAllInTournament(@PathVariable long tournamentId) {
        List<EntityModel<MatchTO>> matches = matchService.findAlInTournament(tournamentId).stream()
                .map(match -> EntityModel.of(matchMapper.mapsToTO(match),
                        linkTo(methodOn(MatchController.class).findOneInTournament(tournamentId, match.getMatchNumber())).withSelfRel()))
                .collect(toList());

        return ResponseEntity.ok(CollectionModel.of(
                matches,
                linkTo(methodOn(MatchController.class).findAllInTournament(tournamentId)).withSelfRel()
        ));
    }

    @GetMapping("/{matchNumber}")
    ResponseEntity<MatchTO> findOneInTournament(@PathVariable long tournamentId,
                                                             @PathVariable Integer matchNumber) {

        return ResponseEntity.ok(matchMapper.mapsToTO(matchService.findByMatchNumber(tournamentId, matchNumber)));
    }

    @PutMapping("/{matchNumber}")
    public ResponseEntity<MatchTO> setMatchResult(@PathVariable long tournamentId,
                                @PathVariable Integer matchNumber,
                                @RequestBody ListOfSetsRequestTO listOfSetsRequestTO) {
        return ResponseEntity.ok(
                matchMapper.mapsToTO(
                        matchService.saveResult(tournamentId,
                                matchNumber,
                                listOfSetsRequestTO.getSets().stream().map(matchSetMapper::mapsToEntity).collect(toList())))
        );

    }

}
