package base.farm;
/** This class represents a block of land that can be used for planting Crops. <p>
 *  crop refers to the crop planted in the Tile object. <p>
 *  tileStatus refers to the current status of the Tile.
 */
public class Tile {

    private Crop crop = null;
    private TileStatus tileStatus;

    private boolean border;

    /** Creates a default Tile object. This initially creates
     *  a Crop object, crop, that initially contains null and tileStatus
     *  that will hold the status of the Tile.
     */
    public Tile() {}

    /** A method that returns the Crop contained by the Tile.
     *  This will return null if the Tile doesn't contain a Crop.
     *
     * @return the crop inside the Tile object in Crop representation.
     */
    public Crop getCrop() { return crop; }

    /** A method that returns the current status of the tile.
     *
     * @return The current status of the tile
     */
    public TileStatus getTileStatus() { return tileStatus; }

    /** A method that modifies the Crop inside the Tile object.
     *
     * @param crop      The Crop inside the Tile object
     */
    public void setCrop(Crop crop) { this.crop = crop; }

    /** A method that modifies the current status of the Tile object.
     *
     * @param tileStatus    The current status of the Tile
     */
    public void setTileStatus(TileStatus tileStatus) { this.tileStatus = tileStatus; }

    /** A function that tells if the specified tile is a border tile.
     *  Border tiles are located at the edges and corners of the board.
     *  This is used for the condition of planting trees.
     *
     * @return true if tile mentioned is a border ti
     */
    public boolean isBorder() {
        return border;
    }

    /** Sets if a tile is a border tile
     *
     * @param border the boolean function if tile is border or not.
     */
    public void setBorder(boolean border) {
        this.border = border;
    }
}