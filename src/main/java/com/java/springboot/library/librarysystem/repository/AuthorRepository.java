package com.java.springboot.library.librarysystem.repository;

import com.java.springboot.library.librarysystem.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
}
