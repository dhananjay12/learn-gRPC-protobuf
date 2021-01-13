package com.mynotes.grpc.demo.service;

import com.mynotes.grpc.demo.CalculateRequest;
import com.mynotes.grpc.demo.CalculateResponse;
import com.mynotes.grpc.demo.CalculateServiceGrpc.CalculateServiceImplBase;
import com.mynotes.grpc.demo.service.requests.CalculateStreamRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class CalculateApiService extends CalculateServiceImplBase {

    @Override
    public void squareAndRoot(CalculateRequest request, StreamObserver<CalculateResponse> responseObserver) {
        long input = request.getInput();
        CalculateResponse calculateResponse = CalculateResponse.newBuilder().setInput(input)
                  .setSquare(Math.multiplyExact(input, input))
            .setSqrt(Math.sqrt(input)).build();

        responseObserver.onNext(calculateResponse);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<CalculateRequest> squareAndRootStream(StreamObserver<CalculateResponse> responseObserver) {
        return new CalculateStreamRequest(responseObserver);
    }
}
