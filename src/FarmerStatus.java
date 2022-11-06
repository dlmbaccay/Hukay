import java.math.BigDecimal;

public enum FarmerStatus {

    DEFAULT("Farmer", 0,
            BigDecimal.valueOf(0), BigDecimal.valueOf(0),
            0, 0, BigDecimal.valueOf(0)),

    REGISTERED("Registered Farmer", 5,
            BigDecimal.valueOf(1), BigDecimal.valueOf(1),
            0, 0, BigDecimal.valueOf(200)),

    DISTINGUISHED("Distinguished  Farmer", 10,
            BigDecimal.valueOf(2), BigDecimal.valueOf(2),
            1, 0, BigDecimal.valueOf(300)),

    LEGENDARY("Legendary   Farmer", 15,
            BigDecimal.valueOf(4), BigDecimal.valueOf(3),
            2, 1, BigDecimal.valueOf(400));

    private final String farmerType;
    private final int levelRequirement;
    private final BigDecimal earningBonus;
    private final BigDecimal seedCostReduction;
    private final int waterBonusLimit;
    private final int fertilizerBonusLimit;
    private final BigDecimal registrationFee;

    FarmerStatus(String farmerType, int levelRequirement,
                 BigDecimal earningBonus, BigDecimal seedCostReduction,
                 int waterBonusLimit, int fertilizerBonusLimit, BigDecimal registrationFee) {
        this.farmerType = farmerType;
        this.levelRequirement = levelRequirement;
        this.earningBonus = earningBonus;
        this.seedCostReduction = seedCostReduction;
        this.waterBonusLimit = waterBonusLimit;
        this.fertilizerBonusLimit = fertilizerBonusLimit;
        this.registrationFee = registrationFee;
    }


    public String getFarmerType() {
        return farmerType;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public BigDecimal getEarningBonus() {
        return earningBonus;
    }

    public BigDecimal getSeedCostReduction() {
        return seedCostReduction;
    }

    public int getWaterBonusLimit() {
        return waterBonusLimit;
    }

    public int getFertilizerBonusLimit() {
        return fertilizerBonusLimit;
    }

    public BigDecimal getRegistrationFee() {
        return registrationFee;
    }
}
