package com.volleyservice.mapper;

import com.volleyservice.DataProvider.MyClock;
import com.volleyservice.entity.Player;
import com.volleyservice.to.PlayerTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class PlayerMapperTest {
    @Mock
    private Player player;
    MyClock myClock = new MyClock();
    private final PlayerMapper playerMapper = new PlayerMapper(myClock);

    @Test
    public void shouldReturnTrueWhenPlayerHasEighteenBirthdayToday() {
//        given
        LocalDate eighteenthBirthdayToday = LocalDate.now().minusYears(18);
        when(player.getDateOfBirth()).thenReturn(eighteenthBirthdayToday);
//        when
        PlayerTO result = playerMapper.mapsToTO(player);
//        then
        assertThat(result.isAdult()).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenYearOfBirthIsInTheFuture() {
        //        given
        LocalDate eighteenthBirthdayToday = LocalDate.now().plusYears(100);
        when(player.getDateOfBirth()).thenReturn(eighteenthBirthdayToday);
//        when
        PlayerTO result = playerMapper.mapsToTO(player);
//        then
        assertThat(result.isAdult()).isFalse();
    }
}
