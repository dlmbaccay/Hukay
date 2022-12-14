package base.farm;

import java.io.FileWriter;
import java.util.ArrayList;

public class FarmLog {

    private int timesPlantedTotal = 0;
    private int timesPlowed = 0;
    private int timesWatered = 0;
    private int timesFertilized = 0;
    private int timesHarvested = 0;

    private int timesPickaxeUsed = 0;

    private int timesShovelUsed = 0;


    private int totalTurnipPlanted = 0;
    private int totalPotatoPlanted = 0;
    private int totalCarrotPlanted = 0;
    private int totalRosePlanted = 0;
    private int totalTulipPlanted = 0;
    private int totalSunflowerPlanted = 0;
    private int totalMangoPlanted = 0;
    private int totalApplePlanted = 0;



    public void incrementTimesPlanted() { this.timesPlantedTotal += 1;}
    public void incrementTimesPlowed() { this.timesPlowed += 1;}
    public void incrementTimesWatered() { this.timesWatered += 1; }
    public void incrementTimesFertilized() { this.timesFertilized += 1; }
    public void incrementTimesHarvested() { this.timesHarvested += 1; }

    public void incrementTimesPickaxeUsed() { this.timesPickaxeUsed += 1;}
    public void incrementTimesShovelUsed() { this.timesShovelUsed += 1;}

    public void incrementPlantedCrops(int index) {

        switch (index){
            case 0 -> totalTurnipPlanted++;
            case 1 -> totalCarrotPlanted++;
            case 2 -> totalPotatoPlanted++;
            case 3 -> totalRosePlanted++;
            case 4 -> totalTulipPlanted++;
            case 5 -> totalSunflowerPlanted++;
            case 6 -> totalMangoPlanted++;
            case 7 -> totalApplePlanted++;
        }

    }


    public void writeFarmLog(Farmer farmer, Board farmBoard) {
        try{
            FileWriter farmLog = new FileWriter("farmLog.txt");
            ArrayList<String> rowOne = new ArrayList<>();
            ArrayList<String> rowTwo = new ArrayList<>();
            ArrayList<String> rowThree = new ArrayList<>();
            ArrayList<String> rowFour = new ArrayList<>();
            ArrayList<String> rowFive = new ArrayList<>();

            for (int position = 1; position <= 10; position++) {
                if (farmBoard.getFARM_LOT().get(position).getTileStatus().equals(TileStatus.SEEDED))
                    rowOne.add(farmBoard.getFARM_LOT().get(position).getCrop().getSeedName());
                else
                    rowOne.add(farmBoard.getFARM_LOT().get(position).getTileStatus().toString()/*FARM_LOT.get(position).getTileStatus().toString()*/);
            }

            for (int position = 11; position <= 20; position++) {
                if (farmBoard.getFARM_LOT().get(position).getTileStatus().equals(TileStatus.SEEDED))
                    rowTwo.add(farmBoard.getFARM_LOT().get(position).getCrop().getSeedName());
                else
                    rowTwo.add(farmBoard.getFARM_LOT().get(position).getTileStatus().toString());
            }

            for (int position = 21; position <= 30; position++) {
                if (farmBoard.getFARM_LOT().get(position).getTileStatus().equals(TileStatus.SEEDED))
                    rowThree.add(farmBoard.getFARM_LOT().get(position).getCrop().getSeedName());
                else
                    rowThree.add(farmBoard.getFARM_LOT().get(position).getTileStatus().toString());
            }

            for (int position = 31; position <= 40; position++) {
                if (farmBoard.getFARM_LOT().get(position).getTileStatus().equals(TileStatus.SEEDED))
                    rowFour.add(farmBoard.getFARM_LOT().get(position).getCrop().getSeedName());
                else
                    rowFour.add(farmBoard.getFARM_LOT().get(position).getTileStatus().toString());
            }

            for (int position = 41; position <= 50; position++) {
                if (farmBoard.getFARM_LOT().get(position).getTileStatus().equals(TileStatus.SEEDED))
                    rowFive.add(farmBoard.getFARM_LOT().get(position).getCrop().getSeedName());
                else
                    rowFive.add(farmBoard.getFARM_LOT().get(position).getTileStatus().toString());
            }

            farmLog.write("FARM STATISTICS\n");

            farmLog.write("\n\tNumber of Days: " + farmBoard.getDayCount() + "\n");
            farmLog.write("\n\t" + rowOne + "\n\t" + rowTwo + "\n\t" + rowThree + "\n\t" + rowFour + "\n\t" + rowFive + "\n\t");

            farmLog.write("\tFarmer Level: " + farmer.getFarmerLevel() + "\n");
            farmLog.write("\tFarmer Experience: " + farmer.getFarmerExperience() + "\n");
            farmLog.write("\tFarmer Object Coins: " + farmer.getObjectCoins() + "\n\n");



            farmLog.write("\tTotal Crops Planted: " + this.timesPlantedTotal + "\n");
            farmLog.write("\tTimes Plowed: " + this.timesPlowed + "\n");
            farmLog.write("\tTimes Watered: " + this.timesWatered + "\n");
            farmLog.write("\tTimes Fertilized: " + this.timesFertilized + "\n");
            farmLog.write("\tTimes Pickaxe was used: " + this.timesPickaxeUsed + "\n");
            farmLog.write("\tTimes Shovel was used: " + this.timesShovelUsed + "\n");
            farmLog.write("\tTimes Harvested: " + this.timesHarvested + "\n");

            farmLog.write("\n\tList of Crops: \n");
            farmLog.write("\tTurnips Planted: " + this.totalTurnipPlanted + "\n");
            farmLog.write("\tCarrots Planted: " + this.totalCarrotPlanted + "\n");
            farmLog.write("\tPotatoes Planted: " + this.totalPotatoPlanted + "\n");
            farmLog.write("\tRoses Planted: " + this.totalRosePlanted + "\n");
            farmLog.write("\tTulips Planted: " + this.totalTulipPlanted + "\n");
            farmLog.write("\tSunflowers Planted: " + this.totalSunflowerPlanted + "\n");
            farmLog.write("\tMango Trees Planted: " + this.totalMangoPlanted + "\n");
            farmLog.write("\tApple Trees Planted: " + this.totalApplePlanted + "\n");

            farmLog.close();
        }catch(Exception e) { e.printStackTrace(); }
    }
}
