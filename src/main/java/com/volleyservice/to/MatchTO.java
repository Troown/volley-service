package com.volleyservice.to;

import com.volleyservice.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Data
public class MatchTO {
    private Integer matchNumber;
    private Map<String, Long> teamToSets;
}
