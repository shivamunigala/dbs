/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author karupakula
 */
public class Decomp_BCNF {
    
    public Set<String> decompbcnf(HashMap<String, String> fds, String rls, Set<String> ckset)
    {
        Set<String> relations = new HashSet<String>();
        Character[] rlsarr = ArrayUtils.toObject(rls.toCharArray());
        Set<Character> rlsset = new HashSet<Character>(Arrays.asList(rlsarr));
        String x;
        String temp="";
        int flag;
        relations.add(rls);
        for(Map.Entry<String, String> entry : fds.entrySet())
        {
            x = entry.getKey();
            int flagx = 0;
            for(String s : ckset)
            {
                if(checksubstring(entry.getKey(),s))
                {
                    flagx  = 1;
                }
            }
            
            if(flagx == 0)
            {
                for(String s : relations)
                {   
                    flag = 0;
                    if(checksubstring(s,x.concat(entry.getValue())))
                    {
                        Set<Character> tempset = new HashSet<Character>(Arrays.asList(ArrayUtils.toObject(entry.getValue().toCharArray())));
                        Set<Character> sset = new HashSet<>(Arrays.asList(ArrayUtils.toObject(s.toCharArray())));
                        sset.removeAll(tempset);
                        Character[] sarr = new Character[sset.size()];
                        sset.toArray(sarr);
                        temp = new String(ArrayUtils.toPrimitive(sarr));
                        flag = 1;
                        relations.add(x.concat(entry.getValue()));
                    }
                    if(flag==1)
                    {
                        relations.remove(s);
                        relations.add(temp);
                    }
                }                
            }    
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
        
        System.out.println(relations.toString());
        return relations;
        
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
