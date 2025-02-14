package com.cuzz.rookieCrates.core.meg;

import com.cuzz.rookieCrates.RookieCrates;
import com.ticxo.modelengine.api.ModelEngineAPI;
import com.ticxo.modelengine.api.animation.BlueprintAnimation;
import com.ticxo.modelengine.api.animation.property.IAnimationProperty;
import com.ticxo.modelengine.api.animation.property.SimpleProperty;
import com.ticxo.modelengine.api.entity.BaseEntity;
import com.ticxo.modelengine.api.generator.blueprint.ModelBlueprint;
import com.ticxo.modelengine.api.model.ActiveModel;
import com.ticxo.modelengine.api.model.ModeledEntity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import com.ticxo.modelengine.api.entity.Dummy;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

public class ModelEngine4 extends ModelEngine {
    public boolean existModel(String modelId) {
        ModelBlueprint modelBlueprint = ModelEngineAPI.getBlueprint(modelId);
        return (modelBlueprint != null);
    }

    public boolean existModelAnimation(String modelId, String animationId) {
        ModelBlueprint modelBlueprint = ModelEngineAPI.getBlueprint(modelId);
        return (modelBlueprint != null && modelBlueprint.getAnimations().containsKey(animationId));
    }

    public String getModelsFormat() {
        Set<String> models = getModels();
        return String.join(", ", (Iterable)models);
    }

    public Set<String> getModels() {
        return ModelEngineAPI.getAPI().getModelRegistry().getKeys();
    }

    public UUID spawnModel(LivingEntity entity, String modelId) {
        ModeledEntity modeledEntity = ModelEngineAPI.createModeledEntity((Entity)entity);
        ActiveModel activeModel = ModelEngineAPI.createActiveModel(modelId);
        activeModel.setCanHurt(false);
        modeledEntity.addModel(activeModel, true);
        return modeledEntity.getBase().getUUID();
    }

    public UUID spawnModel(Location location, String modelId, Player player) {
        Dummy<?> dummy = new Dummy();
        dummy.setDetectingPlayers(false);
        dummy.syncLocation(location);
        ModeledEntity modeledEntity = ModelEngineAPI.createModeledEntity((BaseEntity)dummy);
        ActiveModel activeModel = ModelEngineAPI.createActiveModel(modelId);
        activeModel.setCanHurt(false);
        modeledEntity.addModel(activeModel, true);
        RookieCrates.getInstance().getModelEngine().addPlayerModel(dummy.getUUID(), player);
        return dummy.getUUID();
    }

    public void stopAnimations(UUID uniqueId, String modelId) {
        ModeledEntity modeledEntity = ModelEngineAPI.getModeledEntity(uniqueId);
        if (modeledEntity == null)
            return;
        modeledEntity.getModel(modelId).ifPresent(activeModel -> activeModel.getAnimationHandler().forceStopAllAnimations());
    }

    public void removePlayerModel(UUID uniqueId, String modelId, Player player) {
        ModeledEntity modeledEntity = ModelEngineAPI.getModeledEntity(uniqueId);
        if (modeledEntity == null)
            return;
        modeledEntity.removeModel(modelId).ifPresent(ActiveModel::destroy);
        modeledEntity.markRemoved();
        ModelEngineAPI.removeModeledEntity(uniqueId);
    }

    public void addPlayerModel(UUID uniqueId, Player player) {
        ModeledEntity modeledEntity = ModelEngineAPI.getModeledEntity(uniqueId);
        if (modeledEntity == null)
            return;
        Dummy<?> dummy = (Dummy)modeledEntity.getBase();
        dummy.setForceViewing(player, true);
    }

    public void addAnimation(UUID uniqueId, String modelId, String animationId, double in, double out, double speed, boolean loop) {
        ModeledEntity modeledEntity = ModelEngineAPI.getModeledEntity(uniqueId);
        if (modeledEntity == null)
            return;
        modeledEntity.getModel(modelId).ifPresent(activeModel -> {
            SimpleProperty simpleProperty = new SimpleProperty(activeModel, (BlueprintAnimation)activeModel.getBlueprint().getAnimations().get(animationId), in, out, speed);
            if (!activeModel.getAnimationHandler().playAnimation((IAnimationProperty)simpleProperty, true))
                Bukkit.getLogger().warning("The animation id(" + animationId + ") does not exist, please make sure to add the corresponding animations to the model.");
            simpleProperty.setForceLoopMode(loop ? BlueprintAnimation.LoopMode.LOOP : BlueprintAnimation.LoopMode.ONCE);
        });
    }

    public boolean isNew() {
        return true;
    }
}
