package base.farm.tools;

import base.farm.Tool;

import java.math.BigDecimal;

/** Represents the pickaxe class. This extends from Tool class.
 */
public class Pickaxe extends Tool {
    public Pickaxe() {
        super();
        this.toolName = "Pickaxe";
        this.toolCost = BigDecimal.valueOf(50);
        this.experienceGain = 15.0;
    }

    @Override
    public String toString() {
        return "<html>Pickaxe<p><p>" +
                "Removes a rock from a tile. Can only be applied to tiles with a rock. <p> <p>" +
                "Tool Cost: - 50.0 <p>" +
                "Experience Yield: + 15.0 <p> </html>";
    }
}