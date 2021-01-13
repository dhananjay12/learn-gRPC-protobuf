package com.mynotes.grpc.demo.jsonapi.controller.mapper;

import com.mynotes.grpc.demo.jsonapi.dto.AuthorDTO;
import com.mynotes.grpc.demo.jsonapi.dto.AuthorDTO.AuthorDTOBuilder;
import com.mynotes.grpc.demo.jsonapi.persistence.AuthorEntity;

public class AuthorMapper {

    public static AuthorEntity makeEntity(AuthorDTO authorDTO) {
        return new AuthorEntity(authorDTO.getId(), authorDTO.getFname(), authorDTO.getLname(),
            authorDTO.getEmail(), authorDTO.getDob());
    }

    public static AuthorDTO makeDTO(AuthorEntity entity) {
        AuthorDTOBuilder authorDTOBuilder = AuthorDTO.builder().id(entity.getId()).fname(entity.getFname())
            .lname(entity.getLname())
            .email(entity.getEmail()).dob(entity.getBirthdate());

        return authorDTOBuilder.build();
    }

}
