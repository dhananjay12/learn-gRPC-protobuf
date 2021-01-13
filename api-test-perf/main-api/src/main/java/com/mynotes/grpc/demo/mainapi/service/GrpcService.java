package com.mynotes.grpc.demo.mainapi.service;

import com.mynotes.grpc.demo.AuthorRequest;
import com.mynotes.grpc.demo.BlogServiceGrpc;
import com.mynotes.grpc.demo.CalculateRequest;
import com.mynotes.grpc.demo.CalculateResponse;
import com.mynotes.grpc.demo.CalculateServiceGrpc;
import com.mynotes.grpc.demo.Post;
import com.mynotes.grpc.demo.PostsRequest;
import com.mynotes.grpc.demo.mainapi.dto.CalculateResponseDTO;
import com.mynotes.grpc.demo.mainapi.dto.PostDTO;
import io.grpc.stub.StreamObserver;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GrpcService {

    @GrpcClient("grpc-api")
    private CalculateServiceGrpc.CalculateServiceBlockingStub blockingStub;

    @GrpcClient("grpc-api")
    private CalculateServiceGrpc.CalculateServiceStub stub;

    @GrpcClient("grpc-api")
    private BlogServiceGrpc.BlogServiceBlockingStub blogBlockingStub;

    @GrpcClient("grpc-api")
    private BlogServiceGrpc.BlogServiceStub blogStub;

    public CalculateResponse calculate(long input) {

        CalculateRequest calculateRequest = CalculateRequest.newBuilder().setInput(input).build();
        return this.blockingStub.squareAndRoot(calculateRequest);

    }


    public Object calculateStream(long input) {
        CompletableFuture<List<CalculateResponseDTO>> completableFuture = new CompletableFuture<>();
        CalculateResponseStream calculateResponseStream = new CalculateResponseStream(new ArrayList<>(),
            completableFuture);
        StreamObserver<CalculateRequest> squareAndRootStream = this.stub.squareAndRootStream(calculateResponseStream);

        LongStream.rangeClosed(1, input)
            .mapToObj(i -> CalculateRequest.newBuilder().setInput(i).build())
            .forEach(squareAndRootStream::onNext);
        squareAndRootStream.onCompleted();

        return completableFuture;
    }

    public Object posts(int limit) throws ParseException {

        PostsRequest postsRequest = PostsRequest.newBuilder().setLimit(limit).build();
        Iterator<Post> posts = this.blogBlockingStub.posts(postsRequest);
        List<PostDTO> allPostsList = new ArrayList<PostDTO>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        for (Iterator<Post> i = posts; i.hasNext(); ) {
            Post post = i.next();
            Calendar cal = Calendar.getInstance();
            cal.setTime(formatter.parse(post.getDate()));
            allPostsList.add(new PostDTO(post.getId(), post.getAuthorId(), post.getTitle(),
                post.getDescription(), post.getContent(), cal, null));
        }

        CompletableFuture<List<PostDTO>> completableFuture = new CompletableFuture<>();
        AuthorStream authorStream = new AuthorStream(allPostsList, completableFuture);

        StreamObserver<AuthorRequest> authorStreamRequest = this.blogStub.findAuthorStream(authorStream);

        /*IntStream.rangeClosed(1, allPostsList.size())
            .mapToObj(i -> AuthorRequest.newBuilder().setId(allPostsList.get(i).getAuthorId()).build())
            .forEach(authorStreamRequest::onNext);*/

        allPostsList.stream().map(p -> AuthorRequest.newBuilder().setId(p.getAuthorId()).build())
            .forEach(authorStreamRequest::onNext);

        authorStreamRequest.onCompleted();

        return completableFuture;
    }


}
