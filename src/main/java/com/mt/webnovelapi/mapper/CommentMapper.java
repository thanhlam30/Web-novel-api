package com.mt.webnovelapi.mapper;

import com.mt.webnovelapi.dto.response.CommentResponse;
import com.mt.webnovelapi.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "username", expression = "java(mapUsername(comment))")
    @Mapping(target = "parentCommentId", expression = "java(mapParentCommentId(comment))")
    @Mapping(target = "replies", expression = "java(mapReplies(comment.getReplies()))")
    CommentResponse toCommentResponse(Comment comment);

    default String mapUsername(Comment comment) {
        if (comment.getUser() != null) {
            return comment.getUser().getUsername();
        }
        return null;
    }
    default String mapParentCommentId(Comment comment) {
        if (comment.getParentComment() != null) {
            return comment.getParentComment().getId();
        }
        return null;
    }

    default List<CommentResponse> mapReplies(List<Comment> replies) {
        return replies != null ? replies.stream().map(this::toCommentResponse).toList() : null;
    }
}
