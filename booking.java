package Project1_6713249;

import java.io.*;
import java.util.*;

/**
 *
 * @author sawan
 */
public class booking {

    private String bookingID;
    private String customerID;
    private int day;
    private int[] RoomsPerDay;//index 1 for R1,index 2 for R2,index 3 for R3
    private int Persons;
    private int[] MealsPerPersonPerDay;//index 1 for M1,index 2 forM2,index 3 for M3
    private int RoomPricePerday;
    private int DishPricePerday;
    private double total;
    private double finalprice = 0;
    private double roomTotal;
    private double mealTotal;

    private static int[] parseTriple(String s) throws InvalidNumberException{
        String[] in = s.split(":");
       if(in.length!=3)
           throw new InvalidNumberException(s);
        return new int[]{
            Integer.parseInt(in[0]),
            Integer.parseInt(in[1]),
            Integer.parseInt(in[2])
        };
    }

    public static class InvalidNumberException extends Exception {

        public InvalidNumberException(String message) {
            super(message);
        }

    }

    public static booking loadbooking(String line) throws InvalidNumberException, NumberFormatException {

        String[] parts = line.split(",");

        String bookID = parts[0].trim();
        String cusID = parts[1].trim();

        // Parse days
        int days;
        try {
            days = Integer.parseInt(parts[2].trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("NumberFormatException for days: " + parts[2].trim() + "\n" + line);
        }
        if (days < 1) {
            throw new InvalidNumberException("InvalidNumberException for days : " + days + "\n" + line);
        }

        // Parse rooms
        int[] RoomsDay;

        try {
            RoomsDay = parseTriple(parts[3].trim());
            
            for (int r : RoomsDay) {
                if (r < 0) {
                    throw new InvalidNumberException("InvalidNumberException for room : " + r + "\n" + line);
                }
            }

        } catch (NumberFormatException e) {
            throw new NumberFormatException("NumberFormatException for room: " + parts[3].trim() + "\n" + line);
        } catch(InvalidNumberException e){
        throw new InvalidNumberException("InvalidNumberException for room : " + parts[3].trim() + "\n" + line);}

        // Parse person
        int Person;
        try {
            Person = Integer.parseInt(parts[4].trim());
            if (Person < 1) {
                throw new InvalidNumberException("InvalidNumberException for person : " + Person + "\n" + line);
            }

        } catch (NumberFormatException e) {
            throw new NumberFormatException("NumberFormatException for person: " + parts[4].trim() + "\n" + line);
        }

        // Parse meals
        int[] MealsDay;
        try {
            MealsDay = parseTriple(parts[5].trim());
            
            for (int m : MealsDay) {
                if (m < 0) {
                    throw new InvalidNumberException("InvalidNumberException for meal : " + m + "\n" + line);
                }
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("NumberFormatException for meal: " + parts[5].trim() + "\n" + line);
        }catch(InvalidNumberException e){
        throw new InvalidNumberException("InvalidNumberException for meal : " + parts[5].trim() + "\n" + line);}

        // Return new booking object
        return new booking(bookID, cusID, days, RoomsDay, Person, MealsDay);
    }

    public booking(String BookingID,
            String CustomerID,
            int Day,
            int[] RoomPerDays,
            int PerSons,
            int[] MealsPerPersonPerDays) {

        this.bookingID = BookingID;
        this.customerID = CustomerID;
        this.day = Day;
        this.RoomsPerDay = RoomPerDays;
        this.Persons = PerSons;
        this.MealsPerPersonPerDay = MealsPerPersonPerDays;

    }
    
      public void computePrice(List<item> framItem) {
        double sumRoom = 0.0;
        double sumMeal = 0.0;

        
        // index 0–2 = Room, index 3–5 = Meal
        for (int i = 0; i < RoomsPerDay.length; i++) {
            if (RoomsPerDay[i] > 0) {
                item it = framItem.get(i);
                sumRoom += it.computePrice(RoomsPerDay[i], day, 1); 
            }
        }

        for (int i = 0; i < MealsPerPersonPerDay.length; i++) {
            if (MealsPerPersonPerDay[i] > 0) {
                item it = framItem.get(RoomsPerDay.length + i);
                sumMeal += it.computePrice(MealsPerPersonPerDay[i], day, Persons);
            }
        }

        this.roomTotal = sumRoom;
        this.mealTotal = sumMeal;
        this.total = sumRoom + sumMeal;
    }
  public void ComputeDiscount(Discount dis){ 
 
  


// inside Booking printing method:
  double discountPercent = dis.getDiscountPercent(this.total);
  double discountAmount = this.total * discountPercent / 100.0;
  this.finalprice = this.total - discountAmount;

System.out.printf("discount = %.1f%%%n", discountPercent);
System.out.printf("discount amount = %,10.2f%n", discountAmount);
System.out.printf("total = %,10.2f%n\n", this.finalprice);

  
  
  
  }
    public void print_booking() {

        System.out.printf("Booking %5S, customer %5S  >>  days = %3d, persons = %4d,  rooms=[%d,%d,%d],  meals = [%d,%d,%d] \n",
                bookingID, customerID, day, Persons, RoomsPerDay[0], RoomsPerDay[1],
                RoomsPerDay[2], MealsPerPersonPerDay[0], MealsPerPersonPerDay[1], MealsPerPersonPerDay[2]);
         System.out.printf("Room total = %,.2f\nMealtotal = %,.2f\nSubtotal = %,.2f\n",
                roomTotal, mealTotal, total);

    }

    public double getSubTotal() {
        return this.total;
    }

    public String getBookingId() {
        return this.bookingID;
    }

    public String getCustomerID() {
        return this.customerID;
    }

}
