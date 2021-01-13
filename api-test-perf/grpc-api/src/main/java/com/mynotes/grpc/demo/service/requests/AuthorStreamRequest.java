package com.mynotes.grpc.demo.service.requests;

import com.mynotes.grpc.demo.Author;
import com.mynotes.grpc.demo.AuthorRequest;
import com.mynotes.grpc.demo.persistence.AuthorEntity;
import com.mynotes.grpc.demo.persistence.AuthorsRepository;
import io.grpc.stub.StreamObserver;
import java.util.Calendar;
import java.util.Optional;

public class AuthorStreamRequest implements StreamObserver<AuthorRequest> {

    private StreamObserver<Author> responseStreamObserver;
    private AuthorsRepository authorsRepository;

    public AuthorStreamRequest(StreamObserver<Author> streamObserver, AuthorsRepository authorsRepository) {
        this.responseStreamObserver = streamObserver;
        this.authorsRepository = authorsRepository;
    }

    @Override
    public void onNext(AuthorRequest authorRequest) {

        Optional<AuthorEntity> authorEntity = authorsRepository.findById(authorRequest.getId());

        if (authorEntity.isPresent()) {
            AuthorEntity entity = authorEntity.get();
            Author author = Author.newBuilder().setId(entity.getId()).setFname(entity.getFname())
                .setLname(entity.getLname()).setEmail(entity.getEmail())
                .setDob(convertCalenderToDate(entity.getBirthdate())).build();

            this.responseStreamObserver.onNext(author);
        }
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {
        this.responseStreamObserver.onCompleted();
    }

    private String convertCalenderToDate(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day+"-"+ month+"-"+year;
    }
}
