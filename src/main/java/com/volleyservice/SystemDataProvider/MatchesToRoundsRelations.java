package com.volleyservice.SystemDataProvider;

import com.volleyservice.enums.Phase;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@AllArgsConstructor
@Data
public class MatchesToRoundsRelations {
    private Integer roundNumber;
    private Phase phase;
    private List<Integer> matchNumbers;
}
