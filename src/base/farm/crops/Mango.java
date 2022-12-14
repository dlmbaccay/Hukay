package base.farm.crops;

import base.farm.Crop;
import base.farm.CropType;

import java.math.BigDecimal;

/** Represents the mango tree class. This extends from Crop class.
 */
public class Mango extends Crop {
    public Mango() {
        super();
        this.seedName = "Mango";
        this.cropType = CropType.FRUIT_TREE;
        this.harvestTime = 10;
        this.waterNeeded = 7;
        this.waterBonus = 7;
        this.fertilizerNeeded = 4;
        this.fertilizerBonus = 4;
        this.minProduce = 5;
        this.maxProduce = 15;
        this.seedCost = BigDecimal.valueOf(100);
        this.basePrice = BigDecimal.valueOf(8);
        this.experienceYield = 25.0;
    }
}