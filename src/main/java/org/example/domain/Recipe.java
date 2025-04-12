package org.example.domain;

import javax.persistence.*;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import java.util.List;

@Entity
@Table
@Indexed
public class Recipe {

    @Id
    private Integer id;

    @FullTextField
    private String name;

    @ElementCollection
    private List<String> ingredients;

    @ElementCollection
    private List<String> instructions;

    private Integer prepTimeMinutes;
    private Integer cookTimeMinutes;
    private Integer servings;
    private String difficulty;

    @FullTextField
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

    public Recipe() {
    }

    public Recipe(Integer id, String name, List<String> ingredients, List<String> instructions, Integer prepTimeMinutes, Integer cookTimeMinutes, Integer servings, String difficulty, String cuisine, Integer caloriesPerServing, List<String> tags, Integer userId, String image, double rating, Integer reviewCount, List<String> mealType) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.prepTimeMinutes = prepTimeMinutes;
        this.cookTimeMinutes = cookTimeMinutes;
        this.servings = servings;
        this.difficulty = difficulty;
        this.cuisine = cuisine;
        this.caloriesPerServing = caloriesPerServing;
        this.tags = tags;
        this.userId = userId;
        this.image = image;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.mealType = mealType;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public Integer getPrepTimeMinutes() {
        return prepTimeMinutes;
    }

    public Integer getCookTimeMinutes() {
        return cookTimeMinutes;
    }

    public Integer getServings() {
        return servings;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getCuisine() {
        return cuisine;
    }

    public Integer getCaloriesPerServing() {
        return caloriesPerServing;
    }

    public List<String> getTags() {
        return tags;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getImage() {
        return image;
    }

    public double getRating() {
        return rating;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public List<String> getMealType() {
        return mealType;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public void setPrepTimeMinutes(Integer prepTimeMinutes) {
        this.prepTimeMinutes = prepTimeMinutes;
    }

    public void setCookTimeMinutes(Integer cookTimeMinutes) {
        this.cookTimeMinutes = cookTimeMinutes;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public void setCaloriesPerServing(Integer caloriesPerServing) {
        this.caloriesPerServing = caloriesPerServing;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public void setMealType(List<String> mealType) {
        this.mealType = mealType;
    }
}

