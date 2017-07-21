package com.parot.mtecgwa_jr.myrecipes.RecipeData;

/**
 * Created by mtecgwa-jr on 5/29/17.
 */

public class MyRecipe {

    private String recipeName;
    private String recipeImageResource;
    private int recipeId;

    public MyRecipe(String recipeName , String recipeImageResource , int  recipeId)
    {
        this.recipeName = recipeName ;
        this.recipeImageResource = recipeImageResource;
        this.recipeId = recipeId;
    }

    public String getRecipeName()
    {
        return recipeName;
    }

    public String getRecipeImageResource()
    {
        return recipeImageResource;
    }
    public int getRecipeId()
    {
        return recipeId;
    }

}
