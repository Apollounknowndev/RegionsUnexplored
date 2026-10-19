package net.regions_unexplored.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import net.regions_unexplored.client.gui.widget.SliderWidget;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConfigList extends ContainerObjectSelectionList<ConfigList.BaseEntry> implements ConfigListBuilder {
    private static final Component DEFAULT = Component.translatable("config.regions_unexplored.default");
    
    private DoubleEntry lastDouble = null;
    
    public ConfigList(Minecraft minecraft, int width, RUConfigScreen parent) {
        super(minecraft, width, parent.layout.getContentHeight(), parent.layout.getHeaderHeight(), 25);
    }
    
    public static Tooltip tooltip(String name, Object base) {
        return Tooltip.create(Component.translatable(option(name) + ".tooltip").append(CommonComponents.NEW_LINE).append(DEFAULT).append("§e" + base));
    }
    
    public static String text(String suffix) {
        return "config.regions_unexplored." + suffix;
    }
    
    public static String category(String suffix) {
        return text("category." + suffix);
    }
    
    public static String option(String suffix) {
        return text("option." + suffix);
    }

    public StringWidget addCategory(String name, Font font) {
	    StringWidget widget = new StringWidget(Component.translatable(category(name)), font).alignCenter();
        this.addEntry(widget);
        return widget;
    }
    
    @Override
    public <T extends StringRepresentable> void addEnum(String name, Consumer<T> setter, T getter, T[] values, T defaultValue) {
        CycleButton.Builder<T> button = CycleButton.<T>builder(t -> Component.literal(t.getSerializedName()))
            .withTooltip(v -> tooltip(name + "." + v.getSerializedName(), defaultValue.getSerializedName()))
            .withValues(values)
            .withInitialValue(getter);
        this.addEntry(button.create(Component.translatable(option(name)), (__, value) -> setter.accept(value)));
    }
    
    public void addBoolean(String name, Consumer<Boolean> setter, boolean value, boolean base) {
        CycleButton.Builder<Boolean> button = CycleButton.onOffBuilder(value);
        button.withTooltip(v -> tooltip(name, base));
        
        this.addEntry(button.create(Component.translatable(option(name)), (__, bool) -> setter.accept(bool)));
    }

    public void addInteger(String name, double min, double max, double step, Consumer<Integer> action, double value, double base) {
        SliderWidget widget = new SliderWidget(min, max, step, "config.regions_unexplored.option." + name, newValue -> action.accept(newValue.intValue()), value, true);
        widget.setTooltip(tooltip(name, base));
        this.addEntry(widget);
    }

    public void addDouble(String name, double min, double max, double step, Consumer<Double> action, double value, double base) {
        SliderWidget widget = new SliderWidget(min, max, step, "config.regions_unexplored.option." + name, action, value, false);
        widget.setTooltip(tooltip(name, base));
        this.addEntry(widget);
    }
    
    public void addSmallBoolean(String name, Consumer<Boolean> setter, boolean value, boolean base, boolean hasTooltip) {
        CycleButton.Builder<Boolean> button = CycleButton.onOffBuilder(value);
        if (hasTooltip) {
            button.withTooltip(bool -> tooltip(name, base));
        }
        this.addDoubleEntry(button.create(Component.translatable(hasTooltip ? option(name) : name), (__, bool) -> setter.accept(bool)));
    }

    public void addEntry(AbstractWidget widget) {
        lastDouble = null;
        this.addEntry(new SingleEntry(widget));
    }
    
    public void addDoubleEntry(AbstractWidget widget) {
        if (this.lastDouble == null) {
            this.lastDouble = new DoubleEntry(widget);
            this.addEntry(this.lastDouble);
        } else {
            this.lastDouble.addRightWidget(widget);
            this.lastDouble = null;
        }
    }

    public int getRowWidth() {
        return 310;
    }

    public void updateSize(int width, HeaderAndFooterLayout layout) {
        super.updateSize(width, layout);
        this.children().forEach(entry -> entry.setWidgetX(width));
    }
    
    abstract static class BaseEntry extends Entry<BaseEntry> {
        abstract void setWidgetX(int width);
    }

    class SingleEntry extends BaseEntry {
        final AbstractWidget widget;

        SingleEntry(AbstractWidget widget) {
            widget.setX(ConfigList.this.width / 2 - 155);
            widget.setY(0);
            widget.setHeight(20);
            widget.setWidth(310);
            this.widget = widget;
        }

        public void render(GuiGraphics guiGraphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
            this.widget.setY(top);
            this.widget.render(guiGraphics, mouseX, mouseY, partialTick);
        }

        public List<? extends GuiEventListener> children() {
            return List.of(this.widget);
        }

        public List<? extends NarratableEntry> narratables() {
            return List.of(this.widget);
        }
        
        @Override
        void setWidgetX(int width) {
            this.widget.setX(width / 2 - 155);
        }
    }
    
    class DoubleEntry extends BaseEntry {
        AbstractWidget leftWidget;
        AbstractWidget rightWidget;
        List<AbstractWidget> widgets;
        
        DoubleEntry(AbstractWidget leftWidget) {
            leftWidget.setX(ConfigList.this.width / 2 - 155);
            leftWidget.setY(0);
            leftWidget.setHeight(20);
            leftWidget.setWidth(150);
            this.leftWidget = leftWidget;
            this.widgets = new ArrayList<>();
            this.widgets.add(this.leftWidget);
        }
        
        public void addRightWidget(AbstractWidget rightWidget) {
            rightWidget.setX(ConfigList.this.width / 2 + 5);
            rightWidget.setY(0);
            rightWidget.setHeight(20);
            rightWidget.setWidth(150);
            this.rightWidget = rightWidget;
            this.widgets.add(this.rightWidget);
        }
        
        public void render(GuiGraphics guiGraphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
            this.leftWidget.setY(top);
            this.leftWidget.render(guiGraphics, mouseX, mouseY, partialTick);
            if (this.rightWidget != null) {
                this.rightWidget.setY(top);
                this.rightWidget.render(guiGraphics, mouseX, mouseY, partialTick);
            }
        }
        
        public List<? extends GuiEventListener> children() {
            return this.widgets;
        }
        
        public List<? extends NarratableEntry> narratables() {
            return this.widgets;
        }
        
        @Override
        void setWidgetX(int width) {
            this.leftWidget.setX(width / 2 - 155);
            if (this.rightWidget != null) {
                this.rightWidget.setX(width / 2 + 5);
            }
        }
    }
}
