import java.math.BigDecimal;

public class Farmer {

    private String name = "Dom";
    private FarmerStatus status = FarmerStatus.DEFAULT;
    private int level = 0;
    private double experience = 0;
    private BigDecimal objectCoins = BigDecimal.valueOf(100);

    public Farmer() {}

    public String getName() { return name; }
    public int getLevel() { return level; }
    public double getExperience() { return experience; }
    public BigDecimal getObjectCoins() { return objectCoins; }
    public FarmerStatus getFarmerStatus() { return status; }

    public void setName(String name) { this.name = name; }
    public void setLevel(int level) { this.level = level; }
    public void setExperience(double experience) { this.experience = experience; }
    public void setObjectCoins(BigDecimal objectCoins) {this.objectCoins = objectCoins; }
    public void setFarmerStatus(FarmerStatus status) { this.status = status; }

    public void plowTile(Tool plow, Tile plot) {
        switch (plot.getTileStatus()) {
            case UNPLOWED -> {
                plot.setTileStatus(TileStatus.PLOWED);
                System.out.println("\n\tPlot plowed successfully!");
                System.out.println("\n\t" + plow.getExperienceGain() + " experience gained!");
                this.experience += plow.getExperienceGain();
            }
            case PLOWED -> System.out.println("\n\tThis plot is already plowed!");
            default -> System.out.println("\n\tYou can only plow an unplowed tile!");
        }
    }

    public void waterCrop(Tool water, Tile plot) {
        if (plot.getTileStatus().equals(TileStatus.SEEDED)) { // if seeded, proceed
            /* If times watered + its bonus limit is reached */
            if (plot.getCrop().getTimesWatered() >= plot.getCrop().getWaterBonus())
                /* No experience gain will be awarded to the farmer to avoid any experience gain exploits */
                System.out.println("\n\tYou have reached the water limit " + this.name + "!");
            else {
                /* Water the crop */
                System.out.println("\n\t" + plot.getCrop().getName() + " successfully watered " + this.name + "!");
                System.out.println("\n\t" + water.getExperienceGain() + " experience gained!");

                /* Increment the crop's water tally */
                plot.getCrop().incrementTimesWatered();

                /* Reduce farmer's object coins based on the plow's tool cost */
                this.objectCoins = this.objectCoins.subtract(water.getCost());
                /* Increment the farmer's experience gain based on the plow's experience yield */
                this.experience += water.getExperienceGain();
            }
        } else // else do error checking
            System.out.print("\n\tYou can only water seeded tiles " + this.name + "!");
    }

    public void fertilizeCrop(Tool fertilizer, Tile plot) {
        if (plot.getTileStatus().equals(TileStatus.SEEDED)) { // if seeded, proceed
            /* If times watered + its bonus limit is reached */
            if (plot.getCrop().getTimesFertilized() >= plot.getCrop().getFertilizerBonus())
                /* No experience gain will be awarded to the farmer to avoid any experience gain exploits */
                System.out.println("\n\tYou have reached the fertilizer limit " + this.name + "!");
            else {
                /* Water the crop */
                System.out.println("\n\t" + plot.getCrop().getName() + " successfully fertilized " + this.name + "!");
                System.out.println("\n\t" + fertilizer.getExperienceGain() + " experience gained!");

                /* Increment the crop's water tally */
                plot.getCrop().incrementTimesFertilized();

                /* Reduce farmer's object coins based on the plow's tool cost */
                this.objectCoins = this.objectCoins.subtract(fertilizer.getCost());
                /* Increment the farmer's experience gain based on the plow's experience yield */
                this.experience += fertilizer.getExperienceGain();
            }
        } else // else do error checking
            System.out.print("\n\tYou can only water seeded tiles " + this.name + "!");
    }
    public void pickaxeRock(Tool pickaxe, Tile plot) {
        // implement when there are rocks!
    }
    public void shovelWithered(Tool shovel, Tile plot) {
        switch (plot.getTileStatus()) {
            case WITHERED -> {
                System.out.println("\n\tWithered " + plot.getCrop().getName() + "successfully shoveled!");
                System.out.println("\n\t7 object coins has been deducted from your wallet!");
                System.out.println("\n\t" + shovel.getExperienceGain() + " experience gained!");

                this.objectCoins = objectCoins.subtract(shovel.getCost());
                this.experience += shovel.getExperienceGain();

                plot.setTileStatus(TileStatus.UNPLOWED);
                plot.setCrop(null);
            }

            case UNPLOWED, ROCKED -> {
                System.out.println("\n\tShovel used improperly!");
                System.out.println("\n\t7 object coins deducted from your wallet!");
                this.objectCoins = objectCoins.subtract(shovel.getCost());
            }

            case SEEDED -> {
                System.out.println("\n\tYou have shoveled a " + plot.getCrop().getName() + " crop!");
                System.out.println("\n\t7 object coins deducted from your wallet!");
                plot.setTileStatus(TileStatus.UNPLOWED);
                plot.setCrop(null);
                this.objectCoins = objectCoins.subtract(shovel.getCost());
            }

            case PLOWED -> {
                System.out.println("\n\tYou cannot use a shovel on a plowed plot!");
            }
        }
    }

