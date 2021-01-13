package com.mynotes.grpc.demo.jsonapi.controller;

import com.mynotes.grpc.demo.jsonapi.dto.CalculateResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculate")
public class JsonCalculateController {

    @GetMapping("/{input}")
    public ResponseEntity<CalculateResponseDTO> calculate(@PathVariable long input) {

        CalculateResponseDTO calculateResponse = new CalculateResponseDTO();

        calculateResponse.setInput(input);
        calculateResponse.setSquare(Math.multiplyExact(input, input));
        calculateResponse.setSqrt(Math.sqrt(input));

        return ResponseEntity.ok(calculateResponse);
    }

}
