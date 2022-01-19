package com.skydonald.pdrpaddons.gui.hud.element;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public enum HudElementType {
	VOID(I18n.format("name.void")),
	HOTBAR(I18n.format("name.hotbar")),
	HEALTH(I18n.format("name.health")),
	ARMOR(I18n.format("name.armor")),
	FOOD(I18n.format("name.food")),
	HEALTH_MOUNT(I18n.format("name.health_mount")),
	AIR(I18n.format("name.air")),
	JUMP_BAR(I18n.format("name.jump_bar")),
	EXPERIENCE(I18n.format("name.experience")),
	LEVEL(I18n.format("name.level")),
	CLOCK(I18n.format("name.clock")),
	DETAILS(I18n.format("name.details")),
	WIDGET(I18n.format("name.widget")),
	COMPASS(I18n.format("name.compass")),
	ENTITY_INSPECT(I18n.format("name.entity_inspect")),
	STATUS_EFFECTS(I18n.format("name.status_effects"));

	private String displayName;

	private HudElementType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return this.displayName;
	}
}
