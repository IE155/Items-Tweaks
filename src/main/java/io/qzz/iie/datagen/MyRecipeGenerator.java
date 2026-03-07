package io.qzz.iie.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
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
                // 本类现在只是一个占位符，实际配方由独立的生成器类处理
                // 具体配方在 EnderEyeRecipeGenerator, LeafLitterPickaxeRecipeGenerator, LeafLitterToPlanksRecipeGenerator 中定义
            }
        };
    }

    @Override
    public String getName() {
        return "Items Tweaks Recipes Placeholder";
    }
}