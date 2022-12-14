package base.farm;
import javax.swing.*;
import java.math.BigDecimal;
import java.util.HashMap;

/** Creates a default Farmer object.
 *  Every Farmer starts with 0.0 experience, level 0 , DEFAULT status
 *  and 100 objectCoins
 *
 */
public class Farmer {
    private FarmerStatus farmerStatus = FarmerStatus.DEFAULT;
    private int farmerLevel = 0;
    private double farmerExperience = 0;
    private BigDecimal objectCoins = BigDecimal.valueOf(100);

    public Farmer() {}

    // Getters for Farmer class
    public FarmerStatus getFarmerStatus() { return farmerStatus; }
    public int getFarmerLevel() { return farmerLevel; }
    public double getFarmerExperience() { return farmerExperience; }
    public BigDecimal getObjectCoins() { return objectCoins; }

    /** A method that is in charge of plowing the Tile, or in other words, changing
     *  the status of a specific Tile object from UNPLOWED to PLOWED.
     *  A Tile object can only be plowed only if the status of the Tile is UNPLOWED; if so,
     *  then this will change the current status of the Tile to PLOWED, an experience will be
     *  added to the current experience of the Farmer, and it will print that plowing was successful and a
     *  specific experience is gained by the farmer. <p> </p>
     *  if the user tries to plow a PLOWED tile, then this will print that the tile is already plowed. <p>
     *  If the user tries to plow a Tile anything other than UNPLOWED and PLOWED, this will tell the user
     *  that they can only plow unplowed tiles.
     *
     * @param plow This represents the 'Plow' Tool object that is used for plowing an UNPLOWED Tile
     * @param plot The plot the user selected for plowing
     */
    public void plowTile(Tool plow, Tile plot) { /* plows a tile */
        switch (plot.getTileStatus()){
            case UNPLOWED -> {
                plot.setTileStatus(TileStatus.PLOWED);
                this.objectCoins = this.objectCoins.subtract(plow.getToolCost());
                this.farmerExperience += plow.getExperienceGain();

                System.out.println("\n\tPlot plowed successfully!");
                System.out.println("\n\t" + plow.getExperienceGain() + " experience gained!");
            }
            case PLOWED -> System.out.println("\n\tThis tile is already plowed!");
            default -> System.out.println("\n\tYou can only plow unplowed tiles!");
        }
    }

    /** A method that is in charge of watering the Crop in the selected Tile. <p> </p>
     *  A Tile should have the status SEEDED in order for its Crop to be watered. If the status of the
     *  tile is anything other than SEEDED, then it will tell the user that it can only water seeded tiles.
     *  If the Tile is SEEDED, then this will water the Crop inside the Tile and a specific experience value
     *  will be added to the current experience of the Farmer. <p><p/>
     *  The user cannot water more than the water bonus of the Crop inside the tile, if they do so, then
     *  this will print that they reached the water limit of the Crop inside the Tile, timesWatered of the crop
     *  and experience of the Farmer will not be incremented.
     *
     * @param water This represents the 'Watering Can' Tool that is used for watering the Crop in the Tile.
     * @param plot  The plot the user selected for watering
     */
    public void waterCrop(Tool water, Tile plot) { // waters a crop in a tile
        plot.getCrop().incrementTimesWatered();
        this.objectCoins = this.objectCoins.subtract(water.getToolCost());
        this.farmerExperience = this.farmerExperience + water.getExperienceGain();

        System.out.println("\n\t" + plot.getCrop().getSeedName() + " successfully watered!");
        System.out.println("\n\t" + water.getExperienceGain() + " experience gained!");
    }

