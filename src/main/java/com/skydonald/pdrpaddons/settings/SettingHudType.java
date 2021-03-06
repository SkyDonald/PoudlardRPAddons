package com.skydonald.pdrpaddons.settings;

import java.util.Set;

import com.skydonald.pdrpaddons.main.PoudlardRPAddons;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.skydonald.pdrpaddons.gui.hud.element.HudElementType;

@OnlyIn(Dist.CLIENT)
public class SettingHudType extends Setting {

	public final String defaultValue;
	public String value;

	public SettingHudType(String ID, String value) {
		super(ID);
		this.defaultValue = value;
		this.value = this.defaultValue;
	}

	public SettingHudType(String ID, HudElementType type, int defaultValueId) {
		super(ID, type);
		this.defaultValue = this.value;
		this.value = this.defaultValue;
	}

	@Override
	public void increment() {
		Set<String> huds = PoudlardRPAddons.instance.huds.keySet();
		String[] keys = huds.toArray(new String[huds.size()]);
		int size = keys.length;
		for (int n = 0; n < size; n++) {
			if (keys[n].equals(this.value)) {
				n++;
				if (n == size)
					n = 0;
				this.value = keys[n];
				return;
			}
		}
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
		if (o instanceof String) {
			this.value = (String) o;
		}
		return this;
	}

	@Override
	public Object getDefaultValue() {
		return this.defaultValue;
	}
}
