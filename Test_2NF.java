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
        Set<Character> ckcharset = new HashSet<Character>();
        for(String k : ckset)
        {
            Character[] templ = ArrayUtils.toObject(k.toCharArray());
            for(Character y : templ)
                ckcharset.add(y);
        }
        
        Character[] temprls = ArrayUtils.toObject(rls.toCharArray());
        Set<Character> npset = new HashSet<>(Arrays.asList(temprls));
        npset.removeAll(ckcharset);
        
        Closure_D clsd = new Closure_D();
        String tempcls;
        for(String ck : ckset)
        {
            int n = ck.length();
            char[] pkarr = ck.toCharArray();
            int[] sel = new int[n];
            int index,lt;
            String temp;
            for(int i=1; i<(Math.pow(2,n)-1); i++)
            {
                
                for(int l=0; l<n; l++)
                    sel[l] = 0;
                
                index = 0;
                temp = "";
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
                    
                    tempcls = clsd.findclosure(fds, temp);
                    
                    for(Character c : npset)
                    {
                        if(tempcls.indexOf(c)!=-1)
                        {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
        
    
    
}
