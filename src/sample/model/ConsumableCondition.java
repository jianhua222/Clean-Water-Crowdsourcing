package sample.model;

import java.io.Serializable;

/**
 * Created by Payton on 10/15/16.
 */
public enum ConsumableCondition implements Serializable {
    SAFE("Safe"),
    TREATABLE("Treatable"),
    UNSAFE("Unsafe"),
    NA("N/A");

    private final String consumableCondition;

    /**
     * This constructor sets the consumablecondition to input
     * @param input is the string which describes consumable condition
     */
    ConsumableCondition(String input) {
        this.consumableCondition = input;
    }

    /**
     * This method returns the consumablecondition
     * @return the string consumable condition
     */
    public String toString() {
        return consumableCondition;
    }
}
