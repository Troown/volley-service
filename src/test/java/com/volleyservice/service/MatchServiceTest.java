package com.volleyservice.service;

import com.volleyservice.VolleyServiceApplication;
import com.volleyservice.entity.*;
import com.volleyservice.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {VolleyServiceApplication.class})
//@WebAppConfiguration(value = "")
class MatchServiceTest {
//
//    @Autowired
//    private MatchService matchService;
//
//
//    private final Team r1 = new Team(List.of(new Player("Kuba", "Zdybek"),
//            new Player("Paweł", "Lewandowski")));
//
//    private final Team r2 = new Team(List.of(new Player("Mateusz", "Kańczok"),
//            new Player("Maciej", "Kałuża")));
//
//    private final Team r3 = new Team(List.of(new Player("Kuba", "Zdybek"),
//            new Player("Paweł", "Lewandowski")));
//
//    private final Team r4 = new Team(List.of(new Player("Mateusz", "Kańczok"),
//            new Player("Maciej", "Kałuża")));
//
//
//    @Test
//    public void shouldReturnTrueIfTeamsInSetAreEqualWithTeamsInMatch() {
////        given
//        Match match = new Match(1, List.of(r1, r2));
//
//        MatchSet set1  = new MatchSet(
//                1,
//                new SetResult(
//                        new TeamSetPoint(r1, 21),
//                        new TeamSetPoint(r2, 15)
//                )
//        );
//        MatchSet set2  = new MatchSet(
//                2,
//                new SetResult(
//                        new TeamSetPoint(r1, 21),
//                        new TeamSetPoint(r2, 15)
//                )
//        );
//
//        MatchSet set3  = new MatchSet(
//                2,
//                new SetResult(
//                        new TeamSetPoint(r1, 21),
//                        new TeamSetPoint(r3, 15)
//                )
//        );
//
//        List<MatchSet> sets1 = List.of(set1, set2);
//        List<MatchSet> sets2 = List.of(set1, set3);
////        when
//        boolean result1 = matchService.validateTeamsInSets(match, sets1);
//        boolean result2 = matchService.validateTeamsInSets(match, sets2);
//
////        then
//        assertThat(result1).isEqualTo(true);
//        assertThat(result2).isEqualTo(false);
//
//    }

}