package com.example.myapplication;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test_BCNF {
    public boolean testbcnf(HashMap<String, String> fds, String rls, Set<String> ckset)
    {
        for(Map.Entry<String, String> entry : fds.entrySet())
        {
            int flagx = 0;
            for(String s : ckset)
            {
                if(checkSubString(entry.getKey(),s))
                {
                    flagx  = 1;
                }
            }

            if(flagx == 0)
                return false;
        }

        return true;


    }

    public boolean checkSubString(String s1, String s2)
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
