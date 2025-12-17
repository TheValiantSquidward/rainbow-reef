package net.thevaliantsquidward.rainbowreef.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.*;
import net.minecraftforge.registries.ForgeRegistries;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.registry.tags.ReefItemTags;

import java.util.function.Consumer;

import static net.minecraft.data.recipes.RecipeCategory.*;
import static net.thevaliantsquidward.rainbowreef.registry.ReefBlocks.*;
import static net.thevaliantsquidward.rainbowreef.registry.ReefItems.*;

public class ReefRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ReefRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(FOOD, ANGELFISH_CAKE.get()).define('A', Items.MILK_BUCKET).define('B', Items.SUGAR).define('C', Tags.Items.CROPS_WHEAT).define('D', RAW_ANGELFISH.get()).pattern("DDD").pattern("BAB").pattern("CCC").unlockedBy("has_raw_angelfish", has(RAW_ANGELFISH.get())).save(consumer);
        ShapedRecipeBuilder.shaped(FOOD, BASSLET_COOKIE.get(), 8).define('A', RAW_BASSLET.get()).define('B', Tags.Items.CROPS_WHEAT).pattern("BAB").unlockedBy("has_raw_basslet", has(RAW_BASSLET.get())).save(consumer);
        ShapedRecipeBuilder.shaped(FOOD, BOXFISH_BREAD.get()).define('A', RAW_BOXFISH.get()).define('B', Tags.Items.CROPS_WHEAT).define('C', Items.SUGAR).pattern("BBB").pattern("CAC").pattern("BBB").unlockedBy("has_raw_boxfish", has(RAW_BOXFISH.get())).save(consumer);
        ShapedRecipeBuilder.shaped(FOOD, BUTTERED_TOAST.get()).define('A', RAW_BUTTERFLYFISH.get()).define('B', Items.BREAD).define('C', Items.MILK_BUCKET).pattern("A").pattern("C").pattern("B").unlockedBy("has_raw_butterflyfish", has(RAW_BUTTERFLYFISH.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(FOOD, CLOWNFISH_CUPCAKE.get()).requires(RAW_CLOWNFISH.get()).requires(Tags.Items.CROPS_WHEAT).requires(Tags.Items.EGGS).requires(Items.SUGAR).requires(Items.MILK_BUCKET).unlockedBy("has_raw_clownfish", has(RAW_CLOWNFISH.get())).save(consumer);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ReefItemTags.RAINBOW_REEF_CRABS), FOOD, ROASTED_CRAB.get(), 0.35F, 200).unlockedBy("has_raw_crab", has(ReefItemTags.RAINBOW_REEF_CRABS)).save(consumer);
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ReefItemTags.RAINBOW_REEF_CRABS), FOOD, ROASTED_CRAB.get(), 0.35F, 600).unlockedBy("has_raw_crab", has(ReefItemTags.RAINBOW_REEF_CRABS)).save(consumer, getSaveLocation(getName(ROASTED_CRAB.get()) + "_from_campfire_cooking"));
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ReefItemTags.RAINBOW_REEF_CRABS), FOOD, ROASTED_CRAB.get(), 0.35F, 100).unlockedBy("has_raw_crab", has(ReefItemTags.RAINBOW_REEF_CRABS)).save(consumer, getSaveLocation(getName(ROASTED_CRAB.get()) + "_from_smoking"));
        ShapelessRecipeBuilder.shapeless(FOOD, CRAB_CAKE.get()).requires(ReefItemTags.CRABS).requires(ReefItemTags.CRABS).requires(Items.BREAD).requires(Tags.Items.EGGS).unlockedBy("has_raw_crab", has(ReefItemTags.CRABS)).save(consumer);
        ShapelessRecipeBuilder.shapeless(FOOD, DWARF_ANGELFISH_TART.get()).requires(RAW_DWARF_ANGELFISH.get()).requires(Items.SUGAR).requires(Tags.Items.CROPS_WHEAT).requires(Tags.Items.EGGS).unlockedBy("has_raw_dwarf_angelfish", has(RAW_DWARF_ANGELFISH.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(FOOD, GOBY_GUMMY.get()).requires(RAW_GOBY.get()).requires(Items.SUGAR).requires(Tags.Items.SLIMEBALLS).unlockedBy("has_raw_goby", has(RAW_GOBY.get())).save(consumer);
        conditionalRecipe(ShapelessRecipeBuilder.shapeless(FOOD, HOGFISH_BACON.get(), 2).requires(RAW_HOGFISH.get()).unlockedBy("has_raw_hogfish", has(RAW_HOGFISH.get())), new NotCondition(new ModLoadedCondition("farmersdelight")), consumer, getSaveLocation("hogfish_bacon"));
        cooking(HOGFISH_BACON.get(), COOKED_HOGFISH_BACON.get(), consumer);
        ShapedRecipeBuilder.shaped(FOOD, ULTRA_BACON_SANDWICH.get()).define('A', COOKED_HOGFISH_BACON.get()).define('B', Items.BREAD).pattern("BBB").pattern("AAA").pattern("BBB").unlockedBy("has_cooked_hogfish_bacon", has(COOKED_HOGFISH_BACON.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(FOOD, JELLY_BOTTLE.get()).requires(GLOB_OF_JELLY.get()).requires(GLOB_OF_JELLY.get()).requires(Items.SUGAR).requires(Items.GLASS_BOTTLE).unlockedBy("has_glob_of_jelly", has(GLOB_OF_JELLY.get())).save(consumer);
        ShapedRecipeBuilder.shaped(FOOD, JELLY_SANDWICH.get()).define('A', JELLY_BOTTLE.get()).define('B', Items.BREAD).pattern("B").pattern("A").pattern("B").unlockedBy("has_jelly_bottle", has(JELLY_BOTTLE.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(FOOD, JELLY_TART.get()).requires(JELLY_BOTTLE.get()).requires(Tags.Items.CROPS_WHEAT).requires(Tags.Items.EGGS).unlockedBy("has_jelly_bottle", has(JELLY_BOTTLE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(FOOD, IDOL_COOKIE.get(), 8).define('A', RAW_MOORISH_IDOL.get()).define('B', Tags.Items.CROPS_WHEAT).pattern("BAB").unlockedBy("has_raw_moorish_idol", has(RAW_MOORISH_IDOL.get())).save(consumer);
    }

    private static void conditionalRecipe(RecipeBuilder recipe, ICondition condition, Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        ConditionalRecipe.builder().addCondition(condition).addRecipe(consumer1 -> recipe.save(consumer1, id)).generateAdvancement(new ResourceLocation(id.getNamespace(), "recipes/" + id.getPath())).build(consumer, id);
    }

    private static void cooking(ItemLike ingredient, ItemLike result, Consumer<FinishedRecipe> consumer) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), FOOD, result, 0.35F, 200).unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, getSaveLocation(result));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), FOOD, result, 0.35F, 600).unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, getSaveLocation(getName(result) + "_from_campfire_cooking"));
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), FOOD, result, 0.35F, 100).unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, getSaveLocation(getName(result) + "_from_smoking"));
    }

    private static String getName(ItemLike object) {
        return ForgeRegistries.ITEMS.getKey(object.asItem()).getPath();
    }

    private static ResourceLocation getSaveLocation(ItemLike item) {
        return ForgeRegistries.ITEMS.getKey(item.asItem());
    }

    private static ResourceLocation getSaveLocation(String name) {
        return RainbowReef.modPrefix(name);
    }
}

