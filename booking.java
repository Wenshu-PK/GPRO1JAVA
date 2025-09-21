package gpro1java;

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
    private int[] RoomsPerDay;
    private int Persons;
    private int[] MealsPerPersonPerDay;
    private int[] priceRoom = {8000, 11000, 18000};// 
    private int[] priceDish = {100, 450, 600};//
    private int RoomPricePerday;
    private int DishPricePerday;
    private int total;
    private int finalprice = 0;

    private static int[] parseTriple(String s) {
        String[] in = s.split(":");
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

    public void setFinalPrice(int discountpercent) {
        this.finalprice = total - (total * discountpercent / 100);

    }

    public static booking loadbooking(String line) throws InvalidNumberException, NumberFormatException {

        String[] parts = line.split(",");
       

        String bookID = parts[0].trim();
        String cusID = parts[1].trim();
        int days;
        if (Integer.parseInt(parts[2].trim()) < 1) {
            throw new InvalidNumberException("InvalidNumberException For day : " + parts[2].trim() + "\n" + line);
        }try {
            days = Integer.parseInt(parts[2].trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("NumberFormatException For day : " + parts[2].trim() + "\n" + line);
        }
       
        days = Integer.parseInt(parts[2].trim());

        int[] RoomsDay;
        if (parseTriple(parts[3].trim()).length != 3) {
            throw new InvalidNumberException("InvalidNumberException For room :" + parts[3].trim() + "\n" + line);
        }try {
            RoomsDay = parseTriple(parts[3].trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("NumberFormatException For room :" + parts[3].trim() + "\n" + line);
        }
        
        RoomsDay = parseTriple(parts[3].trim());
        int Person;
        if (Integer.parseInt(parts[4].trim()) < 1) {
            throw new InvalidNumberException("InvalidNumberException For Person : " + parts[4].trim() + "\n" + line);
        }try {
            Person = Integer.parseInt(parts[4].trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("NumberFormatException For Person : " + parts[4].trim() + "\n" + line);
        }
        
        Person = Integer.parseInt(parts[4].trim());

        int[] MealsDay;
        if (parseTriple(parts[5].trim()).length != 3) {
            throw new InvalidNumberException("InvalidNumberException For meal : " + parts[5].trim() + "\n" + line);
        }
        try {
            MealsDay = parseTriple(parts[5].trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("NumberFormatException For meal : " + parts[5].trim() + "\n" + line);
        }
        MealsDay = parseTriple(parts[5].trim());

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
        this.RoomPricePerday = RoomsPerDay[0] * priceRoom[0]
                + RoomsPerDay[1] * priceRoom[1]
                + RoomsPerDay[2] * priceRoom[2];

        this.DishPricePerday = MealsPerPersonPerDay[0] * priceDish[0]
                + MealsPerPersonPerDay[1] * priceDish[1]
                + MealsPerPersonPerDay[2] * priceDish[2];
        this.total = RoomPricePerday * day + DishPricePerday * day;
    }

    public void print_booking() {

        System.out.printf("Booking %5S, customer %5S  >>  days = %3d, persons = %4d,  rooms=[%d,%d,%d],  meals = [%d,%d,%d] \n",
                bookingID, customerID, day, Persons, RoomsPerDay[0], RoomsPerDay[1],
                RoomsPerDay[2], MealsPerPersonPerDay[0], MealsPerPersonPerDay[1], MealsPerPersonPerDay[2]);

    }

}
