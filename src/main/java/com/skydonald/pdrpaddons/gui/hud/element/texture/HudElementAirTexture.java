package com.skydonald.pdrpaddons.gui.hud.element.texture;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.AbstractGui;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.skydonald.pdrpaddons.gui.hud.element.HudElement;
import com.skydonald.pdrpaddons.gui.hud.element.HudElementType;
import com.skydonald.pdrpaddons.settings.Settings;

@OnlyIn(Dist.CLIENT)
public class HudElementAirTexture extends HudElement {

	public HudElementAirTexture() {
		super(HudElementType.AIR, 0, 0, 0, 0, true);
	}

	@Override
	public boolean checkConditions() {
		return (this.mc.player.areEyesInFluid(FluidTags.WATER) || this.mc.player.getAir() < this.mc.player.getMaxAir())
				&& this.mc.playerController.shouldDrawHUD();
	}

	@Override
	public void drawElement(AbstractGui gui, float zLevel, float partialTicks, int scaledWidth, int scaledHeight) {
		bind(INTERFACE);
		RenderSystem.color3f(1f, 1f, 1f);
		int height = scaledHeight + this.settings.getPositionValue(Settings.air_position)[1];
		int adjustedWidth = (scaledWidth / 2) + this.settings.getPositionValue(Settings.air_position)[0];
		int airAmount = this.mc.player.getAir();
		double maxAir = this.mc.player.getMaxAir();
		gui.blit(adjustedWidth - 70, height - 80, 0, 160, 141, 10);
		gui.blit(adjustedWidth - 70, height - 80, 0, 140, (int) (141.0D * (airAmount / maxAir)), 10);
		RenderSystem.color3f(1f, 1f, 1f);
		this.mc.getTextureManager().bindTexture(AbstractGui.GUI_ICONS_LOCATION);
	}

}
