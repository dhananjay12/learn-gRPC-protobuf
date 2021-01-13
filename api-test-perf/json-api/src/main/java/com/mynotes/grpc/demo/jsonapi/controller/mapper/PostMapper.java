package com.mynotes.grpc.demo.jsonapi.controller.mapper;

import com.mynotes.grpc.demo.jsonapi.dto.PostDTO;
import com.mynotes.grpc.demo.jsonapi.dto.PostDTO.PostDTOBuilder;
import com.mynotes.grpc.demo.jsonapi.persistence.PostsEntity;

public class PostMapper {

    public static PostDTO makeDTO(PostsEntity entity) {
        PostDTOBuilder postDTOBuilder = PostDTO.builder().id(entity.getId()).authorId(entity.getAuthorId())
            .title(entity.getTitle()).description(entity.getDescription()).content(entity.getContent())
            .date(entity.getDate());

        return postDTOBuilder.build();
    }

}
