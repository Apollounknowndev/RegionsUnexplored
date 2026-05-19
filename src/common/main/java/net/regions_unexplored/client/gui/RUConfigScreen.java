package net.regions_unexplored.client.gui;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.regions_unexplored.config.RUConfigHandler;

public class RUConfigScreen extends Screen {
    protected final Screen parent;

    private ConfigList list;
    final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this);

    public RUConfigScreen(Screen parent) {
        super(Component.literal("Regions Unexplored Config"));
        this.parent = parent;
    }

    @Override
    public void init() {
        layout.addTitleHeader(title, font);

        list = layout.addToContents(new ConfigList(minecraft, width, this));
        list.build(font);

        LinearLayout footer = layout.addToFooter(LinearLayout.horizontal().spacing(8));

        footer.addChild(Button.builder(CommonComponents.GUI_DONE, button -> onDone()).build());

        layout.visitWidgets(this::addRenderableWidget);
        this.repositionElements();
    }

    protected void repositionElements() {
        this.layout.arrangeElements();
        if (this.list != null) {
            this.list.updateSize(this.width, this.layout);
        }
    }

    private void onDone() {
        RUConfigHandler.saveClient();
        RUConfigHandler.saveCommon();
        this.onClose();
    }

    public void onClose() {
        this.minecraft.setScreen(this.parent);
    }

    public static Component text(String name) {
        return Component.translatable("config.regions_unexplored." + name);
    }

    public static Component option(String name) {
        return text("option." + name);
    }
}