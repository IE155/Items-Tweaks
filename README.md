![title](images/tite.png)

[![Fabric](https://img.shields.io/badge/Fabric-supported-orange.svg)](https://fabricmc.net)
[![MC](https://img.shields.io/badge/MC-1.21.11-brightgreen.svg)](https://zh.minecraft.wiki/w/Java%E7%89%881.21.11?variant=zh-cn)
![Static Badge](https://img.shields.io/badge/build-passing-brightgreen)

# Ttems tweaks / 物品微调

## 简介 / Introduction

**English:**  
Items Tweaks is a Minecraft Fabric mod that adds various item tweaks and new gameplay mechanics. This mod enhances the vanilla experience by introducing new item interactions, custom tools, and automated features.

**中文:**  
Items Tweaks 是一个 Minecraft Fabric 模组,为游戏添加了多种物品调整和新的游戏机制。该模组通过引入新的物品交互、自定义工具和自动化功能来增强原版游戏体验。

---

## 功能特性 / Features

### 1. 枯叶自燃系统 / Leaf Litter Fire System

**English:**  
When leaf litter item entities accumulate in the same block space and exceed 256 items, fire will automatically generate at that location to burn them. This feature helps prevent lag from excessive item entities and adds a realistic combustion mechanic.

**中文:**  
当枯叶物品实体在同一方块空间内堆积并超过256个时,会自动在该位置生成火焰将其烧毁。此功能有助于防止过多物品实体导致的卡顿,并添加了真实的燃烧机制。

- 检测频率: 每0.5秒 / Detection frequency: Every 0.5 seconds
- 触发阈值: 256个枯叶物品 / Trigger threshold: 256 leaf litter items
- 火焰生成位置: 枯叶物品所在位置 / Fire spawns at: Leaf litter location

### 2. 黑曜石转换 / Obsidian Conversion

**English:**  
- Right-click obsidian with a water bottle to convert it to crying obsidian (water bottle becomes empty)
- Right-click crying obsidian with an empty bottle to convert it back to regular obsidian (empty bottle becomes water bottle)
- Allows free conversion between both types of obsidian

**中文:**  
- 用水瓶右键黑曜石可将其转换为哭泣的黑曜石(水瓶变为空瓶)
- 用空瓶右键哭泣的黑曜石可将其转换为普通黑曜石(空瓶变为水瓶)
- 实现两种黑曜石之间的自由转换

### 3. 圆石变黑曜石 / Cobblestone to Obsidian

**English:**  
Right-click cobblestone with black dye to convert it into obsidian. Consumes one black dye in survival mode.

**中文:**  
使用黑色染料右键圆石可将其转换为黑曜石。在生存模式下会消耗一个黑色染料。

### 4. 甜浆果无冷却食用 / Sweet Berries No Cooldown

**English:**  
Right-click sweet berries to eat them instantly without cooldown or eating animation. Works in both survival and creative modes.

**中文:**  
右键甜浆果即可立即食用,无冷却时间且无进食动画。在生存和创造模式下均可使用。

### 5. 自定义物品 / Custom Items

**English:**  
- **Leaf Litter Pickaxe**: Crafted with leaf litter, used for gathering leaf litter efficiently
- **Leaf Litter Sword**: Combat weapon made from leaf litter with 10 durability
- **Light Torch**: Enhanced lighting tool
- **Hard Snow Ball**: Compact snow ball with special properties

**中文:**  
- **落叶镐**: 用枯叶制作,用于高效收集枯叶
- **落叶剑**: 用枯叶制作的战斗武器,耐久度为10
- **发光火把**: 增强型照明工具(暂时未实现)
- **硬雪球**: 具有特殊属性的压缩雪球(暂时未实现)



### 3. 物品合成配方
- **末影之眼合成**:
    - 配方: 1 个末影珍珠 + 1 个烈焰棒
    - 产出: 2 个末影之眼

![](images/image03.png)
- **黑曜石合成**:
    - 配方: 1 个圆石 + 1 个黑色染料
    - 产出: 1 个黑曜石

![](images/image05.png)
- **钻石合成**:
    - 配方: 1 个青金石 + 1 个淡蓝色染料
    - 产出: 1 个钻石

![](images/image04.png)
- **橡木木板合成**:
    - 配方: 4 个枯叶
    - 产出: 1 个橡木木板

![](images/image08.png)
- **线合成**:
    - 配方: 1 个羊毛(任意颜色)
    - 产出: 4 个线

![](images/image07.png)

- **落叶镐合成**
    - 配方: 如图所示
    - 产出: 如图所示

![](images/image09.png)

- **物品堆叠**

![](images/image06.png)

---

## 安装说明 / Installation

### 前置要求 / Requirements

**English:**  
- Minecraft 1.21.11
- Fabric Loader
- Fabric API

**中文:**  
- Minecraft 1.21.11
- Fabric Loader
- Fabric API

### 安装步骤 / Steps

**English:**  
1. Install Minecraft Fabric Loader
2. Download Fabric API and place it in the `mods` folder
3. Download Items Tweaks mod jar file
4. Place the mod jar in the `mods` folder
5. Launch Minecraft with the Fabric profile

**中文:**  
1. 安装 Minecraft Fabric Loader
2. 下载 Fabric API 并放入 `mods` 文件夹
3. 下载 Items Tweaks 模组 jar 文件
4. 将模组 jar 放入 `mods` 文件夹
5. 使用 Fabric 配置启动 Minecraft

---

## 使用方法 / Usage

### 枯叶自燃 / Leaf Litter Fire

**English:**  
1. Drop leaf litter items on the ground
2. Accumulate 256 or more leaf litter items in the same block space
3. Fire will automatically generate and burn the items
4. This prevents item entity lag and adds gameplay depth

**中文:**  
1. 将枯叶物品丢在地上
2. 在同一方块空间内堆积256个或更多枯叶物品
3. 火焰会自动生成并烧毁物品
4. 这可以防止物品实体卡顿并增加游戏深度

### 黑曜石转换 / Obsidian Conversion

**English:**  
- Hold water bottle → Right-click obsidian → Get crying obsidian + empty bottle
- Hold empty bottle → Right-click crying obsidian → Get obsidian + water bottle

**中文:**  
- 手持水瓶 → 右键黑曜石 → 获得哭泣黑曜石 + 空瓶
- 手持空瓶 → 右键哭泣黑曜石 → 获得黑曜石 + 水瓶

---

## 配置说明 / Configuration

**English:**  
Currently, the mod uses default configurations. Future versions may add a config file for customization.

**中文:**  
目前,模组使用默认配置。未来版本可能会添加配置文件以进行自定义。

### 可调参数 / Adjustable Parameters (代码层面 / Code Level)

**English:**  
- Leaf litter fire threshold: Modify `totalLeafLitterCount >= 256` in `LeafLitterFire.java`
- Detection frequency: Modify `time % 10` in `LeafLitterFire.java` (10 ticks = 0.5 seconds)
- Log cooldown: Modify `LOG_COOLDOWN = 100` in `LeafLitterFire.java` (100 ticks = 5 seconds)

**中文:**  
- 枯叶火焰阈值: 修改 `LeafLitterFire.java` 中的 `totalLeafLitterCount >= 256`
- 检测频率: 修改 `LeafLitterFire.java` 中的 `time % 10` (10 tick = 0.5秒)
- 日志冷却: 修改 `LeafLitterFire.java` 中的 `LOG_COOLDOWN = 100` (100 tick = 5秒)

---

##  项目结构 / Project Structure

```
Items-Tweaks/
├── src/main/java/io/qzz/iie/
│   ├── ItemsTweaks.java              # 主类 / Main class
│   ├── events/                        # 事件系统 / Event system
│   │   ├── LeafLitterFire.java       # 枯叶自燃 / Leaf litter fire
│   │   ├── WaterObsidian.java        # 黑曜石转换 / Obsidian conversion
│   │   ├── DyeCobblestoneToObsidian.java  # 圆石变黑曜石 / Cobblestone to obsidian
│   │   └── NoCdEat.java              # 无冷却食用 / No cooldown eat
│   └── newitems/                      # 自定义物品 / Custom items
│       ├── LeafLitterPickaxe.java    # 落叶镐 / Leaf litter pickaxe
│       ├── LeafLitterSword.java      # 落叶剑 / Leaf litter sword
│       ├── LightTorch.java           # 发光火把 / Light torch
│       └── HardSnowBall.java         # 硬雪球 / Hard snow ball
└── build.gradle                       # 构建配置 / Build configuration
```

---

## 🛠️ 开发与构建 / Development & Build

**English:**  
```bash
# Build the mod
./gradlew build

# Run in development environment
./gradlew runClient
```

**中文:**  
```bash
# 构建模组
./gradlew build

# 在开发环境中运行
./gradlew runClient
```

编译后的jar文件位于: `/build/libs/items-tweaks-<version>.jar`

---

## 📝 更新日志 / Changelog

### v1.0.2 (Current / 当前版本)
**English:**
- Added leaf litter auto-fire system
- Added obsidian conversion mechanics
- Added cobblestone to obsidian conversion
- Added sweet berries no-cooldown eating
- Added custom items (Leaf Litter tools, Light Torch, Hard Snow Ball)

**中文:**
- 添加枯叶自燃系统
- 添加黑曜石转换机制
- 添加圆石变黑曜石功能
- 添加甜浆果无冷却食用
- 添加自定义物品(落叶工具、发光火把、硬雪球)

---

## 贡献 / Contributing

**English:**  
Contributions are welcome! Please feel free to submit issues and pull requests.

**中文:**  
欢迎贡献!请随时提交 issue 和 pull request。

---

## 许可证 / License

**English:**  
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

**中文:**  
本项目采用 MIT 许可证 - 详见 [LICENSE](LICENSE) 文件。

---


## 支持 / Support

**English:**  
If you enjoy this mod, please consider giving it a ⭐ on GitHub!

**中文:**  
如果你喜欢这个模组,请考虑在 GitHub 上给它一个 ⭐!

---

**English:** Thank you for using Items Tweaks! Enjoy your enhanced Minecraft experience! 🎮

**中文:** 感谢使用 Items Tweaks!祝你享受增强的 Minecraft 游戏体验! 🎮
