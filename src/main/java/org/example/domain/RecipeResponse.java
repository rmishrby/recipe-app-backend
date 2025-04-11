package org.example.domain;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RecipeResponse {
    private List<Recipe> recipes;
    private int total;
    private int skip;
    private int limit;
}
