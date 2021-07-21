package com.volleyservice.to;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class TeamRequestTO {

    private Long playerNumberOneId;
    private Long playerNumberTwoId;
}
