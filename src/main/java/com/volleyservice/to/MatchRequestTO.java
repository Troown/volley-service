package com.volleyservice.to;

import lombok.Data;

@Data
public class MatchRequestTO {
    private Integer matchNumber;
    private Long teamOneID;
    private Long teamTwoId;
}
