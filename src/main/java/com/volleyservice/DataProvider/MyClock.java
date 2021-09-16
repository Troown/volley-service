package com.volleyservice.DataProvider;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Component
@Data
public class MyClock {
    private Clock clock;

    public MyClock() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }
}
