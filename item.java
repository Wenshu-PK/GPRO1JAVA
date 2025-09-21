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

    protected static double parseDoubleSafe(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e1) {
            try {
                String fixed = s.replaceAll("[Oo]", "0");
                return Double.parseDouble(fixed);
            } catch (NumberFormatException e2) {
                throw new NumberFormatException("For input string: \"" + s + "\"");
            }
        }
    }

    

    public abstract String Printitemu(int quantity, int days, int persons);
    public abstract double computePrice(int quantity, int days, int persons);
}

class itemuFactory {
    public static item createFromLine(String line) {
        String[] cols = line.split(",");
        if (cols.length < 3)
            throw new IllegalArgumentException("Expected at least 3 columns");

        String code = cols[0].trim();
        String name = cols[1].trim();
        double price = Double.parseDouble(cols[2].trim());
      
        if (price < 0) {
            throw new InvalidNumberException("Negative price \"" + price + "\"");
        }
        
        if (code.startsWith("R")) {
            return new Room(code, name, price);
        } else if (code.startsWith("M")) {
            return new Meal(code, name, price);
        } else {
            throw new IllegalArgumentException("Unknown code: " + code);
        }
    }
}

class Meal extends item {
    public Meal(String code, String name, double unitPrice) {
        super(code, name, unitPrice);
    }
    
    @Override
    public String Printitemu(int quantity, int days, int persons) {
        return String.format("%s, %-16s rate (per person per day)=%,.2f",
                code, name, unitPrice);
    }
    
    @Override
    public double computePrice(int quantity, int days, int persons) {
        return unitPrice * quantity * days * persons;
    }
}

class Room extends item {
    public Room(String code, String name, double unitPrice) {
        super(code, name, unitPrice);
    }
    
    @Override
    public String Printitemu(int quantity, int days, int persons){
        double x = computePrice(1,1,1);
        return String.format("%2s, %-20s rate (per day) = %,9.2f     rate++ = %,9.2f",code, name, unitPrice, x);
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
