import java.util.Scanner;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ANUKA
 * FA assignment
 * IT18132410
 */
public class main 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {    
        int programs;
        int partitions;
        int Counter = 0;
        
        memory_partition[] Memory = new memory_partition[10];
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("-----------------------------------------");
        System.out.println("\tPartition Manager");
        System.out.println("-----------------------------------------\n");
        
        //inputs
        for(int c = 0; c < 10; c ++)
        {
            if(scanner.hasNextInt())
            {
               partitions = scanner.nextInt();
               programs = scanner.nextInt();
               
               if(partitions <= 10 && partitions > 0 && programs > 0 && programs <= 50)
               {
                   Memory[c] = new memory_partition(c,partitions,programs);
                   Memory[c].set_partitionSize(); //set partition time input line 1 and 2
                   Memory[c].set_runningTimes();  //set running times                
                   Memory[c].assign_partition(); //assigning partitions
                   Counter ++;
               }
               else if(partitions == 0 && programs == 0) //breakin when 0 0 enters
               {
                   break;
               }
               else
               {
                   System.out.println("Invalid");                  
                   System.exit(0); // exiting the program ifvalues are invalid
               }    
               
            }             
            
        }
        scanner.close();
        
        for(int c = 0; c < Counter; c ++)
        {
            Memory[c].displayDetails();
        }
        
        System.out.println("\n\n-----------------------------------------");
        System.out.println("\tEnd Of The Program");
        System.out.println("-----------------------------------------\n");
          
    }
    
}
