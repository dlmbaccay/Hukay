package base.mvc;

import base.farm.TileStatus;

import javax.swing.*;
import java.awt.event.*;
import java.math.BigDecimal;

/** Class that is bridge between the Model and the View
 */
public class FarmController implements ActionListener{

    private boolean outerListenerExecuted = false;
    private boolean nestedListenerExecuted = false;

    /** Function
     *
     * @param ae the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        // tool buttons
        if (ae.getSource() == FARM_VIEW.getPLOW_BUTTON())
            plowTile();
        else if (ae.getSource() == FARM_VIEW.getWATER_BUTTON())
            waterTile();
        else if (ae.getSource() == FARM_VIEW.getFERTILIZE_BUTTON())
            fertilizeTile();
        else if (ae.getSource() == FARM_VIEW.getPICKAXE_BUTTON())
            pickaxeRock();
        else if (ae.getSource() == FARM_VIEW.getSHOVEL_BUTTON())
            shovelWithered();

        // action buttons
        else if (ae.getSource() == FARM_VIEW.getHARVEST_BUTTON())
            harvestCrop();
        else if (ae.getSource() == FARM_VIEW.getREGISTER_BUTTON())
            registerFarmer();
        else if (ae.getSource() == FARM_VIEW.getEND_DAY_BUTTON())
            endDay();
        else if (ae.getSource() == FARM_VIEW.getQUIT_BUTTON())
            quitGame();

        // seed buttons
        else if (ae.getSource() == FARM_VIEW.getTURNIP_BUTTON())
            plantTile(0);
        else if (ae.getSource() == FARM_VIEW.getCARROT_BUTTON())
            plantTile(1);
        else if (ae.getSource() == FARM_VIEW.getPOTATO_BUTTON())
            plantTile(2);
        else if (ae.getSource() == FARM_VIEW.getROSE_BUTTON())
            plantTile(3);
        else if (ae.getSource() == FARM_VIEW.getTULIPS_BUTTON())
            plantTile(4);
        else if (ae.getSource() == FARM_VIEW.getSUNFLOWER_BUTTON())
            plantTile(5);
        else if (ae.getSource() == FARM_VIEW.getMANGO_BUTTON())
            plantTile(6);
        else if (ae.getSource() == FARM_VIEW.getAPPLE_BUTTON())
            plantTile(7);

        outerListenerExecuted = false;
        nestedListenerExecuted = false;
    }

    private final FarmView FARM_VIEW;
    private final FarmModel FARM_MODEL;

    /** Constructor of the FarmController.
     *
     * @param FARM_VIEW The visuals of the game
     * @param FARM_MODEL The data of the game
     */
    FarmController(FarmView FARM_VIEW, FarmModel FARM_MODEL) {
        this.FARM_VIEW = FARM_VIEW;
        this.FARM_MODEL = FARM_MODEL;
    }

    /**
     * Function that launch the game,
     * has the RunMusic here
     */
    public void launch() {
        SoundHandler.RunMusic("resources/SoundPacks/bgg_02.wav");
        FARM_VIEW.startPhase();
        FARM_VIEW.getSTART_BUTTON().addActionListener(e -> {
            System.out.println("start button clicked...");
            System.out.println("disposing start phase...");
            System.out.println("launching game phase...");

            FARM_VIEW.remove(FARM_VIEW.getSTART_PANEL());
            FARM_VIEW.getContentPane().invalidate();
            FARM_VIEW.getContentPane().validate();
            FARM_VIEW.getContentPane().repaint();

            FARM_VIEW.gamePhase();
            runFarm();
        });
    }

    /** Initialized the farm including the mouse and action listeners and for the visuals to pop up in the screen.
     *
     */
    private void runFarm() {
        mouseListeners();
        actionListeners();
        updateFarmDetails();
        updateFarmTiles();
    }

