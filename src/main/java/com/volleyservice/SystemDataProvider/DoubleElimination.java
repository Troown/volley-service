package com.volleyservice.SystemDataProvider;


import com.volleyservice.enums.Phase;
import com.volleyservice.enums.Seed;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Data
public class DoubleElimination {

//    private final List<MatchesToRoundsRelations> matchesToRoundsRelations = matchesToRoundsRelationsDefiner();
//    private List<MatchesRelations> matchesRelations = matchesRelationsGraphDefiner();

    public List<MatchesToRoundRelation> getMatchesToRoundRelations() {
        return List.of(
                new MatchesToRoundRelation(1, Phase.FIRST_ROUND, IntStream.rangeClosed(1, 8).boxed().collect(Collectors.toList())),
                new MatchesToRoundRelation(2, Phase.SECOND_ROUND, IntStream.rangeClosed(9, 12).boxed().collect(Collectors.toList())),
                new MatchesToRoundRelation(3, Phase.LOSERS_13TH, IntStream.rangeClosed(13, 16).boxed().collect(Collectors.toList())),
                new MatchesToRoundRelation(4, Phase.LOSERS_9TH, IntStream.rangeClosed(17, 20).boxed().collect(Collectors.toList())),
                new MatchesToRoundRelation(5, Phase.QUOTER_FINAL, List.of(21, 22)),
                new MatchesToRoundRelation(6, Phase.LOSERS_7TH, List.of(23, 24)),
                new MatchesToRoundRelation(7, Phase.LOSERS_5TH, List.of(25, 26)),
                new MatchesToRoundRelation(8, Phase.SEMI_FINALS, List.of(27, 28)),
                new MatchesToRoundRelation(9, Phase.BRONZE_MEDAL, List.of(29)),
                new MatchesToRoundRelation(10, Phase.GOLD_MEDAL, List.of(30))
        );
    }

    public List<MatchesRelation> getMatchesRelations() {
        return List.of(
                new MatchesRelation(1, 9, 1),
                new MatchesRelation(2, 9, 1),
                new MatchesRelation(3, 10, 1),
                new MatchesRelation(4, 10, 1),
                new MatchesRelation(5, 11, 1),
                new MatchesRelation(6, 11, 1),
                new MatchesRelation(7, 12, 1),
                new MatchesRelation(8, 12, 1),

                new MatchesRelation(1, 16, -1),
                new MatchesRelation(2, 16, -1),
                new MatchesRelation(3, 15, -1),
                new MatchesRelation(4, 15, -1),
                new MatchesRelation(5, 14, -1),
                new MatchesRelation(6, 14, -1),
                new MatchesRelation(7, 13, -1),
                new MatchesRelation(8, 13, -1),

                new MatchesRelation(9, 21, 1),
                new MatchesRelation(10, 21, 1),
                new MatchesRelation(11, 22, 1),
                new MatchesRelation(12, 22, 1),

                new MatchesRelation(9, 18, -1),
                new MatchesRelation(10, 17, -1),
                new MatchesRelation(11, 20, -1),
                new MatchesRelation(12, 19, -1),

                new MatchesRelation(13, 17, 1),
                new MatchesRelation(14, 18, 1),
                new MatchesRelation(15, 19, 1),
                new MatchesRelation(16, 20, 1),

                new MatchesRelation(17, 23, 1),
                new MatchesRelation(18, 23, 1),
                new MatchesRelation(19, 24, 1),
                new MatchesRelation(20, 24, 1),

                new MatchesRelation(21, 27, 1),
                new MatchesRelation(22, 28, 1),

                new MatchesRelation(21, 26, -1),
                new MatchesRelation(22, 25, -1),

                new MatchesRelation(23, 25, 1),
                new MatchesRelation(24, 26, 1),

                new MatchesRelation(25, 27, 1),
                new MatchesRelation(26, 28, 1),

                new MatchesRelation(27, 30, 1),
                new MatchesRelation(28, 30, 1),

                new MatchesRelation(27, 29, -1),
                new MatchesRelation(28, 29, -1)
        );

    }

    public List<SeedToMatchRelation> getSeedToMatchRelations() {
        return List.of(
                new SeedToMatchRelation(1, Seed.R1, Seed.R16),
                new SeedToMatchRelation(2, Seed.R9, Seed.R8),
                new SeedToMatchRelation(3, Seed.R15, Seed.R12),
                new SeedToMatchRelation(4, Seed.R13, Seed.R4),
                new SeedToMatchRelation(5, Seed.R3, Seed.R14),
                new SeedToMatchRelation(6, Seed.R11, Seed.R6),
                new SeedToMatchRelation(7, Seed.R7, Seed.R10),
                new SeedToMatchRelation(8, Seed.R15, Seed.R2)
        );
    }

}