    /** A method that is in charge of planting the Crop the user selected in the Tile the user also selected.
     *  This first sets the status of the Tile to SEEDED, sets the details of the Crop object of the Tile to
     *  the newCrop, and computes the day when the Crop will be ready for harvesting by calling the
     *  .computeDateForHarvest method. This also deducts the current Object Coins of the Farmer based on
     *  the seedCost of the selected Crop.
     *
     * @param newCrop   The crop that will be planted to the selected Tile
     * @param FARM_LOT  The farm lot of the game
     * @param day       The current day of the game
     * @param plantKey  The selected Tile of the user
     */
    public void plantSeed(Crop newCrop, HashMap<Integer, Tile> FARM_LOT, int day, int plantKey) {
        /* Validation wherein a farmer can only buy & plant seeds when they have enough money */

        if (this.objectCoins.compareTo(newCrop.getSeedCost()) >= 0) {
            if (newCrop.getCropType() == CropType.FRUIT_TREE) {
                System.out.println("Tree");
                int NORTH = -5;
                int SOUTH = 5;
                int EAST = 1;
                int WEST = -1;
                int NORTHEAST = -4;
                int NORTHWEST = -6;
                int SOUTHEAST = 6;
                int SOUTHWEST = 4;
                if (!FARM_LOT.get(plantKey).isBorder()) {
                    if ((FARM_LOT.get(plantKey + WEST).getTileStatus() == TileStatus.UNPLOWED || FARM_LOT.get(plantKey + WEST).getTileStatus() == TileStatus.PLOWED) && //left
                            (FARM_LOT.get(plantKey + EAST).getTileStatus() == TileStatus.UNPLOWED || FARM_LOT.get(plantKey + EAST).getTileStatus() == TileStatus.PLOWED) && //right
                            (FARM_LOT.get(plantKey + NORTH).getTileStatus() == TileStatus.UNPLOWED || FARM_LOT.get(plantKey + NORTH).getTileStatus() == TileStatus.PLOWED) && //up
                            (FARM_LOT.get(plantKey + SOUTH).getTileStatus() == TileStatus.UNPLOWED || FARM_LOT.get(plantKey + SOUTH).getTileStatus() == TileStatus.PLOWED) && //down
                            (FARM_LOT.get(plantKey + NORTHEAST).getTileStatus() == TileStatus.UNPLOWED || FARM_LOT.get(plantKey + NORTHEAST).getTileStatus() == TileStatus.PLOWED) &&
                            (FARM_LOT.get(plantKey + NORTHWEST).getTileStatus() == TileStatus.UNPLOWED || FARM_LOT.get(plantKey + NORTHWEST).getTileStatus() == TileStatus.PLOWED) &&
                            (FARM_LOT.get(plantKey + SOUTHEAST).getTileStatus() == TileStatus.UNPLOWED || FARM_LOT.get(plantKey + SOUTHEAST).getTileStatus() == TileStatus.PLOWED) &&
                            (FARM_LOT.get(plantKey + SOUTHWEST).getTileStatus() == TileStatus.UNPLOWED || FARM_LOT.get(plantKey + SOUTHWEST).getTileStatus() == TileStatus.PLOWED)) {
                        /* plants a seed given the crop, plot, and day */
                        FARM_LOT.get(plantKey).setTileStatus(TileStatus.SEEDED); // sets the FARM_LOT.get(plantKey)'s tile status into seeded
                        FARM_LOT.get(plantKey).setCrop(Crop.newInstance(newCrop, farmerStatus)); // assigns a newly initialized instance of the crop passed to the tile
                        FARM_LOT.get(plantKey).getCrop().setDateForHarvest(day); // sets the date for harvest of the newly planted tile
                        System.out.println("\n\t" + FARM_LOT.get(plantKey).getCrop().getSeedCost() + " Object Coins has been deducted from your wallet!");
                        this.objectCoins = objectCoins.subtract(newCrop.getSeedCost().subtract(getFarmerStatus().getSEED_COST_REDUCTION())); // deduct seed cost from object coin wallet
                    } else {
                        System.out.println("Oops, can't plant, one of the adjacent tiles is occupied.");
                    }
                } else {
                    System.out.println("Can't plant a tree there <edge>.");
                }
            } else {/* plants a seed given the crop, plot, and day */
                FARM_LOT.get(plantKey).setTileStatus(TileStatus.SEEDED); // sets the FARM_LOT.get(plantKey)'s tile status into seeded
                FARM_LOT.get(plantKey).setCrop(Crop.newInstance(newCrop, farmerStatus)); // assigns a newly initialized instance of the crop passed to the tile
                FARM_LOT.get(plantKey).getCrop().setDateForHarvest(day); // sets the date for harvest of the newly planted tile
                System.out.println("\n\t" + FARM_LOT.get(plantKey).getCrop().getSeedCost() + " Object Coins has been deducted from your wallet!");
                this.objectCoins = objectCoins.subtract(newCrop.getSeedCost().subtract(getFarmerStatus().getSEED_COST_REDUCTION())); // deduct seed cost from object coin wallet
            }
        } else {
            System.out.println("Oops, you have insufficient Object Coins.");
        }
    }

    /** A method that is in charge of harvesting the Crop inside the selected Tile. <p></p>
     *  This calls the .computeHarvestPrice() which computes the total earnings the farmer
     *  will get from the harvested crop, and .getExperienceYield() which computes the
     *  experience the farmer will gain after harvesting the crop; the resulting values will
     *  be added to the Object Coins and experience of the farmer through this method. <p></p>
     *  This method also resets the status of the Tile object to UNPLOWED.
     *
     * @param plot The selected Tile of the user that will be subject for harvesting
     */
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

        HarvestTotal = BigDecimal.valueOf(plot.getCrop().getActualProduce()).multiply(plot.getCrop().getBasePrice().add(this.farmerStatus.getEARNING_BONUS()));
        WaterBonus = HarvestTotal.multiply(BigDecimal.valueOf(0.2)).multiply(BigDecimal.valueOf(plot.getCrop().getTimesWatered() - 1));
        FertilizerBonus = HarvestTotal.multiply(BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(plot.getCrop().getTimesFertilized()));

