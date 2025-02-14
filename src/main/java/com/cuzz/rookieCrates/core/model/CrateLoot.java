package com.cuzz.rookieCrates.core.model;

public class CrateLoot extends GlobalLoot{



    //稀有度
    Rarity rarity;

    //权重
    Double weight;

    //所属宝箱的id
    String crateId;

    //数量
    int amount;

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getCrateId() {
        return crateId;
    }

    public void setCrateId(String crateId) {
        this.crateId = crateId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }




    public CrateLoot(GlobalLoot globalLoot){

        this.lootItemStackBase64= globalLoot.getLootItemStackBase64();
        this.id = globalLoot.getId();
        this.lores = globalLoot.getLores();
        this.description = globalLoot.getDescription();
        this.displayName = globalLoot.getDisplayName();

    }




}
