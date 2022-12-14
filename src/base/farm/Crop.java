package base.farm;

import java.math.BigDecimal;

/** This class represents a crop, which contains multiple details needed from the crop, specifically, it contains:
 *  the name of the crop, the type of the crop, harvest time
 *
 */
public class Crop {

    protected String seedName;

    protected CropType cropType;
    protected int harvestTime;
    protected int waterNeeded;
    protected int timesWatered = 0;
    protected int waterBonus;
    protected int fertilizerNeeded;
    protected int timesFertilized = 0;
    protected int fertilizerBonus;
    protected int minProduce;
    protected int maxProduce;
    protected int actualProduce = 0;
    protected BigDecimal seedCost;
    protected BigDecimal basePrice;
    protected BigDecimal harvestPrice = BigDecimal.valueOf(0);
    protected double experienceYield;
    protected int dateForHarvest;

    public Crop() {
    }

    // Copy Constructor
    /** Creates a Crop object by supplying an already existing Crop object.
     *
     * @param copy The Crop to be copied from
     */
    public Crop(Crop copy, FarmerStatus status) {
        this.seedName = copy.seedName;
        this.cropType = copy.cropType;
        this.harvestTime = copy.harvestTime;
        this.waterNeeded = copy.waterNeeded;
        this.waterBonus = copy.waterBonus + status.getWATER_LIMIT_BONUS();
        this.fertilizerNeeded = copy.fertilizerNeeded;
        this.fertilizerBonus = copy.fertilizerBonus + status.getFERTILIZER_LIMIT_BONUS();
        this.minProduce = copy.minProduce;
        this.maxProduce = copy.maxProduce;
        this.seedCost = copy.seedCost.subtract(status.getSEED_COST_REDUCTION());
        this.basePrice = copy.basePrice.add(status.getEARNING_BONUS());
        this.experienceYield = copy.experienceYield;
    }

    // Copy Factory
    /** A method used for copying a new instance of an already existing Crop object.
     *  This is mainly used for planting seeds which needs to create a new copy of an
     *  existing Crop object that will be assigned to a specific Tile.
     *
     * @param newCrop   The selected Crop that will be copied from
     * @return          A new Crop object from the copied Crop object
     */
    public static Crop newInstance(Crop newCrop, FarmerStatus farmerStatus) { return new Crop(newCrop, farmerStatus); }


    // Getters for Crop Class\

    /** Returns the name of the Crop.
     *
     * @return the name of the Crop in String representation
     */
    public String getSeedName() { return seedName; }

    /** Returns the type of the Crop from the CropType enum.
     *
     * @return the type of the crop in CropType representation
     */
    public CropType getCropType() { return cropType; }


    /** Returns the required amount of water of the Crop.
     *
     * @return the needed amount of water of the Crop in 'int' representation
     */
    public int getWaterNeeded() { return waterNeeded; }

    /** Returns the amount of times the Farmer watered the Crop starting from when it was planted.
     *
     * @return the amount of times the Crop was watered in 'int' representation
     */
    public int getTimesWatered() { return Math.min(this.timesWatered, this.waterBonus); }

    /** Returns the actual limit of the water, including the bonus, of the Crop.
     *
     * @return the water limit of the crop in 'int' representation
     */
    public int getWaterBonus() { return waterBonus; }

    /** Returns the required amount of fertilizer of the Crop.
     *
     * @return the needed amount of fertilizer of the Crop in 'int' representation
     */
    public int getFertilizerNeeded() { return fertilizerNeeded; }

    /** Returns the amount of times the Farmer fertilized the Crop starting from when it was planted.
     *
     * @return the amount of times the Crop was fertilized in 'int' representation
     */
    public int getTimesFertilized() { return Math.min(this.timesFertilized, this.fertilizerBonus); }

    /** Returns the actual limit of the fertilizer, including the bonus, of the Crop.
     *
     * @return the limit of the fertilizer of the crop in 'int' representation
     */
    public int getFertilizerBonus() { return fertilizerBonus; }

    /** Returns the actual amount of produce of the Crop based from
     *  its minimum and maximum produce.
     *
     * @return the amount of produce of the Crop in 'int' representation
     */
    public int getActualProduce() { return actualProduce; }

    /** Returns the minimum produce of the crop, which is unique per plant
     *
     * @return the minimum produce a crop can yield
     */
    public int getMinProduce() { return minProduce; }

    /** Returns the maximum produce of the crop, which is unique per plant
     *
     * @return the maximum produce a crop can yield
     */
    public int getMaxProduce() { return maxProduce; }

    /** Returns the base price of the crop, which is to be used on the
     * computation of the HarvestPrice
     *
     * @return the base price of the crop
     */
    public BigDecimal getBasePrice() { return basePrice; }

