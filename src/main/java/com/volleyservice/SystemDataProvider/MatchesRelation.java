package com.volleyservice.SystemDataProvider;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MatchesRelation {
    private Integer sourceMatchNumber;
    private Integer destinationMatchNumber;
    private Integer result;
}
