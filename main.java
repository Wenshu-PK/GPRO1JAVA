package Project1_6713249;

import java.util.*;

public class GPRO1JAVA {


   
  protected ArrayList<booking> booking_list = new ArrayList<>();

    
  public void load_booking(String file_path) 
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
