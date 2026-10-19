package net.regions_unexplored.block.properties.type;

import net.minecraft.util.StringRepresentable;

public enum TallHyacinthStockShape implements StringRepresentable {
    BASE("base"),
    BASE_FRUSTUM("base_frustum"),
    BASE_TIP("base_tip"),
    MIDDLE("middle"),
    FRUSTUM("frustum"),
    TIP("tip");

    private final String name;

    TallHyacinthStockShape(String shape) {
        this.name = shape;
    }
    
    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
