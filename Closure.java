/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module1;

import java.util.Hashtable;
import java.util.StringTokenizer;

/**
 *
 * @author karupakula
 */
public class Closure {
    
    public void findclosure(String fd, String rls)
    {
        StringTokenizer st = new StringTokenizer(fd, "->|;");
        StringTokenizer st1 = new StringTokenizer(fd, "->|;");
        int n = 0;
        
        while(st.hasMoreTokens())
        {
            st.nextToken();
            n = n+1;
        }
        
        int i = 0;
        String[] starr = new String[n];
        while(st1.hasMoreTokens())
        {
            starr[i] = st1.nextToken();
            i = i+1;
        }
        
        Hashtable<String, String> h = new Hashtable<String, String>();
        String[] stx = new String[n/2];
        i = 0;
        for(int l=0; l<n; l=l+2)
        {
            h.put(starr[l],starr[l+1]);
            stx[i] = starr[l];
            i++;
        }
                
        Hashtable<String, String> hc = new Hashtable<String, String>();
        
        String temp;
        String cls;
        int[] ind = new int[n/2];
        
        for(int k=0; k<n/2; k++)
        {
            cls = stx[k];
        
            for(int l=0; l<n/2; l++)
                ind[l] = -1;
            do
            {
                temp = cls;
                for(int l=0; l<n/2; l++)
                {
                    if(checkkey(cls,stx[l]) && ind[l] == -1)
                    {
                        cls = cls.concat(h.get(stx[l]));
                        ind[l] = 0;
                    }
                }
            }while(!temp.equals(cls));
            
            hc.put(stx[k],cls);
        }
        
        
        System.out.println(hc);
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
