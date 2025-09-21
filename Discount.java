package Project1_6713249;

import java.io.*;
import java.util.*;

public class Discount {
    private ArrayList<Double> priceThresholds = new ArrayList<>();
    private ArrayList<Double> discountPercents = new ArrayList<>();

    public void readDiscounts() {
        String path = "src/main/java/Project1_6713249/";
        String inFilename = path + "discount.txt";

        File inFile = new File(inFilename);
        if (!inFile.exists()) {
            // Handle missing file
            System.err.println("File not found: " + inFilename);
            return; // Exit gracefully
        }

        try (Scanner disScan = new Scanner(inFile)) {
            // Skip header line
            if (disScan.hasNextLine()) disScan.nextLine();

            int lineNum = 1; // track line numbers (excluding header)
            while (disScan.hasNextLine()) {
                String line = disScan.nextLine().trim();
                lineNum++;

                if (line.isEmpty()) continue; // skip blank lines

                String[] parts = line.split(",");
                if (parts.length < 2) {
                    System.err.println("Invalid format at line " + lineNum + ": " + line);
                    continue; // skip malformed line
                }

                try {
                    double threshold = Double.parseDouble(parts[0].trim());
                    double percent = Double.parseDouble(parts[1].trim());

                    if (threshold < 0 || percent < 0) {
                        throw new IllegalArgumentException("Negative values not allowed");
                    }

                    priceThresholds.add(threshold);
                    discountPercents.add(percent);

                } catch (NumberFormatException e) {
                    System.err.println("Number format error at line " + lineNum + ": " + line);
                    // skip invalid numeric values
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid value at line " + lineNum + ": " + line);
                    // skip invalid/negative values
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + inFilename);
        }
    }

    // Apply the discount based on thresholds
    public double calculateFinalPrice(double subtotal) {
        double discountPercent = 0.0;

        for (int i = 0; i < priceThresholds.size(); i++) {
            if (subtotal >= priceThresholds.get(i)) {
                discountPercent = discountPercents.get(i);
            }
        }

        double discountAmount = subtotal * discountPercent / 100.0;
        return subtotal - discountAmount;
    }

    public ArrayList<Double> getPriceThresholds() {
        return priceThresholds;
    }

    public ArrayList<Double> getDiscountPercents() {
        return discountPercents;
    }
}
