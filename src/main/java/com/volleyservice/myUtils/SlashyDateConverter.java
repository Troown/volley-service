package com.volleyservice.myUtils;

import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SlashyDateConverter implements ArgumentConverter {
    @Override
    public Object convert(Object source, ParameterContext context)
            throws ArgumentConversionException {
        if (!(source instanceof String)) {
            throw new IllegalArgumentException(
                    "The argument should be a string: " + source);
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            return LocalDate.parse((String) source, formatter);

        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert", e);
        }
    }
}
