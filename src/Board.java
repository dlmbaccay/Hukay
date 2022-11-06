public class Board {

    private final Tile[] farmLot = new Tile[50];
    private int dayCount = 1;
    private int rockCount;


    public Board() {



        for (int position = 0; position < 50; position++) {
            Tile plot = new Tile();
            plot.setTileStatus(TileStatus.UNPLOWED);
            this.farmLot[position] = plot;
        }
    }

    public Tile[] getFarmLot() { return farmLot; }
    public Tile getPlot(int position) { return this.farmLot[position]; }
    public int getDayCount() { return dayCount; }
    public int getRockCount() { return rockCount; }
    public void incrementDayCount() { this.dayCount++; }
    public void decrementRockCount() { this.rockCount--; }

    public void printFarmLot() {
        int position = 0;

        System.out.printf("\t[DAY #%d]\n\n", this.dayCount);

        while (position < 50) {
            for (int row = 0; row < 10; row++) {
                for (int col = 0; col < 5; col++) {
                    String tileOutput = switch (this.farmLot[position].getTileStatus()){
                        case ROCKED -> "ROCKED";
                        case UNPLOWED -> "UNPLOWED";
                        case PLOWED -> "PLOWED";
                        case SEEDED -> this.farmLot[position].getCrop().getName();
                        case HARVEST -> "HARVEST";
                        case WITHERED -> "WITHERED";
                    };
                    System.out.printf("\t%-11s %-2d ", ("[" + tileOutput + "]"), position + 1);
                    position++;
                }
                System.out.println();
            }
        }
    }

    public boolean containsHarvest() {
        boolean found = false;
        for (int index = 0; index < 50; index++) {
            if (this.farmLot[index].getTileStatus().equals(TileStatus.HARVEST)) {
                found = true;
                break;
            }
        }

        return found;
    }

    public void checkForHarvest() {
        for (int position = 0; position < 50; position++) {
            if (this.farmLot[position].getTileStatus().equals(TileStatus.SEEDED)) {
                if (this.dayCount == this.farmLot[position].getCrop().getHarvestDate()) {
                    if (this.farmLot[position].getCrop().getTimesWatered() >= this.farmLot[position].getCrop().getWaterNeeded()
                            && this.farmLot[position].getCrop().getTimesFertilized() >= this.farmLot[position].getCrop().getFertilizerNeeded()) {
                        this.farmLot[position].setTileStatus(TileStatus.HARVEST);
                    } else {
                        this.farmLot[position].setTileStatus(TileStatus.WITHERED);
                    }
                } else {
                    this.farmLot[position].setTileStatus(TileStatus.WITHERED);
                }
            }
        }
    }

    public void printTileDetails(int position) {
        /* prints appropriate tile details for each tileStatus */
        switch (this.farmLot[position].getTileStatus()) {
            case UNPLOWED, PLOWED, ROCKED -> System.out.printf("\n\t[%s] ~ %2d\n\tTile Status: %s\n\n",
                    this.farmLot[position].getTileStatus(), (position + 1), this.farmLot[position].getTileStatus());
            case WITHERED -> System.out.printf("""
                \n\t[%s] ~ %2d
                \tTile Status:   %s
                \tWithered Crop: %s
                \n""", this.farmLot[position].getTileStatus(), (position + 1), this.farmLot[position].getTileStatus(),
                    this.farmLot[position].getCrop().getName());
            case HARVEST -> System.out.printf("""
                \n\t[%s] ~ %2d
                \tTile Status:       %s
                \tReady For Harvest: %s
                \n""", this.farmLot[position].getTileStatus(), (position + 1), this.farmLot[position].getTileStatus(),
                    this.farmLot[position].getCrop().getName());
            case SEEDED -> System.out.printf("""
            \n\t[%s] ~ %2d
            \tCrop Type: %-9s
            \tWater Needed:      %-2s\tTimes Watered:   %2d\tWater Bonus Limit:      %2d
            \tFertilizer Needed: %-2s\tTimes Fertilized %2d\tFertilizer Bonus Limit: %2d
            \tHarvest Time:      %-2d days
            \n""", this.farmLot[position].getCrop().getName(), (position + 1), this.farmLot[position].getCrop().getCropType(),
                    this.farmLot[position].getCrop().getWaterNeeded(), this.farmLot[position].getCrop().getTimesWatered(),
                    this.farmLot[position].getCrop().getWaterBonus(), this.farmLot[position].getCrop().getFertilizerNeeded(),
                    this.farmLot[position].getCrop().getTimesFertilized(), this.farmLot[position].getCrop().getFertilizerBonus(),
                    this.farmLot[position].getCrop().getHarvestTime());
        }
    }
}
