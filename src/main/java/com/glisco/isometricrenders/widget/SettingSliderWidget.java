package com.glisco.isometricrenders.widget;

import com.glisco.isometricrenders.setting.IntSetting;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;

public class SettingSliderWidget extends SliderWidget {

    private final IntSetting setting;
    private final int scrollIncrement;

    public SettingSliderWidget(int x, int y, int width, Text text, int scrollIncrement, IntSetting setting) {
        super(x, y, width, 20, text, setting.progress());
        this.setting = setting;
        this.scrollIncrement = scrollIncrement;

        setting.listen((intSetting, integer) -> this.setValue(setting.progress()));
    }

    @Override
    protected void updateMessage() {}

    @Override
    protected void applyValue() {
        this.setting.setFromProgress(this.value);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 2 && this.clicked(mouseX, mouseY)) {
            this.setting.setToDefault();
            return true;
        } else {
            return super.mouseClicked(mouseX, mouseY, button);
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (!isHovered()) return false;
        this.setting.modify((int) Math.round(amount * this.scrollIncrement));
        return true;
    }
}
