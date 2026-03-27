package io.qzz.iie.datagen;

import io.qzz.iie.ItemsTweaks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import java.util.concurrent.CompletableFuture;

public class LeafLitterSwordRecipeGenerator extends FabricRecipeProvider {
    public LeafLitterSwordRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            @Override
            public void generate() {
                createLeafLitterSwordRecipe(exporter);
            }

            private void createLeafLitterSwordRecipe(RecipeExporter exporter) {
                createShaped(RecipeCategory.COMBAT, ItemsTweaks.LEAF_LITTER_SWORD)
                        .pattern(" L ")
                        .pattern(" L ")
                        .pattern(" S ")
                        .input('L', Items.LEAF_LITTER)
                        .input('S', Items.STICK)
                        .criterion(hasItem(Items.LEAF_LITTER), conditionsFromItem(Items.LEAF_LITTER))
                        .offerTo(exporter, "leaf_litter_sword");
            }
        };
    }

    @Override
    public String getName() {
        return "Leaf Litter Sword Recipe Generator";
    }
}
