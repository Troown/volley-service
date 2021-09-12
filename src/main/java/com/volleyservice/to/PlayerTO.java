package com.volleyservice.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerTO {
    private Long id;
    private String name;
    private String surname;
    private Integer rankingPoints;
    private boolean isAdult;
}
