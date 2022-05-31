package com.abraxas.itemqualities.api.quality;

import com.abraxas.itemqualities.api.APIUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;

import java.util.HashMap;
import java.util.Map;

public class ItemQuality {
    public NamespacedKey key;
    public String display;

    public Map<Attribute, AttributeModifier> modifiers;

    public int addToItemChance;

    public int itemMaxDurabilityAddition;
    public int noDurabilityLossChance;
    public int extraDurabilityLoss;
    public int extraDurabilityLossChance;

    public int noDropChance;
    public int doubleDropsChance;

    public int tier;

    public ItemQuality(NamespacedKey key, String display, int addToItemChance, int tier){
        this.key = key;
        this.display = display;
        this.addToItemChance = addToItemChance;
        this.tier = tier;
        modifiers = new HashMap<>();
    }

    public static ItemQuality deserialize(String json){
        return APIUtils.getGson().fromJson(json,ItemQuality.class);
    }

    public static String serialize(ItemQuality item){
        return APIUtils.getGson().toJson(item,ItemQuality.class);
    }
}