package base.farm.tools;

import base.farm.Tool;

import java.math.BigDecimal;

/** Represents the fertilizer class. This extends from Tool class.
 */
public class Fertilizer extends Tool {
    public Fertilizer() {
        super();
        this.toolName = "Fertilize";
        this.toolCost = BigDecimal.valueOf(10);
        this.experienceGain = 4.0;
    }

    @Override public String toString() {
        return "<html>Fertilizer <p><p>" +
                "Adds to the total number of tiles a tile/crop has been applied with fertilizer. <p>" +
                "Can only be performed on a plowed tile with a crop. <p> <p>" +
                "Tool Cost: - 10.0 <p>" +
                "Experience Yield: + 4.0 <p> </html";
    }
}