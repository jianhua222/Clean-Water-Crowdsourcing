package sample.model;

import java.io.Serializable;

/**
 * Created by Payton on 10/15/16.
 */
public enum WaterCondition implements Serializable {
    WASTE("Waste"),
    TREATABLECLEAR("Treatable-Clear"),
    TREATABLEMUDDY("Treatable-Muddy"),
    POTABLE("Potable"),
    NA("N/A");

    private final String waterCondition;

    /**
     * This constructor set the water condition
     * @param input the string which describes the water condition
     */
    WaterCondition(String input) {
        this.waterCondition = input;
    }

    /**
     * This method gets the string waterCondition
     * @return the string which describes waterCondition
     */
    public String toString() {
        return this.waterCondition;
    }
}
