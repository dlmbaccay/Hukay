package base.farm.crops;

import base.farm.Crop;
import base.farm.CropType;

import java.math.BigDecimal;

/** Represents the tulips class. This extends from Crop class.
 */
public class Tulips extends Crop {
    public Tulips() {
        super();
        this.seedName = "Tulips";
        this.cropType = CropType.FLOWER;
        this.harvestTime = 2;
        this.waterNeeded = 2;
        this.waterBonus = 3;
        this.fertilizerNeeded = 0;
        this.fertilizerBonus = 1;
        this.minProduce = 1;
        this.maxProduce = 1;
        this.seedCost = BigDecimal.valueOf(10);
        this.basePrice = BigDecimal.valueOf(9);
        this.experienceYield = 5.0;
    }
}