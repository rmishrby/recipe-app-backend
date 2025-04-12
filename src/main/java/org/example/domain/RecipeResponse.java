package org.example.domain;

import java.util.List;

public class RecipeResponse {
    private List<Recipe> recipes;
    private int total;
    private int skip;
    private int limit;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
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

    public RecipeResponse(List<Recipe> recipes, int total, int skip, int limit) {
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

    public RecipeResponse() {
    }
}
