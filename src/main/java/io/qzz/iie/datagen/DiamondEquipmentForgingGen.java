package io.qzz.iie.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import java.util.concurrent.CompletableFuture;

public class DiamondEquipmentForgingGen extends FabricRecipeProvider {
    public DiamondEquipmentForgingGen(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(WrapperLookup registries, RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            @Override
            public void generate() {
                createDiamondSwordForgingRecipe(exporter);
                createDiamondPickaxeForgingRecipe(exporter);
                createDiamondAxeForgingRecipe(exporter);
                createDiamondShovelForgingRecipe(exporter);
                createDiamondHoeForgingRecipe(exporter);
            }

            private void createDiamondSwordForgingRecipe(RecipeExporter exporter) {
                // 铁剑+锻造模板+钻石=钻石剑
                SmithingTransformRecipeJsonBuilder.create(
                    Ingredient.ofItems(Items.IRON_SWORD),
                    Ingredient.ofItems(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                    Ingredient.ofItems(Items.DIAMOND),
                    RecipeCategory.COMBAT,
                    Items.DIAMOND_SWORD
                )
                .criterion(hasItem(Items.IRON_SWORD), conditionsFromItem(Items.IRON_SWORD))
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, "diamond_sword_from_iron_sword");
            }

            private void createDiamondPickaxeForgingRecipe(RecipeExporter exporter) {
                // 铁镐+锻造模板+钻石=钻石镐
                SmithingTransformRecipeJsonBuilder.create(
                    Ingredient.ofItems(Items.IRON_PICKAXE),
                    Ingredient.ofItems(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                    Ingredient.ofItems(Items.DIAMOND),
                    RecipeCategory.TOOLS,
                    Items.DIAMOND_PICKAXE
                )
                .criterion(hasItem(Items.IRON_PICKAXE), conditionsFromItem(Items.IRON_PICKAXE))
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, "diamond_pickaxe_from_iron_pickaxe");
            }

            private void createDiamondAxeForgingRecipe(RecipeExporter exporter) {
                // 铁斧+锻造模板+钻石=钻石斧
                SmithingTransformRecipeJsonBuilder.create(
                    Ingredient.ofItems(Items.IRON_AXE),
                    Ingredient.ofItems(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                    Ingredient.ofItems(Items.DIAMOND),
                    RecipeCategory.TOOLS,
                    Items.DIAMOND_AXE
                )
                .criterion(hasItem(Items.IRON_AXE), conditionsFromItem(Items.IRON_AXE))
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, "diamond_axe_from_iron_axe");
            }

            private void createDiamondShovelForgingRecipe(RecipeExporter exporter) {
                // 铁锹+锻造模板+钻石=钻石锹
                SmithingTransformRecipeJsonBuilder.create(
                    Ingredient.ofItems(Items.IRON_SHOVEL),
                    Ingredient.ofItems(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                    Ingredient.ofItems(Items.DIAMOND),
                    RecipeCategory.TOOLS,
                    Items.DIAMOND_SHOVEL
                )
                .criterion(hasItem(Items.IRON_SHOVEL), conditionsFromItem(Items.IRON_SHOVEL))
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, "diamond_shovel_from_iron_shovel");
            }

            private void createDiamondHoeForgingRecipe(RecipeExporter exporter) {
                // 铁锄+锻造模板+钻石=钻石锄
                SmithingTransformRecipeJsonBuilder.create(
                    Ingredient.ofItems(Items.IRON_HOE),
                    Ingredient.ofItems(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                    Ingredient.ofItems(Items.DIAMOND),
                    RecipeCategory.TOOLS,
                    Items.DIAMOND_HOE
                )
                .criterion(hasItem(Items.IRON_HOE), conditionsFromItem(Items.IRON_HOE))
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, "diamond_hoe_from_iron_hoe");
            }
        };
    }

    @Override
    public String getName() {
        return "Diamond Equipment Forging Generator";
    }
}