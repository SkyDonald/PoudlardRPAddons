package com.skydonald.pdrpaddons.gui.hud.element.modern;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.AbstractGui;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.skydonald.pdrpaddons.gui.hud.HudModern;
import com.skydonald.pdrpaddons.gui.hud.element.HudElement;
import com.skydonald.pdrpaddons.gui.hud.element.HudElementType;
import com.skydonald.pdrpaddons.settings.Settings;

@OnlyIn(Dist.CLIENT)
public class HudElementLevelModern extends HudElement {

	public HudElementLevelModern() {
		super(HudElementType.LEVEL, 0, 0, 0, 0, true);
		parent = HudElementType.WIDGET;
	}

	@Override
	public boolean checkConditions() {
		return this.mc.playerController.shouldDrawHUD();
	}

	@Override
	public void drawElement(AbstractGui gui, float zLevel, float partialTicks, int scaledWidth, int scaledHeight) {
		String level = String.valueOf(this.mc.player.experienceLevel);

		int xOffset = ((HudModern) this.main.huds.get("modern")).getPosX();
		int width = this.mc.fontRenderer.getStringWidth(level);
		if (width < 16)
			width = 16;
		if (width < xOffset)
			width = xOffset;
		else
			((HudModern) this.main.huds.get("modern")).setPosX(width);

		if (this.mc.fontRenderer.getStringWidth(level) > (width + 2))
			width = this.mc.fontRenderer.getStringWidth(level) + 2;

		RenderSystem.enableAlphaTest();
		RenderSystem.disableBlend();

		int posX = (this.settings.getBoolValue(Settings.render_player_face) ? 23 : 2)
				+ this.settings.getPositionValue(Settings.level_position)[0];
		int posY = ((this.settings.getBoolValue(Settings.show_numbers_health)
				&& this.settings.getBoolValue(Settings.show_numbers_food)) ? 22 : 26)
				+ this.settings.getPositionValue(Settings.level_position)[1];

		if (this.settings.getStringValue(Settings.clock_time_format) == "time.24"
				|| !this.settings.getBoolValue(Settings.render_player_face)) {
			drawRect(posX, posY, width, 7, 0xA0000000);
		} else {
			drawRect(26 + this.settings.getPositionValue(Settings.level_position)[0], posY, width, 7, 0xA0000000);
		}
		GL11.glScaled(0.5D, 0.5D, 0.5D);

		if (this.settings.getStringValue(Settings.clock_time_format) == "time.24"
				|| !this.settings.getBoolValue(Settings.render_player_face)) {
			gui.drawCenteredString(this.mc.fontRenderer, level, (posX * 2) + width, posY * 2 + 3, 0x80FF20);
		} else {
			gui.drawCenteredString(this.mc.fontRenderer, level,
					70 + this.settings.getPositionValue(Settings.level_position)[0] * 2, posY * 2 + 3, 0x80FF20);
		}
		GL11.glScaled(2.0D, 2.0D, 2.0D);
		RenderSystem.enableBlend();
	}

}
