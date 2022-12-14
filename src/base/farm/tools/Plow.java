package base.farm.tools;

import base.farm.Tool;

import java.math.BigDecimal;

/** Represents the plow class. This extends from Tool class.
 */
public class Plow extends Tool {
    public Plow() {
        super();
        this.toolName = "Plow";
        this.toolCost = BigDecimal.valueOf(0);
        this.experienceGain = 0.5;
    }

    @Override
    public String toString() {
        return "<html>Plow <p><p>" +
                "Converts an unplowed tile to a plowed tile. <p>" +
                "Can only be performed on an unplowed tile. <p> <p>" +
                "Tool Cost: - 0.0 <p>" +
                "Experience Yield: + 0.5 <p> </html";
    }
}