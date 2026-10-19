package net.regions_unexplored.block.set;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class ColoredSet<T extends Block> {
    private final Supplier<T> white;
    private final Supplier<T> lightGray;
    private final Supplier<T> gray;
    private final Supplier<T> black;
    private final Supplier<T> brown;
    private final Supplier<T> red;
    private final Supplier<T> orange;
    private final Supplier<T> yellow;
    private final Supplier<T> lime;
    private final Supplier<T> green;
    private final Supplier<T> lightBlue;
    private final Supplier<T> cyan;
    private final Supplier<T> blue;
    private final Supplier<T> purple;
    private final Supplier<T> magenta;
    private final Supplier<T> pink;

    public ColoredSet(Function<DyeColor, Supplier<T>> factory) {
        this.white = factory.apply(DyeColor.WHITE);
        this.lightGray = factory.apply(DyeColor.LIGHT_GRAY);
        this.gray = factory.apply(DyeColor.GRAY);
        this.black = factory.apply(DyeColor.BLACK);
        this.brown = factory.apply(DyeColor.BROWN);
        this.red = factory.apply(DyeColor.RED);
        this.orange = factory.apply(DyeColor.ORANGE);
        this.yellow = factory.apply(DyeColor.YELLOW);
        this.lime = factory.apply(DyeColor.LIME);
        this.green = factory.apply(DyeColor.GREEN);
        this.lightBlue = factory.apply(DyeColor.LIGHT_BLUE);
        this.cyan = factory.apply(DyeColor.CYAN);
        this.blue = factory.apply(DyeColor.BLUE);
        this.purple = factory.apply(DyeColor.PURPLE);
        this.magenta = factory.apply(DyeColor.MAGENTA);
        this.pink = factory.apply(DyeColor.PINK);
    }

    public ArrayList<T> getAll() {
        ArrayList<T> blocks = new ArrayList<>();
        blocks.add(white.get());
        blocks.add(lightGray.get());
        blocks.add(gray.get());
        blocks.add(black.get());
        blocks.add(brown.get());
        blocks.add(red.get());
        blocks.add(orange.get());
        blocks.add(yellow.get());
        blocks.add(lime.get());
        blocks.add(green.get());
        blocks.add(lightBlue.get());
        blocks.add(cyan.get());
        blocks.add(blue.get());
        blocks.add(purple.get());
        blocks.add(magenta.get());
        blocks.add(pink.get());
        return blocks;
    }

    public Map<DyeColor, T> getMap() {
        Map<DyeColor, T> map = new EnumMap<>(DyeColor.class);
        map.put(DyeColor.WHITE, white.get());
        map.put(DyeColor.LIGHT_GRAY, lightGray.get());
        map.put(DyeColor.GRAY, gray.get());
        map.put(DyeColor.BLACK, black.get());
        map.put(DyeColor.BROWN, brown.get());
        map.put(DyeColor.RED, red.get());
        map.put(DyeColor.ORANGE, orange.get());
        map.put(DyeColor.YELLOW, yellow.get());
        map.put(DyeColor.LIME, lime.get());
        map.put(DyeColor.GREEN, green.get());
        map.put(DyeColor.LIGHT_BLUE, lightBlue.get());
        map.put(DyeColor.CYAN, cyan.get());
        map.put(DyeColor.BLUE, blue.get());
        map.put(DyeColor.PURPLE, purple.get());
        map.put(DyeColor.MAGENTA, magenta.get());
        map.put(DyeColor.PINK, pink.get());
        return map;
    }
    
    public Block getByColor(DyeColor color) {
        return switch (color) {
            case DyeColor.WHITE -> white.get();
            case DyeColor.LIGHT_GRAY -> lightGray.get();
            case DyeColor.GRAY -> gray.get();
            case DyeColor.BLACK -> black.get();
            case DyeColor.BROWN -> brown.get();
            case DyeColor.RED -> red.get();
            case DyeColor.ORANGE -> orange.get();
            case DyeColor.YELLOW -> yellow.get();
            case DyeColor.LIME -> lime.get();
            case DyeColor.GREEN -> green.get();
            case DyeColor.LIGHT_BLUE -> lightBlue.get();
            case DyeColor.CYAN -> cyan.get();
            case DyeColor.BLUE -> blue.get();
            case DyeColor.PURPLE -> purple.get();
            case DyeColor.MAGENTA -> magenta.get();
            case DyeColor.PINK -> pink.get();
        };
    }
    
    public Supplier<T> getWhite() {
        return white;
    }

    public Supplier<T> getLightGray() {
        return lightGray;
    }

    public Supplier<T> getGray() {
        return gray;
    }

    public Supplier<T> getBlack() {
        return black;
    }

    public Supplier<T> getBrown() {
        return brown;
    }

    public Supplier<T> getRed() {
        return red;
    }

    public Supplier<T> getOrange() {
        return orange;
    }

    public Supplier<T> getYellow() {
        return yellow;
    }

    public Supplier<T> getLime() {
        return lime;
    }

    public Supplier<T> getGreen() {
        return green;
    }

    public Supplier<T> getLightBlue() {
        return lightBlue;
    }

    public Supplier<T> getCyan() {
        return cyan;
    }

    public Supplier<T> getBlue() {
        return blue;
    }

    public Supplier<T> getPurple() {
        return purple;
    }

    public Supplier<T> getMagenta() {
        return magenta;
    }

    public Supplier<T> getPink() {
        return pink;
    }
}
