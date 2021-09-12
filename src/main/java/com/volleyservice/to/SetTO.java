package com.volleyservice.to;

import com.volleyservice.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
@Data
@AllArgsConstructor
public class SetTO {
    private int setNumber;
    private Map<Team, Integer> teamToPointsMap;
}
