package com.cuzz.rookieCrates.core.model;

import com.cuzz.rookieCrates.RookieCrates;
import com.ticxo.modelengine.api.ModelEngineAPI;
import com.ticxo.modelengine.api.entity.BaseEntity;
import com.ticxo.modelengine.api.entity.Dummy;
import com.ticxo.modelengine.api.model.ActiveModel;
import com.ticxo.modelengine.api.model.ModeledEntity;
import com.ticxo.modelengine.api.model.bone.BoneBehaviorTypes;
import com.ticxo.modelengine.api.model.bone.type.NameTag;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.Random;
import java.util.UUID;

public class LootModel {
    private UUID megUniqueId;
    private String modelName;
    public void playAnimation(Player player,String animationId) {
        if (player == null)
            return;
        RookieCrates plugin = RookieCrates.getInstance();
        if (this.megUniqueId == null)
            return;
        plugin.getModelEngine().addAnimation(this.megUniqueId, this.modelName, animationId, 0.5, 1, 1, true);
    }
    public LootModel spawnModel(Location location, String modelId, Player player) {
        Dummy<?> dummy = new Dummy();
        dummy.setDetectingPlayers(false);
        dummy.syncLocation(location);
        ModeledEntity modeledEntity = ModelEngineAPI.createModeledEntity((BaseEntity)dummy);
        ActiveModel activeModel = ModelEngineAPI.createActiveModel(modelId);

        activeModel.setCanHurt(false);


        activeModel.setSkyLight(15);
        activeModel.setBlockLight(15);

        RookieCrates.getInstance().getModelEngine().addPlayerModel(dummy.getUUID(), player);
        this.megUniqueId=dummy.getUUID();
        this.modelName=modelId;
        modeledEntity.addModel(activeModel, true);
        modeledEntity.getModel(modelName).ifPresent((i)->{
            i.getBone("item").ifPresent(bone->{
                bone.setModel(getRandomItemStack());

            //        modelBone.setSkyLight(6);
            //        modelBone.setBlockLight(6);
            });
            System.out.println("开始设置物品标签");
            i.getBone("tag_name").ifPresent(modelBone -> {
                String boneId = modelBone.getBoneId();
                System.out.println("骨骼id 是:" +boneId);
                modelBone.getBoneBehavior(BoneBehaviorTypes.NAMETAG).ifPresent(nameTag -> {
                    System.out.println("存在tag_name");
                    ((NameTag)nameTag).setString("23333333333333333333333");
                    System.out.println("成功设置物品标签");
                    nameTag.setVisible(true);
                });


            });
        });
        BukkitTask bukkitTask = RookieCrates.getInstance().getServer().getScheduler().runTaskLater((Plugin)RookieCrates.getInstance(), () -> {
            // playAnimation(player,"anvil_smash");
            removeModel(player);
            System.out.println(" kill !!!!");
        }, 1400);
        return this;
    }
    /**
     * 随机生成一个 ItemStack
     * @return 随机的 ItemStack
     */
    public static ItemStack getRandomItemStack() {
        // 获取所有 Material 枚举值
        Material[] materials = Material.values();

        // 创建一个随机数生成器
        Random random = new Random();

        // 随机选择一个 Material
        Material randomMaterial = materials[random.nextInt(materials.length)];

        // 创建对应的 ItemStack
        return new ItemStack(randomMaterial);
    }
    public void removeModel(Player player) {
        if (this.megUniqueId == null) {
            RookieCrates.getInstance().getLogger().info("ModelEntity not exist");
            return;
        }
        RookieCrates plugin = RookieCrates.getInstance();
        plugin.getModelEngine().removePlayerModel(this.megUniqueId, this.modelName, player);
    }
}
