package com.volleyservice.to;

import lombok.Data;

import java.util.List;


@Data
public class ListOfSetsRequestTO {
    private List<SetRequestTO> sets;
}
