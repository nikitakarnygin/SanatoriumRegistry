package com.project.sanatoriumregistry.SanatoriumRegistry.model;

public enum VacationerType {

    OWNER ("Владелец бронирования"),
    GUEST ("Гость");

    private final String vacationerTypeTextDisplay;

    VacationerType(String text) {
        this.vacationerTypeTextDisplay = text;
    }

    public String getVacationerTypeTextDisplay() {
        return vacationerTypeTextDisplay;
    }
}
