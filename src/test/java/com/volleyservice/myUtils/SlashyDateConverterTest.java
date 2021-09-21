package com.volleyservice.myUtils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterContext;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SlashyDateConverterTest {
    private SlashyDateConverter slashyDateConverter = new SlashyDateConverter();

    @Test
    void shouldReturnLocalDateTypeFromString() {
//        given
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String dateToConvert = "2021/06/21";
//        when
        LocalDate result = LocalDate.parse(dateToConvert, formatter);
//        then
        assertThat(result).isInstanceOf(LocalDate.class);
        assertThat(result.getYear()).isEqualTo(2021);
        assertThat(result.getMonth()).isEqualTo(Month.JUNE);
        assertThat(result.getDayOfMonth()).isEqualTo(21);
    }
}