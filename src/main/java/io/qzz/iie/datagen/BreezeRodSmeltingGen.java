package io.qzz.iie.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BreezeRodSmeltingGen extends FabricRecipeProvider {
    public BreezeRodSmeltingGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            @Override
            public void generate() {
                createBreezeRodSmeltingRecipe(exporter);
            }

            private void createBreezeRodSmeltingRecipe(RecipeExporter exporter) {
                // 旋风棒熔炼成烈焰棒
                // 熔炼时间: 200 tick (10秒), 经验: 0.7
                CookingRecipeJsonBuilder.createSmelting(
                    Ingredient.ofItems(Items.BREEZE_ROD),
                    RecipeCategory.MISC,
                    Items.BLAZE_ROD,
                    0.7F,
                    200
                )
                .criterion(hasItem(Items.BREEZE_ROD), conditionsFromItem(Items.BREEZE_ROD))
                .offerTo(exporter, "breeze_rod_to_blaze_rod");
            }
        };
    }

    @Override
    public String getName() {
        return "Breeze Rod Smelting Recipe Generator";
    }
}
