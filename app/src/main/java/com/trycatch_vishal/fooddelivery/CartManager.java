package com.trycatch_vishal.fooddelivery;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static final List<POJOSTATERS> pojostaters = new ArrayList<>();

    public static List<POJOSTATERS> getCartItems() {
        return pojostaters;
    }

    public static void addToCart(POJOSTATERS item) {
        pojostaters.add(item);
    }

    public static void removeFromCart(POJOSTATERS item) {
        pojostaters.remove(item);
    }
}
