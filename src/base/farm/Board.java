package base.farm;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

/** This class represents a board by the amount of its arable tiles and the amount of days that has passed all
 *  throughout the game.
 *
 */
public class Board {

    /* a 10x5 farm Lot represented in a size 50 tile array */
    private final HashMap<Integer, Tile> FARM_LOT = new HashMap<>();
    private int dayCount = 1;

    /** Creates a default Board object which initializes 50 arable tiles in the game; this
     *  also sets the day to 1, starting the game to day 1
     */
    public Board() {
        // farmLot initialization with ROCKED and UNPLOWED tiles
        int rocks = 0, scOne = 0, scTwo = 0, scThree = 0, scFour = 0, scFive = 0;
        // read "rocks.txt" file
        // assume input file is always in a valid format
        try {
            File rockFile = new File("resources/game_phase/rocks.txt");
            Scanner scanFile = new Scanner(rockFile);

            // assume there are six lines of data within "rocks.txt"
            rocks = Integer.parseInt(scanFile.nextLine());
            scOne = Integer.parseInt(scanFile.nextLine());
            scTwo = Integer.parseInt(scanFile.nextLine());
            scThree = Integer.parseInt(scanFile.nextLine());
            scFour = Integer.parseInt(scanFile.nextLine());
            scFive = Integer.parseInt(scanFile.nextLine());

            scanFile.close();
        } catch (Exception exception) {
            exception.getStackTrace();
        }

        initializeColumn(1, 46, scOne);
        initializeColumn(2, 47, scTwo);
        initializeColumn(3, 48, scThree);
        initializeColumn(4, 49, scFour);
        initializeColumn(5, 50, scFive);
    }

    /** Initializes the rocks scattered in the column
     *
     * @param min       Starting position of a column
     * @param max       End position of a column
     * @param scatter   the number of rocks scattered in one column
     */
    private void initializeColumn(int min, int max, int scatter) {
        int rockCount;
        do {
            rockCount = scatterRocks(min, max);
        } while (rockCount != scatter);
    }

    /** A function that randomly chooses whether a tile will have rocks or not.
     * This function is used only at the start of an instance of a game.
     * @param min Starting Position of a column
     * @param max End position of a column
     * @return returns the number of rocks situated in the column
     */
    private int scatterRocks(int min, int max) {
        int rockCount = 0;
        for (int position = min; position <= max; ) {
            if ((int) Math.floor(Math.random()*(2-1+1)+1) == 1) { // UNPLOWED IF 1
                Tile plot = new Tile();
                plot.setBorder(position < 6 || position > 45 || position % 5 == 0 || position % 5 == 1);
                plot.setTileStatus(TileStatus.UNPLOWED);
                this.FARM_LOT.put(position, plot);
                position += 5;
            } else if ((int) Math.floor(Math.random()*(2-1+1)+1) == 2) { // ROCKED IF 2
                Tile plot = new Tile();
                plot.setBorder(position < 6 || position > 45 || position % 5 == 0 || position % 5 == 1);
                plot.setTileStatus(TileStatus.ROCKED);
                this.FARM_LOT.put(position, plot);
                rockCount++;
                position += 5;
            }
        }
        return rockCount;
    }

    /** Returns a specific tile within the farm lot used in manipulating the farm.
     *
     * @param position  The index of where the tile is within the Tile[] farmLot.
     * @return          The Tile based from the index chosen by the user
     */
    public Tile getPlot(int position) { return this.FARM_LOT.get(position); }

    /** Returns the current day of the game.
     *
     * @return the current day of the game
     */
    public int getDayCount() { return dayCount; }

    /** Returns the FARM_LOT.This consists of the 50 arable tiles
     *
     * @return Returns the FARM_LOT in HashMap<Integer, Tile> representaton.
     */
    public HashMap<Integer, Tile> getFARM_LOT() {
        return FARM_LOT;
    }

    /** A method that increments the day of the game by 1.
     */
    public void incrementDay() { this.dayCount++; }

    /** A method in charge of printing the Tiles of the board, creating a whole board/farm-lot composed
     * of a 10x5 arable Tiles.
     */
    public void printFarmLot() {
        int position = 1, row, col;

        System.out.printf("\t[DAY #%d]\n\n", this.dayCount);

        while (position <= 50) {
            for (row = 0; row < 10; row++) {
                for (col = 0; col < 5; col++) {
                    /* assigned tileOutput a string value based on it's a tile's tileStatus */
                    String tileOutput = switch (this.FARM_LOT.get(position).getTileStatus()) {
                        case ROCKED -> "ROCKED";
                        case UNPLOWED -> "UNPLOWED";
                        case PLOWED -> "PLOWED";
                        case SEEDED -> this.FARM_LOT.get(position).getCrop().getSeedName();
                        case HARVEST -> "HARVEST";
                        case WITHERED -> "WITHERED";
                    };
                    /* prints the tile in table-like fashion */
                    System.out.printf("\t%-11s %-2d", ("[" + tileOutput + "]"), (position ));
                    position++; // increments position
                }
                System.out.print("\n");
            }
        }
    }

    public boolean allWithered() {
        int witheredCount = 0;
        for (int position = 1; position <= 50; position++) {
            if (this.FARM_LOT.get(position).getTileStatus().equals(TileStatus.WITHERED))
                witheredCount++;
        }

        return witheredCount == 50;
    }

    public boolean noPlants() {
        for (int key: FARM_LOT.keySet()){
            if (FARM_LOT.get(key).getTileStatus() == TileStatus.SEEDED || FARM_LOT.get(key).getTileStatus() == TileStatus.HARVEST) {
                return false;
            }
        }
        return true;
    }

