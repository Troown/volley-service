package com.volleyservice.to;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@Data
@NoArgsConstructor
public class SetRequestTO {
    private int setNumber;
    public int lastPoint;
    private long firstTeamId;
    private Integer firstTeamPoints;
    private long secondTeamId;
    private Integer secondTeamPoints;
}
