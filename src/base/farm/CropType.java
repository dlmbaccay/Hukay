package base.farm;
/** This enum represents the type of the crops available within the game.
 *  This will affect certain behaviors in the game such as bonus Object Coins gained and
 *  Tile usage based from the type of the crop.<p></p>
 *  ROOT_CROP represents certain crops in the game which contains no special characteristics.
 *  <p></p>
 *  FLOWER represents certain crops in the game which contains an additional bonus of 10% to its final harvest price.
 *  <p></p>
 *  FRUIT_TREE represent certain crops in the game which takes up 3x3 Tiles in the game
 */
public enum CropType {
    ROOT_CROP("Root Crop"),
    FLOWER("Flower"),
    FRUIT_TREE("Fruit Tree");

    CropType(String cropType) {
        this.cropType = cropType;
    }

    private final String cropType;

    /** Returns the type of the crop.
     *
     * @return the type of the crop in CropType representation
     */
    public String getCropType() { return cropType; }
}