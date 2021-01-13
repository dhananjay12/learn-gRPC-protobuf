package com.mynotes.grpc.demo.jsonapi.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostsRepository extends JpaRepository<PostsEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM posts s LIMIT :limit")
    List<PostsEntity> findAllWithLimit(@Param("limit") int limit);

}
