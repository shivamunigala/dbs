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
public class Directory {
    
    public int dirvalue(int[] gdval)
    {
        int l=0;
        int ret=0;
        for(int i=gdval.length-1; i>=0; i--)
        {
            ret = (int) (ret + Math.pow(2, l)*gdval[i]);
            l++;
        }
        
        return ret;
    }
       
}
