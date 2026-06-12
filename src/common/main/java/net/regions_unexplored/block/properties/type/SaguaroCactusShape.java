package net.regions_unexplored.block.properties.type;

import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.util.StringRepresentable;

public enum SaguaroCactusShape implements StringRepresentable {
    NORTH_SOUTH("north_south", false),
    EAST_WEST("east_west", false),
    UP_DOWN("up_down", false),
    NORTH_UP("north_up", true),
    SOUTH_UP("south_up", true),
    EAST_UP("east_up", true),
    WEST_UP("west_up", true);

    private final String name;
    private final boolean turn;
    
    SaguaroCactusShape(String shape, boolean turn) {
        this.name = shape;
        this.turn = turn;
    }
    
    public static SaguaroCactusShape getAxisAligned(Axis axis) {
        return switch (axis) {
            case X -> EAST_WEST;
            case Y -> UP_DOWN;
            case Z -> NORTH_SOUTH;
        };
    }
    
    public static SaguaroCactusShape getUp(Direction direction) {
        return switch (direction) {
            case NORTH -> NORTH_UP;
            case SOUTH -> SOUTH_UP;
            case EAST -> EAST_UP;
            case WEST -> WEST_UP;
            default -> throw new IllegalStateException("SaguaroCactusShape#getUp shouldn't receive a vertical direction!");
        };
    }
    
    public boolean isTurn() {
        return this.turn;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}
