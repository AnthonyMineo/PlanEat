package com.denma.planeat;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.denma.planeat.models.database.PlaneatDB;
import com.denma.planeat.models.local.FoodMenu;
import com.denma.planeat.models.local.Meal;
import com.denma.planeat.models.remote.Digest;
import com.denma.planeat.models.remote.Ingredient;
import com.denma.planeat.models.remote.Recipe;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class FoodMenuDaoTest {

    // --- FOR DATA ---
    private PlaneatDB database;
    private static Recipe recipe;
    private static Meal meal;
    private static FoodMenu foodMenu;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                PlaneatDB.class)
                .allowMainThreadQueries()
                .build();
        this.configureTestModels();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    // --- DATA SET FOR TEST ---
    private void configureTestModels(){
        // Recipe setting
        recipe = new Recipe();
        recipe.setLabel("Recette test");
        recipe.setImage("https://www.danstontest.com/image_test");
        recipe.setUrlSource("https://www.danstontest.com/recette_test");

        List<String> dietLabels = new ArrayList<>();
        dietLabels.add("diet_test_1");
        recipe.setDietLabels(dietLabels);

        List<String> healthLabels = new ArrayList<>();
        healthLabels.add("health_test_1");
        recipe.setHealthLabels(healthLabels);

        List<String> ingredientLines = new ArrayList<>();
        ingredientLines.add("ingredient_1");
        recipe.setIngredientLines(ingredientLines);

        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setText("apple");
        ingredient1.setWeight(1.0);
        ingredients.add(ingredient1);
        recipe.setIngredients(ingredients);

        recipe.setCalories(1000.0);
        recipe.setTotalWeight(100.0);
        recipe.setTotalTime(10.0);

        List<Digest> digests = new ArrayList<>();
        Digest digest1 = new Digest();
        digest1.setLabel("FAT");
        digest1.setTotal(10.0);
        digest1.setUnit("gr");
        digests.add(digest1);
        recipe.setDigest(digests);

        // Meal setting
        meal = new Meal();
        meal.setRecipe(recipe);
        meal.setDayTiming(1);
        List<Meal> meals = new ArrayList<>();
        meals.add(meal);

        // FoodMenu setting
        foodMenu = new FoodMenu(meals, 20190218, "lun18");
    }

    @Test
    public void getMenuWhenNoMenuInserted() throws InterruptedException {
        FoodMenu test =  LiveDataTestUtil.getValue(this.database.menuDao().getMenuByDate(20190218));
        assertNull(test);
    }

    @Test
    public void insertAndGetMenu() throws InterruptedException {
        this.database.menuDao().insertMenu(foodMenu);
        FoodMenu test =  LiveDataTestUtil.getValue(this.database.menuDao().getMenuByDate(20190218));
        assertEquals(test.getEatingDate(), (foodMenu.getEatingDate()));
        assertEquals(test.getEatingDateString(), foodMenu.getEatingDateString());
        assertEquals(test.getMealList().get(0).getDayTiming(), foodMenu.getMealList().get(0).getDayTiming());
        assertEquals(test.getMealList().get(0).getRecipe().getLabel(), foodMenu.getMealList().get(0).getRecipe().getLabel());
    }

    @Test
    public void insertAndUpdate() throws InterruptedException {
        this.database.menuDao().insertMenu(foodMenu);
        FoodMenu test =  LiveDataTestUtil.getValue(this.database.menuDao().getMenuByDate(20190218));
        test.setEatingDateString("mar19");
        this.database.menuDao().updateMenu(test);

        FoodMenu test2 =  LiveDataTestUtil.getValue(this.database.menuDao().getMenuByDate(20190218));
        assertEquals("mar19", test2.getEatingDateString());
    }

    @Test
    public void insertAndDelete() throws InterruptedException {
        this.database.menuDao().insertMenu(foodMenu);
        this.database.menuDao().deleteMenu(20190218);
        FoodMenu test =  LiveDataTestUtil.getValue(this.database.menuDao().getMenuByDate(20190218));
        assertNull(test);
    }




}
