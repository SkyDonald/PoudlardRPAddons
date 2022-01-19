package com.skydonald.pdrpaddons.gui.hud.element.modern;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.skydonald.pdrpaddons.gui.hud.HudModern;
import com.skydonald.pdrpaddons.gui.hud.element.HudElement;
import com.skydonald.pdrpaddons.gui.hud.element.HudElementType;
import com.skydonald.pdrpaddons.settings.Settings;

@OnlyIn(Dist.CLIENT)
public class HudElementHealthMountModern extends HudElement {

	public HudElementHealthMountModern() {
		super(HudElementType.HEALTH_MOUNT, 0, 0, 0, 0, false);
		parent = HudElementType.WIDGET;
	}

	@Override
	public boolean checkConditions() {
		return this.mc.player.getRidingEntity() instanceof LivingEntity && this.mc.playerController.shouldDrawHUD();
	}

	@Override
	public void drawElement(AbstractGui gui, float zLevel, float partialTicks, int scaledWidth, int scaledHeight) {
		LivingEntity mount = (LivingEntity) this.mc.player.getRidingEntity();
		int health = (int) Math.ceil(mount.getHealth());
		int healthMax = (int) mount.getMaxHealth();
		if (health > healthMax)
			health = healthMax;
		int xOffset = ((HudModern) this.main.huds.get("modern")).getPosX();

		String stringHealth = this.settings.getBoolValue(Settings.mount_health_percentage)
				? (int) Math.floor((double) health / (double) healthMax * 100) + "%"
				: health + "/" + healthMax;

		int posX = (this.settings.getBoolValue(Settings.render_player_face) ? 26 : 4)
				+ (this.settings.getBoolValue(Settings.show_numbers_health) ? xOffset - 2 : -2)
				+ this.settings.getPositionValue(Settings.mount_health_position)[0];
		int posY = this.settings.getPositionValue(Settings.mount_health_position)[1];
		if (this.settings.getBoolValue(Settings.show_numbers_health)) {
			int width2 = this.mc.fontRenderer.getStringWidth(stringHealth) / 2;
			drawRect(posX, 24 + posY, width2 + 4, 5, 0xA0000000);
			GL11.glScaled(0.5D, 0.5D, 0.5D);
			gui.drawString(this.mc.fontRenderer, stringHealth, posX * 2 + 4, 48 + posY * 2, -1);
			GL11.glScaled(2.0D, 2.0D, 2.0D);
		}

		drawTetragon(posX, posX, 21 + posY, 21 + posY, 58, 54, 3, 3, 0xA0000000);
		drawTetragon(posX + 2, posX + 2, 21 + posY, 21 + posY, (int) (((double) health / (double) healthMax) * 53),
				(int) (((double) health / (double) healthMax) * 53 - 2), 1, 1,
				this.settings.getIntValue(Settings.color_health));

	}

}
