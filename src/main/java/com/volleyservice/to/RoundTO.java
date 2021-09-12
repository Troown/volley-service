package com.volleyservice.to;

import com.volleyservice.enums.Phase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;


@Data
@Value
@AllArgsConstructor
public class RoundTO {
    Long id;
    Integer roundNumber;
    Phase phase;
    List<MatchTO> matches;
}
