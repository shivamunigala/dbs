/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.lang.Object;
import java.util.StringTokenizer;

import org.apache.commons.lang3.ArrayUtils; 

/**
 *
 * @author karupakula
 */
public class Closure_D {
    
    public String findclosure(HashMap<String, String> h, String cls)
    {
              
        String temp;
        int n = h.size()*2;
                
        Set<String> hst = new HashSet<String>();
        hst = h.keySet();
        
        String stx[] = hst.toArray(new String[hst.size()]);
        
        do
        {
            temp = cls;
            for(int l=0; l<n/2; l++)
            {
                if(checkkey(cls,stx[l]))
                {
                    Character[] clsarr = ArrayUtils.toObject(cls.toCharArray());
                    Character[] starr = ArrayUtils.toObject((h.get(stx[l])).toCharArray());
                    Set<Character> clsset = new HashSet<>(Arrays.asList(clsarr));
                    Set<Character> stset = new HashSet<>(Arrays.asList(starr));
                    clsset.addAll(stset);
                    
                    Character[] clstemp = new Character[clsset.size()];
                    clsset.toArray(clstemp);
                    cls = new String(ArrayUtils.toPrimitive(clstemp));
                }
            }
        }while(!temp.equals(cls));
            
        return cls;
        
    }
    
    public boolean checkkey(String s1, String s2)
    {
        int n = s2.length();
        for(int l=0; l<n; l++)
        {
            if(s1.indexOf(s2.charAt(l))== -1)
                return false;
        }
        
        return true;
    }
}
