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
public class Test_3NF {
    
    public boolean test3nf(HashMap<String, String> fds, String rls, Set<String> ckset)
    {
        Set<Character> ckcharset = new HashSet<Character>();
        for(String k : ckset)
        {
            Character[] templ = ArrayUtils.toObject(k.toCharArray());
            for(Character y : templ)
                ckcharset.add(y);
        }
        
        for(Map.Entry<String, String> entry : fds.entrySet())
        {
            int flagx = 0;
            for(String s : ckset)
            {
                if(checksubstring(entry.getKey(),s))
                {
                    flagx  = 1;
                }
            }
            
            int flagy = 1;
            for(char ch : entry.getValue().toCharArray())
            {
                if(!ckcharset.contains(ch))
                {
                    flagy = 0;
                }
            }
            
            if(flagx==0 && flagy==0)
                return false;
            
        }
        return true;
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
        return (flag==0 && s1.length()!=s2.length());
    }
    
}
