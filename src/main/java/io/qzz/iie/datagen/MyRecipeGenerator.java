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
// 在 MyRecipeGenerator 的 generate() 方法里
// 2. 落叶镐
                createShaped(RecipeCategory.TOOLS, ItemsTweaks.LEAF_LITTER_PICKAXE)
                        .pattern("LLL")
                        .pattern(" S ")
                        .pattern(" S ")
                        .input('L', Items.LEAF_LITTER)
                        .input('S', Items.STICK)
                        .criterion(hasItem(Items.LEAF_LITTER), conditionsFromItem(Items.LEAF_LITTER))
                        .offerTo(exporter, "leaf_litter_pickaxe");
                // 3. 新增：落叶合成橡木木板 (2x2 落叶)
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
        return "Items Tweaks Recipes";
    }
}