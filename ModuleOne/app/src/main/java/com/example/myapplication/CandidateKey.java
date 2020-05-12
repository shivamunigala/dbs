package com.example.myapplication;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CandidateKey {
    public Set<String> findCandidateKey(HashMap<String, String> fds, String rls)
    {
        Set<String> ret = new HashSet<String>();
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

        Closure c = new Closure();

        Character[] clstemp = new Character[L.size()];
        L.toArray(clstemp);
        String cls = new String(ArrayUtils.toPrimitive(clstemp));

        if(sameChars(c.findclosure(fds, cls),rls))
        {
            System.out.println(cls);
            ret.add(cls);
        }
        else
        {
            int index,lt,flag;
            int[] sel = new int[M.size()];
            String temp;
            Character[] Marr = new Character[M.size()];
            M.toArray(Marr);
            for(int i=1; i<Math.pow(2,M.size()); i++) {
                for(int l=0; l<M.size(); l++)
                    sel[l] = 0;
                index = 0;
                temp = new String(cls);
                lt = i;
                while(lt>0) {
                    sel[index++] = lt%2;
                    lt = lt/2;
                }

                for(int l=0; l<M.size(); l++) {
                    if(sel[l]==1) {
                        temp = temp + Marr[l];
                    }
                }

                flag = 0;
                if(sameChars(c.findclosure(fds, temp),rls)) {
                    for(String s : ret) {
                        if(checkSubString(temp,s)) {
                            flag = 1;
                        }
                    }

                    if(flag == 0) {
                        System.out.println(temp);
                        ret.add(temp);
                    }
                }

            }
        }
        return ret;
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
        return (flag==0 && s1.length()!=s2.length());
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
