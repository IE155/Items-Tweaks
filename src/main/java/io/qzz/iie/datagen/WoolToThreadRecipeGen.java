package io.qzz.iie.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import java.util.concurrent.CompletableFuture;

public class WoolToThreadRecipeGen extends FabricRecipeProvider {
    public WoolToThreadRecipeGen(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(WrapperLookup registries, RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            @Override
            public void generate() {
                // 羊毛分解为线的配方
                createWoolToThreadRecipe(exporter);
            }

            private void createWoolToThreadRecipe(RecipeExporter exporter) {
                createShaped(RecipeCategory.MISC, Items.STRING, 4)
                    .input('#', Items.WHITE_WOOL)
                    .pattern("#")
                    .criterion(hasItem(Items.WHITE_WOOL), conditionsFromItem(Items.WHITE_WOOL))
                    .offerTo(exporter, "string_from_white_wool");
                
                // 为其他颜色的羊毛也添加配方
                addWoolToThreadRecipe(exporter, Items.ORANGE_WOOL, "string_from_orange_wool");
                addWoolToThreadRecipe(exporter, Items.MAGENTA_WOOL, "string_from_magenta_wool");
                addWoolToThreadRecipe(exporter, Items.LIGHT_BLUE_WOOL, "string_from_light_blue_wool");
                addWoolToThreadRecipe(exporter, Items.YELLOW_WOOL, "string_from_yellow_wool");
                addWoolToThreadRecipe(exporter, Items.LIME_WOOL, "string_from_lime_wool");
                addWoolToThreadRecipe(exporter, Items.PINK_WOOL, "string_from_pink_wool");
                addWoolToThreadRecipe(exporter, Items.GRAY_WOOL, "string_from_gray_wool");
                addWoolToThreadRecipe(exporter, Items.LIGHT_GRAY_WOOL, "string_from_light_gray_wool");
                addWoolToThreadRecipe(exporter, Items.CYAN_WOOL, "string_from_cyan_wool");
                addWoolToThreadRecipe(exporter, Items.PURPLE_WOOL, "string_from_purple_wool");
                addWoolToThreadRecipe(exporter, Items.BLUE_WOOL, "string_from_blue_wool");
                addWoolToThreadRecipe(exporter, Items.BROWN_WOOL, "string_from_brown_wool");
                addWoolToThreadRecipe(exporter, Items.GREEN_WOOL, "string_from_green_wool");
                addWoolToThreadRecipe(exporter, Items.RED_WOOL, "string_from_red_wool");
                addWoolToThreadRecipe(exporter, Items.BLACK_WOOL, "string_from_black_wool");
            }

            private void addWoolToThreadRecipe(RecipeExporter exporter, net.minecraft.item.Item woolItem, String recipeId) {
                createShaped(RecipeCategory.MISC, Items.STRING, 4)
                    .input('#', woolItem)
                    .pattern("#")
                    .criterion(hasItem(woolItem), conditionsFromItem(woolItem))
                    .offerTo(exporter, recipeId);
            }
        };
    }

    @Override
    public String getName() {
        return "Wool to Thread Recipe Generator";
    }
}