package com.mynotes.grpc.demo.mainapi.controller;

import com.mynotes.grpc.demo.CalculateResponse;
import com.mynotes.grpc.demo.mainapi.dto.CalculateResponseDTO;
import com.mynotes.grpc.demo.mainapi.service.GrpcService;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grpc")
public class GrpcController {


    @Autowired
    GrpcService grpcService;


    @GetMapping("/calculate/{input}")
    public List<CalculateResponseDTO> calculateGrpc(@PathVariable long input) {
        List<CalculateResponseDTO> list = new ArrayList<>();
        for (int i = 1; i <= input; i++) {
            CalculateResponse calculateResponse = grpcService.calculate(i);
            list.add(new CalculateResponseDTO(calculateResponse.getInput(), calculateResponse.getSquare(),
                calculateResponse.getSqrt()));
        }
        return list;
    }

    @GetMapping("/stream/calculate/{input}")
    public Object calculateGrpcStream(@PathVariable long input) {
        return grpcService.calculateStream(input);
    }

    @GetMapping("/posts/{limit}")
    public Object posts(@PathVariable int limit) throws ParseException {
        return grpcService.posts(limit);
    }


}
