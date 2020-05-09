/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author karupakula
 */
public class Test_2NF {
    
    public boolean test2nf(HashMap<String, String> fds, String rls, Set<String> ckset)
    {
        System.out.println(ckset.toString());
        Set<Character> ckcharset = new HashSet<Character>();
        for(String k : ckset)
        {
            Character[] templ = ArrayUtils.toObject(k.toCharArray());
            for(Character y : templ)
                ckcharset.add(y);
        }
        
        Character[] ckchararray = new Character[ckcharset.size()];
        ckcharset.toArray(ckchararray);
        char[] pkarr = ArrayUtils.toPrimitive(ckchararray);
        
        int n = ckcharset.size();
        int[] sel = new int[n];
        String temp = "";
        int lt,index;
        String tempy = "";
        
        Set<String> hstx = new HashSet<String>();
        hstx = fds.keySet();
        
        Character[] prime = ArrayUtils.toObject(pkarr);
        Set<Character> primeset = new HashSet<>(Arrays.asList(prime));
        
        Character[] temprls = ArrayUtils.toObject(rls.toCharArray());
        Set<Character> rlst = new HashSet<>(Arrays.asList(temprls));
        
        Set<Character> npset = new HashSet<>(Arrays.asList(temprls));
        npset.removeAll(primeset);
        
        for(int i=1; i<Math.pow(2,n)-1; i++)
        {
            for(int l=0; l<n; l++)
                sel[l] = 0;
            index = 0;
            lt = i;
            while(lt>0)
            {
                sel[index++] = lt%2;
                lt = lt/2;
            }
            
            for(int l=0; l<n; l++)
            {
                if(sel[l]==1)
                {
                    temp = temp + pkarr[l];
                }
                
                for(String tempx : hstx)
                {
                    if(sameChars(temp,tempx))
                    {
                        tempx = fds.get(tempx);
                        for(Character x : npset)
                        {
                            if(tempx.indexOf(x)!=-1)
                            {
                                System.out.println("GAYA");
                                return false;
                            }
                        }
                    }
                }
            }
           
        }
        
        return true;
    }
    
    public boolean sameChars(String firstStr, String secondStr) 
    {
        char[] first = firstStr.toCharArray();
        char[] second = secondStr.toCharArray();
        Arrays.sort(first);
        Arrays.sort(second);
        return Arrays.equals(first, second);
    }
        
    
    
}
