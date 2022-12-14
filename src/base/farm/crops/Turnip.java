package base.farm.crops;

import base.farm.Crop;
import base.farm.CropType;

import java.math.BigDecimal;

/** Represents the turnip class. This extends from Crop class.
 */
public class Turnip extends Crop {
    public Turnip() {
        super();
        this.seedName = "Turnip";
        this.cropType = CropType.ROOT_CROP;
        this.harvestTime = 2;
        this.waterNeeded = 1;
        this.waterBonus = 2;
        this.fertilizerNeeded = 0;
        this.fertilizerBonus = 1;
        this.minProduce = 1;
        this.maxProduce = 2;
        this.seedCost = BigDecimal.valueOf(5);
        this.basePrice = BigDecimal.valueOf(6);
        this.experienceYield = 5.0;
    }
}
