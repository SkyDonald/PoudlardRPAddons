package com.skydonald.pdrpaddons.settings;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.skydonald.pdrpaddons.gui.hud.element.HudElementType;

@OnlyIn(Dist.CLIENT)
public class SettingBoolean extends Setting {

	public final boolean defaultValue;
	public boolean value;

	public SettingBoolean(String ID, boolean defaultValue) {
		super(ID);
		this.defaultValue = defaultValue;
		this.value = defaultValue;
	}

	public SettingBoolean(String ID, HudElementType type, boolean defaultValue) {
		super(ID, type);
		this.defaultValue = defaultValue;
		this.value = defaultValue;
	}

	@Override
	public void increment() {
		this.value = !this.value;
	}

	@Override
	public Object getValue() {
		return this.value;
	}

	@Override
	public void resetValue() {
		this.value = this.defaultValue;
	}

	@Override
	public Setting setValue(Object o) {
		if (o instanceof Boolean) {
			this.value = (Boolean) o;
		}
		return this;
	}

	@Override
	public Object getDefaultValue() {
		return this.defaultValue;
	}

}
