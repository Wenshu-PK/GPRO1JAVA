package Project1_6713249;
/*
Anun Luechaphongthip         6713253
Puvit Kitiwongpaisan         6713246
Kanapod Lamthong             6713220
Piyawat Jaroonchaikhanakit   6713240
Sawana Thiputhai             6713249
*/

import java.io.*;
import java.util.*;

public class Discount {
    protected ArrayList<Double> priceThresholds = new ArrayList<>();
    protected ArrayList<Double> discountPercents = new ArrayList<>();

    public void readDiscounts(String filename) throws InvalidDiscountException {
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
            Collections.sort(this.priceThresholds.reversed() );
            Collections.sort(this.discountPercents.reversed() );
            for (int i = 0; i < priceThresholds.size(); i++) {
                System.out.printf("If total Bill >= %,12.2f   discount = %6.2f%% \n",
                        priceThresholds.get(i), discountPercents.get(i));
            }
            

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            throw new InvalidDiscountException("InvalidDiscountFile" + e);
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
//except
class InvalidDiscountException extends RuntimeException {
    public InvalidDiscountException(String message) {
        super(message);
    }
}
