import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DiscountCriteria {
    private final TreeMap<Double, Double> discountTiers;

    public DiscountCriteria(String filePath) throws FileNotFoundException {
        discountTiers = new TreeMap<>();
        loadDiscountTiers(filePath);
    }

    private void loadDiscountTiers(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        
        // Skip header line
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            
            String[] parts = line.split(",");
            if (parts.length < 2) {
                throw new IllegalArgumentException("Invalid line format: " + line);
            }

            try {
                double minSubTotal = Double.parseDouble(parts[0].trim());
                double discountPercent = Double.parseDouble(parts[1].trim());
                discountTiers.put(minSubTotal, discountPercent);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Non-numeric value in line: " + line, e);
            }
        }
        scanner.close();
    }

    public double getDiscountPercentage(double subtotal) {
        Map.Entry<Double, Double> tier = discountTiers.floorEntry(subtotal);
        return tier != null ? tier.getValue() : 0.0;
    }
}

