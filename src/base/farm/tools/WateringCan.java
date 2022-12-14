package base.farm.tools;

import base.farm.Tool;

import java.math.BigDecimal;

/** Represents the watering can class. This extends from Tool c lass.
 */
public class WateringCan extends Tool {
    public WateringCan() {
        super();
        this.toolName = "Water";
        this.toolCost = BigDecimal.valueOf(0);
        this.experienceGain = 0.5;
    }

    @Override
    public String toString() {
        return "<html>Watering Can <p><p>" +
                "Adds to the total number of tiles a tile/crop has been watered. <p>" +
                "Can only be performed on a plowed tile with a crop. <p> <p>" +
                "Tool Cost: - 0.0 <p>" +
                "Experience Yield: + 0.5 <p> </html>";
    }
}