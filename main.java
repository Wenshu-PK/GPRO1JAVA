package Project1_6713249;

import java.util.*;
import java.io.*;

public class Project1_main {
    
  protected ArrayList<booking> booking_list = new ArrayList<>();
  protected ArrayList<Customer> allCustomers = new ArrayList<>();
  
  public static void main(String[] args) {
      
      Project1_main mainapp = new Project1_main();
      
      Scanner sc = new Scanner(System.in);
      String path = "src/main/Java/Project1_6713249/";
        
      String filename = "";
     
      while (true) {
        System.out.print("Enter file name: ");
        filename = sc.nextLine().trim();
        try {
            String inFilename = path + filename;
            mainapp.load_booking(inFilename);  
            break;                   
        } catch(FileNotFoundException e) {
            
            System.out.println(e);
        }catch (Exception e){System.out.println(e);}
      }
     for(int i=0;i<mainapp.booking_list.size();i++){
         mainapp.booking_list.get(i).print_booking();
         System.out.println("Hello world");
     
     }
 }
   
  
    
  public void load_booking(String file_path) throws FileNotFoundException
  {  
      try (Scanner readFile = new Scanner(new File(file_path));) {
        
        
        String line;
        readFile.nextLine();
        while (readFile.hasNext()) 
        {

            line = readFile.nextLine();
            booking_list.add(new booking(line));

        }
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    

    }
  
  public void checkDuplicates(ArrayList<booking> b)
    {
        for(int i = 0; i < b.size(); i++)
        {
            booking tempListI = b.get(i);
            double totalAmount = tempListI.getSubTotal();
            for(int j = 1; j < b.size(); j++)
            {
                ArrayList<booking> t = new ArrayList<>();
                t.add(tempListI);
                booking tempListJ = b.get(j);
                if(tempListI.getCustomerID().equals(tempListJ.getCustomerID()))
                {
                    totalAmount += tempListJ.getSubTotal();
                    t.add(tempListJ);
                }
                Customer a = new Customer(tempListI.getCustomerID(), t, totalAmount);
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
                System.out.println(this.allCustomers.get(i).bookings.get(j).getBookingId());
                if(j != this.allCustomers.get(i).bookings.size())
                {
                    System.out.println(", ");
                }
            }
            System.out.println("\n");
        }   
    }



  
}
