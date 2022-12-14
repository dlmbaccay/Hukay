package base.mvc;

import base.farm.*;
import base.farm.crops.*;
import base.farm.tools.*;

import java.util.ArrayList;

/**
 * Manages the data of the game.
 */
public class FarmModel {

    private final Farmer FARMER = new Farmer();
    private final Board BOARD = new Board();
    private final ArrayList<Crop> SEED_LIST = new ArrayList<>();
    private final ArrayList<Tool> TOOL_INVENTORY = new ArrayList<>();

    private final FarmLog FARM_LOG = new FarmLog();

    FarmModel() {
        // SEED_LIST
        this.SEED_LIST.add(new Turnip());               // index 0
        this.SEED_LIST.add(new Carrot());               // index 1
        this.SEED_LIST.add(new Potato());               // index 2
        this.SEED_LIST.add(new Rose());                 // index 3
        this.SEED_LIST.add(new Tulips());               // index 4
        this.SEED_LIST.add(new Sunflower());            // index 5
        this.SEED_LIST.add(new Mango());                // index 6
        this.SEED_LIST.add(new Apple());                // index 7

        //TOOL_INVENTORY
        this.TOOL_INVENTORY.add(new Plow());            // index 0
        this.TOOL_INVENTORY.add(new WateringCan());     // index 1
        this.TOOL_INVENTORY.add(new Fertilizer());      // index 2
        this.TOOL_INVENTORY.add(new Pickaxe());         // index 3
        this.TOOL_INVENTORY.add(new Shovel());          // index 4
    }

    public Farmer getFARMER() { return FARMER; }
    public Board getBOARD() { return BOARD; }
    public ArrayList<Crop> getSEED_LIST() { return SEED_LIST; }
    public ArrayList<Tool> getTOOL_INVENTORY() { return TOOL_INVENTORY; }
    public FarmLog getFARM_LOG() { return FARM_LOG; }
}