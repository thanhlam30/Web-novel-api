package com.mt.mootruyen.service;

import com.mt.mootruyen.dto.request.StoryCreationRequest;
import com.mt.mootruyen.dto.request.StoryUpdateRequest;
import com.mt.mootruyen.dto.response.PageResponse;
import com.mt.mootruyen.entity.Author;
import com.mt.mootruyen.entity.Category;
import com.mt.mootruyen.entity.Story;
import com.mt.mootruyen.exception.AppException;
import com.mt.mootruyen.exception.ErrorCode;
import com.mt.mootruyen.mapper.StoryMapper;
import com.mt.mootruyen.repository.AuthorRepository;
import com.mt.mootruyen.repository.CategoryRepository;
import com.mt.mootruyen.repository.StoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StoryService {

    StoryRepository storyRepository;

    CategoryRepository categoryRepository;

    AuthorRepository authorRepository;

    @Autowired
    private StoryMapper storyMapper;

    public PageResponse<Story> getAllStories(int page, int size){
        Sort sort = Sort.by("updatedAt").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        var pageData = storyRepository.findAll(pageable);


        return PageResponse.<Story>builder()
                .currentPage(page)
                .pageSize(pageData.getSize())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(pageData.getContent())
                .build();
    }

    public Story getStoryById(String storyId){
        return storyRepository.findById(storyId).orElseThrow(() -> new AppException(ErrorCode.STORY_NOT_FOUND));
    }

    public Story getStoryBySlug(String slug){
        return storyRepository.findBySlug(slug).orElseThrow(() -> new AppException(ErrorCode.STORY_NOT_FOUND));
    }

    public Story createStory(StoryCreationRequest request){

        Optional<Author> authorOptional = authorRepository.findById(request.getAuthorId());
        if(authorOptional.isEmpty()){
            throw new AppException(ErrorCode.AUTHOR_NOT_FOUND);
        }

        List<Category> categories = categoryRepository.findAllById(request.getCategoryIds());
        if(categories.isEmpty()){
            throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);
        }
        if(storyRepository.existsByTitle(request.getTitle())){
            throw new AppException(ErrorCode.STORY_EXISTED);
        }

        Author author = authorOptional.get();
        Story story = storyMapper.toStory(request);
        story.setAuthor(author);
        story.setCategories(categories);
        story.setCreatedAt(LocalDateTime.now());
        return storyRepository.save(story);
    }

    public Story updateStory(String storyId, StoryUpdateRequest request){

        Optional<Author> authorOptional = authorRepository.findById(request.getAuthorId());
        if(authorOptional.isEmpty()){
            throw new AppException(ErrorCode.AUTHOR_NOT_FOUND);
        }

        List<Category> categories = categoryRepository.findAllById(request.getCategoryIds());
        if(categories.isEmpty()){
            throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);
        }

        Author author = authorOptional.get();
        Story story = getStoryById(storyId);
        storyMapper.toUpdateStory(request,story);
        story.setAuthor(author);
        story.setCategories(categories);
        story.setUpdatedAt(LocalDateTime.now());
        return storyRepository.save(story);
    }
    public void deleteStory(String storyId){
        storyRepository.deleteById(storyId);
    }
}
