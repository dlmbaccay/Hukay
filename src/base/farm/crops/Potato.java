package base.farm.crops;

import base.farm.Crop;
import base.farm.CropType;

import java.math.BigDecimal;

/** Represents the potato class. This extends from Crop class.
 */
public class Potato extends Crop {
    public Potato() {
        super();
        this.seedName = "Potato";
        this.cropType = CropType.ROOT_CROP;
        this.harvestTime = 5;
        this.waterNeeded = 3;
        this.waterBonus = 4;
        this.fertilizerNeeded = 1;
        this.fertilizerBonus = 2;
        this.minProduce = 1;
        this.maxProduce = 10;
        this.seedCost = BigDecimal.valueOf(20);
        this.basePrice = BigDecimal.valueOf(3);
        this.experienceYield = 12.5;
    }
}
