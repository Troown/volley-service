package com.volleyservice.controller;

import com.volleyservice.entity.Match;
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
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
public class MatchController {

    private final MatchService matchService;
    private final MatchMapper matchMapper;
    private final MatchSetMapper matchSetMapper;

    @GetMapping("/tournaments/{tournamentId}/matches")
    ResponseEntity<CollectionModel<EntityModel<MatchTO>>> findAllInTournament(@PathVariable long tournamentId) {
        List<EntityModel<MatchTO>> matches = matchService.findAlInTournament(tournamentId).stream()
                .map(match -> EntityModel.of(matchMapper.mapsToTO(match),
                        linkTo(methodOn(MatchController.class).findOneInTournament(tournamentId, match.getMatchNumber())).withSelfRel()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(
                matches,
                linkTo(methodOn(MatchController.class).findAllInTournament(tournamentId)).withSelfRel()
        ));
    }

    @GetMapping("/tournaments/{tournamentId}/matches/{matchNumber}")
    ResponseEntity<EntityModel<MatchTO>> findOneInTournament(@PathVariable long tournamentId,
                                                             @PathVariable Integer matchNumber) {

        return matchService.findByMatchNumber(tournamentId, matchNumber).map(match -> EntityModel.of(matchMapper.mapsToTO(match),
                linkTo(methodOn(MatchController.class).findOneInTournament(tournamentId, match.getMatchNumber())).withSelfRel(),
                linkTo(methodOn(MatchController.class).findAllInTournament(tournamentId)).withRel("matches"))).map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/tournaments/{tournamentId}/matches/{matchNumber}")
    public ResponseEntity<?> setMatchResult(@PathVariable long tournamentId,
                                @PathVariable Integer matchNumber,
                                @RequestBody ListOfSetsRequestTO listOfSetsRequestTO) {
        return matchService.setResult(tournamentId,
                matchNumber,
                listOfSetsRequestTO.getSets().stream().map(matchSetMapper::mapsToEntity).collect(Collectors.toList()))
                .map(match -> EntityModel.of(matchMapper.mapsToTO(match),
                        linkTo(methodOn(MatchController.class).findOneInTournament(tournamentId, match.getMatchNumber())).withSelfRel(),
                        linkTo(methodOn(MatchController.class).findAllInTournament(tournamentId)).withRel("matches")))
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
