/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project1_6713249;

import java.util.*;

public class Customer {
    private String id;
    private List<Booking> bookings;

    public Customer(String id) {
        this.id = id;
        this.bookings = new ArrayList<>();
    }

    public String getId() { return this.id; }

    /** Add a booking belonging to this customer */
    public void addBooking(Booking b) {
        if (b != null) this.bookings.add(b);
    }

    /** Return list (read-only copy) of bookings */
    public List<Booking> getBookings() {
        return new ArrayList<>(this.bookings);
    }

    /** Sum of booking subtotals (before discount) */
    public double getTotalSubBeforeDiscount() {
        double sum = 0.0;
        for (Booking b : bookings) sum += b.getSubTotal();
        return sum;
    }

    /** Print a detailed report for this customer (all bookings) */
    public void printDetailedReport() {
        System.out.printf("Customer %s : %d booking(s)%n", this.id, this.bookings.size());
        for (Booking b : bookings) {
            b.printReport();
        }
        long totalSub = Math.round(getTotalSubBeforeDiscount());
        System.out.printf("  TOTAL (before discount) = %,d%n%n", totalSub);
    }

    /** Print a short summary line used in sorted summary */
    public void printSummaryLine() {
        long totalSub = Math.round(getTotalSubBeforeDiscount());
        System.out.printf("%-6s  TotalBooking=%,d  Bookings: ", this.id, totalSub);
        boolean first = true;
        for (Booking b : bookings) {
            if (!first) System.out.print(", ");
            System.out.print(b.getId());
            first = false;
        }
        System.out.println();
    }

}
