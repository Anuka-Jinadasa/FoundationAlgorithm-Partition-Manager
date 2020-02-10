/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ANUKA
 */
import java.util.Scanner;


public class memory_partition 
{
    private int M_id;
    private int num_partitions;
    private int num_programs;
    private int maxSize = 0;
    private float turnAroundTime = 0;
    private int[] partition_size;
    private program[] pro;
    private Scanner sc = new Scanner(System.in);
    
    public memory_partition(int M_id, int num_partitions , int num_programs) //constructor 
    {
       this.M_id = M_id;
       this.num_partitions = num_partitions;
       this.num_programs = num_programs;
      
       partition_size = new int[num_partitions]; // create partitions
       pro = new program[num_programs];     //create program objects
    }
    
     //take input line 2 and save
    public void set_partitionSize() 
    {
        if (sc.hasNextInt()) // validate input
        {
            for (int c = 0; c < num_partitions; c++) 
            {
                int s = 0;
                s = sc.nextInt();
                if ( s > maxSize ) 
                {
                    maxSize = s;
                }
                
                if (s > 0) 
                {
                    partition_size[c] = s;
                } 
                else 
                {
                    partition_size[c] = 0;
                }
            }
        }
    }
    
    // take program spaces and times
    public void set_runningTimes()
    {
        for (int c = 0; c < num_programs; c ++) 
        {
            if (sc.hasNextInt()) //validate inputs
            {
                int ins = 0; // no of instances
                ins = sc.nextInt();
               
                if (ins <= 10 && ins > 0) // validate input
                {
                    pro[c] = new program(c, ins, num_partitions); // create a program object with program id and no of instances

                    //get values of instances
                    for (int i = 0; i < ins; i ++) 
                    {
                        int space = 0;
                        int time = 0;
                       
                        space = sc.nextInt();
                        time = sc.nextInt();
                       
                        if (space <= maxSize && space > 0 && time > 0) //validate input
                        {
                            pro[c].set_spaceTime(i, time, space); // save instance values
                        }
                    }

                }
                // sort the time to get smallest run time of the program
                pro[c].sort_SpaceTimeArray();
            }
        }

    }

    // calculate Average turn around time
    public void calAvgTurnAroundTimeOfAProgram() 
    {
        for (int c = 0; c < num_programs; c ++) 
        {
            turnAroundTime = turnAroundTime + pro[c].get_Etime();
        }
         
        turnAroundTime = turnAroundTime / num_programs;
    }
    
    public void assign_partition()
    {
        int location = -1;
        int[] TA_time = new int[num_partitions];
        
        for(int c = 0; c < num_partitions; c ++)
        {
            TA_time[c] = 0;
        } 
        
        int [] Arr = new int[num_programs];
        
        for(int d = 0; d < num_programs; d ++)
        {
            Arr[d] = d;
        }   
        
         
        for (int i = 0; i < (num_programs - 1); i ++) //selection sort to sort program run times
        {
            int index = i;
            
            for (int j = (i + 1); j < num_programs; j ++)
            {
                int x = Arr[index];
                int y = Arr[j];
                if (pro[y].get_time() < pro[x].get_time())
                {
                    index = j; //get the smallest run time program
                }
            }
            int temp = Arr[index];
            Arr[index] = Arr[i];
            Arr[i] = temp;

        }
        
        for(int p = 0; p < num_programs; p ++)
        {
            int S = 10000;
            int e = Arr[p];           
            int temp = -1;
            boolean[] hit = new boolean[num_partitions]; // possible memory locations
            int I = 0;
         
          //check possible memory partitions
            for(I = 0; I < num_partitions; I++)
            {
                hit[I] = false;
                
                if (pro[e].get_space() <= partition_size[I])
                {
                        if(TA_time[I] == 0)
                        { // check whether no program assign to partition
                            hit[I] = true;
                            temp = I;
                            TA_time[I] += pro[e].get_time();
                            break;
                        }
                        else 
                        {
                            hit[I] = true;
                            TA_time[I] += pro[e].get_time();
                        }
                }

            }
            
            if(temp != -1) // first program in the partition
            {
                location = temp;
            }
            else
            {
                // determine least turn around memory partition
                for(int r = 0; r < I; r++)
                {
                    if(hit[r])
                    {
                        if( TA_time[r] <= S) 
                        {
                            S = TA_time[r];
                            location = r;
                        }
                    }
                }
            }
            
           for( int C = 0; C < num_partitions; C ++)
           {
               if(C != location && hit[C])
               {
                   TA_time[C] -= pro[e].get_time();
               }    
           
           } 
           
           pro[e].set_memLocations(location + 1);
           pro[e].set_Stime(TA_time[location]);
        
            
            
            
        }    
 
    }
   
    // display case details
    public void displayDetails() 
    {
        sc.close();
        int temp = M_id + 1;
        System.out.println("\nCase" + temp);
        calAvgTurnAroundTimeOfAProgram();
        System.out.print("Average turnaround time = ");
        System.out.printf("%.2f", turnAroundTime);//prnnting avg turnaoung time
        System.out.println();

        for (int c = 0; c < num_programs; c++) 
        {
            pro[c].display(); //output program details
        }
    }
    
}

    