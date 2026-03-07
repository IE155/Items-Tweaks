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

public class LeafLitterPickaxeRecipeGenerator extends FabricRecipeProvider {
    public LeafLitterPickaxeRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            @Override
            public void generate() {
                createLeafLitterPickaxeRecipe(exporter);
            }

            private void createLeafLitterPickaxeRecipe(RecipeExporter exporter) {
                createShaped(RecipeCategory.TOOLS, ItemsTweaks.LEAF_LITTER_PICKAXE)
                        .pattern("LLL")
                        .pattern(" S ")
                        .pattern(" S ")
                        .input('L', Items.LEAF_LITTER)
                        .input('S', Items.STICK)
                        .criterion(hasItem(Items.LEAF_LITTER), conditionsFromItem(Items.LEAF_LITTER))
                        .offerTo(exporter, "leaf_litter_pickaxe");
            }
        };
    }

    @Override
    public String getName() {
        return "Leaf Litter Pickaxe Recipe Generator";
    }
}