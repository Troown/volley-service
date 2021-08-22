package com.volleyservice.to;

import com.volleyservice.enums.Phase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
public class RoundTO {
    private Long id;
    private Integer roundNumber;
    private Phase phase;
    private List<MatchTO> matches;
}
