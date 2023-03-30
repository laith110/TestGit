package com.example.products.enumm;

public enum Provider {
    Арти_партс("Арти Партс"),
    LadyLux("LadyLux"),
    Obi("Obi"),
    Батькин_Резерв("Батькин Резерв");

    private final String displayValue;

    Provider(String displayValue){
        this.displayValue=displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
