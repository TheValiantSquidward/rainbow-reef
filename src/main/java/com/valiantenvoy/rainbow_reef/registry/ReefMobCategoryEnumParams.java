package com.valiantenvoy.rainbow_reef.registry;

@SuppressWarnings("unused")
public class ReefMobCategoryEnumParams {

    public static Object getSeafloorAmbientParameters(int index, Class<?> type) {
        return switch (index) {
            case 0 -> "rainbow_reef:seafloor_ambient"; // name
            case 1 -> 10; // max spawn count
            case 2 -> true; // friendly
            case 3 -> false; // persistent
            case 4 -> 64; // despawn distance
            default -> throw new IllegalArgumentException("Unexpected parameter index: " + index);
        };
    }
}
