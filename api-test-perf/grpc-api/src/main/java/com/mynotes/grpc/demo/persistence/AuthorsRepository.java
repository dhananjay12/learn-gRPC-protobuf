package com.mynotes.grpc.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorsRepository extends JpaRepository<AuthorEntity, Integer> {


}
