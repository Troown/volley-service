package com.volleyservice.streamTests;

import com.volleyservice.entity.Set;
import com.volleyservice.entity.SetResult;
import com.volleyservice.entity.Team;
import com.volleyservice.entity.TeamSetPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IfMatchWithGivenSetsIsFinished {
    @Mock
    private Team r1;
    @Mock
    private Team r2;

    @Test
    public void shouldReturnTrueIfPointsOfOneOfTheTeamIsMoreOrEqualToDefineLastPoint() {
        Set set =
                new Set(1, 21,
                        new SetResult(
                        new TeamSetPoint(r1, 21),
                        new TeamSetPoint(r2, 19)));

//        when
        boolean result = set.getSetResult().getSetWinner().isPresent();
    }
}
