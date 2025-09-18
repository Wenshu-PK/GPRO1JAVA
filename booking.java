package com.mycompany.gpro1java;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author sawan
 */
public class booking  {

    String bookingID;
    String customerID;
    int day;
    int[] RoomsPerDay;
    int Persons;
    int[] MealsPerPersonPerDay;
    int [] priceRoom = {8000,11000,18000};
    int [] priceDish = {100,450,600};
    int RoomPricePerday;
    int DishPricePerday;
    int total;
    int finalprice = 0;
    public void setFinalPrice(int discountpercent) {
       this.finalprice = total-(total*discountpercent/100);
    
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
        RoomPricePerday = RoomsPerDay[0]*priceRoom[0]+RoomsPerDay[1]*priceRoom[1]+RoomsPerDay[2]*priceRoom[2];
        DishPricePerday = MealsPerPersonPerDay[0]*priceDish[0]+MealsPerPersonPerDay[1]*priceDish[1]+MealsPerPersonPerDay[2]*priceDish[2];
        total = RoomPricePerday*day+DishPricePerday*day;
    }

  

}
