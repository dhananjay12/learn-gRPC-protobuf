package com.mynotes.grpc.demo.mainapi.service;

import com.mynotes.grpc.demo.CalculateResponse;
import com.mynotes.grpc.demo.mainapi.dto.CalculateResponseDTO;
import io.grpc.stub.StreamObserver;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CalculateResponseStream implements StreamObserver<CalculateResponse> {

    private List<CalculateResponseDTO> calculateResponseDTOList;
    private final CompletableFuture<List<CalculateResponseDTO>> completableFuture;

    public CalculateResponseStream(List<CalculateResponseDTO> list, CompletableFuture<List<CalculateResponseDTO>> completableFuture) {
        this.calculateResponseDTOList = list;
        this.completableFuture = completableFuture;
    }

    @Override
    public void onNext(CalculateResponse calculateResponse) {
        calculateResponseDTOList.add(new CalculateResponseDTO(calculateResponse.getInput()
            ,calculateResponse.getSquare(),calculateResponse.getSqrt()));
    }

    @Override
    public void onError(Throwable throwable) {
        completableFuture.cancel(true);
    }

    @Override
    public void onCompleted() {
        completableFuture.complete(this.calculateResponseDTOList);
    }
}
