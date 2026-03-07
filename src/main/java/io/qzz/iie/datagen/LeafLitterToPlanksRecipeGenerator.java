package io.qzz.iie.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import java.util.concurrent.CompletableFuture;

public class LeafLitterToPlanksRecipeGenerator extends FabricRecipeProvider {
    public LeafLitterToPlanksRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            @Override
            public void generate() {
                createLeafLitterToPlanksRecipe(exporter);
            }

            private void createLeafLitterToPlanksRecipe(RecipeExporter exporter) {
                createShaped(RecipeCategory.BUILDING_BLOCKS, Items.OAK_PLANKS)
                        .pattern("LL")
                        .pattern("LL")
                        .input('L', Items.LEAF_LITTER)
                        .criterion(hasItem(Items.LEAF_LITTER), conditionsFromItem(Items.LEAF_LITTER))
                        .offerTo(exporter, "oak_planks_from_leaf_litter");
            }
        };
    }

    @Override
    public String getName() {
        return "Leaf Litter to Planks Recipe Generator";
    }
}