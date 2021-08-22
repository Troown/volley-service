package com.volleyservice.SystemDataProvider;


import com.volleyservice.enums.Phase;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Data
public class DoubleElimination {

    private final List<MatchesToRoundsRelations> matchesToRoundsRelations = matchesToRoundsRelationsDefiner();
    private List<MatchesRelations> matchesRelations = matchesRelationsGraphDefiner();

    public List<MatchesToRoundsRelations> matchesToRoundsRelationsDefiner() {
        return List.of(
                new MatchesToRoundsRelations(1, Phase.FIRST_ROUND, IntStream.rangeClosed(1, 8).boxed().collect(Collectors.toList())),
                new MatchesToRoundsRelations(2, Phase.SECOND_ROUND, IntStream.rangeClosed(9, 12).boxed().collect(Collectors.toList())),
                new MatchesToRoundsRelations(3, Phase.LOSERS_13TH, IntStream.rangeClosed(13, 16).boxed().collect(Collectors.toList())),
                new MatchesToRoundsRelations(4, Phase.LOSERS_9TH, IntStream.rangeClosed(17, 20).boxed().collect(Collectors.toList())),
                new MatchesToRoundsRelations(5, Phase.QUOTER_FINAL, List.of(21, 22)),
                new MatchesToRoundsRelations(6, Phase.LOSERS_7TH, List.of(23, 24)),
                new MatchesToRoundsRelations(7, Phase.LOSERS_5TH, List.of(25, 26)),
                new MatchesToRoundsRelations(8, Phase.SEMI_FINALS, List.of(27, 28)),
                new MatchesToRoundsRelations(9, Phase.BRONZE_MEDAL, List.of(29)),
                new MatchesToRoundsRelations(10, Phase.GOLD_MEDAL, List.of(30))
        );
    }

    public List<MatchesRelations> matchesRelationsGraphDefiner() {
        return List.of(
                new MatchesRelations(1, 9, 1),
                new MatchesRelations(2, 9, 1),
                new MatchesRelations(3, 10, 1),
                new MatchesRelations(4, 10, 1),
                new MatchesRelations(5, 11, 1),
                new MatchesRelations(6, 11, 1),
                new MatchesRelations(7, 12, 1),
                new MatchesRelations(8, 12, 1),

                new MatchesRelations(1, 16, -1),
                new MatchesRelations(2, 16, -1),
                new MatchesRelations(3, 15, -1),
                new MatchesRelations(4, 15, -1),
                new MatchesRelations(5, 14, -1),
                new MatchesRelations(6, 14, -1),
                new MatchesRelations(7, 13, -1),
                new MatchesRelations(8, 13, -1),

                new MatchesRelations(9, 21, 1),
                new MatchesRelations(10, 21, 1),
                new MatchesRelations(11, 22, 1),
                new MatchesRelations(12, 22, 1),

                new MatchesRelations(9, 18, -1),
                new MatchesRelations(10, 17, -1),
                new MatchesRelations(11, 20, -1),
                new MatchesRelations(12, 19, -1),

                new MatchesRelations(13, 17, 1),
                new MatchesRelations(14, 18, 1),
                new MatchesRelations(15, 19, 1),
                new MatchesRelations(16, 20, 1),

                new MatchesRelations(17, 23, 1),
                new MatchesRelations(18, 23, 1),
                new MatchesRelations(19, 24, 1),
                new MatchesRelations(20, 24, 1),

                new MatchesRelations(21, 27, 1),
                new MatchesRelations(22, 28, 1),

                new MatchesRelations(21, 26, -1),
                new MatchesRelations(22, 25, -1),

                new MatchesRelations(23, 25, 1),
                new MatchesRelations(24, 26, 1),

                new MatchesRelations(25, 27, 1),
                new MatchesRelations(26, 28, 1),

                new MatchesRelations(27, 30, 1),
                new MatchesRelations(28, 30, 1),

                new MatchesRelations(27, 29, -1),
                new MatchesRelations(28, 29, -1)
        );

    }
}
