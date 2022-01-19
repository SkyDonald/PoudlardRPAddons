package com.skydonald.pdrpaddons.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.skydonald.pdrpaddons.gui.TextFieldWidgetMod.ValueType;
import com.skydonald.pdrpaddons.gui.hud.element.HudElementType;
import com.skydonald.pdrpaddons.main.PoudlardRPAddons;
import com.skydonald.pdrpaddons.settings.SettingColor;
import com.skydonald.pdrpaddons.settings.SettingDouble;
import com.skydonald.pdrpaddons.settings.SettingPosition;
import com.skydonald.pdrpaddons.settings.Settings;

@OnlyIn(Dist.CLIENT)
public class GuiSettingsMod extends GuiScreenTooltip {

    /** The ModSettings instance */
    private Settings settings;

    /** The GuiScreen which lead to this GUI */
    private Screen parent;

    private String subSetting;

    private Map<String, List<TextFieldWidget>> textFields = new HashMap<>();

    private GuiSettingsMod instance;

    public GuiSettingsMod(Screen parent, String subSetting, ITextComponent titleIn) {
        super(titleIn);
        this.parent = parent;
        this.settings = PoudlardRPAddons.instance.settings;
        this.subSetting = subSetting;
        this.instance = this;
    }

    public GuiSettingsMod(Screen parent, ITextComponent titleIn) {
        super(titleIn);
        this.parent = parent;
        this.settings = PoudlardRPAddons.instance.settings;
        this.subSetting = "";
        this.instance = this;
    }

