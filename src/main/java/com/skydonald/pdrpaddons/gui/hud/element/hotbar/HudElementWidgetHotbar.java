package com.skydonald.pdrpaddons.gui.hud.element.hotbar;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.AbstractGui;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.skydonald.pdrpaddons.gui.hud.element.HudElement;
import com.skydonald.pdrpaddons.gui.hud.element.HudElementType;
import com.skydonald.pdrpaddons.main.PoudlardRPAddons;
import com.skydonald.pdrpaddons.settings.Settings;

@OnlyIn(Dist.CLIENT)
public class HudElementWidgetHotbar extends HudElement {

	public HudElementWidgetHotbar() {
		super(HudElementType.WIDGET, 0, 0, 0, 0, true);
	}

	@Override
	public boolean checkConditions() {
		return this.mc.playerController.shouldDrawHUD();
	}

	@Override
	public void drawElement(AbstractGui gui, float zLevel, float partialTicks, int scaledWidth, int scaledHeight) {
		bind(INTERFACE);
		int posX = this.settings.getPositionValue(Settings.widget_position)[0];
		int posY = scaledHeight + this.settings.getPositionValue(Settings.widget_position)[1];
		gui.blit(posX + (this.settings.getBoolValue(Settings.render_player_face) ? 50 : 26), posY - 16 - 52 + 9, 0, 172,
				251, 48);

		int facePosX = this.settings.getPositionValue(Settings.face_position)[0];
		int facePosY = this.settings.getPositionValue(Settings.face_position)[1];
		if (PoudlardRPAddons.instance.settings.getBoolValue(Settings.render_player_face)) {
			gui.blit(posX + facePosX, posY - 16 - 52 + 7 + facePosY, 164, 20, 50, 52);
			bind(getPlayerSkin(this.mc.player));
			GL11.glScaled(0.5D, 0.5D, 0.5D);
			gui.blit(posX * 2 + 34 + facePosX * 2, posY * 2 - 88 + facePosY * 2, 32, 32, 32, 32);
			gui.blit(posX * 2 + 34 + facePosX * 2, posY * 2 - 88 + facePosY * 2, 160, 32, 32, 32);
			GL11.glScaled(2.0D, 2.0D, 2.0D);
			this.mc.getTextureManager().bindTexture(AbstractGui.GUI_ICONS_LOCATION);
		} else {
			gui.blit(posX, posY - 12 - 52 + 7, 214, 58, 26, 42);
			this.mc.getTextureManager().bindTexture(AbstractGui.GUI_ICONS_LOCATION);
		}
	}
}
