package base.farm.crops;

import base.farm.Crop;
import base.farm.CropType;

import java.math.BigDecimal;

/** Represents the rose class. This extends from Crop class.
 */
public class Rose extends Crop {
    public Rose() {
        super();
        this.seedName = "Rose";
        this.cropType = CropType.FLOWER;
        this.harvestTime = 1;
        this.waterNeeded = 1;
        this.waterBonus = 2;
        this.fertilizerNeeded = 0;
        this.fertilizerBonus = 1;
        this.minProduce = 1;
        this.maxProduce = 1;
        this.seedCost = BigDecimal.valueOf(5);
        this.basePrice = BigDecimal.valueOf(5);
        this.experienceYield = 2.5;
    }
}