    @Override
    public void init() {
        FontRenderer fontRenderer = minecraft.fontRenderer;
        if(this.subSetting.equals("")) {
            GuiButtonTooltip guismallbutton = new GuiButtonTooltip(this.width / 2 - 155, this.height / 6 - 14, "general",
                    I18n.format("gui.pdrp.general"), button -> {
                GuiButtonTooltip b = (GuiButtonTooltip) button;
                if(b.enumOptions != null)
                    minecraft.displayGuiScreen(new GuiSettingsMod(instance, b.enumOptions, new TranslationTextComponent("gui.settings.pdrp")));
            }).setTooltip(I18n.format("tooltip.general"));
            this.addButton(guismallbutton);

            int count = 1;

            for(HudElementType type : HudElementType.values()) {
                List<String> settings = this.settings.getSettingsOf(type);
                if(!settings.isEmpty()) {
                    guismallbutton = new GuiButtonTooltip(this.width / 2 - 155 + count % 2 * 160, this.height / 6 - 14 + 20 * (count >> 1), type.name(),
                            I18n.format(type.getDisplayName()), button -> {
                        GuiButtonTooltip b = (GuiButtonTooltip) button;
                        if(b.enumOptions != null) {
                            minecraft.displayGuiScreen(new GuiSettingsMod(instance, b.enumOptions, new TranslationTextComponent("gui.settings.pdrp")));
                        }
                    }).setTooltip(I18n.format("tooltip.element"));
                    this.addButton(guismallbutton);
                    count++;
                }
            }
        } else {
            List<String> settingList = this.settings.getSettingsOf(this.subSetting);
            for(int i = 0; i < settingList.size(); i++) {
                if(this.settings.getSetting(settingList.get(i)) instanceof SettingPosition) {
                    String[] values = ((String) this.settings.getSetting(settingList.get(i)).getValue()).split("_");
                    List<TextFieldWidget> fields = new ArrayList<TextFieldWidget>();

                    GuiTextLabel settingLabel = new GuiTextLabel(this.width / 2 - 152 + i % 2 * 160, this.height / 6 - 8 + 20 * (i >> 1),
                            this.settings.getButtonString(settingList.get(i)));
                    labelList.add(settingLabel);

                    TextFieldWidgetMod xPos = new TextFieldWidgetMod(fontRenderer, ValueType.POSITION, this.width / 2 - 100 + i % 2 * 160,
                            this.height / 6 - 12 + 20 * (i >> 1), 45, 15, values[0]);
                    xPos.setText(values[0]);
                    xPos.setMaxStringLength(6);
                    this.children.add(xPos);
                    fields.add(xPos);

                    TextFieldWidgetMod yPos = new TextFieldWidgetMod(fontRenderer, ValueType.POSITION, this.width / 2 - 100 + i % 2 * 160 + 48,
                            this.height / 6 - 12 + 20 * (i >> 1), 45, 15, values[1]);
                    yPos.setText(values[1]);
                    yPos.setMaxStringLength(6);
                    this.children.add(yPos);
                    fields.add(yPos);

                    textFields.put(settingList.get(i), fields);
                } else if(this.settings.getSetting(settingList.get(i)) instanceof SettingDouble) {
                    List<TextFieldWidget> fields = new ArrayList<TextFieldWidget>();
                    GuiTextLabel scaleLabel = new GuiTextLabel(this.width / 2 - 151 + i % 2 * 160, this.height / 6 - 8 + 20 * (i >> 1),
                            this.settings.getButtonString(settingList.get(i)));
                    TextFieldWidget scale = new TextFieldWidgetMod(fontRenderer, ValueType.DOUBLE, this.width / 2 - 100 + i % 2 * 160 + 3,
                            this.height / 6 - 12 + 20 * (i >> 1), 90, 15, String.valueOf(this.settings.getDoubleValue(settingList.get(i))));
                    scale.setText(String.valueOf(this.settings.getDoubleValue(settingList.get(i))));
                    labelList.add(scaleLabel);
                    this.children.add(scale);
                    fields.add(scale);
                    textFields.put(settingList.get(i), fields);
                } else {
                    GuiButtonTooltip guismallbutton = new GuiButtonTooltip(this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 14 + 20 * (i >> 1),
                            settingList.get(i), this.settings.getButtonString(settingList.get(i)), button -> {
                        GuiButtonTooltip b = (GuiButtonTooltip) button;
                        if(b.enumOptions != null) {
                            if(settings.getSetting(b.enumOptions) instanceof SettingColor) {
                                minecraft.displayGuiScreen(
                                        new GuiSettingsModColor(instance, b.enumOptions, new TranslationTextComponent("gui.settings.pdrp")));
                            } else {
                                settings.increment(b.enumOptions);
                                button.setMessage(settings.getButtonString(b.enumOptions));
                            }
                        }
                    }).setTooltip(this.settings.getSetting(settingList.get(i)).getTooltip());
                    this.addButton(guismallbutton);
                }
            }
        }

        this.addButton(new Button(this.width / 2 - 100, this.height / 6 + 168, 200, 20, I18n.format("gui.done"), button -> {
            Settings settings = PoudlardRPAddons.instance.settings;
            for(String settingID : textFields.keySet()) {
                for(TextFieldWidget t : textFields.get(settingID)) {
                    if(t instanceof TextFieldWidgetMod) {
                        ValueType type = ((TextFieldWidgetMod) t).getValueType();
                        switch(type) {
                            case DOUBLE:
                                double value;
                                try {
                                    value = Double.parseDouble(textFields.get(settingID).get(0).getText());
                                    this.settings.getSetting(settingID).setValue(value);
                                } catch(NumberFormatException ignored) {
                                }
                                break;
                            case POSITION:
                                this.settings.getSetting(settingID)
                                        .setValue(textFields.get(settingID).get(0).getText() + "_" + textFields.get(settingID).get(1).getText());
                                break;
                        }
                    }
                }
            }
            settings.saveSettings();
            minecraft.displayGuiScreen(parent);
        }));
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        this.drawCenteredString(minecraft.fontRenderer, I18n.format("gui.pdrp.settings"), this.width / 2, 12, 16777215);
        for(List<TextFieldWidget> positionPairs : textFields.values()) {
            for(TextFieldWidget t : positionPairs)
                t.render(mouseX, mouseY, partialTicks);
        }
        super.render(mouseX, mouseY, partialTicks);
    }

    @Override
    public void tick() {
        super.tick();
        for(String settingID : textFields.keySet()) {
            for(TextFieldWidget t : textFields.get(settingID)) {
                if(t instanceof TextFieldWidgetMod) {
                    ValueType type = ((TextFieldWidgetMod) t).getValueType();
                    switch(type) {
                        case DOUBLE:
                            double value;
                            try {
                                value = Double.parseDouble(textFields.get(settingID).get(0).getText());
                                this.settings.getSetting(settingID).setValue(value);
                            } catch(NumberFormatException ignored) {
                            }
                            break;
                        case POSITION:
                            this.settings.getSetting(settingID)
                                    .setValue(textFields.get(settingID).get(0).getText() + "_" + textFields.get(settingID).get(1).getText());
                            break;
                    }
                }
                t.tick();
            }
        }
    }
}
