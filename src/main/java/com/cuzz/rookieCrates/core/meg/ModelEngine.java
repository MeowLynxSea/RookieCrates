package com.cuzz.rookieCrates.core.meg;

import java.util.Set;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public abstract class ModelEngine {
    public abstract boolean existModel(String paramString);

    public abstract boolean existModelAnimation(String paramString1, String paramString2);

    public String getModelsFormat() {
        Set<String> models = getModels();
        return String.join(", ", (Iterable)models);
    }

    public abstract Set<String> getModels();

    public abstract UUID spawnModel(LivingEntity paramLivingEntity, String paramString);

    public abstract UUID spawnModel(Location paramLocation, String paramString, Player paramPlayer);

    public abstract void removePlayerModel(UUID paramUUID, String paramString, Player paramPlayer);

    public abstract void addPlayerModel(UUID paramUUID, Player paramPlayer);

    public abstract void addAnimation(UUID paramUUID, String paramString1, String paramString2, double paramDouble1, double paramDouble2, double paramDouble3, boolean paramBoolean);

    public abstract void stopAnimations(UUID paramUUID, String paramString);

    public abstract boolean isNew();
}
