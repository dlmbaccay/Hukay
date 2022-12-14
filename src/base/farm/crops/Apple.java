package base.farm.crops;

import base.farm.Crop;
import base.farm.CropType;

import java.math.BigDecimal;

/** Represents the apple tree class. This extends from Crop class.
 */
public class Apple extends Crop {

    public Apple() {
        super();
        this.seedName = "Apple";
        this.cropType = CropType.FRUIT_TREE;
        this.harvestTime = 10;
        this.waterNeeded = 7;
        this.waterBonus = 7;
        this.fertilizerNeeded = 5;
        this.fertilizerBonus = 5;
        this.minProduce = 10;
        this.maxProduce = 15;
        this.seedCost = BigDecimal.valueOf(200);
        this.basePrice = BigDecimal.valueOf(5);
        this.experienceYield = 25.0;
    }
}