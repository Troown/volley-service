package com.volleyservice.to;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamRequestTO {
    private Long playerNumberOneId;
    private Long playerNumberTwoId;
}
