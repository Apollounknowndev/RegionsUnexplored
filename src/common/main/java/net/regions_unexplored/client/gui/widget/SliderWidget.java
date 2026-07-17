package net.regions_unexplored.client.gui.widget;

import net.minecraft.client.InputType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;

import java.util.function.Consumer;

public class SliderWidget extends AbstractWidget {
    private static final Identifier SLIDER_SPRITE = Identifier.withDefaultNamespace("widget/slider");
    private static final Identifier HIGHLIGHTED_SPRITE = Identifier.withDefaultNamespace("widget/slider_highlighted");
    private static final Identifier SLIDER_HANDLE_SPRITE = Identifier.withDefaultNamespace("widget/slider_handle");
    private static final Identifier SLIDER_HANDLE_HIGHLIGHTED_SPRITE = Identifier.withDefaultNamespace("widget/slider_handle_highlighted");
    private final double min;
    private final double max;
    private final double step;
    private final String name;
    private final Consumer<Double> action;
    protected double delta;
    protected double value;
    private boolean canChangeValue;
    protected boolean displayInt;

    public SliderWidget(double min, double max, double step, String name, Consumer<Double> action, double value, boolean displayInt) {
        super(0, 0, 0, 0, CommonComponents.EMPTY);
        this.min = min;
        this.max = max;
        this.step = 1 / step;
        this.name = name;
        this.action = action;
        this.value = value;
        this.delta = valueToDelta();
        this.displayInt = displayInt;
        
        this.updateMessage();
    }

    private Identifier getSprite() {
        return this.isFocused() && !this.canChangeValue ? HIGHLIGHTED_SPRITE : SLIDER_SPRITE;
    }

    private Identifier getHandleSprite() {
        return !this.isHovered && !this.canChangeValue ? SLIDER_HANDLE_SPRITE : SLIDER_HANDLE_HIGHLIGHTED_SPRITE;
    }
    
    @Override
    protected MutableComponent createNarrationMessage() {
        return Component.translatable("gui.narrate.slider", this.getMessage());
    }
    
    @Override
    public void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        narrationElementOutput.add(NarratedElementType.TITLE, this.createNarrationMessage());
        if (this.active) {
            if (this.isFocused()) {
                narrationElementOutput.add(NarratedElementType.USAGE, Component.translatable("narration.slider.usage.focused"));
            } else {
                narrationElementOutput.add(NarratedElementType.USAGE, Component.translatable("narration.slider.usage.hovered"));
            }
        }

    }
    
    @Override
    protected void extractWidgetRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, this.getSprite(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), ARGB.white(this.alpha));
        graphics.blitSprite(
            RenderPipelines.GUI_TEXTURED,
            this.getHandleSprite(),
            this.getX() + (int)(this.value * (this.width - 8)),
            this.getY(),
            8,
            this.getHeight(),
            ARGB.white(this.alpha)
        );
        this.extractScrollingStringOverContents(graphics.textRendererForWidget(this, GuiGraphicsExtractor.HoveredTextEffects.NONE), this.getMessage(), 2);
        this.handleCursor(graphics);
    }

    @Override
    public void onClick(MouseButtonEvent event, final boolean doubleClick) {
        this.setValueFromMouse(event);
    }

    public void setFocused(boolean focused) {
        super.setFocused(focused);
        if (!focused) {
            this.canChangeValue = false;
        } else {
            InputType inputtype = Minecraft.getInstance().getLastInputType();
            if (inputtype == InputType.MOUSE || inputtype == InputType.KEYBOARD_TAB) {
                this.canChangeValue = true;
            }
        }

    }

    public boolean keyPressed(KeyEvent event) {
        if (event.isSelection()) {
            this.canChangeValue = !this.canChangeValue;
            return true;
        } else {
            if (this.canChangeValue) {
                boolean left = event.isLeft();
                boolean right = event.isRight();
                if (left || right) {
                    float direction = left ? -1.0F : 1.0F;
                    this.setValue(this.delta + direction / (this.width - 8));
                    return true;
                }
            }

            return false;
        }
    }

    private void setValueFromMouse(MouseButtonEvent event) {
        this.setValue((event.x() - (this.getX() + 4)) / (this.width - 8));
    }

    private void setValue(double value) {
        double oldDelta = this.delta;
        this.delta = Mth.clamp(value, 0.0, 1.0);
        this.value = deltaToValue();

        if (oldDelta != this.delta) {
            this.action.accept(this.value);
        }

        this.updateMessage();
    }

    private double deltaToValue() {
        double lerped = Mth.lerp(this.delta, this.min, this.max);
        return Math.round(lerped * this.step) / this.step;
    }

    private double valueToDelta() {
        return (this.value - this.min) / (this.max - this.min);
    }
    
    @Override
    protected void onDrag(final MouseButtonEvent event, final double dx, final double dy) {
        this.setValueFromMouse(event);
        super.onDrag(event, dx, dy);
    }
    
    @Override
    public void playDownSound(final SoundManager soundManager) {
    }
    
    @Override
    public void onRelease(final MouseButtonEvent event) {
        super.playDownSound(Minecraft.getInstance().getSoundManager());
    }

    private void updateMessage() {
        if (this.displayInt) {
            this.setMessage(Component.translatable(this.name).append(": " + (int) this.value));
        } else {
            this.setMessage(Component.translatable(this.name).append(": " + this.value));
        }
    }
}