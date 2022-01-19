package com.skydonald.pdrpaddons.gui.hud.element.texture;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.skydonald.pdrpaddons.gui.hud.element.HudElement;
import com.skydonald.pdrpaddons.gui.hud.element.HudElementType;
import com.skydonald.pdrpaddons.settings.Settings;

@OnlyIn(Dist.CLIENT)
public class HudElementExperienceTexture extends HudElement {

	public HudElementExperienceTexture() {
		super(HudElementType.EXPERIENCE, 0, 0, 0, 0, true);
		parent = HudElementType.WIDGET;
	}

	@Override
	public boolean checkConditions() {
		return this.mc.playerController.shouldDrawHUD();
	}

	@Override
	public void drawElement(AbstractGui gui, float zLevel, float partialTicks, int scaledWidth, int scaledHeight) {
		bind(INTERFACE);
		RenderSystem.color3f(1f, 1f, 1f);
		int exp = MathHelper.ceil(this.mc.player.xpBarCap() * this.mc.player.experience);
		int expCap = this.mc.player.xpBarCap();
		int posX = (this.settings.getBoolValue(Settings.render_player_face) ? 49 : 25)
				+ this.settings.getPositionValue(Settings.experience_position)[0];
		int posY = (this.settings.getBoolValue(Settings.render_player_face) ? 35 : 31)
				+ this.settings.getPositionValue(Settings.experience_position)[1];

		gui.blit(posX, posY, 0, 132, (int) (88.0D * (exp / (double) expCap)), 8);

		String stringExp = this.settings.getBoolValue(Settings.experience_percentage)
				? (int) Math.floor((double) exp / (double) expCap * 100) + "%"
				: exp + "/" + expCap;

		if (this.settings.getBoolValue(Settings.show_numbers_experience)) {
			RenderSystem.scaled(0.5D, 0.5D, 0.5D);
			gui.drawCenteredString(this.mc.fontRenderer, stringExp, posX * 2 + 88, posY * 2 + 4, -1);
			RenderSystem.scaled(2.0D, 2.0D, 2.0D);
		}
		RenderSystem.color3f(1f, 1f, 1f);
		this.mc.getTextureManager().bindTexture(AbstractGui.GUI_ICONS_LOCATION);
	}

}
