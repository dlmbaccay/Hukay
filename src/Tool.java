import java.math.BigDecimal;

public class Tool {

    private final String name;
    private final BigDecimal cost;
    private final double experienceGain;
    private int useCount = 0;

    public Tool(String name, BigDecimal cost, double experienceGain) {
        this.name = name;
        this.cost = cost;
        this.experienceGain = experienceGain;
    }

    public String getName() { return name; }

    public BigDecimal getCost() { return cost; }

    public double getExperienceGain() { return experienceGain; }

    public int getUseCount() { return useCount; }

    public void incrementUseCount() { this.useCount++; }

    public void printToolDetails() {
        System.out.printf("\t%12s   Cost: %2.0f   Experience Gain: %.2f\n", ("[" + this.name + "]"), this.cost, this.experienceGain);
    }
}
