package Project1_6713249;

import java.io.*;
import java.util.*;

public class Discount {
    private ArrayList<Double> priceThresholds = new ArrayList<>();
    private ArrayList<Double> discountPercents = new ArrayList<>();

    public void readDiscounts() {
        String basePath = "src/main/java/Project1_6713249/";
        Scanner input = new Scanner(System.in);
        File inFile = null;

        // Ask until valid file is found
        while (true) {
            System.out.print("Enter discount file name : ");
            String fileName = input.nextLine().trim();
            inFile = new File(basePath + fileName);

            if (!inFile.exists()) {
                System.out.println("Error: File not found. Try again.");
            } else if (!inFile.canRead()) {
                System.out.println("Error: Cannot read file. Try again.");
            } else {
                break; // valid file
            }
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
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid value at line " + lineNum + ": " + line);
                }
            }

            // ✅ Print out discount rules (like in screenshot)
            System.out.println("Read from " + inFile.getPath());
            for (int i = 0; i < priceThresholds.size(); i++) {
                System.out.printf("If total bill >= %,10.2f, discount = %.1f%%%n",
                        priceThresholds.get(i), discountPercents.get(i));
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + inFile.getName());
        }
    }

    // ✅ Get discount percent only (for Booking to use)
    public double getDiscountPercent(double subtotal) {
        double discountPercent = 0.0;
        for (int i = 0; i < priceThresholds.size(); i++) {
            if (subtotal >= priceThresholds.get(i)) {
                discountPercent = discountPercents.get(i);
            }
        }
        return discountPercent;
    }

    // ✅ Calculate final price (can be used directly if needed)
    public double calculateFinalPrice(double subtotal) {
        double discountPercent = getDiscountPercent(subtotal);
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
