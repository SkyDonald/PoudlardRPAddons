package com.skydonald.pdrpaddons.gui.hud;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.skydonald.pdrpaddons.gui.hud.element.HudElement;
import com.skydonald.pdrpaddons.gui.hud.element.extended.HudElementClockExtended;
import com.skydonald.pdrpaddons.gui.hud.element.extended.HudElementDetailsExtended;
import com.skydonald.pdrpaddons.gui.hud.element.extended.HudElementExperienceExtended;
import com.skydonald.pdrpaddons.gui.hud.element.extended.HudElementFoodExtended;
import com.skydonald.pdrpaddons.gui.hud.element.extended.HudElementHealthExtended;
import com.skydonald.pdrpaddons.gui.hud.element.extended.HudElementHealthMountExtended;
import com.skydonald.pdrpaddons.gui.hud.element.extended.HudElementLevelExtended;
import com.skydonald.pdrpaddons.gui.hud.element.extended.HudElementWidgetExtended;

@OnlyIn(Dist.CLIENT)
public class HudExtendedWidget extends HudDefault {

	public HudExtendedWidget(Minecraft mc, String hudKey, String hudName) {
		super(mc, hudKey, hudName);
	}

	@Override
	public HudElement setElementExperience() {
		return new HudElementExperienceExtended();
	}

	@Override
	public HudElement setElementHealthMount() {
		return new HudElementHealthMountExtended();
	}

	@Override
	public HudElement setElementHotbar() {
		return null;
	}

	@Override
	public HudElement setElementFood() {
		return new HudElementFoodExtended();
	}

	@Override
	public HudElement setElementHealth() {
		return new HudElementHealthExtended();
	}

	@Override
	public HudElement setElementWidget() {
		return new HudElementWidgetExtended();
	}

	@Override
	public HudElement setElementClock() {
		return new HudElementClockExtended();
	}

	@Override
	public HudElement setElementDetails() {
		return new HudElementDetailsExtended();
	}

	@Override
	public HudElement setElementLevel() {
		return new HudElementLevelExtended();
	}
}
