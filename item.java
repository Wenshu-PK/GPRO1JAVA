package Project1_6713249;
/*
Anun Luechaphongthip         6713253
Puvit Kitiwongpaisan         6713246
Kanapod Lamthong             6713220
Piyawat Jaroonchaikhanakit   6713240
Sawana Thiputhai             6713249
*/

import java.util.*;

public abstract class item {
    private String code;
    private String name;
    private double unitPrice;

    public item(String code, String name, double unitPrice) {
        this.code = code;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getUnitPrice() { return unitPrice; }

    public abstract String Printitemu(int quantity, int days, int persons);
    public abstract double computePrice(int quantity, int days, int persons);
}

// ----------------- Factory -----------------
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

// ----------------- Meal -----------------
class Meal extends item {
    public Meal(String code, String name, double unitPrice) {
        super(code, name, unitPrice);
    }

    @Override
    public String Printitemu(int quantity, int days, int persons) {
        return String.format("%s, %-16s rate (per person per day)=%,.2f",
                getCode(), getName(), getUnitPrice());
    }

    @Override
    public double computePrice(int quantity, int days, int persons) {
        return getUnitPrice() * quantity * days * persons;
    }
}

// ----------------- Room -----------------
class Room extends item {
    public Room(String code, String name, double unitPrice) {
        super(code, name, unitPrice);
    }

    @Override
    public String Printitemu(int quantity, int days, int persons){
        return String.format("%s, %-20s rate (per day) = %,9.2f", getCode(), getName(), getUnitPrice());
    }

    @Override
    public double computePrice(int quantity, int days, int persons) {
        double base = getUnitPrice() * quantity * days;
        double service = base * 0.10;
        double vat = (base + service) * 0.07;
        return base + service + vat;
    }
}

// ----------------- Exception -----------------
class InvalidNumberException extends RuntimeException {
    public InvalidNumberException(String message) {
        super(message);
    }
}
