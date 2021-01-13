package com.mynotes.grpc.demo.mainapi.service;

import com.mynotes.grpc.demo.Author;
import com.mynotes.grpc.demo.mainapi.dto.AuthorDTO;
import com.mynotes.grpc.demo.mainapi.dto.PostDTO;
import io.grpc.stub.StreamObserver;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public class AuthorStream implements StreamObserver<Author> {

    private final CompletableFuture<List<PostDTO>> completableFuture;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
    private List<PostDTO> list;
    private int counter;

    public AuthorStream(List<PostDTO> list, CompletableFuture<List<PostDTO>> completableFuture) {
        this.list = list;
        this.completableFuture = completableFuture;
    }

    @Override
    public void onNext(Author author) {
        try {
            Calendar cal =   Calendar.getInstance();
            cal.setTime(formatter.parse(author.getDob()));
            list.get(counter).setAuthor(AuthorDTO.builder().id(author.getId()).fname(author.getFname())
                .lname(author.getLname()).email(author.getEmail())
                .dob(cal)
                .build());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        counter++;
    }

    @Override
    public void onError(Throwable throwable) {
        completableFuture.cancel(true);
    }

    @Override
    public void onCompleted() {
        completableFuture.complete(list);
    }
}
