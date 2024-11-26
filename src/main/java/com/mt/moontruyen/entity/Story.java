package com.mt.moontruyen.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mt.moontruyen.utils.SlugUntils;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stories")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;
    private String description;
    private String image;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany
    @JoinTable(
            name = "story_category",
            joinColumns = @JoinColumn(name = "story_id"),
            inverseJoinColumns = @JoinColumn(name ="category_id")
    )
    private List<Category> categories;

    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"content","createdAt","updatedAt"})
    private List<Chapter> chapters;

    private int views;
    private Boolean isCompleted;
    private String slug;

    @PrePersist
    @PreUpdate
    private void generateSlug(){
        this.slug = SlugUntils.generateSlugFromName(this.title);
    }
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
