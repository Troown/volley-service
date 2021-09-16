package com.volleyservice.howDoesItWorkTest;

import com.volleyservice.DataProvider.MyClock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.*;

import static java.time.Instant.ofEpochMilli;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SetTimeDuringTest {
    @Mock
    MyClock myClock;

    @Test
    public void shouldCheckIfLocalDateNowReturnDefinedDate() {
        String myDate = "1993-06-03T00:00:00Z";
//        given
        Clock clock = Clock.fixed(Instant.parse(myDate), ZoneId.of("UTC"));
//        when
        LocalDate result = LocalDate.now(clock);
//        then
        assertThat(result.getYear()).isEqualTo(1993);
        assertThat(result.getMonth()).isEqualTo(Month.JUNE);
        assertThat(result.getDayOfMonth()).isEqualTo(3);
    }

    @Test
    public void shouldMockLocalDateNowWithDefineDate() {
//        given
        String myDate = "1993-06-03T00:00:00Z";
        when(myClock.getClock()).thenReturn(Clock.fixed(Instant.parse(myDate), ZoneId.of("UTC")));
//        when
        LocalDate result = LocalDate.now(myClock.getClock());
//        then
        assertThat(result.getYear()).isEqualTo(1993);
        assertThat(result.getMonth()).isEqualTo(Month.JUNE);
        assertThat(result.getDayOfMonth()).isEqualTo(3);

    }
}
