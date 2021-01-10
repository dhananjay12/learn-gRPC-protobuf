package com.mynotes.grpc.service;

import io.grpc.stub.StreamObserver;

public class EmployeeBiStreamRequest implements StreamObserver<EmployeeCreateRequest> {

    private StreamObserver<Employee> responseStreamObserver;

    //race condition?
    private int counter;

    public EmployeeBiStreamRequest(StreamObserver<Employee> employeeStreamObserver){
        this.responseStreamObserver = employeeStreamObserver;
    }

    @Override
    public void onNext(EmployeeCreateRequest employeeCreateRequest) {
        String fName = employeeCreateRequest.getFName();
        String lName = employeeCreateRequest.getLName();
        System.out.println("Request ==> " + fName + " " + lName);
        this.counter++;

        Employee employee = Employee.newBuilder().setId(this.counter).setFName(fName).setLName(lName).build();

        this.responseStreamObserver.onNext(employee);
    }
    @Override
    public void onError(Throwable throwable) {
    }
    @Override
    public void onCompleted() {
        this.responseStreamObserver.onCompleted();
    }
}
