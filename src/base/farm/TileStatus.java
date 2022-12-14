package base.farm;

/** This enum represents the status that the Tile is currently in. <p>
 *  ROCKED represents a Tile that contains rocks. <p>
 *  UNPLOWED represents a Tile that is currently not plowed. <p>
 *  PLOWED represents a Tile that is currently plowed, this means the Tile is currently available
 *  to be planted. <p>
 *  SEEDED represents a Tile that currently contains a Crop object. <p>
 *  HARVEST represents a Tile which contains a Crop that is subject for harvesting. <p>
 *  WITHERED represents a Tile that contains a withered crop. <p>
 */
public enum TileStatus {
    ROCKED,
    UNPLOWED,
    PLOWED,
    SEEDED,
    HARVEST,
    WITHERED
}