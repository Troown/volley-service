package com.volleyservice.mapper;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;


class PlayerMapperTest {

    private final PlayerMapper playerMapper = new PlayerMapper();

    @Test
    void isAdult() {
//        given
        LocalDate eighteenthBirthDayYesterday = LocalDate.now().minusYears(18).minusDays(1);
        LocalDate eighteenthBirthdayToday = LocalDate.now().minusYears(18);
        LocalDate eighteenthBirthdayTomorrow = LocalDate.now().minusDays(18).plusDays(1);

//        when
        var isAdultAtTheAgeOf18andOneDay = playerMapper.isAdult(eighteenthBirthDayYesterday);
        var isAdultAtTheAgeOf18Exactly = playerMapper.isAdult(eighteenthBirthdayToday);
        var isAdultAtTheAgeOf17 = playerMapper.isAdult(eighteenthBirthdayTomorrow);

//        then
        assertThat(isAdultAtTheAgeOf18andOneDay).isEqualTo(true);
        assertThat(isAdultAtTheAgeOf18Exactly).isEqualTo(true);
        assertThat(isAdultAtTheAgeOf17).isEqualTo(false);
    }
}