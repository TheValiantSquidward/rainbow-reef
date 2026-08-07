package com.valiantenvoy.rainbow_reef.datagen;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.registry.ReefBlocks;
import com.valiantenvoy.rainbow_reef.tags.ReefItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.conditions.NotCondition;

import java.util.concurrent.CompletableFuture;

import static com.valiantenvoy.rainbow_reef.registry.ReefItems.*;

public class ReefRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ReefRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ReefBlocks.ANGELFISH_CAKE.get()).define('A', Items.MILK_BUCKET).define('B', Items.SUGAR).define('C', Tags.Items.CROPS_WHEAT).define('D', RAW_ANGELFISH.get()).pattern("DDD").pattern("BAB").pattern("CCC").unlockedBy("has_raw_angelfish", has(RAW_ANGELFISH.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, BASSLET_COOKIE.get(), 8).define('A', RAW_BASSLET.get()).define('B', Tags.Items.CROPS_WHEAT).pattern("BAB").unlockedBy("has_raw_basslet", has(RAW_BASSLET.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, BOXFISH_BREAD.get()).define('A', RAW_BOXFISH.get()).define('B', Tags.Items.CROPS_WHEAT).define('C', Items.SUGAR).pattern("BBB").pattern("CAC").pattern("BBB").unlockedBy("has_raw_boxfish", has(RAW_BOXFISH.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, BUTTERED_TOAST.get()).define('A', RAW_BUTTERFLYFISH.get()).define('B', Items.BREAD).define('C', Items.MILK_BUCKET).pattern("A").pattern("C").pattern("B").unlockedBy("has_raw_butterflyfish", has(RAW_BUTTERFLYFISH.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CLOWNFISH_CUPCAKE.get()).requires(RAW_CLOWNFISH.get()).requires(Tags.Items.CROPS_WHEAT).requires(Tags.Items.EGGS).requires(Items.SUGAR).requires(Items.MILK_BUCKET).unlockedBy("has_raw_clownfish", has(RAW_CLOWNFISH.get())).save(consumer);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ReefItemTags.RAINBOW_REEF_CRABS), RecipeCategory.FOOD, ROASTED_CRAB.get(), 0.35F, 200).unlockedBy("has_raw_crab", has(ReefItemTags.RAINBOW_REEF_CRABS)).save(consumer);
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ReefItemTags.RAINBOW_REEF_CRABS), RecipeCategory.FOOD, ROASTED_CRAB.get(), 0.35F, 600).unlockedBy("has_raw_crab", has(ReefItemTags.RAINBOW_REEF_CRABS)).save(consumer, getSaveLocation(getName(ROASTED_CRAB.get()) + "_from_campfire_cooking"));
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ReefItemTags.RAINBOW_REEF_CRABS), RecipeCategory.FOOD, ROASTED_CRAB.get(), 0.35F, 100).unlockedBy("has_raw_crab", has(ReefItemTags.RAINBOW_REEF_CRABS)).save(consumer, getSaveLocation(getName(ROASTED_CRAB.get()) + "_from_smoking"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, CRAB_CAKE.get()).requires(ReefItemTags.CRABS).requires(ReefItemTags.CRABS).requires(Items.BREAD).requires(Tags.Items.EGGS).unlockedBy("has_raw_crab", has(ReefItemTags.CRABS)).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DWARF_ANGELFISH_TART.get()).requires(RAW_DWARF_ANGELFISH.get()).requires(Items.SUGAR).requires(Tags.Items.CROPS_WHEAT).requires(Tags.Items.EGGS).unlockedBy("has_raw_dwarf_angelfish", has(RAW_DWARF_ANGELFISH.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GOBY_GUMMY.get()).requires(RAW_GOBY.get()).requires(Items.SUGAR).requires(Tags.Items.SLIME_BALLS).unlockedBy("has_raw_goby", has(RAW_GOBY.get())).save(consumer);
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HOGFISH_BACON.get(), 2).requires(RAW_HOGFISH.get()).unlockedBy("has_raw_hogfish", has(RAW_HOGFISH.get())), new NotCondition(new ModLoadedCondition("farmersdelight")), consumer, getSaveLocation("hogfish_bacon"));
        foodCookingRecipes(consumer, HOGFISH_BACON.get(), COOKED_HOGFISH_BACON.get());
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ULTRA_BACON_SANDWICH.get()).define('A', COOKED_HOGFISH_BACON.get()).define('B', Items.BREAD).pattern("BBB").pattern("AAA").pattern("BBB").unlockedBy("has_cooked_hogfish_bacon", has(COOKED_HOGFISH_BACON.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, JELLY_BOTTLE.get()).requires(GLOB_OF_JELLY.get()).requires(GLOB_OF_JELLY.get()).requires(Items.SUGAR).requires(Items.GLASS_BOTTLE).unlockedBy("has_glob_of_jelly", has(GLOB_OF_JELLY.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, JELLY_SANDWICH.get()).define('A', JELLY_BOTTLE.get()).define('B', Items.BREAD).pattern("B").pattern("A").pattern("B").unlockedBy("has_jelly_bottle", has(JELLY_BOTTLE.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, JELLY_TART.get()).requires(JELLY_BOTTLE.get()).requires(Tags.Items.CROPS_WHEAT).requires(Tags.Items.EGGS).unlockedBy("has_jelly_bottle", has(JELLY_BOTTLE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, IDOL_COOKIE.get(), 8).define('A', RAW_MOORISH_IDOL.get()).define('B', Tags.Items.CROPS_WHEAT).pattern("BAB").unlockedBy("has_raw_moorish_idol", has(RAW_MOORISH_IDOL.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ReefBlocks.CORALSTONE_SLAB.get(), 6).define('C', ReefBlocks.CORALSTONE.get()).pattern("CCC").unlockedBy("has_coralstone", has(ReefBlocks.CORALSTONE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ReefBlocks.CORALSTONE_STAIRS.get(), 4).define('C', ReefBlocks.CORALSTONE.get()).pattern("C  ").pattern("CC ").pattern("CCC").unlockedBy("has_coralstone", has(ReefBlocks.CORALSTONE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ReefBlocks.CORALSTONE_WALL.get(), 6).define('C', ReefBlocks.CORALSTONE.get()).pattern("CCC").pattern("CCC").unlockedBy("has_coralstone", has(ReefBlocks.CORALSTONE.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ReefBlocks.CORALSTONE_BRICKS.get(), 4).define('C', ReefBlocks.POLISHED_CORALSTONE.get()).pattern("CC").pattern("CC").unlockedBy("has_coralstone", has(ReefBlocks.CORALSTONE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ReefBlocks.CORALSTONE_BRICK_SLAB.get(), 6).define('C', ReefBlocks.CORALSTONE_BRICKS.get()).pattern("CCC").unlockedBy("has_coralstone", has(ReefBlocks.CORALSTONE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ReefBlocks.CORALSTONE_BRICK_STAIRS.get(), 4).define('C', ReefBlocks.CORALSTONE_BRICKS.get()).pattern("C  ").pattern("CC ").pattern("CCC").unlockedBy("has_coralstone", has(ReefBlocks.CORALSTONE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ReefBlocks.CORALSTONE_BRICK_WALL.get(), 6).define('C', ReefBlocks.CORALSTONE_BRICKS.get()).pattern("CCC").pattern("CCC").unlockedBy("has_coralstone", has(ReefBlocks.CORALSTONE.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ReefBlocks.POLISHED_CORALSTONE.get(), 4).define('C', ReefBlocks.CORALSTONE.get()).pattern("CC").pattern("CC").unlockedBy("has_coralstone", has(ReefBlocks.CORALSTONE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ReefBlocks.POLISHED_CORALSTONE_SLAB.get(), 6).define('C', ReefBlocks.POLISHED_CORALSTONE.get()).pattern("CCC").unlockedBy("has_coralstone", has(ReefBlocks.CORALSTONE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ReefBlocks.POLISHED_CORALSTONE_STAIRS.get(), 4).define('C', ReefBlocks.POLISHED_CORALSTONE.get()).pattern("C  ").pattern("CC ").pattern("CCC").unlockedBy("has_coralstone", has(ReefBlocks.CORALSTONE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ReefBlocks.POLISHED_CORALSTONE_WALL.get(), 6).define('C', ReefBlocks.POLISHED_CORALSTONE.get()).pattern("CCC").pattern("CCC").unlockedBy("has_coralstone", has(ReefBlocks.CORALSTONE.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ReefBlocks.CHISELED_CORALSTONE.get(), 1).define('C', ReefBlocks.CORALSTONE_SLAB.get()).pattern("C").pattern("C").unlockedBy("has_coralstone", has(ReefBlocks.CORALSTONE.get())).save(consumer);
    }

    private static void conditionalRecipe(RecipeBuilder recipe, ICondition condition, RecipeOutput consumer, ResourceLocation id) {
        recipe.save(consumer.withConditions(condition), id);
    }

    public static void foodCookingRecipes(RecipeOutput recipeOutput, ItemLike input, ItemLike output) {
        foodCookingRecipes(recipeOutput, input, output, 0.35F, 200);
    }

    public static void foodCookingRecipes(RecipeOutput recipeOutput, ItemLike input, ItemLike output, float xp, int baseCookTime) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.FOOD, output, xp, baseCookTime).unlockedBy(getHasName(input), has(input)).save(recipeOutput);
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(input), RecipeCategory.FOOD, output, xp, baseCookTime / 2).unlockedBy(getHasName(input), has(input)).save(recipeOutput, RecipeBuilder.getDefaultRecipeId(output) + "_from_smoking");
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(input), RecipeCategory.FOOD, output, xp, baseCookTime * 3).unlockedBy(getHasName(input), has(input)).save(recipeOutput, RecipeBuilder.getDefaultRecipeId(output) + "_from_campfire_cooking");
    }

    private static String getName(ItemLike object) {
        return BuiltInRegistries.ITEM.getKey(object.asItem()).getPath();
    }

    private static ResourceLocation getSaveLocation(ItemLike item) {
        return BuiltInRegistries.ITEM.getKey(item.asItem());
    }

    private static ResourceLocation getSaveLocation(String name) {
        return RainbowReef.location(name);
    }
}

