/*puvit
 */
package Project1_6713249;

import java.util.*;

public abstract class item {
    protected String code;
    protected String name;
    protected double unitPrice;
    public item(String code, String name, double unitPrice) {
        this.code = code; this.name = name; this.unitPrice = unitPrice;
    }
    public String getCode() { return code; }
    public String getName() { return name; }
    public double getUnitPrice() { return unitPrice; }
    public abstract double computePrice(int quantity, int days, int persons);
}

class Meal extends item {
    public Meal(String code, String name, double unitPrice) {
        super(code,name,unitPrice);
    }
    @Override
    public double computePrice(int quantity, int days, int persons) {
        // unit price already includes VAT
        return unitPrice * quantity * days * persons;
    }
}

 class Room extends item {
    public Room(String code, String name, double unitPrice) {
        super(code,name,unitPrice);
    }
    @Override
    public double computePrice(int quantity, int days, int persons) {
        double base = unitPrice * quantity * days;
        double service = base * 0.10;
        double vat = (base + service) * 0.07;
        return base + service + vat;
    }
}