    /**
     * Adds a mouse listener to all the buttons.
     */
    private void mouseListeners() {
        // farmTilesMouseListener
        for (int position = 1; position <= 50; position++) {
            int finalPosition = position;
            FARM_VIEW.getFARM_TILES().get(finalPosition).addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) { showTileDetails(finalPosition); }
                @Override public void mouseExited(MouseEvent e) { resetDetailLabel(); }
            });
        }

        // toolMouseListener
        for (int index = 0; index < FARM_VIEW.getTOOL_BUTTONS().size(); index++) {
            int finalIndex = index;
            FARM_VIEW.getTOOL_BUTTONS().get(index).addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    FARM_VIEW.getDETAIL_LABEL().setText(FARM_MODEL.getTOOL_INVENTORY().get(finalIndex).toString());
                }

                @Override public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    resetDetailLabel();
                }
            });
        }

        // actionMouseListener
        for (int index = 0; index < FARM_VIEW.getACTION_BUTTONS().size(); index++) {
            int finalIndex = index;
            FARM_VIEW.getACTION_BUTTONS().get(index).addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);

                    String details = switch (finalIndex) {
                        case 0 -> "<html>Harvestable crops will appear as their product on the tile!<p>" +
                                "Harvesting costs nothing and you will earn object coins<p>" +
                                "depending on the produce your harvest will make.</html>";
                        case 1 -> "<html>Register to get farmer benefits!<p>" +
                                "When attained a certain level, you shall get <p>" +
                                "bonus earnings per produce, <p>" +
                                "seed cost reduction, <p>" +
                                "water bonus limit increase, <p>" +
                                "fertilizer bonus limit increase, <p>" +
                                "with a registration fee.</html>";
                        case 2 -> "<html>Ends a day and lets the farmer sleep for a bit! </html>";
                        case 3 -> "<html>Let's the farmer quit the game and call it a game!";
                        default -> "";
                    };
                    FARM_VIEW.getDETAIL_LABEL().setText(details);
                }

                @Override public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    resetDetailLabel();
                }
            });
        }

        // seedMouseListener
        for (int index = 0; index < FARM_VIEW.getSEED_BUTTONS().size(); index++) {
            int finalIndex = index;
            FARM_VIEW.getSEED_BUTTONS().get(index).addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    FARM_VIEW.getDETAIL_LABEL().setText(FARM_MODEL.getSEED_LIST().get(finalIndex).toString(FARM_MODEL.getFARMER().getFarmerStatus()));
                }

                @Override public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    resetDetailLabel();
                }
            });
        }
    }

    /**
     * Adds an action listener to all the buttons.
     */
    private void actionListeners() {
        // tool buttons
        FARM_VIEW.getPLOW_BUTTON().addActionListener(this);
        FARM_VIEW.getWATER_BUTTON().addActionListener(this);
        FARM_VIEW.getFERTILIZE_BUTTON().addActionListener(this);
        FARM_VIEW.getPICKAXE_BUTTON().addActionListener(this);
        FARM_VIEW.getSHOVEL_BUTTON().addActionListener(this);

        // action buttons
        FARM_VIEW.getHARVEST_BUTTON().addActionListener(this);
        FARM_VIEW.getREGISTER_BUTTON().addActionListener(this);
        FARM_VIEW.getEND_DAY_BUTTON().addActionListener(this);
        FARM_VIEW.getQUIT_BUTTON().addActionListener(this);

        // seed buttons
        FARM_VIEW.getTURNIP_BUTTON().addActionListener(this);
        FARM_VIEW.getCARROT_BUTTON().addActionListener(this);
        FARM_VIEW.getPOTATO_BUTTON().addActionListener(this);
        FARM_VIEW.getROSE_BUTTON().addActionListener(this);
        FARM_VIEW.getTULIPS_BUTTON().addActionListener(this);
        FARM_VIEW.getSUNFLOWER_BUTTON().addActionListener(this);
        FARM_VIEW.getMANGO_BUTTON().addActionListener(this);
        FARM_VIEW.getAPPLE_BUTTON().addActionListener(this);
    }

    /** A function that updates what is being seen in the GUI
     */
    private void updateFarmDetails() {

        FARM_MODEL.getFARMER().checkFarmerLevel();

        FARM_VIEW.getDAY_LABEL().setText(String.valueOf(FARM_MODEL.getBOARD().getDayCount()));

        FARM_VIEW.getLEVEL_LABEL().setText(String.valueOf(FARM_MODEL.getFARMER().getFarmerLevel()));
        FARM_VIEW.getEXPERIENCE_LABEL().setText(String.valueOf(FARM_MODEL.getFARMER().getFarmerExperience()));
        FARM_VIEW.getSTATUS_LABEL().setText(String.valueOf(FARM_MODEL.getFARMER().getFarmerStatus()));
        FARM_VIEW.getOBJECT_COINS_LABEL().setText(String.valueOf(FARM_MODEL.getFARMER().getObjectCoins()));

        checkGameOver();
    }

    /**
     * A function that updates the visuals of the arable tiles in the game.
     */
    private void updateFarmTiles() {
        for (int position = 1; position <= 50; position++) {

            ImageIcon tileIcon = switch (FARM_MODEL.getBOARD().getPlot(position).getTileStatus()) {
                case ROCKED -> new ImageIcon("resources/game_phase/board_tiles/rocked-80.png");
                case UNPLOWED -> new ImageIcon("resources/game_phase/board_tiles/unplowed-80.png");
                case PLOWED -> new ImageIcon("resources/game_phase/board_tiles/plowed-80.png");
                case SEEDED -> switch (FARM_MODEL.getBOARD().getPlot(position).getCrop().getSeedName()) {
                    case "Turnip" -> new ImageIcon("resources/game_phase/crop_tiles/seeded-turnip-80.png");
                    case "Carrot" -> new ImageIcon("resources/game_phase/crop_tiles/seeded-carrot-80.png");
                    case "Potato" -> new ImageIcon("resources/game_phase/crop_tiles/seeded-potato-80.png");
                    case "Rose" -> new ImageIcon("resources/game_phase/crop_tiles/seeded-rose-80.png");
                    case "Tulips" -> new ImageIcon("resources/game_phase/crop_tiles/seeded-tulip-80.png");
                    case "Sunflower" -> new ImageIcon("resources/game_phase/crop_tiles/seeded-sunflower-80.png");
                    case "Mango" -> new ImageIcon("resources/game_phase/crop_tiles/seeded-mango-tree-80.png");
                    case "Apple" -> new ImageIcon("resources/game_phase/crop_tiles/seeded-apple-tree-80.png");
                    default -> null;
                };
                case HARVEST -> switch (FARM_MODEL.getBOARD().getPlot(position).getCrop().getSeedName()) {
                    case "Turnip" -> new ImageIcon("resources/game_phase/crop_tiles/harvestable-turnip-80.png");
                    case "Carrot" -> new ImageIcon("resources/game_phase/crop_tiles/harvestable-carrot-80.png");
                    case "Potato" -> new ImageIcon("resources/game_phase/crop_tiles/harvestable-potato-80.png");
                    case "Rose" -> new ImageIcon("resources/game_phase/crop_tiles/harvestable-rose-80.png");
                    case "Tulips" -> new ImageIcon("resources/game_phase/crop_tiles/harvestable-tulip-80.png");
                    case "Sunflower" -> new ImageIcon("resources/game_phase/crop_tiles/harvestable-sunflower-80.png");
                    case "Mango" -> new ImageIcon("resources/game_phase/crop_tiles/harvestable-mango-tree-80.png");
                    case "Apple" -> new ImageIcon("resources/game_phase/crop_tiles/harvestable-apple-tree-80.png");
                    default -> null;
                };
                case WITHERED -> new ImageIcon("resources/game_phase/board_tiles/withered-80.png");
            };

            FARM_VIEW.getFARM_TILES().get(position).setIcon(tileIcon); // sets visual representations
            FARM_VIEW.getFARM_TILES().get(position).setDisabledIcon(tileIcon); // sets visual representations
        }
    }

    /** Main function for planting a crop to a tile.
     *
     * @param seedIndex The chosen seed to be planted  in the tile
     */
    private void plantTile(int seedIndex) {

        if (outerListenerExecuted) { return; }

        System.out.println(FARM_MODEL.getSEED_LIST().get(seedIndex) + " seed button clicked...");
        disableActionPanelButtons();
        enableFarmTileButtons();

        JOptionPane.showMessageDialog(null,
                "Select a Tile to Plant this " + FARM_MODEL.getSEED_LIST().get(seedIndex).getSeedName() + " on!",
                "Plant Seed Message", JOptionPane.INFORMATION_MESSAGE);

        for (int position = 1; position <= 50; position++) {
            int finalPosition = position;
            FARM_VIEW.getFARM_TILES().get(position).addActionListener(e -> {
                if (nestedListenerExecuted) { return; }

                switch (FARM_MODEL.getBOARD().getPlot(finalPosition).getTileStatus()) {
                    case PLOWED -> {
                        if (FARM_MODEL.getFARMER().getObjectCoins().
                                compareTo(FARM_MODEL.getSEED_LIST().get(seedIndex).getSeedCost()) >= 0) {
                            FARM_MODEL.getFARMER().plantSeed(
                                    FARM_MODEL.getSEED_LIST().get(seedIndex),
                                    FARM_MODEL.getBOARD().getFARM_LOT(),
                                    FARM_MODEL.getBOARD().getDayCount(),
                                    finalPosition);

                            updateFarmDetails();
                            updateFarmTiles();
                            FARM_MODEL.getFARM_LOG().incrementTimesPlanted();
                            FARM_MODEL.getFARM_LOG().incrementPlantedCrops(seedIndex);

                            if (FARM_MODEL.getBOARD().getFARM_LOT().get(finalPosition).getTileStatus() == TileStatus.SEEDED) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        FARM_MODEL.getSEED_LIST().get(seedIndex).getSeedName() + " Successfully Planted!",
                                        "Plant Seed Message", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "You cannot plant "+ FARM_MODEL.getSEED_LIST().get(seedIndex).getSeedName() + " there.",
                                        "Plant Seed Message", JOptionPane.INFORMATION_MESSAGE);
                            }

                        } else  { JOptionPane.showMessageDialog(null, "Insufficient Object Coins!",
                                    "Plant Seed Message", JOptionPane.ERROR_MESSAGE); }
                    }
                    case SEEDED -> JOptionPane.showMessageDialog(null,
                                    "This tile is already seeded!", "Plant Seed Message", JOptionPane.ERROR_MESSAGE);
                    case ROCKED, UNPLOWED, HARVEST, WITHERED -> JOptionPane.showMessageDialog(null,
                            "You can only plant on a plowed tile!", "Plant Seed Message", JOptionPane.ERROR_MESSAGE);
                }

                disableFarmTileButtons();
                enableActionPanelButtons();
                nestedListenerExecuted = true;
            });
        }
        outerListenerExecuted = true;
    }

    /** Main function for plowing a tile.
     *  This changes the status of a tile from UNPLOWED to PLOWED.
     *  If tile status is anything other than UNPLOWED, this function will not modify the status.
     *
     */
    private void plowTile() {

        if (outerListenerExecuted) { return; }

        System.out.println("plow button clicked...");
        disableActionPanelButtons();
        enableFarmTileButtons();

        JOptionPane.showMessageDialog(null,
                "Select a Tile Plow!", "Plow Message", JOptionPane.INFORMATION_MESSAGE);

        for (int position = 1; position <= 50; position++) {
            int finalPosition = position;
            FARM_VIEW.getFARM_TILES().get(position).addActionListener(e -> {
                if (nestedListenerExecuted) { return; }

                switch (FARM_MODEL.getBOARD().getPlot(finalPosition).getTileStatus()) {
                    case UNPLOWED -> {
                        FARM_MODEL.getFARMER().plowTile(FARM_MODEL.getTOOL_INVENTORY().get(0), FARM_MODEL.getBOARD().getPlot(finalPosition));
                        JOptionPane.showMessageDialog(null, "Successfully Plowed!", "Plow Message", JOptionPane.INFORMATION_MESSAGE);

                        updateFarmDetails();
                        updateFarmTiles();
                        FARM_MODEL.getFARM_LOG().incrementTimesPlowed();
                    }
                    case PLOWED -> JOptionPane.showMessageDialog(null, "This tile is already plowed!", "Plow Message", JOptionPane.ERROR_MESSAGE);
                    case ROCKED -> JOptionPane.showMessageDialog(null, "This is a rocked tile! Remove it first before unplowing...", "Plow Message", JOptionPane.ERROR_MESSAGE);
                    case SEEDED, HARVEST -> JOptionPane.showMessageDialog(null, "This tile is occupied!", "Plow Message", JOptionPane.ERROR_MESSAGE);
                    case WITHERED -> JOptionPane.showMessageDialog(null, "This tile is withered! Remove it with a shovel...", "Plow Message", JOptionPane.ERROR_MESSAGE);
                }

                disableFarmTileButtons();
                enableActionPanelButtons();
                nestedListenerExecuted = true;
            });
        }
        outerListenerExecuted = true;
    }

    /** Increments the timesWatered variable of the crop object within the tile chosen.
     * If the conditions are not met, this will not increment the variable.
     *
     */
    private void waterTile() {

        if (outerListenerExecuted) { return; }

        System.out.println("water tile button clicked...");
        disableActionPanelButtons();
        enableFarmTileButtons();

        JOptionPane.showMessageDialog(null,
                "Select a Tile to Water On!", "Water Tile Message", JOptionPane.INFORMATION_MESSAGE);

        for (int position = 1; position <= 50; position++) {
            int finalPosition = position;
            FARM_VIEW.getFARM_TILES().get(position).addActionListener(e -> {
                if (nestedListenerExecuted) { return; }

                switch (FARM_MODEL.getBOARD().getPlot(finalPosition).getTileStatus()) {
                    case SEEDED -> {

                        FARM_MODEL.getFARMER().waterCrop(FARM_MODEL.getTOOL_INVENTORY().get(1), FARM_MODEL.getBOARD().getPlot(finalPosition));
                        JOptionPane.showMessageDialog(null, "Successfully Watered!", "Water Tile Message", JOptionPane.INFORMATION_MESSAGE);

                        updateFarmDetails();
                        updateFarmTiles();
                        FARM_MODEL.getFARM_LOG().incrementTimesWatered();
                    }
                    case ROCKED, UNPLOWED, PLOWED, HARVEST, WITHERED ->
                            JOptionPane.showMessageDialog(null, "You can only water seeded tiles!", "Water Tile Message", JOptionPane.ERROR_MESSAGE);
                }

                disableFarmTileButtons();
                enableActionPanelButtons();
                nestedListenerExecuted = true;
            });
        }
        outerListenerExecuted = true;
    }

    /** Increments the timesFertilized variable of the crop object within the tile chosen.
     * If the conditions are not met, this will not increment the variable.
     *
     */
    private void fertilizeTile() {

        if (outerListenerExecuted) { return; }

        System.out.println("fertilize tile button clicked...");
        disableActionPanelButtons();
        enableFarmTileButtons();

        JOptionPane.showMessageDialog(null,
                "Select a Tile to Fertilize!", "Fertilize Tile Message", JOptionPane.INFORMATION_MESSAGE);

        for (int position = 1; position <= 50; position++) {
            int finalPosition = position;
            FARM_VIEW.getFARM_TILES().get(position).addActionListener(e -> {
                if (nestedListenerExecuted) { return; }

                switch (FARM_MODEL.getBOARD().getPlot(finalPosition).getTileStatus()) {
                    case SEEDED -> {

                        if (FARM_MODEL.getFARMER().getObjectCoins().compareTo(BigDecimal.valueOf(7)) < 0) {
                            JOptionPane.showMessageDialog(null, "Insufficient Object Coins!",
                                    "Plant Seed Message", JOptionPane.ERROR_MESSAGE);
                        } else{
                            FARM_MODEL.getFARMER().fertilizeCrop(FARM_MODEL.getTOOL_INVENTORY().get(2), FARM_MODEL.getBOARD().getPlot(finalPosition));
                            FARM_MODEL.getFARM_LOG().incrementTimesFertilized();
                            JOptionPane.showMessageDialog(null, "Successfully Fertilized!", "Fertilize Tile Message", JOptionPane.INFORMATION_MESSAGE);
                            updateFarmDetails();
                            updateFarmTiles();
                        }

                    }
                    case ROCKED, UNPLOWED, PLOWED, HARVEST, WITHERED ->
                            JOptionPane.showMessageDialog(null, "You can only fertilize seeded tiles!", "Water Tile Message", JOptionPane.ERROR_MESSAGE);
                }

                disableFarmTileButtons();
                enableActionPanelButtons();
                nestedListenerExecuted = true;
            });
        }
        outerListenerExecuted = true;
    }

    /** Changes the status of a tile from ROCKED to UNPLOWED.
     * If tile status is anything other than ROCKED, this function will not modify the status.
     *
     */
    private void pickaxeRock() {

        if (outerListenerExecuted) { return; }

        System.out.println("pickaxe button clicked...");

        if (FARM_MODEL.getBOARD().containsRocks()) {
            disableActionPanelButtons();
            enableFarmTileButtons();

            JOptionPane.showMessageDialog(null,
                    "Select a Tile to Remove a Rock On!", "Pickaxe Message", JOptionPane.INFORMATION_MESSAGE);


            for (int position = 1; position <= 50; position++) {
                int finalPosition = position;
                FARM_VIEW.getFARM_TILES().get(position).addActionListener(e -> {
                    if (nestedListenerExecuted) { return; }

                    switch (FARM_MODEL.getBOARD().getPlot(finalPosition).getTileStatus()) {
                        case ROCKED -> {
                            if (FARM_MODEL.getFARMER().getObjectCoins().
                                    compareTo(FARM_MODEL.getTOOL_INVENTORY().get(3).getToolCost()) >= 0) {
                                FARM_MODEL.getFARMER().removeRock(FARM_MODEL.getTOOL_INVENTORY().get(3), FARM_MODEL.getBOARD().getPlot(finalPosition));
                                JOptionPane.showMessageDialog(
                                        null, "Rocks Successfully Removed!",
                                        "Pickaxe Message",
                                        JOptionPane.INFORMATION_MESSAGE);
                                updateFarmDetails();
                                updateFarmTiles();
                                FARM_MODEL.getFARM_LOG().incrementTimesPickaxeUsed();
                            } else { JOptionPane.showMessageDialog(null,
                                        "Insufficient Object Coins!",
                                        "Pickaxe Message", JOptionPane.ERROR_MESSAGE); }
                        }
                        case UNPLOWED, PLOWED, SEEDED, HARVEST, WITHERED ->
                                JOptionPane.showMessageDialog(null, "You can only pickaxe rocked tiles!", "Pickaxe Message", JOptionPane.ERROR_MESSAGE);
                    }

                    disableFarmTileButtons();
                    enableActionPanelButtons();
                    nestedListenerExecuted = true;
                });
            }
        } else {
            JOptionPane.showMessageDialog(null, "You can only pickaxe rocked tiles!", "Pickaxe Message", JOptionPane.ERROR_MESSAGE);
        }
        outerListenerExecuted = true;
    }

    /** Changes the status of a tile from WITHERED to UNPLOWED.
     *  If tile status is anything other than ROCKED, this function will not modify the status.
     */
    private void shovelWithered() {

        if (outerListenerExecuted) { return; }

        System.out.println("shovel button clicked...");
        disableActionPanelButtons();
        enableFarmTileButtons();

        JOptionPane.showMessageDialog(null,
                "Select a Tile to Shovel", "Shovel Message", JOptionPane.INFORMATION_MESSAGE);

        for (int position = 1; position <= 50; position++) {
            int finalPosition = position;
            FARM_VIEW.getFARM_TILES().get(position).addActionListener(e -> {
                if (nestedListenerExecuted) { return; }

                switch (FARM_MODEL.getBOARD().getPlot(finalPosition).getTileStatus()) {
                    case WITHERED, UNPLOWED, ROCKED, SEEDED -> {
                        if (FARM_MODEL.getFARMER().getObjectCoins().compareTo(FARM_MODEL.getTOOL_INVENTORY().get(4).getToolCost()) >= 0) {
                            FARM_MODEL.getFARMER().shovelWithered(FARM_MODEL.getTOOL_INVENTORY().get(4), FARM_MODEL.getBOARD().getPlot(finalPosition));
                            JOptionPane.showMessageDialog(
                                    null, "Shovel Successfully Used...!", //
                                    "Shovel Message",
                                    JOptionPane.INFORMATION_MESSAGE);

                            updateFarmDetails();
                            updateFarmTiles();
                            FARM_MODEL.getFARM_LOG().incrementTimesShovelUsed();
                        } else { JOptionPane.showMessageDialog(null,
                                    "Insufficient Object Coins!",
                                    "Shovel Message", JOptionPane.ERROR_MESSAGE); }
                    }
                    default -> JOptionPane.showMessageDialog(null, "Invalid Action!", "Shovel Message", JOptionPane.ERROR_MESSAGE);
                }

                disableFarmTileButtons();
                enableActionPanelButtons();
                nestedListenerExecuted = true;
            });
        }
        outerListenerExecuted = true;
    }

    /** Main function for harvesting the crop.
     *  If tile status is HARVEST, increments object coins and experience of the user.
     *
     */
    private void harvestCrop() {

        if (outerListenerExecuted) { return; }

        System.out.println("harvest button clicked...");


        if (FARM_MODEL.getBOARD().containsHarvest()) {
            disableActionPanelButtons();
            enableFarmTileButtons();

            JOptionPane.showMessageDialog(null,
                    "Select a Tile to Fertilize!", "Harvest Crop Message", JOptionPane.INFORMATION_MESSAGE);


            for (int position = 1; position <= 50; position++) {
                int finalPosition = position;
                FARM_VIEW.getFARM_TILES().get(position).addActionListener(e -> {
                    if (nestedListenerExecuted) { return; }

                    switch (FARM_MODEL.getBOARD().getPlot(finalPosition).getTileStatus()) {
                        case HARVEST -> {
                            FARM_MODEL.getFARMER().harvestCrop(FARM_MODEL.getBOARD().getPlot(finalPosition));
                            JOptionPane.showMessageDialog(
                                    null, "Successfully Harvested!",
                                    "Harvest Crop Message",
                                    JOptionPane.INFORMATION_MESSAGE);

                            updateFarmDetails();
                            updateFarmTiles();
                            FARM_MODEL.getFARM_LOG().incrementTimesHarvested();
                        }
                        case ROCKED, UNPLOWED, PLOWED, SEEDED, WITHERED ->
                                JOptionPane.showMessageDialog(null, "Invalid Action!", "Harvest Crop Message", JOptionPane.ERROR_MESSAGE);
                    }

                    disableFarmTileButtons();
                    enableActionPanelButtons();
                    nestedListenerExecuted = true;
                });
            }
        } else {
            JOptionPane.showMessageDialog(null, "There are no seeds ready for harvest!", "Harvest Crop Message", JOptionPane.ERROR_MESSAGE);
        }
        outerListenerExecuted = true;
    }

    /** Main function for registering/ ranking up the status of the farmer.
     *  If the conditions are met, farmer status will change 1 higher that it's current status, and certain benefits will
     *  be on play based on the specifications of the MP.
     *
     */
    private void registerFarmer() {
        System.out.println("register button clicked...");

        switch (FARM_MODEL.getFARMER().getFarmerStatus()) {
            case DEFAULT -> {
                if (FARM_MODEL.getFARMER().getObjectCoins().compareTo(BigDecimal.valueOf(200)) < 0) {
                    JOptionPane.showMessageDialog(null,
                            "You have insufficient Object Coins!",
                            "Register Farmer Message",
                            JOptionPane.ERROR_MESSAGE);
                } else if(FARM_MODEL.getFARMER().getFarmerLevel() < 5) {
                    JOptionPane.showMessageDialog(null,
                            "Your level is not enough!",
                            "Register Farmer Message",
                            JOptionPane.ERROR_MESSAGE);
                } else  {
                    JOptionPane.showMessageDialog(null,
                            "You've successfully registered!",
                            "Register Farmer Message",
                            JOptionPane.ERROR_MESSAGE);
                    FARM_MODEL.getFARMER().rankUp();
                    updateFarmDetails();
                }
            }
            case REGISTERED -> {
                if (FARM_MODEL.getFARMER().getObjectCoins().compareTo(BigDecimal.valueOf(300)) < 0) {
                    JOptionPane.showMessageDialog(null,
                            "You have insufficient Object Coins!",
                            "Register Farmer Message",
                            JOptionPane.ERROR_MESSAGE);

                } else if(FARM_MODEL.getFARMER().getFarmerLevel() < 10) {

                    JOptionPane.showMessageDialog(null,
                            "Your level is not enough!",
                            "Register Farmer Message",
                            JOptionPane.ERROR_MESSAGE);

                } else  {
                    JOptionPane.showMessageDialog(null,
                            "You've successfully registered!",
                            "Register Farmer Message",
                            JOptionPane.ERROR_MESSAGE);
                    FARM_MODEL.getFARMER().rankUp();
                    updateFarmDetails();
                }
            }

            case DISTINGUISHED -> {
                if (FARM_MODEL.getFARMER().getObjectCoins().compareTo(BigDecimal.valueOf(400)) < 0) {
                    JOptionPane.showMessageDialog(null,
                            "You have insufficient Object Coins!",
                            "Register Farmer Message",
                            JOptionPane.ERROR_MESSAGE);

                } else if(FARM_MODEL.getFARMER().getFarmerLevel() < 15) {

                    JOptionPane.showMessageDialog(null,
                            "Your level is not enough!",
                            "Register Farmer Message",
                            JOptionPane.ERROR_MESSAGE);

                } else  {
                    JOptionPane.showMessageDialog(null,
                            "You've successfully registered!",
                            "Register Farmer Message",
                            JOptionPane.ERROR_MESSAGE);
                    FARM_MODEL.getFARMER().rankUp();
                    updateFarmDetails();
                }
            }
            case LEGENDARY -> JOptionPane.showMessageDialog(null,
                    "You already achieved the highest rank.",
                    "Register Farmer Message",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Increments day count.
     *
     */
    private void endDay() {
        System.out.println("end day button pressed");
        if (JOptionPane.showConfirmDialog(null,
                "Do you want to end the day?", "End Day",
                JOptionPane.YES_NO_OPTION) == 0) {
            System.out.println("ending day...");
            FARM_MODEL.getBOARD().incrementDay();
            FARM_VIEW.getDAY_LABEL().setText(String.valueOf(FARM_MODEL.getBOARD().getDayCount()));

            FARM_MODEL.getBOARD().checkForHarvest();
            updateFarmDetails();
            updateFarmTiles();
        } else { System.out.println("continuing day..."); }
    }

    /** Ends the program.
     *  This will also creat a statistics/log of the game based on the latest instance of the game.
     *
     */
    private void quitGame() {
        System.out.println("quit game button pressed");
        if (JOptionPane.showConfirmDialog(null,
                "Do you want to quit the game and log your farm?", "Quit Game",
                JOptionPane.YES_NO_OPTION) == 0) {
            System.out.println("quitting game...");
            FARM_VIEW.dispose();
            FARM_MODEL.getFARM_LOG().writeFarmLog(FARM_MODEL.getFARMER(), FARM_MODEL.getBOARD());
        } else { System.out.println("continuing game..."); }
    }

    /** will check the if the losing conditions are met.
     *  If the all tiles are withered OR if player doesn't have enough object coins && tiles doesn't contain any seeded or harvestable tiles.
     *  Then game will be over due to the losing condition.
     */
    private void checkGameOver() {
        if (FARM_MODEL.getFARMER().getObjectCoins().compareTo(BigDecimal.valueOf(5).subtract(FARM_MODEL.getFARMER().getFarmerStatus().getSEED_COST_REDUCTION())) < 0){
            if (FARM_MODEL.getBOARD().noPlants()) {
                JOptionPane.showMessageDialog(null,
                        "You can no longer proceed in tending this farm!",
                        "Game Over Message", JOptionPane.ERROR_MESSAGE);
                FARM_VIEW.remove(FARM_VIEW.getGAME_PANEL());
                FARM_VIEW.getContentPane().invalidate();
                FARM_VIEW.getContentPane().validate();
                FARM_VIEW.getContentPane().repaint();
                restartOrExit();
            }
        } else if (FARM_MODEL.getBOARD().allWithered()) {
            JOptionPane.showMessageDialog(null,
                    "You can no longer proceed in tending this farm!",
                    "Game Over Message", JOptionPane.ERROR_MESSAGE);
            FARM_VIEW.remove(FARM_VIEW.getGAME_PANEL());
            FARM_VIEW.getContentPane().invalidate();
            FARM_VIEW.getContentPane().validate();
            FARM_VIEW.getContentPane().repaint();

            restartOrExit();
        }
    }

    /** A function to check if user wants to restart or exit the game.
     *
     */
    private void restartOrExit() {
        FARM_VIEW.gameOverPhase();
        FARM_VIEW.getSTART_OVER_BUTTON().addActionListener(e -> {
            if (e.getSource() == FARM_VIEW.getSTART_OVER_BUTTON()) {
                FARM_VIEW.remove(FARM_VIEW.getGAME_OVER_PANEL());
                FARM_VIEW.getContentPane().invalidate();
                FARM_VIEW.getContentPane().validate();
                FARM_VIEW.getContentPane().repaint();
                FARM_VIEW.dispose();

                FarmModel newFarmModel = new FarmModel();
                FarmView newFarmView = new FarmView();
                FarmController newFarmController = new FarmController(newFarmView, newFarmModel);
                newFarmController.launch();
                newFarmView.setVisible(true);
            }
        });

        FARM_VIEW.getEXIT_GAME_BUTTON().addActionListener(e -> {
            if (e.getSource() == FARM_VIEW.getEXIT_GAME_BUTTON()) {
                FARM_VIEW.remove(FARM_VIEW.getGAME_OVER_PANEL());
                FARM_VIEW.getContentPane().invalidate();
                FARM_VIEW.getContentPane().validate();
                FARM_VIEW.getContentPane().repaint();
                FARM_VIEW.dispose();
                FARM_MODEL.getFARM_LOG().writeFarmLog(FARM_MODEL.getFARMER(), FARM_MODEL.getBOARD());
            }
        });
    }

    /** Shows all the necessary data of a tile.
     *
     * @param position chosen tile to be printed
     */
    private void showTileDetails(int position) {
        String details = switch (FARM_MODEL.getBOARD().getPlot(position).getTileStatus()) {
            case ROCKED -> "<html>This tile is rocked!<p><p>" +
                    "You first have to remove it with a pickaxe tool before unplowing it... </html>";
            case UNPLOWED -> "<html>This tile is unplowed!<p><p>" +
                    "You first have to plow it with a plow tool to begin planting seeds... </html>";
            case PLOWED -> "<html>This tile is plowed!<p><p>" +
                    "Navigate to plant seeds phase to begin planting seeds unto it... </html>";

            case SEEDED -> "<html>Seeded " + FARM_MODEL.getBOARD().getPlot(position).getCrop().getSeedName() + " Details: <p>" +
                    "Crop Type: " + FARM_MODEL.getBOARD().getPlot(position).getCrop().getCropType() + "<p><p>" +
                    "Water Needed: " + FARM_MODEL.getBOARD().getPlot(position).getCrop().getWaterNeeded() + "<p>" +
                    "Times Watered: " + FARM_MODEL.getBOARD().getPlot(position).getCrop().getTimesWatered() + "<p>" +
                    "Water Bonus Limit: " + FARM_MODEL.getBOARD().getPlot(position).getCrop().getWaterBonus() + "<p><p>" +
                    "Fertilizer Needed: " + FARM_MODEL.getBOARD().getPlot(position).getCrop().getFertilizerNeeded() + "<p>" +
                    "Times Fertilized: " + FARM_MODEL.getBOARD().getPlot(position).getCrop().getTimesFertilized() + "<p>" +
                    "Fertilizer Bonus Limit: " + FARM_MODEL.getBOARD().getPlot(position).getCrop().getFertilizerBonus() + "<p><p>" +
                    "Base Price: " + FARM_MODEL.getBOARD().getPlot(position).getCrop().getBasePrice() + "<p>" +
                    "Date for Harvest: Day #" + FARM_MODEL.getBOARD().getPlot(position).getCrop().getDateForHarvest() + "</html>";

            case HARVEST -> "<html>This tile is now ready for harvest!<p><p>" +
                    "Harvestable Crop: " + FARM_MODEL.getBOARD().getPlot(position).getCrop().getSeedName() + "<p><p>" +
                    "Harvest this crop today as it will wither tomorrow!</html>";

            case WITHERED -> "<html>This tile is withered!<p><p>" +
                    "The crop planted upon this tile is no longer harvestable... <p> " +
                    "Please remove the withered crop using a shovel tool... </html>";
        };
        FARM_VIEW.getDETAIL_LABEL().setText(details);
    }

    /** Enable the buttons located at the right panels.
     *
     */
    private void enableActionPanelButtons() {
        for (int index = 0; index < FARM_VIEW.getACTION_BUTTONS().size(); index++)
            FARM_VIEW.getACTION_BUTTONS().get(index).setEnabled(true);
        for (int index = 0; index < FARM_VIEW.getSEED_BUTTONS().size(); index++)
            FARM_VIEW.getSEED_BUTTONS().get(index).setEnabled(true);
        for (int index = 0; index < FARM_VIEW.getTOOL_BUTTONS().size(); index++)
            FARM_VIEW.getTOOL_BUTTONS().get(index).setEnabled(true);
    }

    /** Disable the buttons located at the right panel.
     */
    private void disableActionPanelButtons() {
        for (int index = 0; index < FARM_VIEW.getACTION_BUTTONS().size(); index++)
            FARM_VIEW.getACTION_BUTTONS().get(index).setEnabled(false);
        for (int index = 0; index < FARM_VIEW.getSEED_BUTTONS().size(); index++)
            FARM_VIEW.getSEED_BUTTONS().get(index).setEnabled(false);
        for (int index = 0; index < FARM_VIEW.getTOOL_BUTTONS().size(); index++)
            FARM_VIEW.getTOOL_BUTTONS().get(index).setEnabled(false);
    }

    /** Enable the Farm tiles to be clickable
     *
     */
    private void enableFarmTileButtons() {
        for (int position = 1; position <= FARM_VIEW.getFARM_TILES().size(); position++) {
            FARM_VIEW.getFARM_TILES().get(position).setEnabled(true);
            FARM_VIEW.getFARM_TILES().get(position).setBorderPainted(true);
        }
    }

    /** Disable the farm tiles to be clickable.
     *
     */
    private void disableFarmTileButtons() {
        for (int position = 1; position < FARM_VIEW.getFARM_TILES().size(); position++) {
            FARM_VIEW.getFARM_TILES().get(position).setEnabled(false);
            FARM_VIEW.getFARM_TILES().get(position).setBorderPainted(false);
        }
    }

    /** For updating the detail label.
     */
    private void resetDetailLabel() { FARM_VIEW.getDETAIL_LABEL().setText("<html>" +
            "Select an action to enable the farm tiles! <p><p>" +
            "When in the middle of doing something, you can <p>" +
            "can only proceed by pushing through with the action! </html>"); }
}