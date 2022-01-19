package com.skydonald.pdrpaddons.gui.hud.element.defaulthud;

import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.skydonald.pdrpaddons.gui.hud.element.HudElement;
import com.skydonald.pdrpaddons.gui.hud.element.HudElementType;
import com.skydonald.pdrpaddons.settings.Settings;

@OnlyIn(Dist.CLIENT)
public class HudElementJumpBarDefault extends HudElement {

	public HudElementJumpBarDefault() {
		super(HudElementType.JUMP_BAR, 0, 0, 0, 0, true);
	}

	@Override
	public boolean checkConditions() {
		return this.mc.player.getRidingEntity() instanceof LivingEntity
				&& (!this.settings.getBoolValue(Settings.limit_jump_bar) || this.mc.player.getHorseJumpPower() > 0F);
	}

	@Override
	public void drawElement(AbstractGui gui, float zLevel, float partialTicks, int scaledWidth, int scaledHeight) {
		int height = scaledHeight + this.settings.getPositionValue(Settings.jump_bar_position)[1];
		int center = (scaledWidth / 2) + this.settings.getPositionValue(Settings.jump_bar_position)[0];
		float jumpPower = this.mc.player.getHorseJumpPower();
		int value = (int) (jumpPower * 100.0F);
		drawCustomBar(center - 70, height - 80, 141, 10, value / 100.0D * 100.0D,
				this.settings.getIntValue(Settings.color_jump_bar),
				offsetColorPercent(this.settings.getIntValue(Settings.color_jump_bar), OFFSET_PERCENT));
	}

}
