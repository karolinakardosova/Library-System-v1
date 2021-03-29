package com.java.springboot.library.librarysystem.controller;

import com.java.springboot.library.librarysystem.config.DataTransformer;
import com.java.springboot.library.librarysystem.dto.TagDto;
import com.java.springboot.library.librarysystem.entity.TagEntity;
import com.java.springboot.library.librarysystem.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/tags")
public class TagController {

    private TagService tagService;
    private DataTransformer dataTransformer;


    public TagController(TagService tagService,DataTransformer dataTransformer) {
        this.tagService = tagService;
        this.dataTransformer = dataTransformer;
    }

    @GetMapping()
    public ResponseEntity<List<TagDto>> viewTags() {
        List<TagEntity> entities = tagService.getAllTags();

        List<TagDto> tags = entities.stream().map(tagEntity -> dataTransformer.transform(tagEntity)).collect(Collectors.toList());
        return ResponseEntity.ok(tags);
    }

    @PostMapping()
    public ResponseEntity<Void> saveTag(@RequestBody TagDto tagDto) {

        tagService.saveTag(dataTransformer.transform(tagDto));

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteTag(@PathVariable String key) {


        return null;
    }

}
