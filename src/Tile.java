public class Tile {

    private Crop crop = null;
    private TileStatus tileStatus;

    public Crop getCrop() { return crop; }
    public TileStatus getTileStatus() { return tileStatus; }

    public void setCrop(Crop crop) { this.crop = crop; }
    public void setTileStatus(TileStatus tileStatus) { this.tileStatus = tileStatus; }
}
