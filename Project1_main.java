package Project1_6713249;

import java.util.*;
import java.io.*;

public class Project1_main {
    
  protected ArrayList<booking> booking_list = new ArrayList<>();
  protected ArrayList<Customer> allCustomers = new ArrayList<>();
  protected ArrayList<item> framItem = new ArrayList<>();
  
  public static void main(String[] args) {
      
      Project1_main mainapp = new Project1_main();
      
      Scanner sc = new Scanner(System.in);
      String path = "src/main/Java/Project1_6713249/";
        
      String filename = "";
      
      //input name -> items.txt ( louis )*********************************************************
      while (true) {
        System.out.print("Enter file name: ");
        String filename_itemu = sc.nextLine().trim();
        try {
              String inFilename = path + filename_itemu;
              mainapp.load_itemu(inFilename);
              break;
        } catch (FileNotFoundException e) {
              System.out.println(e);
        }
      }

      for (int i = 0; i < mainapp.framItem.size(); i++) {
            
        System.out.println(mainapp.framItem.get(i).Printitemu(1, 1, 1));
      }      
      //input name -> discounts.txr ( Est )***********************************************************
       
        // 1) Create Discount object
       

        // 2) Read discounts from file
       System.out.print("Enter discount filename: ");
       String filename_discount = sc.nextLine().trim();
       Discount discount = new Discount();
       discount.readDiscounts(path + filename_discount);
      
      //input name -> bookings.txt ( khung )**********************************************************
      boolean loop = true;
      while (loop) {
          System.out.print("Enter file name: ");
          String filename_booking = sc.nextLine().trim();
          File read_file = new File(path + filename_booking);
        try {
            Scanner readFile = new Scanner(read_file);

            String buf = readFile.nextLine();
            while (readFile.hasNext()) {

                try {
                    String line = readFile.nextLine();
                    booking b = booking.loadbooking(line);
                    mainapp.booking_list.add(b);
                    loop = false;
                } catch (Exception e) {
                    System.out.println(e.getMessage()+" skip");
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
      }  
     
        for (int i = 0; i < mainapp.booking_list.size(); i++) {
            booking b = mainapp.booking_list.get(i);
            b.computePrice(mainapp.framItem);
            b.print_booking();
           b.ComputeDiscount(discount);
        }
      //  ( Max&Jamesbond )*******************************************************************************
      mainapp.checkDuplicates(mainapp.booking_list);
      mainapp.removeDuplicates();
      mainapp.sortCustomerList();
      mainapp.printCustomerSummary();
        
    
     
 }//end of method main---------------------------------------------------------------------------------------------------------------

  
  
//readfile items.txt ( louis ) 
 public void load_itemu(String filename) throws FileNotFoundException {
    try (Scanner scf = new Scanner(new File(filename))) {
         if (scf.hasNextLine()) scf.nextLine(); // skip header
         while (scf.hasNextLine()) {
         String line = scf.nextLine();
         try {
               item it = itemuFactory.createFromLine(line);
               framItem.add(it);
         } catch (Exception e) {
               System.out.println("Error: " + e.getMessage());
           }
         }
    }
 }//end of method load_itemu---------------------------------------------------------------------------------------------------------------
    
  

//About Customers ( Max & Jamesbond ) 
  public void checkDuplicates(ArrayList<booking> b)
    {
        for(int i = 0; i < b.size(); i++)
        {   ArrayList<booking> t = new ArrayList<>();
            booking tempListI = b.get(i);
            double totalAmount = tempListI.getSubTotal();
            t.add(tempListI);
            for(int j = i+1; j < b.size(); j++)
            {
                
                //t.add(tempListI);
                booking tempListJ = b.get(j);
                if(tempListI.getCustomerID().equals(tempListJ.getCustomerID()))
                {
                    totalAmount += tempListJ.getSubTotal();
                    t.add(tempListJ);
                }
                
            }
            Customer a = new Customer(tempListI.getCustomerID(), t, totalAmount);
            this.allCustomers.add(a);
        }
    }//end of method checkDuplicates---------------------------------------------------------------------------------------------------------------
    public void removeDuplicates()
    {
        for(int i = 0; i < this.allCustomers.size(); i++)
        {
            Customer tempI = this.allCustomers.get(i);
            for(int j = i+1; j< this.allCustomers.size(); j++)
            {
                Customer tempJ = this.allCustomers.get(j);
                if(tempI.getActualCustomerID().equals(tempJ.getActualCustomerID()))
                {
                    this.allCustomers.remove(j);
                }
            }
        }
    } //end of method removeDuplicates ---------------------------------------------------------------------------------------------------------------
    
    public void sortCustomerList(){
        Collections.sort(this.allCustomers);
    }//end of method sortCustomerList ---------------------------------------------------------------------------------------------------------------
    
    public void printCustomerSummary()
    {
        System.out.println("===== Customer Summary =====");
        for(int i = 0; i < this.allCustomers.size(); i++)
        {
            System.out.printf("%-3s >>  total amount = %,13.2f", this.allCustomers.get(i).actualCustomerID, this.allCustomers.get(i).ctotal );
            System.out.printf("    bookings = [");
            for(int j = 0; j < this.allCustomers.get(i).bookings.size(); j++)
            {
                System.out.printf("%-3s",this.allCustomers.get(i).bookings.get(j).getBookingId());
                if(j < this.allCustomers.get(i).bookings.size()-1)
                {
                    System.out.printf(", ");
                }
                else if(j == this.allCustomers.get(i).bookings.size()-1){System.out.printf("]");}
                //if(j == this.allCustomers.get(i).bookings.size()-1){ System.out.printf("]");}
            }
            System.out.println("");
        }   
    }//end of method printCustomerSummary---------------------------------------------------------------------------------------------------------------



  
}// end of class
