package base.farm;
import java.math.BigDecimal;

/** This is an enum that represents the rank/type of the Farmer that will determine
 *  the benefits of the Farmer.
 *  Each type has a degree of benefits, LEGENDARY having the most amount of benefits and DEFAULT being the starting
 *  rank/type with having no benefits.<p>
 *
 */
public enum FarmerStatus {

    DEFAULT("DEFAULT", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
            0, 0),


    REGISTERED("REGISTERED", BigDecimal.valueOf(1), BigDecimal.valueOf(1),
            0, 0),


    DISTINGUISHED("DISTINGUISHED", BigDecimal.valueOf(2), BigDecimal.valueOf(2),
            1, 0),


    LEGENDARY("LEGENDARY", BigDecimal.valueOf(4), BigDecimal.valueOf(3),
            2, 1);

    private final String FARMER_TYPE;
    private final BigDecimal EARNING_BONUS;
    private final BigDecimal SEED_COST_REDUCTION;
    private final int WATER_LIMIT_BONUS;
    private final int FERTILIZER_LIMIT_BONUS;

    /** Constructor class that creates/modifies the status of the Farmer and its benefits
     *
     * @param FARMER_TYPE            can be of DEFAULT, REGISTERED, DISTINGUISHED, or LEGENDARY
     * @param EARNING_BONUS          earning bonus gained from respective farmer types
     * @param SEED_COST_REDUCTION     seed cost discounts gained from respective farmer types
     * @param WATER_LIMIT_BONUS       water limit bonus gained from respective farmer types
     * @param FERTILIZER_LIMIT_BONUS  fertilizer limit bonus gained from respective farmer types
     */
    FarmerStatus(String FARMER_TYPE,
                 BigDecimal EARNING_BONUS, BigDecimal SEED_COST_REDUCTION,
                 int WATER_LIMIT_BONUS, int FERTILIZER_LIMIT_BONUS) {
        this.FARMER_TYPE = FARMER_TYPE;
        this.EARNING_BONUS = EARNING_BONUS;
        this.SEED_COST_REDUCTION = SEED_COST_REDUCTION;
        this.WATER_LIMIT_BONUS = WATER_LIMIT_BONUS;
        this.FERTILIZER_LIMIT_BONUS = FERTILIZER_LIMIT_BONUS;

    }

    /** A getter method that returns the current type of the Farmer.
     *
     * @return the current type of the Farmer in String representation
     */
    public String getFARMER_TYPE() { return FARMER_TYPE; }

    /** Returns the current bonus earnings of the Farmer for harvesting
     *
     * @return The bonus earnings the Farmer can get when harvesting in BigDecimal representation
     */
    public BigDecimal getEARNING_BONUS() { return EARNING_BONUS; }

    /** Returns the value that will be reduced to the seed cost of a Crop
     *
     * @return The value that will be deducted to the seed cost of a Crop in BigDecimal representation
     */
    public BigDecimal getSEED_COST_REDUCTION() { return SEED_COST_REDUCTION; }

    /** Returns the value that will increment the water bonus limit of a Crop
     *
     * @return The value that will be added to the water bonus limit of a Crop in 'int' representation
     */
    public int getWATER_LIMIT_BONUS() { return WATER_LIMIT_BONUS; }

    /** Returns the value that will be used to increment the fertilizer bonus limit of a Crop
     *
     * @return The value that will be added to the fertilizer limit bonus of a Crop in 'int' representation
     */
    public int getFERTILIZER_LIMIT_BONUS() { return FERTILIZER_LIMIT_BONUS; }

}