package com.mynotes.grpc;

import com.mynotes.grpc.service.EmployeeService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class GrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(3009)
            .addService(new EmployeeService())
            .build();

        server.start();

        server.awaitTermination();

    }

}
