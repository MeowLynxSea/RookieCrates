package com.cuzz.rookieCrates.core.model;

import com.cuzz.rookieCrates.RookieCrates;
import com.ticxo.modelengine.api.ModelEngineAPI;
import com.ticxo.modelengine.api.entity.BaseEntity;
import com.ticxo.modelengine.api.entity.Dummy;
import com.ticxo.modelengine.api.model.ActiveModel;
import com.ticxo.modelengine.api.model.ModeledEntity;
import com.ticxo.modelengine.api.model.bone.ModelBone;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.Map;
import java.util.UUID;

public class CrateModel {
    private UUID megUniqueId;
    private String modelName;
    public void playAnimation(Player player,String animationId) {
        if (player == null)
            return;
        RookieCrates plugin = RookieCrates.getInstance();
        if (this.megUniqueId == null)
            return;
        plugin.getModelEngine().addAnimation(this.megUniqueId, this.modelName, animationId, 0.5, 1, 1, false);
    }
    public CrateModel spawnModel(Location location, String modelId, Player player) {
        Dummy<?> dummy = new Dummy();
        dummy.setDetectingPlayers(false);
        dummy.syncLocation(location);
        ModeledEntity modeledEntity = ModelEngineAPI.createModeledEntity((BaseEntity)dummy);
        ActiveModel activeModel = ModelEngineAPI.createActiveModel(modelId);
        activeModel.setCanHurt(false);
        Map<String, ModelBone> bones = activeModel.getBones();
        System.out.println("bones 大小为 "+bones.keySet().size());
        bones.keySet().stream().forEach(item->{
            System.out.println(item);
        });
        activeModel.setSkyLight(15);
        activeModel.setBlockLight(15);
        modeledEntity.addModel(activeModel, true);
        RookieCrates.getInstance().getModelEngine().addPlayerModel(dummy.getUUID(), player);
        this.megUniqueId=dummy.getUUID();
        this.modelName=modelId;

        BukkitTask bukkitTask = RookieCrates.getInstance().getServer().getScheduler().runTaskLater((Plugin)RookieCrates.getInstance(), () -> {
            // playAnimation(player,"anvil_smash");
            removeModel(player);
            System.out.println(" kill !!!!");
        }, 2400);
        return this;
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
