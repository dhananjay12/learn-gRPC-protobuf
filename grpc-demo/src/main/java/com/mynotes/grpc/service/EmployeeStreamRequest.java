package com.mynotes.grpc.service;

import io.grpc.stub.StreamObserver;

public class EmployeeStreamRequest implements StreamObserver<EmployeeCreateRequest> {

    private StreamObserver<EmployeeCreateResponse> responseStreamObserver;
    //race condition?
    private int numberOfEmployeeAdded;

    public EmployeeStreamRequest(StreamObserver<EmployeeCreateResponse> streamObserver){
        this.responseStreamObserver = streamObserver;
    }

    @Override
    public void onNext(EmployeeCreateRequest employeeCreateRequest) {
        String fName = employeeCreateRequest.getFName();
        String lName = employeeCreateRequest.getLName();
        System.out.println("Request ==> " + fName + " " + lName);
        this.numberOfEmployeeAdded++;
    }

    @Override
    public void onError(Throwable throwable) {
    }

    @Override
    public void onCompleted() {
        EmployeeCreateResponse response = EmployeeCreateResponse.newBuilder().setCreatedEmployees(numberOfEmployeeAdded)
            .build();
        this.responseStreamObserver.onNext(response);
        this.responseStreamObserver.onCompleted();

    }
}
