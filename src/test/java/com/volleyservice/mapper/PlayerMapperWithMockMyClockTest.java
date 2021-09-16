package com.volleyservice.mapper;

import com.volleyservice.DataProvider.MyClock;
import com.volleyservice.DataProvider.SlashyDateConverter;
import com.volleyservice.entity.Player;
import com.volleyservice.to.PlayerTO;
import org.apache.tomcat.jni.Local;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerMapperWithMockMyClockTest {
    @Mock
    private Player player;
    @Mock
    private MyClock myClock;
    @InjectMocks
    private PlayerMapper playerMapper;

    @ParameterizedTest
    @CsvSource({"1920/09/08,true", "2003/01/01,true", "2003/01/02,false", "2100/06/06,false"})
    public void shouldCheckIfGivenDateOfBirthIsAdult(
            @ConvertWith(SlashyDateConverter.class) LocalDate dateOfBirth, boolean expected
    ) {
        String dateSetAsNow = "2021-01-01T00:00:00Z";
        when(myClock.getClock()).thenReturn(Clock.fixed(Instant.parse(dateSetAsNow), ZoneId.systemDefault()));
        when(player.getDateOfBirth()).thenReturn(dateOfBirth);
//        when
        PlayerTO result = playerMapper.mapsToTO(player);
//        then
        assertThat(result.isAdult()).isEqualTo(expected);
    }
}