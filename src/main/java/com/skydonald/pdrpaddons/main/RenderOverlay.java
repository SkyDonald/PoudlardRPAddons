package com.skydonald.pdrpaddons.main;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.skydonald.pdrpaddons.gui.hud.HudHotbarWidget;
import com.skydonald.pdrpaddons.gui.hud.element.HudElementType;
import com.skydonald.pdrpaddons.settings.Settings;

@OnlyIn(Dist.CLIENT)
public class RenderOverlay {

    private final PoudlardRPAddons main;
    private final Minecraft mc;

    public RenderOverlay() {
        main = PoudlardRPAddons.instance;
        mc = Minecraft.getInstance();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onGameOverlayRender(RenderGameOverlayEvent event) {
        ElementType type = event.getType();
        switch (type) {
            case AIR:
                if (!shouldRenderVanilla(HudElementType.AIR))
                    event.setCanceled(true);
                break;
            case ARMOR:
                if (!shouldRenderVanilla(HudElementType.ARMOR))
                    event.setCanceled(true);
                break;
            case EXPERIENCE:
                if (!shouldRenderVanilla(HudElementType.EXPERIENCE))
                    event.setCanceled(true);
                break;
            case FOOD:
                if (!shouldRenderVanilla(HudElementType.FOOD))
                    event.setCanceled(true);
                break;
            case HEALTH:
                if (!shouldRenderVanilla(HudElementType.HEALTH))
                    event.setCanceled(true);
                break;
            case HEALTHMOUNT:
                if (!shouldRenderVanilla(HudElementType.HEALTH_MOUNT))
                    event.setCanceled(true);
                break;
            case HOTBAR:
                if (!shouldRenderVanilla(HudElementType.HOTBAR))
                    event.setCanceled(true);
                break;
            case JUMPBAR:
                if (!shouldRenderVanilla(HudElementType.JUMP_BAR))
                    event.setCanceled(true);
                break;
            case POTION_ICONS:
                if (!shouldRenderVanilla(HudElementType.STATUS_EFFECTS)) {
                    event.setCanceled(true);
                }
                break;
            default:
                break;

        }
    }

    @SubscribeEvent
    public void onGameOverlayRender(RenderGameOverlayEvent.Pre event) {
        switch (event.getType()) {
            case ALL:
                renderOverlay(event.getPartialTicks());
                break;
            case AIR:
                if (preventEventType(HudElementType.AIR))
                    event.setCanceled(true);
                break;
            case ARMOR:
                if (preventEventType(HudElementType.ARMOR))
                    event.setCanceled(true);
                break;
            case EXPERIENCE:
                if (preventEventType(HudElementType.EXPERIENCE))
                    event.setCanceled(true);
                break;
            case FOOD:
                if (preventEventType(HudElementType.FOOD))
                    event.setCanceled(true);
                break;
            case HEALTH:
                if (preventEventType(HudElementType.HEALTH))
                    event.setCanceled(true);
                break;
            case HEALTHMOUNT:
                if (preventEventType(HudElementType.HEALTH_MOUNT))
                    event.setCanceled(true);
                break;
            case HOTBAR:
                if (preventEventType(HudElementType.HOTBAR))
                    event.setCanceled(true);
                break;
            case JUMPBAR:
                if (preventEventType(HudElementType.JUMP_BAR))
                    event.setCanceled(true);
                break;
            case POTION_ICONS:
                if (preventEventType(HudElementType.STATUS_EFFECTS))
                    event.setCanceled(true);
                break;
            default:
                break;

        }
    }

    @SubscribeEvent
    public void onChatRender(RenderGameOverlayEvent.Chat event) {
        if (main.getActiveHud() instanceof HudHotbarWidget) {
            event.setPosY(event.getPosY() - 22);
        }
    }

    private void renderOverlay(float partialTicks) {
        drawElement(HudElementType.WIDGET, partialTicks);
        drawElement(HudElementType.CLOCK, partialTicks);
        drawElement(HudElementType.DETAILS, partialTicks);
        drawElement(HudElementType.COMPASS, partialTicks);
        drawElement(HudElementType.ENTITY_INSPECT, partialTicks);
        if (!shouldRenderVanilla(HudElementType.HEALTH))
            drawElement(HudElementType.HEALTH, partialTicks);
        if (!shouldRenderVanilla(HudElementType.ARMOR))
            drawElement(HudElementType.ARMOR, partialTicks);
        if (!shouldRenderVanilla(HudElementType.FOOD))
            drawElement(HudElementType.FOOD, partialTicks);
        if (!shouldRenderVanilla(HudElementType.HEALTH_MOUNT))
            drawElement(HudElementType.HEALTH_MOUNT, partialTicks);
        if (!shouldRenderVanilla(HudElementType.AIR))
            drawElement(HudElementType.AIR, partialTicks);
        if (!shouldRenderVanilla(HudElementType.JUMP_BAR))
            drawElement(HudElementType.JUMP_BAR, partialTicks);
        if (!shouldRenderVanilla(HudElementType.EXPERIENCE)) {
            drawElement(HudElementType.EXPERIENCE, partialTicks);
            drawElement(HudElementType.LEVEL, partialTicks);
        }
        if (!shouldRenderVanilla(HudElementType.HOTBAR))
            drawElement(HudElementType.HOTBAR, partialTicks);
        if (!shouldRenderVanilla(HudElementType.STATUS_EFFECTS)) {
            drawElement(HudElementType.STATUS_EFFECTS, partialTicks);
        }
    }

    /**
     * Draw the specified HudElement of the HudElementType from the active Hud
     * 
     * @param type         the HudElementType to be rendered
     * @param partialTicks the partialTicks to be used for animations
     */
    private void drawElement(HudElementType type, float partialTicks) {

        if (main.getActiveHud().checkElementConditions(type)) {
            if (!preventElementRenderType(type)) {
                bind(AbstractGui.GUI_ICONS_LOCATION);
                RenderSystem.pushMatrix();
                RenderSystem.enableBlend();
                main.getActiveHud().drawElement(type, mc.ingameGUI, partialTicks, partialTicks,
                        mc.getMainWindow().getScaledWidth(),
                        mc.getMainWindow().getScaledHeight());
                RenderSystem.popMatrix();
            }

        }
    }

    /**
     * Checks if the HudElementType has a setting to prevent it's rendering and if
     * it is activated
     */
    private boolean preventElementRenderType(HudElementType type) {
        String id = Settings.prevent_element_render + "_" + type.name().toLowerCase();
        if (main.settings.doesSettingExist(id)) {
            return main.settings.getBoolValue(id);
        }
        return false;
    }

    private boolean shouldRenderVanilla(HudElementType type) {
        return isVanillaElement(type) || forceRenderTypeVanilla(type);
    }

    /**
     * Checks if the HudElementType has a setting to force the vanilla hud element
     * to be rendered and if it is activated
     */
    private boolean forceRenderTypeVanilla(HudElementType type) {
        String id = Settings.render_vanilla + "_" + type.name().toLowerCase();
        if (main.settings.doesSettingExist(id)) {
            return main.settings.getBoolValue(id);
        }
        return false;
    }

    /**
     * Checks if the HudElementType has a setting to prevent the forge event and if
     * it is activated
     */
    private boolean preventEventType(HudElementType type) {
        String id = Settings.prevent_event + "_" + type.name().toLowerCase();
        if (main.settings.doesSettingExist(id))
            return main.settings.getBoolValue(id);
        return false;
    }

    private void bind(ResourceLocation res) {
        mc.getTextureManager().bindTexture(res);
    }

    private boolean isVanillaElement(HudElementType type) {
        return main.getActiveHud().isVanillaElement(type);
    }
}
