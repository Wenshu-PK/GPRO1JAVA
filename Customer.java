/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project1_6713249;
/*
Anun Luechaphongthip         6713253
Puvit Kitiwongpaisan         6713246
Kanapod Lamthong             6713220
Piyawat Jaroonchaikhanakit   6713240
Sawana Thiputhai             6713249
*/

import java.util.*;
//import java.lang.Math;

public class Customer implements Comparable<Customer>
{
    //private ArrayList<Customer> allCustomers = new ArrayList<>();
    private String actualCustomerID;
    private double ctotal;
    private ArrayList<booking> bookings;
    
    public Customer(String id, ArrayList<booking> booking, double ctotal) {
        this.actualCustomerID = id;
        this.bookings = booking;
        this.ctotal = ctotal;
    }
    public String getActualCustomerID(){ return actualCustomerID;}
    public double getCTotal(){ return ctotal;}
    public ArrayList<booking> getBookings(){ return bookings;}
    
    @Override
    public int compareTo(Customer other){
        
        if (this.ctotal != other.ctotal) {return (int)Math.round(other.ctotal - this.ctotal);}
        return 0;
    } 
}

