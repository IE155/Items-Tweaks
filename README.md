# Items Tweaks 模组

这是一个 Minecraft Fabric 模组，提供了一些物品使用和堆叠的调整功能。

## 功能特性

### 1. 末地传送门框架物品提取
- **功能**: 允许玩家空手潜行右键点击末地传送门框架来取出末影之眼
- **操作**: 右键点击已放置末影之眼的末地传送门框架
- **结果**: 末影之眼会掉落，可以从框架中移除

### 2. 物品堆叠数量调整
- **桶 (Bucket)**: 堆叠上限从 16 提升至 32
- **潜影盒 (Shulker Box)**: 
  - 空潜影盒堆叠上限提升至 64
  - 装有物品的潜影盒保持堆叠上限为 1（防止数据崩溃）
- **不死图腾 (Totem of Undying)**: 堆叠上限从 1 提升至 64
- **雪球(Snow Ball) 末影珍珠(Ender Pearl) 鸡蛋(Egg)**: 堆叠上限从 16 提升至 64

### 3. 物品合成配方
- **末影之眼合成**: 
  - 配方: 1 个末影珍珠 + 1 个烈焰棒
  - 产出: 2 个末影之眼
  - 配方名: `ender_eye_from_pearl_and_rod`

## 技术实现

### Mixin 注入
- `ShulkerBoxMixin`: 修改潜影盒堆叠数量
- `TotemStackMixin`: 修改不死图腾堆叠数量
- `BucketStackMixin`: 修改桶堆叠数量
- `EndPortalFrameBlockMixin`: 实现末地传送门框架的末影之眼取出功能

### 核心代码
- `ItemsTweaks.java`: 主要功能实现
- `MyRecipeGenerator.java`: 合成配方生成

## 安装说明

1. 确保您的 Minecraft 版本为 1.21.11 并已安装 Fabric API
2. 将本模组文件放入 `.minecraft/mods` 目录
3. 启动游戏即可使用

## 依赖要求

- Minecraft 1.21.11
- Fabric Loader >= 0.15.0
- Java >= 21
- Fabric API

