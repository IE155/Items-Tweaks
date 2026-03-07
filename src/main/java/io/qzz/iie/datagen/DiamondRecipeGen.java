package io.qzz.iie.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import java.util.concurrent.CompletableFuture;

public class DiamondRecipeGen extends FabricRecipeProvider {
    public DiamondRecipeGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            @Override
            public void generate() {
                createDiamondRecipe(exporter);
            }

            private void createDiamondRecipe(RecipeExporter exporter) {
                // 青金石+淡蓝色染料=钻石 (lapis_lazuli+light_blue_dye=diamond)
                createShapeless(RecipeCategory.MISC, Items.DIAMOND)
                        .input(Items.LAPIS_LAZULI)
                        .input(Items.LIGHT_BLUE_DYE)
                        .criterion(hasItem(Items.LAPIS_LAZULI), conditionsFromItem(Items.LAPIS_LAZULI))
                        .criterion(hasItem(Items.LIGHT_BLUE_DYE), conditionsFromItem(Items.LIGHT_BLUE_DYE))
                        .offerTo(exporter, "diamond_from_lapis_lazuli_and_light_blue_dye");
            }
        };
    }

    @Override
    public String getName() {
        return "Diamond Recipe Generator";
    }
}