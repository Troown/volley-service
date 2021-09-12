package com.volleyservice.myUtils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static com.volleyservice.myUtils.DateUtil.isAdult;
import static org.assertj.core.api.Assertions.assertThat;

class DateUtilTest {

    @ParameterizedTest
    @CsvSource({"1993/06/03,true", "2003/08/31,true", "2003/09/16,true",
            "2003/09/17,false", "2003/09/30,false", "2100/06/06,false"})
    void shouldReturnTrueOnlyIfPersonWithGivenYearOfBirthIsAdult(
            @ConvertWith(SlashyDateConverter.class) LocalDate dateOfBirth, boolean expected
    ) {
//        given
        LocalDate testPresentDate = LocalDate.of(2021, 9, 16);
//        when
        boolean result = isAdult(dateOfBirth, testPresentDate);
//        then
        assertThat(result).isEqualTo(expected);
    }
}