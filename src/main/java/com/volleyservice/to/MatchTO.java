package com.volleyservice.to;

import com.volleyservice.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Data
@Value
public class MatchTO {
    Integer matchNumber;
    Map<String, Long> teamToSets;
}
