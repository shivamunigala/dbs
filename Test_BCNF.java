/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author karupakula
 */
public class Test_BCNF {
    
    public boolean testbcnf(HashMap<String, String> fds, String rls, Set<String> ckset)
    {
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
            
            if(flagx == 0)
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
