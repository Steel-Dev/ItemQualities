package com.abraxas.itemqualities.inventories.providers;

import com.abraxas.itemqualities.ItemQualities;
import com.abraxas.itemqualities.QualitiesManager;
import com.abraxas.itemqualities.api.Keys;
import com.abraxas.itemqualities.inventories.Inventories;
import com.abraxas.itemqualities.inventories.utils.InvUtils;
import com.abraxas.itemqualities.utils.QualityChatValues;
import com.abraxas.itemqualities.utils.Utils;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

import static com.abraxas.itemqualities.utils.Utils.colorize;
import static com.abraxas.itemqualities.utils.Utils.sendMessageWithPrefix;

public class QualityEditInvProvider implements InventoryProvider {
    ItemQualities main = ItemQualities.getInstance();

    @Override
    public void init(Player player, InventoryContents contents) {
        var rawQualityPreviewingKey = player.getPersistentDataContainer().getOrDefault(Keys.PLAYER_QUALITY_EDITING_OR_PREVIEWING_KEY, PersistentDataType.STRING, "").split(":");
        var qualityNamespace = new NamespacedKey(rawQualityPreviewingKey[0], rawQualityPreviewingKey[1]);
        var quality = QualitiesManager.getQualityById(qualityNamespace);
        if (quality == null) {
            Utils.sendMessageWithPrefix(player, main.getTranslation("message.plugin.error"));
            player.closeInventory();
            return;
        }

        contents.fill(ClickableItem.of(InvUtils.blankItemSecondary, InvUtils.PREVENT_PICKUP));

        // General Quality Attributes
        var editId = new ItemStack(Material.PAPER);
        var editIdMeta = editId.getItemMeta();
        editIdMeta.setDisplayName(colorize("&aChange ID"));
        editIdMeta.setLore(new ArrayList<>() {{
            add(colorize("&7Current Value: &e%s".formatted(quality.key.getKey())));
            add("");
            add(colorize("&7Left-Click to edit."));
        }});
        editId.setItemMeta(editIdMeta);
        contents.set(0, 0, ClickableItem.of(editId, e -> {
            e.setCancelled(true);
            player.getPersistentDataContainer().set(Keys.PLAYER_TYPING_VALUE_KEY, PersistentDataType.STRING, QualityChatValues.UPDATE_QUALITY_ID);
            sendMessageWithPrefix(player, ItemQualities.getInstance().getTranslation("message.plugin.quality_creation.enter_value").formatted("ID"));
            player.closeInventory();
        }));

        var editDisplay = new ItemStack(Material.NAME_TAG);
        var editDisplayMeta = editDisplay.getItemMeta();
        editDisplayMeta.setDisplayName(colorize("&aChange Display"));
        editDisplayMeta.setLore(new ArrayList<>() {{
            add(colorize("&7Current Value: &e%s".formatted(quality.display)));
            add("");
            add(colorize("&7Left-Click to edit."));
        }});
        editDisplay.setItemMeta(editDisplayMeta);
        contents.set(0, 1, ClickableItem.of(editDisplay, e -> {
            e.setCancelled(true);
            player.getPersistentDataContainer().set(Keys.PLAYER_TYPING_VALUE_KEY, PersistentDataType.STRING, QualityChatValues.UPDATE_QUALITY_DISPLAY);
            sendMessageWithPrefix(player, ItemQualities.getInstance().getTranslation("message.plugin.quality_creation.enter_value").formatted("Display"));
            player.closeInventory();
        }));

        var editTier = new ItemStack(Material.IRON_NUGGET);
        var editTierMeta = editTier.getItemMeta();
        editTierMeta.setDisplayName(colorize("&aChange Tier"));
        editTierMeta.setLore(new ArrayList<>() {{
            add(colorize("&7Current Value: &e%s".formatted(quality.tier)));
            add("");
            add(colorize("&7Left-Click to edit."));
        }});
        editTier.setItemMeta(editTierMeta);
        contents.set(0, 2, ClickableItem.of(editTier, e -> {
            e.setCancelled(true);
            player.getPersistentDataContainer().set(Keys.PLAYER_TYPING_VALUE_KEY, PersistentDataType.STRING, QualityChatValues.UPDATE_QUALITY_TIER);
            sendMessageWithPrefix(player, ItemQualities.getInstance().getTranslation("message.plugin.quality_creation.enter_value").formatted("Tier"));
            player.closeInventory();
        }));

        var editAddChance = new ItemStack(Material.IRON_NUGGET);
        var editAddChanceMeta = editAddChance.getItemMeta();
        editAddChanceMeta.setDisplayName(colorize("&aChange Add Chance"));
        editAddChanceMeta.setLore(new ArrayList<>() {{
            add(colorize("&7Current Value: &e%s".formatted(quality.addToItemChance)));
            add("");
            add(colorize("&7Left-Click to edit."));
        }});
        editAddChance.setItemMeta(editAddChanceMeta);
        contents.set(0, 3, ClickableItem.of(editAddChance, e -> {
            e.setCancelled(true);
            player.getPersistentDataContainer().set(Keys.PLAYER_TYPING_VALUE_KEY, PersistentDataType.STRING, QualityChatValues.UPDATE_QUALITY_ADD_CHANCE);
            sendMessageWithPrefix(player, ItemQualities.getInstance().getTranslation("message.plugin.quality_creation.enter_value").formatted("Add Chance"));
            player.closeInventory();
        }));


        // Quality Attributes
        var editNoDropsChance = new ItemStack(Material.GOLD_NUGGET);
        var editNoDropsChanceMeta = editNoDropsChance.getItemMeta();
        editNoDropsChanceMeta.setDisplayName(colorize("&aChange No Drops Chance"));
        editNoDropsChanceMeta.setLore(new ArrayList<>() {{
            add(colorize("&7Current Value: &e%s".formatted(quality.noDropChance)));
            add("");
            add(colorize("&7Left-Click to edit."));
        }});
        editNoDropsChance.setItemMeta(editNoDropsChanceMeta);
        contents.set(0, 3, ClickableItem.of(editNoDropsChance, e -> {
            e.setCancelled(true);
            player.getPersistentDataContainer().set(Keys.PLAYER_TYPING_VALUE_KEY, PersistentDataType.STRING, QualityChatValues.UPDATE_QUALITY_NO_DROPS_CHANCE);
            sendMessageWithPrefix(player, ItemQualities.getInstance().getTranslation("message.plugin.quality_creation.enter_value").formatted("No Drops Chance"));
            player.closeInventory();
        }));

        var editDoubleDropsChance = new ItemStack(Material.DIAMOND);
        var editDoubleDropsChanceMeta = editDoubleDropsChance.getItemMeta();
        editDoubleDropsChanceMeta.setDisplayName(colorize("&aChange Double Drops Chance"));
        editDoubleDropsChanceMeta.setLore(new ArrayList<>() {{
            add(colorize("&7Current Value: &e%s".formatted(quality.doubleDropsChance)));
            add("");
            add(colorize("&7Left-Click to edit."));
        }});
        editDoubleDropsChance.setItemMeta(editDoubleDropsChanceMeta);
        contents.set(0, 4, ClickableItem.of(editDoubleDropsChance, e -> {
            e.setCancelled(true);
            player.getPersistentDataContainer().set(Keys.PLAYER_TYPING_VALUE_KEY, PersistentDataType.STRING, QualityChatValues.UPDATE_QUALITY_DOUBLE_DROPS_CHANCE);
            sendMessageWithPrefix(player, ItemQualities.getInstance().getTranslation("message.plugin.quality_creation.enter_value").formatted("Double Drops Chance"));
            player.closeInventory();
        }));

        var editMaxDurabilityMod = new ItemStack(Material.SCUTE);
        var editMaxDurabilityModMeta = editMaxDurabilityMod.getItemMeta();
        editMaxDurabilityModMeta.setDisplayName(colorize("&aChange Max Durability Mod"));
        editMaxDurabilityModMeta.setLore(new ArrayList<>() {{
            add(colorize("&7Current Value: &e%s".formatted(quality.itemMaxDurabilityMod)));
            add("");
            add(colorize("&7Left-Click to edit."));
        }});
        editMaxDurabilityMod.setItemMeta(editMaxDurabilityModMeta);
        contents.set(0, 5, ClickableItem.of(editMaxDurabilityMod, e -> {
            e.setCancelled(true);
            player.getPersistentDataContainer().set(Keys.PLAYER_TYPING_VALUE_KEY, PersistentDataType.STRING, QualityChatValues.UPDATE_QUALITY_MAX_DURABILITY_MOD);
            sendMessageWithPrefix(player, ItemQualities.getInstance().getTranslation("message.plugin.quality_creation.enter_value").formatted("Max Durability Mod"));
            player.closeInventory();
        }));

        var editNoDurabilityLossChance = new ItemStack(Material.OBSIDIAN);
        var editNoDurabilityLossChanceMeta = editNoDurabilityLossChance.getItemMeta();
        editNoDurabilityLossChanceMeta.setDisplayName(colorize("&aChange No Durability Loss Chance"));
        editNoDurabilityLossChanceMeta.setLore(new ArrayList<>() {{
            add(colorize("&7Current Value: &e%s".formatted(quality.noDurabilityLossChance)));
            add("");
            add(colorize("&7Left-Click to edit."));
        }});
        editNoDurabilityLossChance.setItemMeta(editNoDurabilityLossChanceMeta);
        contents.set(0, 6, ClickableItem.of(editNoDurabilityLossChance, e -> {
            e.setCancelled(true);
            player.getPersistentDataContainer().set(Keys.PLAYER_TYPING_VALUE_KEY, PersistentDataType.STRING, QualityChatValues.UPDATE_QUALITY_NO_DURABILITY_LOSS_CHANCE);
            sendMessageWithPrefix(player, ItemQualities.getInstance().getTranslation("message.plugin.quality_creation.enter_value").formatted("No Durability Loss Chance"));
            player.closeInventory();
        }));

        var editExtraDurabilityLoss = new ItemStack(Material.STICK);
        var editExtraDurabilityLossMeta = editExtraDurabilityLoss.getItemMeta();
        editExtraDurabilityLossMeta.setDisplayName(colorize("&aChange Extra Durability Loss"));
        editExtraDurabilityLossMeta.setLore(new ArrayList<>() {{
            add(colorize("&7Current Value: &e%s".formatted(quality.extraDurabilityLoss)));
            add("");
            add(colorize("&7Left-Click to edit."));
        }});
        editExtraDurabilityLoss.setItemMeta(editExtraDurabilityLossMeta);
        contents.set(0, 7, ClickableItem.of(editExtraDurabilityLoss, e -> {
            e.setCancelled(true);
            player.getPersistentDataContainer().set(Keys.PLAYER_TYPING_VALUE_KEY, PersistentDataType.STRING, QualityChatValues.UPDATE_QUALITY_EXTRA_DURABILITY_LOSS);
            sendMessageWithPrefix(player, ItemQualities.getInstance().getTranslation("message.plugin.quality_creation.enter_value").formatted("Extra Durability Loss"));
            player.closeInventory();
        }));

        var editExtraDurabilityLossChance = new ItemStack(Material.STICK);
        var editExtraDurabilityLossChanceMeta = editExtraDurabilityLossChance.getItemMeta();
        editExtraDurabilityLossChanceMeta.setDisplayName(colorize("&aChange Extra Durability Loss Chance"));
        editExtraDurabilityLossChanceMeta.setLore(new ArrayList<>() {{
            add(colorize("&7Current Value: &e%s".formatted(quality.extraDurabilityLossChance)));
            add("");
            add(colorize("&7Left-Click to edit."));
        }});
        editExtraDurabilityLossChance.setItemMeta(editExtraDurabilityLossChanceMeta);
        contents.set(0, 8, ClickableItem.of(editExtraDurabilityLossChance, e -> {
            e.setCancelled(true);
            player.getPersistentDataContainer().set(Keys.PLAYER_TYPING_VALUE_KEY, PersistentDataType.STRING, QualityChatValues.UPDATE_QUALITY_EXTRA_DURABILITY_LOSS_CHANCE);
            sendMessageWithPrefix(player, ItemQualities.getInstance().getTranslation("message.plugin.quality_creation.enter_value").formatted("Extra Durability Loss Chance"));
            player.closeInventory();
        }));

        var editModifiers = new ItemStack(Material.IRON_AXE);
        var editModifiersMeta = editModifiers.getItemMeta();
        editModifiersMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        editModifiersMeta.setDisplayName(colorize("&aEdit Modifiers"));
        editModifiersMeta.setLore(new ArrayList<>() {{
            add(colorize("&7Left-Click to edit."));
        }});
        editModifiers.setItemMeta(editModifiersMeta);
        contents.set(1, 4, ClickableItem.of(editModifiers, e -> {
            e.setCancelled(true);
            Inventories.QUALITY_MODIFIERS_LIST.open(player);
        }));


        contents.fillRow(2, ClickableItem.of(InvUtils.blankItem, InvUtils.PREVENT_PICKUP));
        // Go Back
        contents.set(2, 4, ClickableItem.of(InvUtils.arrowLeftBtn, e -> {
            e.setCancelled(true);
            player.getPersistentDataContainer().remove(Keys.PLAYER_TYPING_VALUE_KEY);
            player.getPersistentDataContainer().remove(Keys.PLAYER_QUALITY_EDITING_OR_PREVIEWING_KEY);
            Inventories.QUALITY_MANAGER_INVENTORY.open(player, 0);
        }));

        var deleteBtn = new ItemStack(Material.LIGHT_GRAY_BANNER);
        var deleteBtnMeta = (BannerMeta) deleteBtn.getItemMeta();
        deleteBtnMeta.addPattern(new Pattern(DyeColor.RED, PatternType.STRIPE_DOWNRIGHT));
        deleteBtnMeta.addPattern(new Pattern(DyeColor.RED, PatternType.STRIPE_DOWNLEFT));
        deleteBtnMeta.addPattern(new Pattern(DyeColor.LIGHT_GRAY, PatternType.CURLY_BORDER));
        deleteBtnMeta.setDisplayName(colorize("&c&lDelete Quality"));
        deleteBtnMeta.setLore(new ArrayList<>() {{
            add(colorize("&4Warning: &cYou can NOT undo this."));
            add(colorize("&conly click this if you are SURE."));
        }});
        deleteBtnMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS); // Wtf mojang
        deleteBtn.setItemMeta(deleteBtnMeta);
        contents.set(2, 8, ClickableItem.of(deleteBtn, e -> {
            e.setCancelled(true);
            QualitiesManager.deleteQuality(quality);
            player.getPersistentDataContainer().remove(Keys.PLAYER_TYPING_VALUE_KEY);
            player.getPersistentDataContainer().remove(Keys.PLAYER_QUALITY_EDITING_OR_PREVIEWING_KEY);
            Inventories.QUALITY_MANAGER_INVENTORY.open(player, 0);
        }));
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
