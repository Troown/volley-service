package com.volleyservice.controller;

import com.volleyservice.mapper.MatchMapper;
import com.volleyservice.mapper.SetMapper;
import com.volleyservice.service.MatchService;
import com.volleyservice.service.SetService;
import com.volleyservice.to.ListOfSetsRequestTO;
import com.volleyservice.to.MatchTO;
import com.volleyservice.to.SetTO;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.manager.ManagerServlet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tournaments/{tournamentId}/matches")
public class SetController {
    private final SetMapper setMapper;
    private final SetService setService;
    private final MatchService matchService;
    private final MatchMapper matchMapper;


    @PutMapping("/{matchNumber}")
    ResponseEntity<MatchTO> saveFinalResult(
            @PathVariable long tournamentId,
            @PathVariable int matchNumber,
            @RequestBody ListOfSetsRequestTO setsRequestTO) {

        setService.saveFinalResult(
                tournamentId,
                matchNumber,
                setsRequestTO.getSets().stream().map(setMapper::mapsToEntity).collect(Collectors.toUnmodifiableList()));

        return ResponseEntity.ok(matchMapper.mapsToTO(matchService.findByMatchNumber(tournamentId, matchNumber)));
    }

    @GetMapping("/{matchNumber}/sets")
    ResponseEntity<List<SetTO>> findAll(
            @PathVariable long tournamentId,
            @PathVariable int matchNumber) {

        return ResponseEntity.ok(setService.findAll(tournamentId, matchNumber)
                .stream().map(setMapper::mapsToTO).collect(toList()));
    }

}
