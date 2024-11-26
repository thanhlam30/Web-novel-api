package com.mt.mootruyen.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mt.mootruyen.utils.SlugUntils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private String bio;
    private String avatar;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Story> stories;
    private String slug;

    @PrePersist
    @PreUpdate
    private void generateSlug(){
        this.slug = SlugUntils.generateSlugFromName(this.name);
    }
}
