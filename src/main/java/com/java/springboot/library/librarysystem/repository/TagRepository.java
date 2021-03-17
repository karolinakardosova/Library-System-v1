package com.java.springboot.library.librarysystem.repository;

import com.java.springboot.library.librarysystem.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, String> {
}
