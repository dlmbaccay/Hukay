import java.math.BigDecimal;

public class Crop {
    private final String name;
    private final CropType cropType;
    private final int harvestTime;
    private int harvestDate;
    private final int waterNeeded;
    private final int fertilizerNeeded;
    private final int waterBonus;
    private final int fertilizerBonus;
    private int timesWatered = 0;
    private int timesFertilized = 0;
    private final int minProduce;
    private final int maxProduce;
    private int actualProduce;
    private final BigDecimal seedCost;
    private final BigDecimal basePrice;
    private BigDecimal harvestPrice;
    private final double experienceYield;

    /* Getters for Crop Class */
    public String getName() { return name; }
    public CropType getCropType() { return cropType; }
    public int getHarvestTime() { return harvestTime; }
    public int getHarvestDate() { return harvestDate; }
    public void setHarvestDate(int day) { this.harvestDate = this.harvestTime + day; }
    public int getWaterNeeded() { return waterNeeded; }
    public int getFertilizerNeeded() { return fertilizerNeeded; }
    public int getWaterBonus() { return waterBonus; }
    public int getFertilizerBonus() { return fertilizerBonus; }
    public int getTimesWatered() { return Math.min(this.timesWatered, this.waterBonus); }
    public int getTimesFertilized() { return Math.min(this.timesFertilized, this.fertilizerBonus); }
    public int getMinProduce() { return minProduce; }
    public int getMaxProduce() { return maxProduce; }
    public int getActualProduce() { return actualProduce; }
    public BigDecimal getSeedCost() { return seedCost; }
    public BigDecimal getBasePrice() { return basePrice; }
    public BigDecimal getHarvestPrice() { return harvestPrice; }
    public double getExperienceYield() { return experienceYield; }

    /* Setters for Crop Class */
    public void setActualProduce(int actualProduce) { this.actualProduce = actualProduce; }
    public void setHarvestPrice(BigDecimal harvestPrice) { this.harvestPrice = harvestPrice; }

    /* Helper methods for Crop Class variables */
    public void incrementTimesWatered() { this.timesWatered++; }
    public void incrementTimesFertilized() { this.timesFertilized++; }

    /* Crop Constructor */

    public Crop(String name, CropType cropType, int harvestTime,
                int waterNeeded, int fertilizerNeeded,
                int waterBonus, int fertilizerBonus, int minProduce, int maxProduce,
                BigDecimal seedCost, BigDecimal basePrice, double experienceYield) {
        this.name = name;
        this.cropType = cropType;
        this.harvestTime = harvestTime;
        this.waterNeeded = waterNeeded;
        this.fertilizerNeeded = fertilizerNeeded;
        this.waterBonus = waterBonus;
        this.fertilizerBonus = fertilizerBonus;
        this.minProduce = minProduce;
        this.maxProduce = maxProduce;
        this.seedCost = seedCost;
        this.basePrice = basePrice;
        this.experienceYield = experienceYield;
    }

    /* Crop Copy Constructor */
    public Crop(Crop copy) {
        this.name = copy.name;
        this.cropType = copy.cropType;
        this.harvestTime = copy.harvestTime;
        this.waterNeeded = copy.waterNeeded;
        this.fertilizerNeeded = copy.fertilizerNeeded;
        this.waterBonus = copy.waterBonus;
        this.fertilizerBonus = copy.fertilizerBonus;
        this.minProduce = copy.minProduce;
        this.maxProduce = copy.maxProduce;
        this.seedCost = copy.seedCost;
        this.basePrice = copy.basePrice;
        this.experienceYield = copy.experienceYield;
    }

    /* Crop Copy Factory */
    public static Crop newInstance(Crop newCrop) { return new Crop(newCrop); }

    public void printCropDetails() {
        /* prints a specific crop's detail */
        System.out.printf("""
                \n\t%-11s           Crop Type: %-9s
                \tSeed Cost:    %-3.0f     Base Price:         %-2.1f
                \tHarvest Time: %-3d     Experience Yield:   %-2.1f
                \tWater Needed: %-3d     Fertilizer Needed:  %-2d
                \tWater Bonus:  %-3d     Fertilizer Bonus:   %-2d
                \tMin. Produce: %-3d     Max. Produce:       %-2d
                """, ("[" + this.name + "]"), this.cropType, this.seedCost, this.basePrice,
                this.harvestTime, this.experienceYield, this.waterNeeded, this.fertilizerNeeded,
                this.waterBonus, this.fertilizerBonus, this.minProduce, this.maxProduce);
    }
}
