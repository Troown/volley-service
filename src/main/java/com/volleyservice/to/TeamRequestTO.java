package com.volleyservice.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class TeamRequestTO {

    @Size(min = 2, max = 2)
    private List<Long> ids;
}
