package com.volleyservice.service;

import com.volleyservice.entity.Set;
import com.volleyservice.entity.SetResult;
import com.volleyservice.entity.Team;
import com.volleyservice.entity.TeamSetPoint;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class ValidateScoreOfSetTest {
    @Mock
    private Team r1;
    @Mock
    private Team r16;
    @Mock
    MatchService matchService;
    @InjectMocks
    SetService setService;

    @ParameterizedTest
    @DisplayName("should return true only if given set score is valid")
    @MethodSource("providePointsInSetsWhenFinalPointIs21")
    void shouldCheckIfScoreIsValid(List<Integer> points, boolean expected) {
//        given
        Set set = new Set(2, 21,
                new SetResult(
                        new TeamSetPoint(r1, points.get(0)),
                        new TeamSetPoint(r16, points.get(1))));
//        when
        boolean result = setService.resultIsValid(set);
//        then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> providePointsInSetsWhenFinalPointIs21() {
        return Stream.of(
                Arguments.of(List.of(21, 19), true),
                Arguments.of(List.of(0, 0), true),
                Arguments.of(List.of(11, 9), true),
                Arguments.of(List.of(24, 23), true),
                Arguments.of(List.of(25, 27), true),
                Arguments.of(List.of(19, 19), true),
                Arguments.of(List.of(21, 20), true),
                Arguments.of(List.of(-2, 19), false),
                Arguments.of(List.of(15, 22), false),
                Arguments.of(List.of(22, 25), false),
                Arguments.of(List.of(-1, -2), false),
                Arguments.of(List.of(Integer.MAX_VALUE, 9999), false));
    }
}
