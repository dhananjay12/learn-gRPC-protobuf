package com.mynotes.grpc.demo.jsonapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Calendar;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostDTO {

    private Integer id;

     private Integer authorId;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String content;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Calendar date;

}
