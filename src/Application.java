import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {

    /* declaration of farmer and a farmBoard consisting the 10x5 farm lot */
    public static final Farmer farmer = new Farmer();
    public static final Board farmBoard = new Board();

    /* declaration of PLOW, WATER, FERTILIZER, PICKAXE, and SHOVEL tools */
    public static final ArrayList<Tool> toolInventory = new ArrayList<>();
    static {
        toolInventory.add(new Tool("PLOW", BigDecimal.valueOf(0), 0.5));
        toolInventory.add(new Tool("WATER", BigDecimal.valueOf(0), 0.5));
        toolInventory.add(new Tool("FERTILIZER", BigDecimal.valueOf(10), 4.0));
        toolInventory.add(new Tool("PICKAXE", BigDecimal.valueOf(50), 15.0));
        toolInventory.add(new Tool("SHOVEL", BigDecimal.valueOf(7), 2.0));
    }

    /* declaration of TURNIP, CARROT, POTATO, ROSE, TULIPS, SUNFLOWER, MANGO, and APPLE crops */
    public static final ArrayList<Crop> seedList = new ArrayList<>();
    static {
        /*  seedList Indexes
         *
         *  index 0 ~ TURNIP
         *  index 1 ~ CARROT
         *  index 2 ~ POTATO
         *  index 3 ~ ROSE
         *  index 4 ~ TULIPS
         *  index 5 ~ SUNFLOWER
         *  index 6 ~ MANGO
         *  index 7 ~ APPLE
         *
         */

        seedList.add(new Crop(

                "TURNIP", CropType.ROOT_CROP, 2, 1, 0,
                2 + farmer.getFarmerStatus().getWaterBonusLimit(),
                1 + farmer.getFarmerStatus().getFertilizerBonusLimit(),
                1, 2, BigDecimal.valueOf(5), BigDecimal.valueOf(6), 5.0 ));

        seedList.add(new Crop(
                "CARROT", CropType.ROOT_CROP, 3, 1, 0,
                2 + farmer.getFarmerStatus().getWaterBonusLimit(),
                1 + farmer.getFarmerStatus().getFertilizerBonusLimit(),
                1, 2, BigDecimal.valueOf(10), BigDecimal.valueOf(9), 7.5 ));

        seedList.add(new Crop(
                "POTATO", CropType.ROOT_CROP, 5, 3, 1,
                4 + farmer.getFarmerStatus().getWaterBonusLimit(),
                2 + farmer.getFarmerStatus().getFertilizerBonusLimit(),
                1, 10, BigDecimal.valueOf(20), BigDecimal.valueOf(3), 12.5 ));

        seedList.add(new Crop(
                "ROSE", CropType.FLOWER, 1, 1, 0,
                2 + farmer.getFarmerStatus().getWaterBonusLimit(),
                1 + farmer.getFarmerStatus().getFertilizerBonusLimit(),
                1, 1, BigDecimal.valueOf(5), BigDecimal.valueOf(5), 2.5 ));

        seedList.add(new Crop(
                "TULIPS", CropType.FLOWER, 2, 2, 0,
                3 + farmer.getFarmerStatus().getWaterBonusLimit(),
                1 + farmer.getFarmerStatus().getFertilizerBonusLimit(),
                1, 1, BigDecimal.valueOf(10), BigDecimal.valueOf(9), 5.0 ));

        seedList.add(new Crop(
                "SUNFLOWER", CropType.FLOWER, 3, 2, 1,
                3 + farmer.getFarmerStatus().getWaterBonusLimit(),
                2 + farmer.getFarmerStatus().getFertilizerBonusLimit(),
                1, 1, BigDecimal.valueOf(20), BigDecimal.valueOf(19), 7.5 ));

        seedList.add(new Crop(
                "MANGO", CropType.FRUIT_TREE, 10, 7, 4,
                7 + farmer.getFarmerStatus().getWaterBonusLimit(),
                4 + farmer.getFarmerStatus().getFertilizerBonusLimit(),
                5, 15, BigDecimal.valueOf(100), BigDecimal.valueOf(8), 25 ));

        seedList.add(new Crop(
                "APPLE", CropType.FRUIT_TREE, 10, 7, 5,
                7 + farmer.getFarmerStatus().getWaterBonusLimit(),
                5 + farmer.getFarmerStatus().getFertilizerBonusLimit(),
                10, 15, BigDecimal.valueOf(200), BigDecimal.valueOf(5), 25 ));
    }

    /* declaration of essential game logic variables */
    public static Scanner scan = new Scanner(System.in);    /* handles farmer inputs */
    public static boolean isRunning = true;                 /* simulates an instance of the game */
    public static boolean isDay = true;                     /* simulates a day */

    /* main method */
    public static void main(String[] args) {

//        welcomeUser();
//        askUserName();

        while (isRunning) {
            while (isDay) {
                clearScreen();
                monitorFarmLot();
                farmer.printFarmerDetails();
                farmBoard.printFarmLot();
                executeCommand(getCommand());
            }
            isDay = true;
            farmBoard.incrementDayCount();
        }
    }

    /* prompts the farmer for the command they want to put out */
    public static String getCommand() {
        System.out.println("""
                \n\t[T] Use Tool    [P] Buy & Plant Seed    [H] Harvest Crop    [F] Register Benefits
                \t[V] View Tile   [E] End Day             [Q] Quit Game
                """);
        System.out.print("\tSelect Command: ");
        return scan.nextLine();
    }

    public static void executeCommand(String command) {
        switch (command.toUpperCase()) {
            case "T" -> {
                System.out.println("\n\tAvailable Tools:");
                for (Tool tool : toolInventory) { tool.printToolDetails(); }

                try {
                    System.out.print("\n\tSelect Tool & Tile: ");
                    String[] toolInput = splitString(scan.nextLine());
                    int tilePosition = Integer.parseInt(toolInput[1]) - 1;

                    switch (toolInput[0].toUpperCase()) {
                        case "PLOW" -> farmer.plowTile(toolInventory.get(0), farmBoard.getPlot(tilePosition));
                        case "WATER" -> farmer.waterCrop(toolInventory.get(1), farmBoard.getPlot(tilePosition));
                        case "FERTILIZER" -> farmer.fertilizeCrop(toolInventory.get(2), farmBoard.getPlot(tilePosition));
                        case "PICKAXE" -> farmer.pickaxeRock(toolInventory.get(3), farmBoard.getPlot(tilePosition));
                        case "SHOVEL" -> farmer.shovelWithered(toolInventory.get(4), farmBoard.getPlot(tilePosition));
                    }

                    farmBoard.printTileDetails(tilePosition);
                    System.out.println("\n\t[PRESS ENTER TO CONTINUE]"); scan.nextLine();
                } catch (Exception e) {
                    System.out.println("\n\tInvalid Tool Input!");
                    System.out.println("\n\t[PRESS ENTER TO CONTINUE]"); scan.nextLine();
                }
            }

            case "P" -> {
                try {
                    System.out.print("\n\tSelect Tile : ");
                    int plantKey = scan.nextInt() - 1; scan.nextLine();

                    switch (farmBoard.getPlot(plantKey).getTileStatus()) {
                        case PLOWED -> {
                            try {
                                System.out.println("\n\tAvailable Crops:");
                                for (Crop crop : seedList) { crop.printCropDetails(); }

                                System.out.print("\n\tSelect Crop to Buy & Plant: ");
                                String toPlant = scan.nextLine();

                                int seedKey = switch (toPlant.toUpperCase()) {
                                    case "TURNIP" -> 0;
                                    case "CARROT" -> 1;
                                    case "POTATO" -> 2;
                                    case "ROSE" -> 3;
                                    case "TULIPS" -> 4;
                                    case "SUNFLOWER" -> 5;
                                    case "MANGO" -> 6;
                                    case "APPLE" -> 7;
                                    default -> -1;
                                };

                                farmer.plantSeed(seedList.get(seedKey), farmBoard.getPlot(plantKey), farmBoard.getDayCount());
                            } catch (Exception e) {
                                System.out.println("\n\tInvalid Crop Input!");
                            }
                        }
                        case SEEDED -> System.out.println("\n\tThis tile is already seeded! " + farmer.getName() + "!");
                        default -> System.out.println("\n\tYou can only plant on a plowed tile " + farmer.getName() + "!");
                    }
                    farmBoard.printTileDetails(plantKey);
                    System.out.println("\t[PRESS ENTER TO CONTINUE]"); scan.nextLine();
                } catch (Exception e) {
                    System.out.println("\n\tInvalid Tile Input!\n");
                    System.out.println("\t[PRESS ENTER TO CONTINUE]"); scan.nextLine();
                }
            }

            case "H" -> {
                if (farmBoard.containsHarvest()) {
                    try {
                        /* Ask farmer what tile they want to harvest */
                        System.out.print("\n\tSelect Tile to Harvest (ex. 1): ");
                        int harvestKey = scan.nextInt() - 1;

                        /* Validations when farmer accesses different tiles, .harvestCrop() only triggered
                         * when a tile and its crop is ready for harvest.
                         */
                        switch (farmBoard.getPlot(harvestKey).getTileStatus()) {
                            case HARVEST -> farmer.harvestCrop(farmBoard.getPlot(harvestKey));
                            case SEEDED -> System.out.println("\n\tThis seed is not ready for harvest " + farmer.getName() + "!");
                            case UNPLOWED, PLOWED, WITHERED -> System.out.println("\n\tYou cannot harvest a "
                                    + farmBoard.getPlot(harvestKey).getTileStatus() + " " + farmer.getName() + "!");
                        }

                        System.out.println("\n\t[PRESS ENTER TO CONTINUE]");
                        scan.nextLine(); scan.nextLine();
                    } catch (Exception e) { /* Error checking, catches any error the code might encounter. */
                        System.out.print("\n\tOops, that's an invalid input " + farmer.getName() + "!");
                        System.out.println("\n\t[PRESS ENTER TO CONTINUE]");
                        scan.nextLine(); scan.nextLine();
                    }
                } else {
                    System.out.println("\n\tThere are no tiles ready for harvest yet " + farmer.getName() + "!");
                    System.out.println("\n\t[PRESS ENTER TO CONTINUE]");
                    scan.nextLine();
                }
            }
            case "E" -> {
                System.out.println("\n\tEnding day...");
                if (farmBoard.getDayCount() == 1)
                    System.out.println("\n\t1 day has passed...");
                else
                    System.out.println("\n\t" + farmBoard.getDayCount() + " days has passed...");
                System.out.println("\n\t[PRESS ENTER TO CONTINUE]"); scan.nextLine();
                isDay = false;
                isRunning = true;
            }
            case "Q" -> {
                System.out.println("\n\tQuitting Game...");
                System.out.println("\n\tThanks for playing MyFarm " + farmer.getName() + "!\n");
                isDay = false;
                isRunning = false;
            }
            default -> {
                System.out.println("\n\tInvalid Command!");
                System.out.println("\n\t[PRESS ENTER TO CONTINUE]"); scan.nextLine();
            }
        }
    }

    public static void monitorFarmLot() {
        farmBoard.checkForHarvest();
    }
    public static String[] splitString(String toSplit) { return toSplit.split(", ", 2); }

    public static void welcomeUser() {
        clearScreen();
        System.out.println("""
                \n\tWelcome to MyFarm!
                \n\tDevelopers:
                \tDominic Luis Baccay
                \tBien Aaron Miranda
                \n\tGoals:
                \t- Plow a Tile, Water and Harvest a Turnip!
                \t- Earn Object Coins!
                \t- Have Fun!
                """);
        System.out.println("\t[PRESS ENTER TO CONTINUE]"); scan.nextLine();
    }

    public static void askUserName() {
        clearScreen();
        System.out.println("\n\tBefore we get going,\n\tlet us know how would you like us to call you!");
        System.out.print("\n\tEnter a username: ");
        farmer.setName(scan.nextLine());
        System.out.println("\n\tEnjoy the game " + farmer.getName() + "!");
        System.out.println("\n\t[PRESS ENTER TO CONTINUE]"); scan.nextLine();
    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}