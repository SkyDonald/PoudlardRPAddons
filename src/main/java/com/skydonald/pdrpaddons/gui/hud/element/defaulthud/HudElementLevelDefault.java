package com.skydonald.pdrpaddons.gui.hud.element.defaulthud;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.AbstractGui;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.skydonald.pdrpaddons.gui.hud.element.HudElement;
import com.skydonald.pdrpaddons.gui.hud.element.HudElementType;
import com.skydonald.pdrpaddons.settings.Settings;

@OnlyIn(Dist.CLIENT)
public class HudElementLevelDefault extends HudElement {

	public HudElementLevelDefault() {
		super(HudElementType.LEVEL, 0, 0, 0, 0, true);
		parent = HudElementType.WIDGET;
	}

	@Override
	public boolean checkConditions() {
		return this.mc.playerController.shouldDrawHUD();
	}

	@Override
	public void drawElement(AbstractGui gui, float zLevel, float partialTicks, int scaledWidth, int scaledHeight) {
		GlStateManager.disableBlend();
		String level = String.valueOf(this.mc.player.experienceLevel);
		this.mc.fontRenderer.drawStringWithShadow(level,
				(this.settings.getBoolValue(Settings.render_player_face) ? 38 : 12)
						- (this.mc.fontRenderer.getStringWidth(level) / 2)
						+ this.settings.getPositionValue(Settings.level_position)[0],
				(this.settings.getBoolValue(Settings.render_player_face) ? 38 : 14)
						+ this.settings.getPositionValue(Settings.level_position)[1],
				0x80FF20);
		GlStateManager.enableBlend();
	}

}