        plot.getCrop().setHarvestPrice(HarvestTotal.add(WaterBonus).add(FertilizerBonus));

        System.out.println("\n\tSuccessfully Harvested!");
        System.out.println("\n\tTurnips Produced: " + plot.getCrop().getActualProduce());
        System.out.println("\n\t" + plot.getCrop().getHarvestPrice() + " Object Coins has been incremented to your wallet!");
        System.out.println("\n\t" + plot.getCrop().getExperienceYield() + " experience gained!");

        this.objectCoins = this.objectCoins.add(plot.getCrop().getHarvestPrice()); // increment harvest price to object coin wallet
        this.farmerExperience = this.farmerExperience + plot.getCrop().getExperienceYield(); // increment farmer experience based on crop's exp. yield

        plot.setTileStatus(TileStatus.UNPLOWED); // resets to unplowed since crop's produce is now harvested
        plot.setCrop(null); // resets to null since crop's produce is now harvested
    }

    /** A function that modifies the tile status for ROCKED tiles.
     * This also subtracts the user's coins per use.
     * This also add farmer experience
     *
     * @param pickaxe The pickaxe tool
     * @param plot    The chosen plot to be modified
     */
    public void removeRock(Tool pickaxe, Tile plot) {
        plot.setTileStatus(TileStatus.UNPLOWED);
        System.out.println("\n\tRock pickaxed successfully!");

        this.objectCoins = this.objectCoins.subtract(pickaxe.getToolCost());
        this.farmerExperience = this.farmerExperience + pickaxe.getExperienceGain();

    }

    /** A function that modifies the tile status for ROCKED tiles.
     * This also subtracts the user's coins per use.
     * This also add farmer experience
     *
     * @param shovel The shovel tool
     * @param plot   The chosen plot to be modified
     */
    public void shovelWithered(Tool shovel, Tile plot) {

        switch (plot.getTileStatus()) {
            case WITHERED -> {
                plot.setCrop(null);
                plot.setTileStatus(TileStatus.UNPLOWED);
                System.out.println("\n\tWithered tile shoveled successfully!");

                this.objectCoins = this.objectCoins.subtract(shovel.getToolCost());
                this.farmerExperience = this.farmerExperience + shovel.getExperienceGain();
            }
            case SEEDED -> {
                plot.setCrop(null);
                plot.setTileStatus(TileStatus.UNPLOWED);

                this.objectCoins = this.objectCoins.subtract(shovel.getToolCost());
                this.farmerExperience = this.farmerExperience + shovel.getExperienceGain();
                System.out.println("shovel used on a seeded tile...");
            }
            case UNPLOWED, ROCKED -> {
                this.objectCoins = this.objectCoins.subtract(shovel.getToolCost());
                this.farmerExperience = this.farmerExperience + shovel.getExperienceGain();
                System.out.println("shovel used on an unplowed/rocked tile...");
            }
        }
    }

    /** A function that fertilize the crop.
     *
     * @param fertilize The fertilize tool
     * @param plot      The chosen plot to be modified
     */
    public void fertilizeCrop(Tool fertilize, Tile plot) {

        System.out.println("\n\t" + plot.getCrop().getSeedName() + " successfully fertilized!");

        plot.getCrop().incrementTimesFertilized();

        this.objectCoins = this.objectCoins.subtract(fertilize.getToolCost()); // placeholder for tools with cost > 0
        this.farmerExperience = this.farmerExperience + fertilize.getExperienceGain();
    }


    /** Modifies the farmer level. Also for level up
     *
     */
    public void checkFarmerLevel(){
        if (farmerLevel != (int) (farmerExperience / 100)  && farmerExperience != 0.0){
            if (farmerLevel != (int) (farmerExperience / 100) ) {
                JOptionPane.showMessageDialog(
                        null,
                        "Congratulations, you leveled up!",
                        "Level Up Message", JOptionPane.INFORMATION_MESSAGE);
            }
            farmerLevel = (int) (farmerExperience / 100);
        }
    }

    /** A function that modifies the rank up of the farmer status
     *
     */
    public void rankUp(){

        switch (this.farmerStatus) {
            case DEFAULT -> {
                this.farmerStatus = FarmerStatus.REGISTERED;
                this.objectCoins = this.objectCoins.subtract(BigDecimal.valueOf(200));
            }
            case REGISTERED -> {
                this.farmerStatus = FarmerStatus.DISTINGUISHED;
                this.objectCoins = this.objectCoins.subtract(BigDecimal.valueOf(300));
            }

            case DISTINGUISHED -> {
                this.farmerStatus = FarmerStatus.LEGENDARY;
                this.objectCoins = this.objectCoins.subtract(BigDecimal.valueOf(400));
            }
        }
    }

}