package com.volleyservice.to;

import com.volleyservice.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.util.Map;
@Data
@AllArgsConstructor
@Value
public class SetTO {
    int setNumber;
    Map<TeamTO, Integer> teamToPointsMap;
}
