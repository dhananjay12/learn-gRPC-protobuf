package com.mynotes.grpc.service;

import io.grpc.Status;
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

        if (count <= 0){
            Status status = Status.INVALID_ARGUMENT.withDescription("Count has to be greater than 0");
            responseObserver.onError(status.asRuntimeException());
            return;
        }

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

    @Override
    public StreamObserver<EmployeeCreateRequest> create(StreamObserver<EmployeeCreateResponse> responseObserver) {
        return new EmployeeStreamRequest(responseObserver);
    }

    @Override
    public StreamObserver<EmployeeCreateRequest> createBiStream(StreamObserver<Employee> responseObserver) {
        return new EmployeeBiStreamRequest(responseObserver);
    }
}
