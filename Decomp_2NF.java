/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author karupakula
 */
public class Decomp_2NF {
    
    public void decomp2nf(HashMap<String, String> fds, String rls, Set<String> ckset)
    {
        String x;
        String temp = new String();
        String temps = new String();
        
        Set<String> relations = new HashSet<String>();
        int n = fds.size();
        int[] flag = new int[n];
        for(int i=0; i<n; i++)
            flag[i] = 0;
        int count=0;
        for(Map.Entry<String, String> entry : fds.entrySet())
        {
            x = entry.getKey();
            for(String ck : ckset)
            {
                if(checksubstring(ck,x))
                {
                    relations.add(x.concat(entry.getValue()));
                    flag[count] = 1;
                    break;
                }
            }
            count++;
            
        }
        for(String ck : ckset)
        {
            int f = 0;
            for(String st : relations)
            {
                if(checksubstring(st,ck))
                {
                    f = 1;
                    break;
                }
            }
            if(f==0)
                relations.add(ck);
        }
        count = 0;
        for(Map.Entry<String, String> entry : fds.entrySet())
        {
            x = entry.getKey();
            if(flag[count]==0)
            {
                System.out.println(entry.getValue());
                for (String s : relations) {
                    if(checksubstring(s,x))
                    {
                        temps = new String(s);
                        temp = new String(s);
                        for(char c : entry.getValue().toCharArray())
                        {
                            if(temp.indexOf(c)==-1)
                                temp = temp + c;
                        }
                        break;
                    }                    
                }
                
                relations.remove(temps);
                relations.add(temp);
            }
            count++;
        }
              
        
        System.out.println(relations.toString());
    }
    
    public boolean checksubstring(String s1, String s2)
    {
        char[] first = s1.toCharArray();
        char[] second = s2.toCharArray();
        int flag = 0;
        for(char c : second)
        {
            if(s1.indexOf(c)==-1)
            {
                flag = 1;
            }
        }
        return (flag==0);
    }
    
}
