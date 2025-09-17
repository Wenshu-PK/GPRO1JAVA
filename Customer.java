/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project1_6713249;

import java.util.*;
//import java.lang.Math;

public class Customer implements Comparable<Customer>
{
    //private ArrayList<Customer> allCustomers = new ArrayList<>();
    private String actualCustomerID;
    private double ctotal;
    private ArrayList<Booking> bookings;
    
    public Customer(String id, ArrayList<Booking> booking, double ctotal) {
        this.actualCustomerID = id;
        this.bookings = booking;
        this.ctotal = ctotal;
    }
    public String getActualCustomerID(){ return actualCustomerID;}
    public double getCTotal(){ return ctotal;}
    public ArrayList<Booking> getBookings(){ return bookings;}
    public void checkDuplicates(ArrayList<Booking> b)
    {
        for(int i = 0; i < b.size(); i++)
        {
            Booking tempListI = b.get(i);
            double totalAmount = tempListI.getSubTotal();
            for(int j = 1; j < b.size(); j++)
            {
                ArrayList<Booking> t = new ArrayList<>();
                t.add(tempListI);
                Booking tempListJ = b.get(j);
                if(tempListI.getCustomerID().equals(tempListJ.getCustomerID()))
                {
                    totalAmount += tempListJ.getSubTotal();
                    t.add(tempListJ);
                }
                Customer a = new Customer(tempListI.getCustomerId(), t, totalAmount);
                this.allCustomers.add(a);
            }
        }
    }
    public void removeDuplicates()
    {
        for(int i = 0; i < this.allCustomers.size(); i++)
        {
            Customer tempI = this.allCustomers.get(i);
            for(int j = 1; j< this.allCustomers.size(); j++)
            {
                Customer tempJ = this.allCustomers.get(j);
                if(tempI.getActualCustomerID().equals(tempJ.getActualCustomerID()))
                {
                    this.allCustomers.remove(j);
                }
            }
        }
    }
    public void sortCustomerList(){
        Collections.sort(this.allCustomers);
    }
    public void printCustomerSummary()
    {
        System.out.println("===== Customer Summary =====\n");
        for(int i = 0; i < this.allCustomers.size(); i++)
        {
            System.out.printf("%-3s >>  total amount = %-13d", this.allCustomers.get(i).actualCustomerID, this.allCustomers.get(i).ctotal );
            System.out.println("    bookings = [");
            for(int j = 0; j < this.allCustomers.get(i).bookings.size(); j++)
            {
                System.out.println(this.allCustomers.get(i).bookings.get(j).getId());
                if(j != this.allCustomers.get(i).bookings.size())
                {
                    System.out.println(", ");
                }
            }
            System.out.println("\n");
        }   
    }
    @Override
    public int compareTo(Customer other){
        
        if (this.ctotal != other.ctotal) {return (int)Math.round(other.ctotal - this.ctotal);}
        return 0;
    } 
}

