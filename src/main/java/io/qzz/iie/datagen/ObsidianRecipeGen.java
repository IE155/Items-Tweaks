package io.qzz.iie.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import java.util.concurrent.CompletableFuture;

public class ObsidianRecipeGen extends FabricRecipeProvider {
    public ObsidianRecipeGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            @Override
            public void generate() {
                createObsidianRecipe(exporter);
            }

            private void createObsidianRecipe(RecipeExporter exporter) {
                // 圆石+黑色染料=黑曜石 (cobblestone+black_dye=obsidian)
                createShapeless(RecipeCategory.MISC, Items.OBSIDIAN)
                        .input(Items.COBBLESTONE)
                        .input(Items.BLACK_DYE)
                        .criterion(hasItem(Items.COBBLESTONE), conditionsFromItem(Items.COBBLESTONE))
                        .criterion(hasItem(Items.BLACK_DYE), conditionsFromItem(Items.BLACK_DYE))
                        .offerTo(exporter, "obsidian_from_cobblestone_and_black_dye");
            }
        };
    }

    @Override
    public String getName() {
        return "Obsidian Recipe Generator";
    }
}