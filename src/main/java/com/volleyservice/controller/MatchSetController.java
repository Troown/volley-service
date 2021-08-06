package com.volleyservice.controller;


import com.volleyservice.entity.SetResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchSetController {


    @PostMapping("/sets")
    public void newSet(@RequestBody SetResult setResult) {
        setResult.getWinnerOfSet();

    }
}
