package com.skydonald.pdrpaddons.gui.hud;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.skydonald.pdrpaddons.gui.hud.element.HudElement;
import com.skydonald.pdrpaddons.gui.hud.element.HudElementEmpty;
import com.skydonald.pdrpaddons.gui.hud.element.vanilla.HudElementClockVanilla;
import com.skydonald.pdrpaddons.gui.hud.element.vanilla.HudElementCompassVanilla;
import com.skydonald.pdrpaddons.gui.hud.element.vanilla.HudElementDetailsVanilla;
import com.skydonald.pdrpaddons.gui.hud.element.vanilla.HudElementEntityInspectVanilla;
import com.skydonald.pdrpaddons.gui.hud.element.vanilla.HudElementStatusEffectsVanilla;

@OnlyIn(Dist.CLIENT)
public class HudVanilla extends Hud {

	public HudVanilla(Minecraft mc, String hudKey, String hudName) {
		super(mc, hudKey, hudName);
	}

	@Override
	public HudElement setElementHotbar() {
		return null;
	}

	@Override
	public HudElement setElementHealth() {
		return null;
	}

	@Override
	public HudElement setElementFood() {
		return null;
	}

	@Override
	public HudElement setElementArmor() {
		return null;
	}

	@Override
	public HudElement setElementAir() {
		return null;
	}

	@Override
	public HudElement setElementExperience() {
		return null;
	}

	@Override
	public HudElement setElementLevel() {
		return null;
	}

	@Override
	public HudElement setElementJumpBar() {
		return null;
	}

	@Override
	public HudElement setElementHealthMount() {
		return null;
	}

	@Override
	public HudElement setElementClock() {
		return new HudElementClockVanilla();
	}

	@Override
	public HudElement setElementDetails() {
		return new HudElementDetailsVanilla();
	}

	@Override
	public HudElement setElementWidget() {
		return new HudElementEmpty();
	}

	@Override
	protected HudElement setElementCompass() {
		return new HudElementCompassVanilla();
	}

	@Override
	protected HudElement setElementEntityInspect() {
		return new HudElementEntityInspectVanilla();
	}

	@Override
	protected HudElement setElementStatusEffects() {
		return new HudElementStatusEffectsVanilla();
	}
}
