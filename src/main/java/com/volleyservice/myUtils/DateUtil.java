package com.volleyservice.myUtils;

import java.time.LocalDate;
import java.time.Period;

public class DateUtil {
    public static boolean isAdult(LocalDate dateOfBirth, LocalDate presentDate) {
        return Period.between(dateOfBirth, presentDate).getYears() >= 18;
    }
}
