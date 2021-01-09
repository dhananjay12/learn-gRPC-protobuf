package com.mynotes.grpc.service;

import io.grpc.stub.StreamObserver;

public class EmployeeService extends EmployeeServiceGrpc.EmployeeServiceImplBase {

    @Override
    public void details(EmployeeDetailsRequest request, StreamObserver<Employee> responseObserver) {

        Employee employee = Employee.newBuilder()
            .setId(request.getId())
            .setFName("John")
            .setLName("Doe").build();

        responseObserver.onNext(employee);
        responseObserver.onCompleted();
    }

    @Override
    public void list(EmployeeListRequest request, StreamObserver<Employee> responseObserver) {
        int count = request.getCount();

        for (int i = 1; i <= count; i++) {
            Employee employee = Employee.newBuilder()
                .setId(i)
                .setFName("John" + i)
                .setLName("Doe" + i).build();
            responseObserver.onNext(employee);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        responseObserver.onCompleted();
    }
}