    public void plantSeed(Crop newCrop, Tile plot, int day) {
        if (this.objectCoins.compareTo(newCrop.getSeedCost()) >= 0) {
            /* plants a seed given the crop, plot, and day */
            plot.setTileStatus(TileStatus.SEEDED); // sets the plot's tile status into seeded
            plot.setCrop(Crop.newInstance(newCrop)); // assigns a newly initialized instance of the crop passed to the tile
            plot.getCrop().setHarvestDate(day); // sets the date for harvest of the newly planted tile
            System.out.println("\n\t" + plot.getCrop().getSeedCost() + " Object Coins has been deducted from your wallet " + this.name + "!");
            this.objectCoins = objectCoins.subtract(newCrop.getSeedCost()); // reduce seed cost from object coin wallet
        } else {
            System.out.println("\n\tOuch! You have insufficient object coins " + this.name + "!");
        }
    }

    public void harvestCrop(Tile plot) { // harvest a ready for harvest crop in the tile

        BigDecimal HarvestTotal, WaterBonus, FertilizerBonus;

        // assigns a value to actual produce based on the crop's ability to produce such

        if (plot.getCrop().getMinProduce() == plot.getCrop().getMaxProduce())
            plot.getCrop().setActualProduce(plot.getCrop().getMaxProduce());
        else
            plot.getCrop().setActualProduce( (int) Math.floor(Math.random()*(plot.getCrop().getMaxProduce() - plot.getCrop().getMinProduce() + 1) + plot.getCrop().getMinProduce()) );

        /*
         * HarvestTotal = actualProduce * (basePrice + earningBonus)
         * WaterBonus = HarvestTotal * 0.2 * (timesWatered - 1)
         * FertilizerBonus = HarvestTotal * 0.5 * timesFertilized
         * FinalHarvestBonus = HarvestTotal + WaterBonus + FertilizerBonus
         */

        HarvestTotal = BigDecimal.valueOf(plot.getCrop().getActualProduce()).multiply(plot.getCrop().getBasePrice().add(this.status.getEarningBonus()));
        WaterBonus = HarvestTotal.multiply(BigDecimal.valueOf(0.2)).multiply(BigDecimal.valueOf(plot.getCrop().getTimesWatered() - 1));
        FertilizerBonus = HarvestTotal.multiply(BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(plot.getCrop().getTimesFertilized()));

        plot.getCrop().setHarvestPrice(HarvestTotal.add(WaterBonus).add(FertilizerBonus));

        System.out.println("\n\tSuccessfully Harvested " + this.name + "!");
        System.out.println("\n\tTurnips Produced: " + plot.getCrop().getActualProduce());
        System.out.println("\n\t" + plot.getCrop().getHarvestPrice()
                + " object Coins has been incremented to your wallet "
                + this.name + "!");
        System.out.println("\n\t" + plot.getCrop().getExperienceYield() + " experience gained!");

        this.objectCoins = this.objectCoins.add(plot.getCrop().getHarvestPrice()); // increment harvest price to object coin wallet
        this.experience = this.experience + plot.getCrop().getExperienceYield(); // increment farmer experience based on crop's expi yield

        plot.setTileStatus(TileStatus.UNPLOWED); // resets to unplowed since crop's produce is now harvested
        plot.setCrop(null); // resets to null since crop's produce is now harvested
    }

    public void printFarmerDetails() {
        System.out.printf("""
                \n\t[%s]
                \n\tName:  %-10s  Farmer Type: %s
                \tLevel: %-10d  Experience:  %.2f\t\tObject Coins: %.2f
                \n""", (this.name + "'s Farm"), this.name, this.status.getFarmerType(),
                this.level, this.experience, this.objectCoins);
    }
}
