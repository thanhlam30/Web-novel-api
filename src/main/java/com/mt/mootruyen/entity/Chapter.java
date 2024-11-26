package com.mt.mootruyen.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chapters")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Lob
    private String content;
    private int chapterNumber;

    @ManyToOne
    @JoinColumn(name = "story_id")
    @JsonIgnore
    private Story story;


    private String slug;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    private void generateSlug(){
        this.slug = "chuong" + this.chapterNumber;
    }
}
