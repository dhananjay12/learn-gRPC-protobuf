package com.mynotes.grpc.demo.service.requests;

import com.mynotes.grpc.demo.CalculateRequest;
import com.mynotes.grpc.demo.CalculateResponse;
import io.grpc.stub.StreamObserver;

public class CalculateStreamRequest  implements StreamObserver<CalculateRequest> {

    private StreamObserver<CalculateResponse> responseStreamObserver;

    //race condition?
    private int counter;

    public CalculateStreamRequest(StreamObserver<CalculateResponse> streamObserver){
        this.responseStreamObserver = streamObserver;
    }

    @Override
    public void onNext(CalculateRequest calculateRequest) {

        long input = calculateRequest.getInput();
        CalculateResponse calculateResponse = CalculateResponse.newBuilder().setInput(input)
                  .setSquare(Math.multiplyExact(input, input))
            .setSqrt(Math.sqrt(input)).build();

        this.responseStreamObserver.onNext(calculateResponse);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {
        this.responseStreamObserver.onCompleted();
    }
}
