/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module2;

/**
 *
 * @author karupakula
 */
public class Misc {
    
    public int[] tobin(int i)
    {
        int ret[] = new int[4];
        for(int l=0; l<4; l++)
            ret[l] = 0;
        
        int mval = i;
        int l = 3;
        while(mval>0)
        {
            ret[l] = mval%2;
            mval = mval/2;
            l--;
        }
        
        return ret;
    }
    
    public String bintostr(int[] bin)
    {
        String s = new String();
        for(int l=0; l<bin.length; l++)
        {
            char c;
            if(bin[l]==0)
                c='0';
            else
                c='1';
            
            s = s + c;
        }
        
        return s;
    }
}
