package Project1_6713249;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Discount {
    private ArrayList<Double> pThresh = new ArrayList<>();
    private ArrayList<Double> disPer = new ArrayList<>();

    // Read discounts from file
    public void readDiscounts() {
        String path = "src/main/java/Project1_6713249/";
        String inFilename = path + "discount.txt";

        try {
            File inFile = new File(inFilename);
            Scanner disScan = new Scanner(inFile);

            if (disScan.hasNextLine()) disScan.nextLine(); // skip header

            while (disScan.hasNextLine()) {
                String line = disScan.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 2) continue;

                try {
                    double threshold = Double.parseDouble(parts[0].trim());
                    double percent = Double.parseDouble(parts[1].trim());
                    pThresh.add(threshold);
                    disPer.add(percent);
                } catch (NumberFormatException e) {
                    // Skip invalid lines
                }
            }
            disScan.close();

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + inFilename);
        }
    }

    public ArrayList<Double> getPriceThresholds() {
        return pThresh;
    }

    public ArrayList<Double> getDiscountPercents() {
        return disPer;
    }

    // Calculate final price
    public double calculateFinalPrice(double subtotal) {
        double discountToApply = 0.0;

        for (int i = 0; i < pThresh.size(); i++) {
            if (subtotal >= pThresh.get(i)) {
                discountToApply = disPer.get(i);
            }
        }

        double discountAmount = subtotal * (discountToApply / 100.0);
        return subtotal - discountAmount;
    }

    // Calculate discount amount only
    public double getDiscountAmount(double subtotal) {
        double discountToApply = 0.0;

        for (int i = 0; i < pThresh.size(); i++) {
            if (subtotal >= pThresh.get(i)) {
                discountToApply = disPer.get(i);
            }
        }

        return subtotal * (discountToApply / 100.0);
    }
}
