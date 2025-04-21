
package org.example.dto;

import java.util.List;

public class RecipeResponseDto {
    private List<RecipeDto> recipes;
    private int total;
    private int skip;
    private int limit;

    public List<RecipeDto> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeDto> recipes) {
        this.recipes = recipes;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public RecipeResponseDto(List<RecipeDto> recipes, int total, int skip, int limit) {
        this.recipes = recipes;
        this.total = total;
        this.skip = skip;
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public RecipeResponseDto() {
    }
}
