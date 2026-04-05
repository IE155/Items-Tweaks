package io.qzz.iie.events;

/**
 * 允许玩家使用三叉戟的激流附魔在任何环境下使用(无需水或雨)
 * 
 * 注意: 此功能通过TridentRiptideMixin实现
 * Mixin会修改Entity.isTouchingWaterOrRain()方法
 * 当玩家手持三叉戟时,返回true让游戏认为玩家在水中或雨中
 */
public class RiptideAnywhere {
    
    public static void registerEvents() {
        // 激流三叉戟无水使用的功能已通过Mixin实现
        // TridentRiptideMixin会自动修改isTouchingWaterOrRain()方法
        // 无需额外事件注册
    }
}
