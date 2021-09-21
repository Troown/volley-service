package com.volleyservice.streamTests;

import com.volleyservice.entity.Set;
import com.volleyservice.entity.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.SecondaryTable;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class StreamsNeededToGetWinner {
    @Mock
    Team r1;
    @Mock
    Team r16;

    @Test
    public void shouldTransformList_To_TeamToNumberOfOccurrenceInListMap() {
//        given
        List<Team> teams = List.of(r1, r16, r1, r1, r1, r16);
//        when
        var result = teams.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//        then
        assertThat(result).containsExactlyInAnyOrderEntriesOf(Map.of(r1, 4L, r16, 2L));

    }
    @Test
    public void shouldReturnTeamThatOccurTheMostOftenWithNumberOfOccurrence() {
        //        given
        List<Team> teams = List.of(r1, r16, r1, r1, r1, r16);
//        when
        var result = teams.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue());
//        then
        assertThat(result).isPresent();
        assertThat(result.get().getKey()).isEqualTo(r1);
        assertThat(result.get().getValue()).isEqualTo(4L);
    }
    @Test
    public void shouldReturnTeamThatOccurTheMostOftenWithNumberOfOccurrenceWhenValueIsEqual() {
        //        given
        List<Team> teams = List.of(r16, r1, r1, r1, r16, r16);
//        when
        var result = teams.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue());
//        then
        assertThat(result).isPresent();
        assertThat(result.get().getValue()).isEqualTo(3L);
    }
}
