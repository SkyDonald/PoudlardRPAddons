package com.skydonald.pdrpaddons.main;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import com.skydonald.pdrpaddons.event.ClientEventHandler;
import com.skydonald.pdrpaddons.gui.hud.Hud;
import com.skydonald.pdrpaddons.gui.hud.HudDefault;
import com.skydonald.pdrpaddons.gui.hud.HudExtendedWidget;
import com.skydonald.pdrpaddons.gui.hud.HudFullTexture;
import com.skydonald.pdrpaddons.gui.hud.HudHotbarWidget;
import com.skydonald.pdrpaddons.gui.hud.HudModern;
import com.skydonald.pdrpaddons.gui.hud.HudVanilla;
import com.skydonald.pdrpaddons.settings.Settings;

@OnlyIn(Dist.CLIENT)
@Mod("pdrpaddons")
public class PoudlardRPAddons {

	public static PoudlardRPAddons instance;

	public static boolean[] renderDetailsAgain = { false, false, false };

	public Settings settings;

	/** Map of all registered HUDs */
	public Map<String, Hud> huds = new LinkedHashMap<>();

	public PoudlardRPAddons() {
		instance = this;
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
	}

	private void setup(final FMLCommonSetupEvent event) {
		settings = new Settings();
		registerHud(new HudVanilla(Minecraft.getInstance(), "vanilla", "Vanilla"));
		registerHud(new HudDefault(Minecraft.getInstance(), "default", "Default"));
		registerHud(new HudExtendedWidget(Minecraft.getInstance(), "extended", "Extended Widget"));
		registerHud(new HudFullTexture(Minecraft.getInstance(), "texture", "Full Texture"));
		registerHud(new HudHotbarWidget(Minecraft.getInstance(), "hotbar", "Hotbar Widget"));
		registerHud(new HudModern(Minecraft.getInstance(), "modern", "Modern Style"));

		if (!isHudKeyValid(settings.getStringValue(Settings.hud_type))) {
			settings.setSetting(Settings.hud_type, "vanilla");
		}
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
		ClientEventHandler.init();
		new RenderOverlay();
	}

	/**
	 * Register a new HUD
	 * 
	 * @param hud
	 *            the hud to be registered
	 */
	public void registerHud(Hud hud) {
		huds.put(hud.getHudKey(), hud);
	}

	/** Returns the active HUD */
	public Hud getActiveHud() {
		return huds.get(settings.getStringValue(Settings.hud_type));
	}

	/** Returns the vanilla HUD */
	public Hud getVanillaHud() {
		return this.huds.get("vanilla");
	}

	public boolean isVanillaHud() {
		return Objects.equals(settings.getStringValue(Settings.hud_type), "vanilla");
	}

	/** Checks if a Hud with the specified key is registered */
	public boolean isHudKeyValid(String key) {
		return huds.containsKey(key);
	}
}
