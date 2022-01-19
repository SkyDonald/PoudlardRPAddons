package com.skydonald.pdrpaddons.gui.hud.element.vanilla;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.skydonald.pdrpaddons.gui.hud.element.HudElement;
import com.skydonald.pdrpaddons.gui.hud.element.HudElementType;
import com.skydonald.pdrpaddons.settings.Settings;

@OnlyIn(Dist.CLIENT)
public class HudElementCompassVanilla extends HudElement {

	public HudElementCompassVanilla() {
		super(HudElementType.COMPASS, 0, 0, 0, 0, true);
	}

	@Override
	public boolean checkConditions() {
		return this.settings.getBoolValue(Settings.enable_compass)
				&& (!this.settings.getBoolValue(Settings.enable_immersive_compass) || this.mc.player.inventory.hasItemStack(new ItemStack(Items.COMPASS)));
	}

	@Override
	public void drawElement(AbstractGui gui, float zLevel, float partialTicks, int scaledWidth, int scaledHeight) {
		int width = scaledWidth / 2 + this.settings.getPositionValue(Settings.compass_position)[0];
		int posY = this.settings.getPositionValue(Settings.compass_position)[1];
		int swapSides = this.settings.getBoolValue(Settings.invert_compass) ? -1 : 1;
		int rotation = Math.round(((this.mc.player.rotationYaw % 360) / 360) * 200);
		if (rotation < 0)
			rotation = 200 + rotation;

		bind(INTERFACE);
		gui.blit(width - 56, posY, 34, 234, 112, 9);
		if (rotation > 0 && rotation <= 100) {
			gui.drawCenteredString(this.mc.fontRenderer, "W", width + (50 * swapSides) - (rotation * swapSides),
					posY + 1, -1);
		}

		if (rotation > 25 && rotation <= 125) {
			gui.drawCenteredString(this.mc.fontRenderer, ".", width + (75 * swapSides) - (rotation * swapSides),
					posY - 2, -1);
		}

		if (rotation > 50 && rotation <= 150) {
			gui.drawCenteredString(this.mc.fontRenderer, "N", width + (100 * swapSides) - (rotation * swapSides),
					posY + 1, this.settings.getBoolValue(Settings.enable_compass_color) ? 0xE60909 : -1);
		}

		if (rotation > 75 && rotation <= 175) {
			gui.drawCenteredString(this.mc.fontRenderer, ".", width + (125 * swapSides) - (rotation * swapSides),
					posY - 2, -1);
		}

		if (rotation > 100 && rotation <= 200) {
			gui.drawCenteredString(this.mc.fontRenderer, "E", width + (150 * swapSides) - (rotation * swapSides),
					posY + 1, -1);
		}

		if (rotation >= 125) {
			gui.drawCenteredString(this.mc.fontRenderer, ".", width + (175 * swapSides) - (rotation * swapSides),
					posY - 2, -1);
		} else if (rotation <= 25) {
			gui.drawCenteredString(this.mc.fontRenderer, ".", width - (25 * swapSides) - (rotation * swapSides),
					posY - 2, -1);
		}

		if (rotation >= 150) {
			gui.drawCenteredString(this.mc.fontRenderer, "S", width + (200 * swapSides) - (rotation * swapSides),
					posY + 1, -1);
		} else if (rotation <= 50) {
			gui.drawCenteredString(this.mc.fontRenderer, "S", width - (rotation * swapSides), posY + 1, -1);
		}

		if (rotation >= 175) {
			gui.drawCenteredString(this.mc.fontRenderer, ".", width + (225 * swapSides) - (rotation * swapSides),
					posY - 2, -1);
		} else if (rotation <= 75) {
			gui.drawCenteredString(this.mc.fontRenderer, ".", width + (25 * swapSides) - (rotation * swapSides),
					posY - 2, -1);
		}

		if (this.settings.getBoolValue(Settings.enable_compass_coordinates)) {
			if (this.settings.getBoolValue(Settings.reduce_size))
				RenderSystem.scaled(0.5D, 0.5D, 0.5D);
			int[] pos = getPlayerPos();
			gui.drawString(this.mc.fontRenderer, String.valueOf(pos[0]),
					(width - 50) * (this.settings.getBoolValue(Settings.reduce_size) ? 2 : 1),
					(posY + 11) * (this.settings.getBoolValue(Settings.reduce_size) ? 2 : 1), -1);
			gui.drawCenteredString(this.mc.fontRenderer, String.valueOf(pos[1]),
					width * (this.settings.getBoolValue(Settings.reduce_size) ? 2 : 1),
					(posY + 11) * (this.settings.getBoolValue(Settings.reduce_size) ? 2 : 1), -1);
			gui.drawString(this.mc.fontRenderer, String.valueOf(pos[2]),
					(width + 50) * (this.settings.getBoolValue(Settings.reduce_size) ? 2 : 1)
							- mc.fontRenderer.getStringWidth(String.valueOf(pos[2])),
					(posY + 11) * (this.settings.getBoolValue(Settings.reduce_size) ? 2 : 1), -1);
			if (this.settings.getBoolValue(Settings.reduce_size))
				RenderSystem.scaled(2D, 2D, 2D);
		}
	}

	public static int[] getPlayerPos() {
		Minecraft mc = Minecraft.getInstance();
		int[] pos = new int[3];
		pos[0] = mc.player.getPosition().getX();
		pos[1] = mc.player.getPosition().getY();
		pos[2] = mc.player.getPosition().getZ();
		return pos;
	}
}
