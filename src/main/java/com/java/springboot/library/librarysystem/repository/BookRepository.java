package com.java.springboot.library.librarysystem.repository;


import com.java.springboot.library.librarysystem.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

}
