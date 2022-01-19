package com.skydonald.pdrpaddons.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.IngameMenuScreen;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.skydonald.pdrpaddons.gui.GuiSettingsMod;
import com.skydonald.pdrpaddons.main.PoudlardRPAddons;

@OnlyIn(Dist.CLIENT)
public class ClientEventHandler {

    public static void init() {
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }

    @SubscribeEvent
    public void onGuiInit(InitGuiEvent event) {
        if (event.getGui() instanceof MainMenuScreen || event.getGui() instanceof IngameMenuScreen) {
            Minecraft mc = Minecraft.getInstance();
            String s = I18n.format("name.pdrp");
            event.addWidget(new Button(event.getGui().width - (mc.fontRenderer.getStringWidth(s) + 8), 0,
                    mc.fontRenderer.getStringWidth(s) + 8, 20, s, button -> {
                        mc.displayGuiScreen(new GuiSettingsMod(event.getGui(),
                                new TranslationTextComponent("gui.settings.pdrp")));
                    }));
        }
    }

    @SubscribeEvent
    public void onPlayerCloseContainer(PlayerContainerEvent.Close event) {
        PoudlardRPAddons.renderDetailsAgain[0] = true;
        PoudlardRPAddons.renderDetailsAgain[1] = true;
        PoudlardRPAddons.renderDetailsAgain[2] = true;
    }

}
