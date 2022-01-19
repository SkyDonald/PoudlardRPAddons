package com.skydonald.pdrpaddons.gui;

import net.minecraft.client.gui.widget.button.Button;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class GuiButtonLib extends Button{

	public GuiButtonLib(int x, int y, int widthIn, int heightIn, String buttonText, IPressable ip) {
		super(x, y, widthIn, heightIn, buttonText, ip);
	}
	
	public GuiButtonLib(int x, int y, String buttonText, IPressable ip) {
		super(x, y, 200, 20, buttonText, ip);
	}

	@Override
	public void render(int mouseX, int mouseY, float partial) {
		super.render(mouseX, mouseY, partial);
		this.drawButton(mouseX, mouseY);
	}
	
	public void drawButton(int mouseX, int mouseY){
	}
}
