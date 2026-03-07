package io.qzz.iie;

import io.qzz.iie.datagen.DiamondRecipeGen;
import io.qzz.iie.datagen.EnderEyeRecipeGenerator;
import io.qzz.iie.datagen.LeafLitterPickaxeRecipeGenerator;
import io.qzz.iie.datagen.LeafLitterToPlanksRecipeGenerator;
import io.qzz.iie.datagen.MyRecipeGenerator;
import io.qzz.iie.datagen.ObsidianRecipeGen;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;


public class ItemsTweaksDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		// 注册所有独立的配方生成器
		pack.addProvider(EnderEyeRecipeGenerator::new);
		pack.addProvider(LeafLitterPickaxeRecipeGenerator::new);
		pack.addProvider(LeafLitterToPlanksRecipeGenerator::new);
		pack.addProvider(ObsidianRecipeGen::new);
		pack.addProvider(DiamondRecipeGen::new);
		// 保留原有的占位提供者
		pack.addProvider(MyRecipeGenerator::new);
	}
}