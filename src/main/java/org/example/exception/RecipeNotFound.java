package org.example.exception;

public class RecipeNotFound extends RuntimeException {
    public RecipeNotFound(Integer id) {
        super("No recipe with the id " + id);
    }
}
