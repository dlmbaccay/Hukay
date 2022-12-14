package base.farm.tools;

import base.farm.Tool;

import java.math.BigDecimal;

/** Represents the shover class. This extends from Tool class.
 */
public class Shovel extends Tool {
    public Shovel() {
        super();
        this.toolName = "Shovel";
        this.toolCost = BigDecimal.valueOf(7);
        this.experienceGain = 2.0;
    }

    @Override
    public String toString() {
        return "<html>Shovel<p><p>" +
                "Removes a withered plant from a tile. <p>" +
                "*PLACEHOLDER* INFORM USER AS WELL ABOUT OTHER USES OF SHOVEL <p> <p>" +
                "Tool Cost: - 7.0 <p>" +
                "Experience Yield: + 2.0 <p> </html>";
    }
}