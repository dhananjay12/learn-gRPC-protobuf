package com.mynotes.grpc.demo.jsonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculateResponseDTO {

    long input;
    long square;
    double sqrt;

}
