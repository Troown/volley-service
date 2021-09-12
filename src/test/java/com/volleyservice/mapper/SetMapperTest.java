package com.volleyservice.mapper;

import com.volleyservice.entity.Set;
import com.volleyservice.entity.SetResult;
import com.volleyservice.entity.Team;
import com.volleyservice.entity.TeamSetPoint;
import com.volleyservice.exception.NotFoundException;
import com.volleyservice.repository.TeamRepository;
import com.volleyservice.to.SetRequestTO;
import com.volleyservice.to.SetTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SetMapperTest {
    @Mock
    private TeamRepository teamRepository;
    @InjectMocks
    private SetMapper setMapper;
    @Mock
    private Team firstTeam;
    @Mock
    private Team secondTeam;


    @Test
    public void shouldMapMatchSetRequestTOToMatchSet() {
//        given
        SetRequestTO setRequestTO = new SetRequestTO();
        setRequestTO.setSetNumber(1);
        setRequestTO.setFirstTeamId(1);
        setRequestTO.setFirstTeamPoints(21);
        setRequestTO.setSecondTeamId(16);
        setRequestTO.setSecondTeamPoints(19);
        when(teamRepository.findById(1L)).thenReturn(Optional.of(firstTeam));
        when(teamRepository.findById(16L)).thenReturn(Optional.of(secondTeam));
//        when
        Set result = setMapper.mapsToEntity(setRequestTO);
//        then
        assertThat(result.getSetResult().getFirstTeamSetResult().getTeam()).isEqualTo(firstTeam);
        assertThat(result.getSetResult().getFirstTeamSetResult().getPoints()).isEqualTo(21);
        assertThat(result.getSetResult().getSecondTeamSetResult().getTeam()).isEqualTo(secondTeam);
        assertThat(result.getSetResult().getSecondTeamSetResult().getPoints()).isEqualTo(19);
    }
    @Test
    public void shouldThrowNotFoundExWhenTeamWithOneOfIdsIsNotInRepository() {
//        given
        SetRequestTO setRequestTO = new SetRequestTO();
        setRequestTO.setSetNumber(1);
        setRequestTO.setFirstTeamId(1);
        setRequestTO.setFirstTeamPoints(21);
        setRequestTO.setSecondTeamId(16);
        setRequestTO.setSecondTeamPoints(19);
        when(teamRepository.findById(1L)).thenReturn(Optional.of(firstTeam));
        when(teamRepository.findById(16L)).thenReturn(Optional.empty());
//        when
        Throwable thrown = catchThrowable(() -> setMapper.mapsToEntity(setRequestTO)) ;
//        then
        assertThat(thrown).isInstanceOf(NotFoundException.class)
                .hasMessage("Team does not exist for id = 16");
//TODO        ex message could contains two ids if both are not in repo.
    }

    @Test
    void shouldTransferToTO() {
//        given
        Set set = new Set(1, 21,
                new SetResult(
                        new TeamSetPoint(firstTeam, 21),
                        new TeamSetPoint(secondTeam, 19)));
//        when
        SetTO result = setMapper.mapsToTO(set);
//        then
        assertThat(result).isInstanceOf(SetTO.class);
        assertThat(result.getSetNumber()).isEqualTo(1);
        assertThat(result.getTeamToPointsMap())
                .containsExactlyInAnyOrderEntriesOf(Map.of(firstTeam, 21, secondTeam, 19));
    }
}