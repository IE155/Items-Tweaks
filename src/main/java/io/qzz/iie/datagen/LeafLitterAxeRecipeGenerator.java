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

public class LeafLitterAxeRecipeGenerator extends FabricRecipeProvider {
    public LeafLitterAxeRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            @Override
            public void generate() {
                createLeafLitterAxeRecipe(exporter);
            }

            private void createLeafLitterAxeRecipe(RecipeExporter exporter) {
                createShaped(RecipeCategory.TOOLS, ItemsTweaks.LEAF_LITTER_AXE)
                        .pattern("LL ")
                        .pattern("LS ")
                        .pattern(" S ")
                        .input('L', Items.LEAF_LITTER)
                        .input('S', Items.STICK)
                        .criterion(hasItem(Items.LEAF_LITTER), conditionsFromItem(Items.LEAF_LITTER))
                        .offerTo(exporter, "leaf_litter_axe");
            }
        };
    }

    @Override
    public String getName() {
        return "Leaf Litter Axe Recipe Generator";
    }
}
