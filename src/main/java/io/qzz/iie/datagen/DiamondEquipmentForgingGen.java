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
                createDiamondHelmetForgingRecipe(exporter);
                createDiamondChestplateForgingRecipe(exporter);
                createDiamondLeggingsForgingRecipe(exporter);
                createDiamondBootsForgingRecipe(exporter);
            }

            private void createDiamondSwordForgingRecipe(RecipeExporter exporter) {
                // 使用锻造模板：基础物品(铁剑) + 顶料(钻石) + 模板 = 成品(钻石剑)
                SmithingTransformRecipeJsonBuilder.create(
                    Ingredient.ofItems(Items.IRON_SWORD),      // 基础物品 (base ingredient)
                    Ingredient.ofItems(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),  // 模板 (template)
                    Ingredient.ofItems(Items.DIAMOND),         // 顶料 (addition ingredient)
                    RecipeCategory.COMBAT,
                    Items.DIAMOND_SWORD                      // 成品
                )
                .criterion(hasItem(Items.IRON_SWORD), conditionsFromItem(Items.IRON_SWORD))
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, "diamond_sword_from_iron_sword");
            }

            private void createDiamondPickaxeForgingRecipe(RecipeExporter exporter) {
                // 铁镐升级为钻石镐
                SmithingTransformRecipeJsonBuilder.create(
                    Ingredient.ofItems(Items.IRON_PICKAXE),   // 基础物品
                    Ingredient.ofItems(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),  // 模板
                    Ingredient.ofItems(Items.DIAMOND),        // 顶料
                    RecipeCategory.TOOLS,
                    Items.DIAMOND_PICKAXE                    // 成品
                )
                .criterion(hasItem(Items.IRON_PICKAXE), conditionsFromItem(Items.IRON_PICKAXE))
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, "diamond_pickaxe_from_iron_pickaxe");
            }

            private void createDiamondAxeForgingRecipe(RecipeExporter exporter) {
                // 铁斧升级为钻石斧
                SmithingTransformRecipeJsonBuilder.create(
                    Ingredient.ofItems(Items.IRON_AXE),       // 基础物品
                    Ingredient.ofItems(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),  // 模板
                    Ingredient.ofItems(Items.DIAMOND),        // 顶料
                    RecipeCategory.TOOLS,
                    Items.DIAMOND_AXE                        // 成品
                )
                .criterion(hasItem(Items.IRON_AXE), conditionsFromItem(Items.IRON_AXE))
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, "diamond_axe_from_iron_axe");
            }

            private void createDiamondShovelForgingRecipe(RecipeExporter exporter) {
                // 铁锹升级为钻石锹
                SmithingTransformRecipeJsonBuilder.create(
                    Ingredient.ofItems(Items.IRON_SHOVEL),    // 基础物品
                    Ingredient.ofItems(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),  // 模板
                    Ingredient.ofItems(Items.DIAMOND),        // 顶料
                    RecipeCategory.TOOLS,
                    Items.DIAMOND_SHOVEL                     // 成品
                )
                .criterion(hasItem(Items.IRON_SHOVEL), conditionsFromItem(Items.IRON_SHOVEL))
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, "diamond_shovel_from_iron_shovel");
            }

            private void createDiamondHoeForgingRecipe(RecipeExporter exporter) {
                // 铁锄升级为钻石锄
                SmithingTransformRecipeJsonBuilder.create(
                    Ingredient.ofItems(Items.IRON_HOE),       // 基础物品
                    Ingredient.ofItems(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),  // 模板
                    Ingredient.ofItems(Items.DIAMOND),        // 顶料
                    RecipeCategory.TOOLS,
                    Items.DIAMOND_HOE                        // 成品
                )
                .criterion(hasItem(Items.IRON_HOE), conditionsFromItem(Items.IRON_HOE))
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, "diamond_hoe_from_iron_hoe");
            }
            
            private void createDiamondHelmetForgingRecipe(RecipeExporter exporter) {
                // 铁头盔升级为钻石头盔
                SmithingTransformRecipeJsonBuilder.create(
                    Ingredient.ofItems(Items.IRON_HELMET),       // 基础物品
                    Ingredient.ofItems(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),  // 模板
                    Ingredient.ofItems(Items.DIAMOND),        // 顶料
                    RecipeCategory.COMBAT,
                    Items.DIAMOND_HELMET                        // 成品
                )
                .criterion(hasItem(Items.IRON_HELMET), conditionsFromItem(Items.IRON_HELMET))
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, "diamond_helmet_from_iron_helmet");
            }
            
            private void createDiamondChestplateForgingRecipe(RecipeExporter exporter) {
                SmithingTransformRecipeJsonBuilder.create(
                    Ingredient.ofItems(Items.IRON_CHESTPLATE),       // 基础物品
                    Ingredient.ofItems(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),  // 模板
                    Ingredient.ofItems(Items.DIAMOND),        // 顶料
                    RecipeCategory.COMBAT,
                    Items.DIAMOND_CHESTPLATE                        // 成品
                )
                .criterion(hasItem(Items.IRON_CHESTPLATE), conditionsFromItem(Items.IRON_CHESTPLATE))
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, "diamond_chestplate_from_iron_chestplate");
            }
            
            private void createDiamondLeggingsForgingRecipe(RecipeExporter exporter) {
                SmithingTransformRecipeJsonBuilder.create(
                    Ingredient.ofItems(Items.IRON_LEGGINGS),       // 基础物品
                    Ingredient.ofItems(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),  // 模板
                    Ingredient.ofItems(Items.DIAMOND),        // 顶料
                    RecipeCategory.COMBAT,
                    Items.DIAMOND_LEGGINGS                       // 成品
                )
                .criterion(hasItem(Items.IRON_LEGGINGS), conditionsFromItem(Items.IRON_LEGGINGS))
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, "diamond_leggings_from_iron_leggings");
            }
            
            private void createDiamondBootsForgingRecipe(RecipeExporter exporter) {
                SmithingTransformRecipeJsonBuilder.create(
                    Ingredient.ofItems(Items.IRON_BOOTS),       // 基础物品
                    Ingredient.ofItems(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),  // 模板
                    Ingredient.ofItems(Items.DIAMOND),        // 顶料
                    RecipeCategory.COMBAT,
                    Items.DIAMOND_BOOTS                       // 成品
                )
                .criterion(hasItem(Items.IRON_BOOTS), conditionsFromItem(Items.IRON_BOOTS))
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, "diamond_boots_from_iron_boots");
            }
        };
    }

    @Override
    public String getName() {
        return "Diamond Equipment Forging Generator";
    }
}