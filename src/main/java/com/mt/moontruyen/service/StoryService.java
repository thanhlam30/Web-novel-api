package com.mt.moontruyen.service;

import com.mt.moontruyen.dto.request.StoryCreationRequest;
import com.mt.moontruyen.dto.request.StoryUpdateRequest;
import com.mt.moontruyen.dto.response.PageResponse;
import com.mt.moontruyen.dto.response.StoryResponse;
import com.mt.moontruyen.entity.Author;
import com.mt.moontruyen.entity.Category;
import com.mt.moontruyen.entity.Story;
import com.mt.moontruyen.exception.AppException;
import com.mt.moontruyen.exception.ErrorCode;
import com.mt.moontruyen.mapper.StoryMapper;
import com.mt.moontruyen.repository.AuthorRepository;
import com.mt.moontruyen.repository.CategoryRepository;
import com.mt.moontruyen.repository.StoryRepository;
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

    public PageResponse<StoryResponse> getAllStories(int page, int size){
        Sort sort = Sort.by("updatedAt").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        var pageData = storyRepository.findAll(pageable);

        List<StoryResponse> storyResponses = pageData.getContent().stream()
                .map(storyMapper::toStoryResponse)
                .toList();

        return PageResponse.<StoryResponse>builder()
                .currentPage(page)
                .pageSize(pageData.getSize())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(storyResponses)
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
