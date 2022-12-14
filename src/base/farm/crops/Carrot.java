package base.farm.crops;

import base.farm.Crop;
import base.farm.CropType;

import java.math.BigDecimal;

/** Represents the carrot class. This extends from Crop class.
 */
public class Carrot extends Crop {
    public Carrot() {
        super();
        this.seedName = "Carrot";
        this.cropType = CropType.ROOT_CROP;
        this.harvestTime = 3;
        this.waterNeeded = 1;
        this.waterBonus = 2;
        this.fertilizerNeeded = 0;
        this.fertilizerBonus = 1;
        this.minProduce = 1;
        this.maxProduce = 2;
        this.seedCost = BigDecimal.valueOf(10);
        this.basePrice = BigDecimal.valueOf(9);
        this.experienceYield = 7.5;
    }
}
