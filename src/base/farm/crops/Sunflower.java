package base.farm.crops;

import base.farm.Crop;
import base.farm.CropType;

import java.math.BigDecimal;

/** Represents the sunflower class. This extends from Crop class.
 */
public class Sunflower extends Crop {
    public Sunflower() {
        super();
        this.seedName = "Sunflower";
        this.cropType = CropType.FLOWER;
        this.harvestTime = 3;
        this.waterNeeded = 2;
        this.waterBonus = 3;
        this.fertilizerNeeded = 1;
        this.fertilizerBonus = 2;
        this.minProduce = 1;
        this.maxProduce = 1;
        this.seedCost = BigDecimal.valueOf(20);
        this.basePrice = BigDecimal.valueOf(19);
        this.experienceYield = 7.5;
    }
}