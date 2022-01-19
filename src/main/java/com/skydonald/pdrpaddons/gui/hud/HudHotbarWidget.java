package com.skydonald.pdrpaddons.gui.hud;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.skydonald.pdrpaddons.gui.hud.element.HudElement;
import com.skydonald.pdrpaddons.gui.hud.element.hotbar.HudElementArmorHotbar;
import com.skydonald.pdrpaddons.gui.hud.element.hotbar.HudElementFoodHotbar;
import com.skydonald.pdrpaddons.gui.hud.element.hotbar.HudElementHealthHotbar;
import com.skydonald.pdrpaddons.gui.hud.element.hotbar.HudElementHealthMountHotbar;
import com.skydonald.pdrpaddons.gui.hud.element.hotbar.HudElementHotbarHotbar;
import com.skydonald.pdrpaddons.gui.hud.element.hotbar.HudElementLevelHotbar;
import com.skydonald.pdrpaddons.gui.hud.element.hotbar.HudElementWidgetHotbar;

@OnlyIn(Dist.CLIENT)
public class HudHotbarWidget extends HudDefault {

	public HudHotbarWidget(Minecraft mc, String hudKey, String hudName) {
		super(mc, hudKey, hudName);
		this.chatOffset = -22;
	}

	@Override
	public HudElement setElementArmor() {
		return new HudElementArmorHotbar();
	}

	@Override
	public HudElement setElementFood() {
		return new HudElementFoodHotbar();
	}

	@Override
	public HudElement setElementHealth() {
		return new HudElementHealthHotbar();
	}

	@Override
	public HudElement setElementHealthMount() {
		return new HudElementHealthMountHotbar();
	}

	@Override
	public HudElement setElementLevel() {
		return new HudElementLevelHotbar();
	}

	@Override
	public HudElement setElementWidget() {
		return new HudElementWidgetHotbar();
	}

	@Override
	public HudElement setElementHotbar() {
		return new HudElementHotbarHotbar();
	}
}
