package com.project.sanatoriumregistry.SanatoriumRegistry.model;

public enum ServiceCategory {
    HYDROTHERAPY ("Водолечение"),
    ENDOSCOPY ("Эндоскопия"),
    MASSAGE ("Массаж"),
    INHALATIONS ("Ингаляции");


    private final String categoryTextDisplay;

    ServiceCategory(String text) {
        this.categoryTextDisplay = text;
    }

    public String getCategoryTextDisplay() {
        return this.categoryTextDisplay;
    }
}
