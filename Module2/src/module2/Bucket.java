/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module2;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author karupakula
 */
public class Bucket {
    
    String id;
    int ld;
    ArrayList<Integer> buck;
    
    public Bucket(String s,int ldi)
    {
        this.id = s;
        this.ld = ldi;
        buck = new ArrayList<Integer>();
    }
    
    public boolean isFull()
    {
        if(this.buck.size()>=3)
            return true;
        else
            return false;
    }
    
    public void addtobuck(int i)
    {
        this.buck.add(i);
    }
    
}
