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
        }
      }
 }
   
  
    
  public void load_booking(String file_path) throws FileNotFoundException
  {
        Scanner readFile = new Scanner(file_path);
        String line;
        readFile.nextLine();
        while (readFile.hasNext()) 
        {

            line = readFile.nextLine();
            booking_list.add(new booking(line));

        }

    }



  
}
