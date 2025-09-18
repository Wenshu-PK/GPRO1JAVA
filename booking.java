package Project1_6713249;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author sawan
 */
public class booking {

    String bookingID;
    String customerID;
    int day;
    int[] RoomsPerDay;
    int Persons;
    int[] MealsPerPersonPerDay;
    int[] priceRoom = {8000, 11000, 18000};//เผื่อไว้เฉยๆใช้จากคลาสที่มีก่ารคำนวณเลย
    int[] priceDish = {100, 450, 600};//เผื่อไว้เฉยๆใช้จากคลาสที่มีก่ารคำนวณเลย
    int RoomPricePerday;//เผื่อไว้เฉยๆใช้จากคลาสที่มีก่ารคำนวณเลย
    int DishPricePerday;//เผื่อไว้เฉยๆใช้จากคลาสที่มีก่ารคำนวณเลย
    int total;//เผื่อไว้เฉยๆใช้จากคลาสที่มีก่ารคำนวณเลย
    int finalprice = 0;//เผื่อไว้เฉยๆใช้จากคลาสที่มีก่ารคำนวณเลย

    public void setFinalPrice(int discountpercent) {//เผื่อไว้เฉยๆใช้จากคลาสที่มีก่ารคำนวณเลย
        this.finalprice = total - (total * discountpercent / 100);

    }

    private int[] parseTriple(String s) {
        String[] in = s.split(":");
        return new int[]{
            Integer.parseInt(in[0]),
            Integer.parseInt(in[1]),
            Integer.parseInt(in[2])
        };
    }

    public booking(String line) {
        String[] parts = line.split(",");
        this.bookingID = parts[0];
        this.customerID = parts[1];
        this.day = Integer.parseInt(parts[2]);

        this.RoomsPerDay = parseTriple(parts[3]);
        this.Persons = Integer.parseInt(parts[4]);
        this.MealsPerPersonPerDay = parseTriple(parts[5]);
        //เผื่อไว้เฉยๆใช้จากคลาสที่มีก่ารคำนวณเลย
        this.RoomPricePerday = RoomsPerDay[0] * priceRoom[0]
                + RoomsPerDay[1] * priceRoom[1]
                + RoomsPerDay[2] * priceRoom[2];
        //เผื่อไว้เฉยๆใช้จากคลาสที่มีก่ารคำนวณเลย
        this.DishPricePerday = MealsPerPersonPerDay[0] * priceDish[0]
                + MealsPerPersonPerDay[1] * priceDish[1]
                + MealsPerPersonPerDay[2] * priceDish[2];
        //เผื่อไว้เฉยๆใช้จากคลาสที่มีก่ารคำนวณเลย
        this.total = RoomPricePerday * day + DishPricePerday * day;
    }

    public void print_booking() {

        System.out.printf("Booking %S, customer %S  >>  days = %d, persons = %d,  rooms=[%d,%d,%d],  meals = [%d,%d,%d] \n",
                bookingID, customerID, day, Persons, RoomsPerDay[0], RoomsPerDay[1],
                RoomsPerDay[2], MealsPerPersonPerDay[0], MealsPerPersonPerDay[1], MealsPerPersonPerDay[2]);

    }

  

}
