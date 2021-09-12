package com.volleyservice.SystemDataProvider;

import com.volleyservice.enums.Seed;
import lombok.Data;

import java.util.List;

@Data
public class SeedToMatchRelation {
    private int matchNumber;
    private List<Seed> seeds;

    public SeedToMatchRelation(int matchNumber, Seed seed1, Seed seed2) {
        this.matchNumber = matchNumber;
        this.seeds = List.of(seed1, seed2);
    }
}