    /** Returns the seed cost of the Crop that will be used for planting.
     *
     * @return the seed cost of the Crop
     */
    public BigDecimal getSeedCost() { return seedCost; }

    /** Returns the total Object Coins to be awarded to the Farmer after a successful
     *  harvest of a Crop. This includes the added bonus value from the harvest.
     *
     * @return the Object Coins that will be awarded to the Farmer after a harvest in BigDecimal representation
     */
    public BigDecimal getHarvestPrice() { return harvestPrice; }

    /** Returns the experience you can gain from harvesting the Crop
     *
     * @return the experience you can gain from harvesting the Crop in double representation
     */
    public double getExperienceYield() { return experienceYield; }

    /** Returns the actual day when the Crop can/should be harvested.
     *
     * @return the day when the crop can be harvested in 'int' representation
     */
    public int getDateForHarvest() { return dateForHarvest; }

    /** Assigns a value on the actual produce of a crop, to be used on the
     * computation of the HarvestPrice
     *
     * @param actualProduce a value based on the minimum & maximum produces of the crop
     */

    public void setActualProduce(int actualProduce) { this.actualProduce = actualProduce; }

    /** Assigns a computed value to harvest price, to be incremented on the farmer's wallet
     * when they harvest the produce of the planted crop.
     *
     * @param harvestPrice the final harvest price based on the following computations:
     * </p>
     * HarvestTotal = actualProduce * (basePrice + earningBonus)
     * WaterBonus = HarvestTotal * 0.2 * (timesWatered - 1)
     * FertilizerBonus = HarvestTotal * 0.5 * timesFertilized
     * FinalHarvestBonus = HarvestTotal + WaterBonus + FertilizerBonus
     */
    public void setHarvestPrice(BigDecimal harvestPrice) { this.harvestPrice = harvestPrice; }

    /** A helper method which increments the times the Crop was watered by 1.
     *  This is used on the .waterCrop() method.
     */
    public void incrementTimesWatered() { this.timesWatered++; }

    /** A helper method which increments the times the Crop was fertilized by 1.
     *  This is used on the .fertilizeCrop() method. // to be implemented
     */
    public void incrementTimesFertilized() { this.timesFertilized++; }

    /** A method which computes and sets the harvestTime of a Crop. <p></p>
     *  dateForHarvest is computed by: <p> the day the crop was planted + harvestTime
     *
     * @param day This is the day when the Crop was planted. This will be the basis of the harvestTime
     */
    public void setDateForHarvest(int day) { this.dateForHarvest = day + this.harvestTime; }

    /** A print function that prints the details of a crop object.
     *  This is only used for printing the .....
     *
     * @param status the status of the
     */
    public void printCropDetails(FarmerStatus status) {
        /* prints a specific crop's detail */
        System.out.printf("""
                \n\t%-11s           Crop Type: %-9s
                \tSeed Cost:    %-3.0f     Base Price:         %-2.1f
                \tHarvest Time: %-3d     Experience Yield:   %-2.1f
                \tWater Needed: %-3d     Fertilizer Needed:  %-2d
                \tWater Bonus:  %-3d     Fertilizer Bonus:   %-2d
                \tMin. Produce: %-3d     Max. Produce:       %-2d
                """, ("[" + this.seedName + "]"), this.cropType, this.seedCost.subtract(status.getSEED_COST_REDUCTION()), this.basePrice.add(status.getEARNING_BONUS()),
                this.harvestTime, this.experienceYield, this.waterNeeded, this.fertilizerNeeded,
                this.waterBonus + status.getWATER_LIMIT_BONUS(), this.fertilizerBonus + status.getFERTILIZER_LIMIT_BONUS(), this.minProduce, this.maxProduce);
    }

    /**
     *
     * @return string details of the crop
     */

    public String toString(FarmerStatus status) {
        return "<html>" + this.seedName + " Seed Details: <p><p>" +
                "Seed Name: " + this.seedName + "<p>" +
                "Crop Type: " + this.cropType.getCropType() + "<p>" +
                "Water Needed: " + this.waterNeeded+ "<p>" +
                "Water Bonus Limit: " + (this.waterBonus + status.getWATER_LIMIT_BONUS() ) + "<p>" +
                "Fertilizer Needed: " + this.fertilizerNeeded + "<p>" +
                "Fertilizer Bonus Limit: " + (this.fertilizerBonus + status.getFERTILIZER_LIMIT_BONUS()) + "<p>" +
                "Min. Products Produced: " + this.minProduce + "<p>" +
                "Max. Products Produced: " + this.maxProduce + "<p>" +
                "Seed Cost: " + this.seedCost.subtract(status.getEARNING_BONUS()) + "<p>" +
                "Base Price: " + this.basePrice.add(status.getEARNING_BONUS()) + "<p>" +
                "Experience Yield: " + this.experienceYield + "</html>";
    }
}














