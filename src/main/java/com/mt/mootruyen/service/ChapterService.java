package com.mt.mootruyen.service;

import com.mt.mootruyen.dto.request.ChapterCreationRequest;
import com.mt.mootruyen.dto.request.ChapterUpdatingRequest;
import com.mt.mootruyen.entity.Chapter;
import com.mt.mootruyen.entity.Story;
import com.mt.mootruyen.exception.AppException;
import com.mt.mootruyen.exception.ErrorCode;
import com.mt.mootruyen.mapper.ChapterMapper;
import com.mt.mootruyen.repository.ChapterRepository;
import com.mt.mootruyen.repository.StoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChapterService {
    ChapterRepository chapterRepository;

    StoryRepository storyRepository;

    ChapterMapper chapterMapper;

    public List<Chapter> getAllChaptersByStoryId(String storyId){
        if(!storyRepository.existsById(storyId)){
            throw new AppException(ErrorCode.STORY_NOT_FOUND);
        }
        return chapterRepository.findByStoryId(storyId);
    }

    public List<Chapter> getAllChaptersByStorySlug(String slug){
        if(!storyRepository.existsBySlug(slug)){
            throw new AppException(ErrorCode.STORY_NOT_FOUND);
        }
        return chapterRepository.findByStorySlug(slug);
    }

    public Chapter getChapterById(String chapterId){
        return chapterRepository.findById(chapterId).orElseThrow(() -> new AppException(ErrorCode.CHAPTER_NOT_FOUND));
    }

    public Chapter createChapter(ChapterCreationRequest request){
        Optional<Story> storyOptional = storyRepository.findById(request.getStoryId());
        if(storyOptional.isEmpty()){
            throw new AppException(ErrorCode.STORY_NOT_FOUND);
        }

        if(chapterRepository.existsByChapterNumber(request.getChapterNumber())){
            throw new AppException(ErrorCode.CHAPTER_EXISTED);
        }

        Story story = storyOptional.get();
        Chapter chapter = chapterMapper.toChapter(request);
        chapter.setStory(story);
        chapter.setCreatedAt(LocalDateTime.now());
        chapter.setUpdatedAt(LocalDateTime.now());
        story.setUpdatedAt(LocalDateTime.now());
        return chapterRepository.save(chapter);
    }

    public Chapter updateChapter(ChapterUpdatingRequest request, String chapterId){
        Chapter chapter = getChapterById(chapterId);

        chapter.setContent(request.getContent());
        chapter.setChapterNumber(request.getChapterNumber());
        chapter.setUpdatedAt(LocalDateTime.now());
        return chapterRepository.save(chapter);
    }

    public void deleteChapter(String chapterId){
        chapterRepository.deleteById(chapterId);
    }
}
