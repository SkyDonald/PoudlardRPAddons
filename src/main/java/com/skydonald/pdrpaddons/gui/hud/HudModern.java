package com.skydonald.pdrpaddons.gui.hud;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.skydonald.pdrpaddons.gui.hud.element.HudElement;
import com.skydonald.pdrpaddons.gui.hud.element.modern.HudElementAirModern;
import com.skydonald.pdrpaddons.gui.hud.element.modern.HudElementArmorModern;
import com.skydonald.pdrpaddons.gui.hud.element.modern.HudElementClockModern;
import com.skydonald.pdrpaddons.gui.hud.element.modern.HudElementCompassModern;
import com.skydonald.pdrpaddons.gui.hud.element.modern.HudElementDetailsModern;
import com.skydonald.pdrpaddons.gui.hud.element.modern.HudElementEntityInspectModern;
import com.skydonald.pdrpaddons.gui.hud.element.modern.HudElementExperienceModern;
import com.skydonald.pdrpaddons.gui.hud.element.modern.HudElementFoodModern;
import com.skydonald.pdrpaddons.gui.hud.element.modern.HudElementHealthModern;
import com.skydonald.pdrpaddons.gui.hud.element.modern.HudElementHealthMountModern;
import com.skydonald.pdrpaddons.gui.hud.element.modern.HudElementHotbarModern;
import com.skydonald.pdrpaddons.gui.hud.element.modern.HudElementJumpBarModern;
import com.skydonald.pdrpaddons.gui.hud.element.modern.HudElementLevelModern;
import com.skydonald.pdrpaddons.gui.hud.element.modern.HudElementWidgetModern;

@OnlyIn(Dist.CLIENT)
public class HudModern extends HudDefault {

	/** offset position for element */
	private int posX = 0;

	public HudModern(Minecraft mc, String hudKey, String hudName) {
		super(mc, hudKey, hudName);
	}

	/** get the offset position */
	public int getPosX() {
		return this.posX;
	}

	/** set the offset position */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	@Override
	public HudElement setElementAir() {
		return new HudElementAirModern();
	}

	@Override
	public HudElement setElementArmor() {
		return new HudElementArmorModern();
	}

	@Override
	public HudElement setElementClock() {
		return new HudElementClockModern();
	}

	@Override
	public HudElement setElementDetails() {
		return new HudElementDetailsModern();
	}

	@Override
	public HudElement setElementExperience() {
		return new HudElementExperienceModern();
	}

	@Override
	public HudElement setElementFood() {
		return new HudElementFoodModern();
	}

	@Override
	public HudElement setElementHealth() {
		return new HudElementHealthModern();
	}

	@Override
	public HudElement setElementHealthMount() {
		return new HudElementHealthMountModern();
	}

	@Override
	public HudElementHotbarModern setElementHotbar() {
		return new HudElementHotbarModern();
	}

	@Override
	public HudElement setElementJumpBar() {
		return new HudElementJumpBarModern();
	}

	@Override
	public HudElement setElementLevel() {
		return new HudElementLevelModern();
	}

	@Override
	public HudElement setElementWidget() {
		return new HudElementWidgetModern();
	}

	@Override
	protected HudElement setElementCompass() {
		return new HudElementCompassModern();
	}

	@Override
	protected HudElement setElementEntityInspect() {
		return new HudElementEntityInspectModern();
	}
}
