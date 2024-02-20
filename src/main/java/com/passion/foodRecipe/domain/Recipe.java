package com.passion.foodRecipe.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recipeId;
    private String recipeTitle;
    @ManyToOne
    private User user;
    private String recipeImage;
    private String recipeDescription;
    private boolean vegeterian;
    private LocalDateTime recipeCreatedAt;
    private List<Long> likes= new ArrayList<>();

}
