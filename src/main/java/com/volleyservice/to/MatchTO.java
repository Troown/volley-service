package com.volleyservice.to;

import com.volleyservice.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class MatchTO {
    private String teamOneName;
    private String teamTwoName;
    private List<List<Integer>> results;

}
