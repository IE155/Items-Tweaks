package io.qzz.iie.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import java.util.concurrent.CompletableFuture;

public class MyRecipeGenerator extends FabricRecipeProvider {
    public MyRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {

        return new RecipeGenerator(registries, exporter) {
            @Override
            public void generate() {
                createShapeless(RecipeCategory.MISC, Items.ENDER_EYE, 2)
                        .input(Items.ENDER_PEARL)
                        .input(Items.BLAZE_ROD)
                        .criterion(hasItem(Items.ENDER_PEARL), conditionsFromItem(Items.ENDER_PEARL))
                        .criterion(hasItem(Items.BLAZE_ROD), conditionsFromItem(Items.BLAZE_ROD))
                        // 明确指定文件名，不要留空
                        .offerTo(exporter, "ender_eye_from_pearl_and_rod");
            }
        };
    }

    @Override
    public String getName() {
        return "Items Tweaks Recipes";
    }
}