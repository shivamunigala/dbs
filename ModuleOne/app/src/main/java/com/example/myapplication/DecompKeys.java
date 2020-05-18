package com.example.myapplication;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;

public class DecompKeys {
    public HashMap<String, Set<String>> decompsedkeys(HashMap<String, String> fds, Set<String> relations)
    {
        HashMap<String, Set<String>> finalmap = new HashMap<String, Set<String>>();
        CandidateKey ck = new CandidateKey();

        for(String rl : relations)
        {
            HashMap<String, String> tempfd = new HashMap<String,String>();

            for(Map.Entry<String, String> entry : fds.entrySet())
            {
                Set<Character> s = new HashSet<Character>();
                if(containschar(rl,entry.getKey()))
                {
                    for(char ch : entry.getValue().toCharArray())
                    {
                        if(rl.indexOf(ch)!=-1)
                            s.add(ch);
                    }

                    Character[] sarr = new Character[s.size()];
                    s.toArray(sarr);
                    String val = new String(ArrayUtils.toPrimitive(sarr));
                    tempfd.put(entry.getKey(), val);
                }
            }

            Set<String> ckset;
            ckset = ck.findCandidateKey(tempfd, rl);

            finalmap.put(rl, ckset);
        }

        return finalmap;
    }

    public boolean containschar(String rl, String s)
    {
        for(char c : s.toCharArray())
        {
            if(rl.indexOf(c)==-1)
                return false;
        }

        return true;
    }
}
