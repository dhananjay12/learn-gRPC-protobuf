package com.mynotes.grpc.demo.service;

import com.mynotes.grpc.demo.Author;
import com.mynotes.grpc.demo.AuthorRequest;
import com.mynotes.grpc.demo.BlogServiceGrpc.BlogServiceImplBase;
import com.mynotes.grpc.demo.Post;
import com.mynotes.grpc.demo.PostsRequest;
import com.mynotes.grpc.demo.persistence.AuthorEntity;
import com.mynotes.grpc.demo.persistence.AuthorsRepository;
import com.mynotes.grpc.demo.persistence.PostsEntity;
import com.mynotes.grpc.demo.persistence.PostsRepository;
import com.mynotes.grpc.demo.service.requests.AuthorStreamRequest;
import io.grpc.stub.StreamObserver;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class BlogApiService extends BlogServiceImplBase {

    @Autowired
    AuthorsRepository authorsRepository;

    @Autowired
    PostsRepository postsRepository;

    @Override
    public void findAuthor(AuthorRequest request, StreamObserver<Author> responseObserver) {

        Optional<AuthorEntity> authorEntity = authorsRepository.findById(request.getId());
        if (authorEntity.isPresent()) {
            AuthorEntity entity = authorEntity.get();
            Author author = Author.newBuilder().setId(entity.getId()).setFname(entity.getFname())
                .setLname(entity.getLname()).setEmail(entity.getEmail())
                .setDob(convertCalenderToDate(entity.getBirthdate())).build();

            responseObserver.onNext(author);
        }

        responseObserver.onCompleted();

    }

    @Override
    public StreamObserver<AuthorRequest> findAuthorStream(StreamObserver<Author> responseObserver) {
        return new AuthorStreamRequest(responseObserver, authorsRepository);
    }

    @Override
    public void posts(PostsRequest request, StreamObserver<Post> responseObserver) {
        List<PostsEntity> posts = postsRepository.findAllWithLimit(request.getLimit());
        for (PostsEntity entity: posts) {
            responseObserver.onNext(Post.newBuilder().setId(entity.getId()).setAuthorId(entity.getAuthorId())
            .setTitle(entity.getTitle()).setDescription(entity.getDescription())
            .setContent(entity.getContent()).setDate(convertCalenderToDate(entity.getDate())).build());
        }
        responseObserver.onCompleted();
    }

    private String convertCalenderToDate(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day+"-"+ month+"-"+year;
    }
}