    /** A boolean method that checks if there are harvestable tiles in the board/farm-lot.
     *
     * @return true if it founds a harvestable Tile, false if not
     */
    public boolean containsHarvest() {
        /* checks if there are harvestable tiles in the farmLot */
        boolean found = false;
        for (int position = 1; position <= 50; position++) {
            if (this.FARM_LOT.get(position).getTileStatus().equals(TileStatus.HARVEST)) {
                found = true;
                break;
            }
        }
        return found;
    }


    /** A function that checks if any tiles contains a rock
     *
     * @return true if there is a present tile with rocks, false if not.
     */
    public boolean containsRocks() {
        /* checks if there are rocked tiles in the farmLot */
        boolean found = false;
        for (int position = 1; position <= 50; position++) {
            if (this.FARM_LOT.get(position).getTileStatus().equals(TileStatus.ROCKED)) {
                found = true;
                break;
            }
        }
        return found;
    }

    /** A method that checks and sets the statuses of the Tiles.<p></p>
     *  If a Tile contains a Crop, reached the Crop's dateForHarvest and waterNeeded, then
     *  this will modify the Tile's status to HARVEST.<p>
     *  If a Tile contains a Crop, reached the Crop's dateForHarvest, but didn't reach the
     *  waterNeeded, then this will modify the Tile's status to WITHERED.<p>
     *  If a Tile contains a Crop and reached beyond the Crop's dateForHarvest, then this will
     *  modify the Tile's status to WITHERED.
     *
     */
    public void checkForHarvest() {
        /* iterates through the whole Tile[] farmLot and checks if there are tiles ready for harvesting */
        for (int position = 1; position <= 50; position++) {
            /* if the tile contains a seeded tile */

            if (this.FARM_LOT.get(position).getTileStatus().equals(TileStatus.SEEDED)) {
                /* if the day corresponds to the date for harvest of a crop */
                if (this.dayCount == this.FARM_LOT.get(position).getCrop().getDateForHarvest()) {
                    /* if the water and fertilized conditions are satisfied */
                    if (this.FARM_LOT.get(position).getCrop().getTimesWatered() >= this.FARM_LOT.get(position).getCrop().getWaterNeeded()
                            && this.FARM_LOT.get(position).getCrop().getTimesFertilized() >= this.FARM_LOT.get(position).getCrop().getFertilizerNeeded()) {
                        this.FARM_LOT.get(position).setTileStatus(TileStatus.HARVEST);

                    } else { /* if the water and fertilized conditions are not satisfied */
                        /* the crop within a withered tile remains, and will only reset to null when shoveled */
                        this.FARM_LOT.get(position).setTileStatus(TileStatus.WITHERED);
                    }
                }
                } else if (this.FARM_LOT.get(position).getTileStatus().equals(TileStatus.HARVEST)){
                /* if the day succeeds the crop's date for harvest */
                if (this.dayCount > this.FARM_LOT.get(position).getCrop().getDateForHarvest()) {
                    /* the crop within a withered tile remains, and will only reset to null when shoveled */
                    this.FARM_LOT.get(position).setTileStatus(TileStatus.WITHERED);
                }
            }
        }
    }

    /** A method that prints the information of a selected Tile.
     *
     * @param position An integer value that represents the selected tile of the user
     */
    public void printTileDetails(int position) {
        /* prints appropriate tile details for each tileStatus */
        switch (this.FARM_LOT.get(position).getTileStatus()) {
            case UNPLOWED, PLOWED, ROCKED -> System.out.printf("\n\t[%s] ~ %2d\n\tTile Status: %s\n\n",
                    this.FARM_LOT.get(position).getTileStatus(), (position ), this.FARM_LOT.get(position).getTileStatus());
            case WITHERED -> System.out.printf("""
                \n\t[%s] ~ %2d
                \tTile Status:   %s
                \tWithered Crop: %s
                \n""", this.FARM_LOT.get(position).getTileStatus(), (position ), this.FARM_LOT.get(position).getTileStatus(),
                    this.FARM_LOT.get(position).getCrop().getSeedName());
            case HARVEST -> System.out.printf("""
                \n\t[%s] ~ %2d
                \tTile Status:       %s
                \tReady For Harvest: %s
                \n""", this.FARM_LOT.get(position).getTileStatus(), (position ), this.FARM_LOT.get(position).getTileStatus(),
                    this.FARM_LOT.get(position).getCrop().getSeedName());
            case SEEDED -> System.out.printf("""
            \n\t[%s] ~ %2d
            \tCrop Type: %-9s
            \tWater Needed:      %-2s\tTimes Watered:   %2d\tWater Bonus Limit:      %2d
            \tFertilizer Needed: %-2s\tTimes Fertilized %2d\tFertilizer Bonus Limit: %2d \t Base Price: %.2f
            \tDate for Harvest Day #%d
            \n""", this.FARM_LOT.get(position).getCrop().getSeedName(), (position),
                    this.FARM_LOT.get(position).getCrop().getCropType(),
                    this.FARM_LOT.get(position).getCrop().getWaterNeeded(), this.FARM_LOT.get(position).getCrop().getTimesWatered(),
                    this.FARM_LOT.get(position).getCrop().getWaterBonus(), this.FARM_LOT.get(position).getCrop().getFertilizerNeeded(),
                    this.FARM_LOT.get(position).getCrop().getTimesFertilized(), this.FARM_LOT.get(position).getCrop().getFertilizerBonus(), this.FARM_LOT.get(position).getCrop().getBasePrice(),
                    this.FARM_LOT.get(position).getCrop().getDateForHarvest());
        }
    }
}