package com.example.tedabot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mansurov Abdusamad  *  14.12.2022  *  12:37   *  tedaUz
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    @GetMapping()
    public ResponseEntity<?> getAll(){
        return null;
    }

}
