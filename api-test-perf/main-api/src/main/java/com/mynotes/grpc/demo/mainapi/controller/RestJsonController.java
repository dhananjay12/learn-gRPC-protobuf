package com.mynotes.grpc.demo.mainapi.controller;


import com.mynotes.grpc.demo.mainapi.dto.CalculateResponseDTO;
import com.mynotes.grpc.demo.mainapi.dto.PostDTO;
import com.mynotes.grpc.demo.mainapi.service.JsonService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RestJsonController {

    @Autowired
    JsonService jsonService;

    @GetMapping("/calculate/{input}")
    public List<CalculateResponseDTO> calculateRest(@PathVariable long input) {
        List<CalculateResponseDTO> list = new ArrayList<>();
        for (int i = 1; i <= input; i++) {
            list.add(jsonService.calculate(i));
        }
        return list;
    }

    @GetMapping("/posts/{limit}")
    public PostDTO[] posts(@PathVariable int limit) {
        return jsonService.posts(limit);
    }

}
