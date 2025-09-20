/* Puvit
 */
package Project1_6713249;

import java.io.*;
import java.util.*;

public abstract class item {
    protected String code;
    protected String name;
    protected double unitPrice;

    public item(String code, String name, double unitPrice) {
        this.code = code;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getUnitPrice() { return unitPrice; }

    public abstract double computePrice(int quantity, int days, int persons);


    public static Map<String, item> readFile(String filename) throws FileNotFoundException {
        Map<String, item> items = new HashMap<>();

        try (Scanner scf = new Scanner(new File(filename))) {
            if (scf.hasNextLine()) scf.nextLine(); // skip header line

            while (scf.hasNextLine()) {
                String line = scf.nextLine();
                try {
                    // Split by comma
                    String[] cols = line.split(",");
                    if (cols.length < 3) {
                        throw new ArrayIndexOutOfBoundsException(
                                "Expected 3 columns but got " + cols.length);
                    }

                    String code = cols[0].trim();
                    String name = cols[1].trim();

                    double price = parseDoubleSafe(cols[2].trim());
                    if (price < 0) {
                        throw new InvalidNumberException("Negative price \"" + price + "\"");
                    }

                    if (code.startsWith("R")) {
                        items.put(code, new Room(code, name, price));
                    } else if (code.startsWith("M")) {
                        items.put(code, new Meal(code, name, price));
                    } else {
                        throw new IllegalArgumentException("Unknown code prefix: " + code);
                    }

                } catch (Exception e) {
                    // Print error but continue reading next line
                    System.out.println(e.getClass().getName() + ": " + e.getMessage());
                    System.out.println("Skipping line: " + line);
                }
            }
        }

        return items;
    }

    // Helper like in Ex4GateMain to safely parse double
    private static double parseDoubleSafe(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e1) {
            try {
                // Maybe input like “12O” with O
                String fixed = s.replaceAll("[Oo]", "0");
                return Double.parseDouble(fixed);
            } catch (NumberFormatException e2) {
                throw new NumberFormatException("For input string: \"" + s + "\"");
            }
        }
    }
}

class Meal extends item {
    public Meal(String code, String name, double unitPrice) {
        super(code, name, unitPrice);
    }

    @Override
    public double computePrice(int quantity, int days, int persons) {
        // unit price already includes VAT
        return unitPrice * quantity * days * persons;
    }
}

class Room extends item {
    public Room(String code, String name, double unitPrice) {
        super(code, name, unitPrice);
    }

    @Override
    public double computePrice(int quantity, int days, int persons) {
        double base = unitPrice * quantity * days;
        double service = base * 0.10;
        double vat = (base + service) * 0.07;
        return base + service + vat;
    }
}


class InvalidNumberException extends RuntimeException {
    public InvalidNumberException(String message) {
        super(message);
    }
}
