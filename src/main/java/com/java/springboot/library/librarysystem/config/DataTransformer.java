package com.java.springboot.library.librarysystem.config;

import com.java.springboot.library.librarysystem.dto.AuthorDto;
import com.java.springboot.library.librarysystem.dto.BookDto;
import com.java.springboot.library.librarysystem.dto.TagDto;
import com.java.springboot.library.librarysystem.entity.AuthorEntity;
import com.java.springboot.library.librarysystem.entity.BookEntity;
import com.java.springboot.library.librarysystem.entity.TagEntity;
import org.springframework.stereotype.Component;

import java.util.List;

//Ked je to component tak nemaju byt static metody
@Component
public class DataTransformer {

    public BookDto transform(BookEntity entity) {
        return new BookDto(entity.getTitle(), entity.getAllAuthorsId());
    }


    public BookEntity transform(BookDto dto, List<AuthorEntity> authors) {

        return new BookEntity(dto.getTitle(), authors);
    }

    public AuthorDto transform(AuthorEntity entity) {
        return new AuthorDto(entity.getName());
    }


    public AuthorEntity transform(AuthorDto dto) {
        return new AuthorEntity(dto.getName());
    }

    public TagEntity transform(TagDto dto) {
        return new TagEntity(dto.getKey(), dto.getValue());
    }

    public TagDto transform(TagEntity entity) {
        return new TagDto(entity.getKey(), entity.getValue());
    }
}
