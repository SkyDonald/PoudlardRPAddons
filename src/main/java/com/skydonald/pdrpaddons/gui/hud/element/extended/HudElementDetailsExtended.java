package com.skydonald.pdrpaddons.gui.hud.element.extended;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.skydonald.pdrpaddons.gui.hud.element.vanilla.HudElementDetailsVanilla;

@OnlyIn(Dist.CLIENT)
public class HudElementDetailsExtended extends HudElementDetailsVanilla {

	public HudElementDetailsExtended() {
		super();
		this.typeOffset = 10;
	}
}
