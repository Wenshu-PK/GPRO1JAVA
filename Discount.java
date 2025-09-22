package Project1_6713249;

import java.io.*;
import java.util.*;

public class Discount {
    protected ArrayList<Double> priceThresholds = new ArrayList<>();
    protected ArrayList<Double> discountPercents = new ArrayList<>();

    public void readDiscounts(String filename) {
        try (Scanner disScan = new Scanner(new File(filename))) {
            if (disScan.hasNextLine()) disScan.nextLine(); // skip header

            while (disScan.hasNextLine()) {
                String line = disScan.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 2) continue;

                try {
                    double threshold = Double.parseDouble(parts[0].trim());
                    double percent = Double.parseDouble(parts[1].trim());
                    priceThresholds.add(threshold);
                    discountPercents.add(percent);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid line in discount file: " + line);
                }
            }

            // Print loaded discounts
            System.out.println("=== Loaded Discounts ===");
            for (int i = 0; i < priceThresholds.size(); i++) {
                System.out.printf("Subtotal >= %,.2f → %.2f%% off\n",
                        priceThresholds.get(i), discountPercents.get(i));
            }
            System.out.println("========================");

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        }
    }

    public double getDiscountPercent(double subtotal) {
        double discountPercent = 0;
        for (int i = 0; i < priceThresholds.size(); i++) {
            if (subtotal >= priceThresholds.get(i)) {
                discountPercent = discountPercents.get(i);
            }
        }
        return discountPercent;
    }

   
}
