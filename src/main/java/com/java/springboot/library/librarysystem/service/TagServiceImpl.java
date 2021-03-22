package com.java.springboot.library.librarysystem.service;


import com.java.springboot.library.librarysystem.entity.TagEntity;
import com.java.springboot.library.librarysystem.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository repository;

    public TagServiceImpl(TagRepository repository) {
        this.repository = repository;
    }


    @Override
    public void saveTag(TagEntity tagEntity) {
        repository.save(tagEntity);
    }

    @Override
    public void deleteTag(TagEntity tagEntity) {
        repository.delete(tagEntity);
    }

    @Override
    public List<TagEntity> getAllTags() {
        return repository.findAll();
    }


    @Override
    public List<TagEntity> getTagByKey(String key) {

        List<TagEntity> found = new ArrayList<>();
        //TODO: finish
        return found;


    }

}
