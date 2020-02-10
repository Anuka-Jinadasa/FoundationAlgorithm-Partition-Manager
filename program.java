/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ANUKA
 */
public class program 
{
    private int p_id;
    private int[][] space_time;
    private int mem_location;
    private int num_instences = 0;
    private int Stime;
    private int Etime;
    private int[] possible_memLocations;
    private int num_memLocations;
    
    public program(int p_id,int num_instences ,int loc) //constructor
    {
        this.p_id = p_id;
        space_time = new int[num_instences][2]; // 2D array to save instance parameters
        this.num_instences = num_instences; 
        mem_location = 0;
        num_memLocations = 0;
        possible_memLocations = new int[loc];
        Stime = -1;
    }
    
     
    public void set_spaceTime(int ins,int time,int space) // store instances
    {
        space_time[ins][0] = time;
        space_time[ins][1] = space;
    }
    
    //selection sort 
    public void sort_SpaceTimeArray() //sort time in space time array 
    {
        for (int C = 0; C < (num_instences - 1); C ++)
        {
            int index = C;
            for (int j = (C + 1); j < num_instences; j ++)
            {
                if (space_time[j][0] < space_time[index][0])
                {
                    index = j; //searching for lowest time
                }
            }
            
            int Time = space_time[index][0];
            int temp = space_time[index][1];
            space_time[index][0] = space_time[C][0];
            space_time[index][1] = space_time[C][1];
            space_time[C][0] = Time;
            space_time[C][1] = temp;
            
        }
    
    }
    
    public void set_memLocations(int mem_locations)
    {
        this.mem_location = mem_locations;
    }
    
    public void set_PossibleLocations(int possible_memLocations)
    {
        this.possible_memLocations[num_memLocations ++] = possible_memLocations;
    }
    
    public int get_numMemLocations()
    {
        return num_memLocations;
    }

    public int get_PossibleLocations(int locations)
    {
        return possible_memLocations[locations];
    }

    public int get_space()
    {
        return space_time[0][1];
    }

    public int get_time()
    {
        return space_time[0][0];
    }
    
    public void set_Stime(int time)
    {
        Etime = time;
        Stime = Etime - get_time();
    }

    public int get_Etime()
    {
        return  Etime;
    }
    
    public void display()
    {
        this.p_id = p_id + 1;
        System.out.println("Program "+ p_id +" runs in region "+ mem_location +" from "+ Stime +" to "+ Etime);

    } //outputing program ID and running memory region
    
    
}
