package com.gardenplanner.gardenplanner.model;

import java.util.ArrayList;
import java.util.List;

public class PerenualCollection
{
    private List<PerenualItem> perenualItems;

    public PerenualCollection() {
        perenualItems = new ArrayList<>();
    }

    public void add(PerenualItem item) {
        perenualItems.add(item);
    }

    public List<String> getItemNames() {
        return perenualItems.stream()
                .map(PerenualItem::getCommonName)
                .toList();
    }
}
