package com.gardenplanner.gardenplanner.model;

import java.util.ArrayList;
import java.util.List;

public class PerenualCollection {
    public List<PerenualItem> perenualItems;
    public int pages;

    /**
     * Create a new PerenualCollection object.
     */
    public PerenualCollection() {
        perenualItems = new ArrayList<>();
    }

    /**
     * Add a PerenualItem to the collection.
     *
     * @param item the PerenualItem to add
     */
    public void add(PerenualItem item) {
        perenualItems.add(item);
    }

    /**
     * Get the item names in the collection.
     *
     * @return a list of item names
     */
    public List<String> getItemNames() {
        return perenualItems.stream()
                .map(PerenualItem::getName)
                .toList();
    }
}
