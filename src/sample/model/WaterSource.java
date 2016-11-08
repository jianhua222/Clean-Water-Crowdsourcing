package sample.model;

import java.io.Serializable;

/**
 * Created by Payton on 10/15/16.
 */
public enum WaterSource implements Serializable {
    BOTTLED("Bottled"),
    WELL("Well"),
    STREAM("Stream"),
    LAKE("Lake"),
    SPRING("Spring"),
    OTHER("Other"),
    NA("N/A");


    private String waterSource;

    /**
     * This constructor set the waterSource string
     * @param input is the type of water source
     */
    WaterSource(String input) {
        this.waterSource = input;
    }

    /**
     * This method returns the waterSource string
     * @return waterSource string which describes the type of
     * water source (Lake, Spring, etc.)
     */
    public String toString() {
        return this.waterSource;
    }
}
