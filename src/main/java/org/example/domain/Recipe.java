package org.example.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "recipes") // Optional: Name for the database table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Recipe {

    @Id
    private Integer id;

    private String name;

    @ElementCollection
    private List<String> ingredients;

    @ElementCollection
    private List<String> instructions;

    private Integer prepTimeMinutes;
    private Integer cookTimeMinutes;
    private Integer servings;
    private String difficulty;
    private String cuisine;
    private Integer caloriesPerServing;

    @ElementCollection
    private List<String> tags;

    private Integer userId;
    private String image;
    private double rating;
    private Integer reviewCount;

    @ElementCollection
    private List<String> mealType;
}

