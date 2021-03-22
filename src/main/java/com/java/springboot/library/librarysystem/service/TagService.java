package com.java.springboot.library.librarysystem.service;

import com.java.springboot.library.librarysystem.entity.TagEntity;

import java.util.List;

public interface TagService {


    void saveTag(TagEntity tagEntity);

    void deleteTag(TagEntity tagEntity);

    List<TagEntity> getAllTags();

    List<TagEntity> getTagByKey(String key);

}
