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
public class CandidateKey {
    
    public void findCandidateKey(HashMap<String, String> fds, String rls)
    {
        Set<String> hstl = new HashSet<String>();
        hstl = fds.keySet();
       
        Set<Character> chstl = new HashSet<Character>();
        Set<Character> chstr = new HashSet<Character>();
        for(String x : hstl)
        {
            Character[] templ = ArrayUtils.toObject(x.toCharArray());
            for(Character y : templ)
                chstl.add(y);
            
            Character[] tempr = ArrayUtils.toObject(fds.get(x).toCharArray());
            for(Character z : tempr)
                chstr.add(z);
        }
        
        Character[] temprls = ArrayUtils.toObject(rls.toCharArray());
        Set<Character> rlst = new HashSet<>(Arrays.asList(temprls));
        
        Set<Character> templr = new HashSet<Character>(chstl);
        Set<Character> L = new HashSet<Character>(Arrays.asList(temprls));
        L.removeAll(chstr);
        L.removeAll(chstl);
        templr.removeAll(chstr);
        L.addAll(templr);
        
        Set<Character> R = new HashSet<Character>(chstr);
        R.removeAll(chstl);
        
        Set<Character> M = new HashSet<Character>(chstr);
        M.addAll(chstl);
        M.removeAll(templr);
        M.removeAll(R);
        
        System.out.println(L.toString());
        System.out.println(R.toString());
        System.out.println(M.toString());

        Character[] clstemp = new Character[L.size()];
        L.toArray(clstemp);
        String cls = new String(ArrayUtils.toPrimitive(clstemp));
        
        Closure c = new Closure();
        if(sameChars(c.findclosure(fds, cls),rls))
        {
            System.out.println(cls);
        }
        else
        {
            Character[] choice = new Character[M.size()];
            M.toArray(choice);
            char[] choicechar  = ArrayUtils.toPrimitive(choice);
            int[] sel = new int[M.size()];
            
            int index;
            int count;
            int flag;
            for(int n=1; n<=M.size(); n++)
            {
                flag = 0;
                for(int lt=1; lt<Math.pow(2,M.size()); lt++)
                {
                    index = 0;
                    for(int i=0; i<M.size(); i++)
                        sel[i] = 0;
                    String tempcls = new String(cls);
                    int l = lt;
                    while(l>0)
                    {
                        sel[index++] = l%2;
                        l = l/2;
                    }
                    
                    count = 0;
                    for(int i=0; i<M.size(); i++)
                    {
                        if(sel[i]==1)
                            count++;
                    }
                    
                    if(count == n)
                    {
                        //System.out.println(lt + " " + n);
                        for(int i=0; i<M.size(); i++)
                        {
                            if(sel[i]==1)
                            {
                                tempcls = tempcls + choicechar[i]; 
                            }
                        }
                        if(sameChars(c.findclosure(fds, tempcls),rls))
                        {
                            System.out.println(tempcls);
                            flag = 1;
                        }                    
                    }                        
                }                
                if(flag == 1)
                    break;
            }
        }
        
        
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
