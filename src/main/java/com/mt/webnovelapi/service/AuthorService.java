package com.mt.webnovelapi.service;

import com.mt.webnovelapi.dto.request.AuthorCreationRequest;
import com.mt.webnovelapi.dto.request.AuthorUpdateRequest;
import com.mt.webnovelapi.entity.Author;
import com.mt.webnovelapi.exception.AppException;
import com.mt.webnovelapi.exception.ErrorCode;
import com.mt.webnovelapi.mapper.AuthorMapper;
import com.mt.webnovelapi.repository.AuthorRepository;
import com.mt.webnovelapi.repository.StoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorService {
    AuthorRepository authorRepository;

    AuthorMapper authorMapper;

    StoryRepository storyRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(String authorId) {
        return authorRepository.findById(authorId).orElseThrow(() -> new AppException(ErrorCode.AUTHOR_NOT_FOUND));
    }

    public Author getAuthorBySlug(String slug) {
        return authorRepository.findBySlug(slug).orElseThrow(() -> new AppException(ErrorCode.AUTHOR_NOT_FOUND));
    }

    public Author createAuthor(AuthorCreationRequest request){
       if(authorRepository.existsByName(request.getName())){
           throw new AppException(ErrorCode.AUTHOR_EXISTED);
       }
       Author author = authorMapper.toAuthor(request);
       return authorRepository.save(author);
    }

    public Author updateAuthor(String authorId,AuthorUpdateRequest request){
        Author author = getAuthorById(authorId);
        authorMapper.toUpdateAuthor(request, author);
        return authorRepository.save(author);
    }

    public void deleteAuthor(String authorId){
        storyRepository.deleteById(authorId);
    }
}
