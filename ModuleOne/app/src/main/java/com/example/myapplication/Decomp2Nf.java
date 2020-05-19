package com.example.myapplication;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Decomp2Nf {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Set<String> decomp2nf(HashMap<String, String> fds, String rls, Set<String> ckset)
    {
        Set<Set<Character>> ckSetSet = ckset.stream().map(s -> s.chars().mapToObj(c -> (char) c).collect(Collectors.toSet())).collect(Collectors.toSet());
        Set<Character> ckSetCons = ckSetSet.stream().reduce(new HashSet<>(), (s, e) -> {s.addAll(e); return s;}, (s1, s2) -> {s1.addAll(s2); return s1;});


        Map<Set<Character>, Set<Character>> mspSets = new HashMap<>();
        fds.forEach((k, v) -> {
            mspSets.put(k.chars().mapToObj(c -> (char) c).collect(Collectors.toSet()), v.chars().mapToObj(c -> (char) c).collect(Collectors.toSet()));
        });

        Set<Character> rlsSet = rls.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
        Set<String> dec = new HashSet<>();

        mspSets.forEach((k,v) -> {
            if(!ckSetCons.containsAll(v)) {
                if(ckSetSet.stream().anyMatch(e -> e.containsAll(k) && !e.equals(k))) {
                    v.removeAll(ckSetCons);
                    rlsSet.removeAll(v);
                    v.addAll(k);
                    dec.add(v.stream().map(String::valueOf).collect(Collectors.joining()));
                }
            }
        });

        dec.add(rlsSet.stream().map(String::valueOf).collect(Collectors.joining()));

        return dec;
    }
        //HashMap<Set<Character>,Set<Character>> hashMap =
//        String x;
//        String temp = new String();
//        String temps = new String();
//
//        Set<String> relations = new HashSet<String>();
//        int n = fds.size();
//        int[] flag = new int[n];
//        for(int i=0; i<n; i++)
//            flag[i] = 0;
//        int count=0;
//        for(Map.Entry<String, String> entry : fds.entrySet())
//        {
//            x = entry.getKey();
//            for(String ck : ckset)
//            {
//                if(checksubstring(ck,x))
//                {
//
//                    if(sameChars(x.concat(entry.getValue()),rls))
//                    {
//                        String sx;
//                        String tempx = x;
//                        for(char c : entry.getValue().toCharArray())
//                        {
//                            int flagchar = 0;
//                            for(Map.Entry<String, String> entries : fds.entrySet())
//                            {
//                                sx = entries.getKey();
//                                if(!sx.equals(tempx))
//                                {
//                                    sx = sx.concat(fds.get(sx));
//                                    if(sx.indexOf(c)!=-1)
//                                        flagchar = 1;
//                                }
//
//                            }
//                            if(flagchar==0)
//                                x = x+c;
//                        }
//
//                        relations.add(x);
//                    }
//                    else
//                    {
//                        relations.add(x.concat(entry.getValue()));
//                    }
//
//                    flag[count] = 1;
//                    break;
//                }
//            }
//            count++;
//
//        }
//        for(String ck : ckset)
//        {
//            int f = 0;
//            for(String st : relations)
//            {
//                if(checksubstring(st,ck))
//                {
//                    f = 1;
//                    break;
//                }
//            }
//            if(f==0)
//                relations.add(ck);
//        }
//        count = 0;
//        for(Map.Entry<String, String> entry : fds.entrySet())
//        {
//            x = entry.getKey();
//            if(flag[count]==0)
//            {
//                System.out.println(entry.getValue());
//                for (String s : relations) {
//                    if(checksubstring(s,x))
//                    {
//                        temps = new String(s);
//                        temp = new String(s);
//                        for(char c : entry.getValue().toCharArray())
//                        {
//                            if(temp.indexOf(c)==-1)
//                                temp = temp + c;
//                        }
//                        break;
//                    }
//                }
//
//                relations.remove(temps);
//                relations.add(temp);
//            }
//            count++;
//        }
//
//        System.out.println(relations.toString());
//        return relations;
//
//    }
//
//    public boolean sameChars(String firstStr, String secondStr)
//    {
//        char[] first = firstStr.toCharArray();
//        char[] second = secondStr.toCharArray();
//        Arrays.sort(first);
//        Arrays.sort(second);
//        return Arrays.equals(first, second);
//    }
//
//    public boolean checksubstring(String s1, String s2)
//    {
//        char[] first = s1.toCharArray();
//        char[] second = s2.toCharArray();
//        int flag = 0;
//        for(char c : second)
//        {
//            if(s1.indexOf(c)==-1)
//            {
//                flag = 1;
//            }
//        }
//        return (flag==0);
//    }
//    public Set<String> decomp2nf(HashMap<String, String> fds, String rls, Set<String> ckset)
//    {
//        String x;
//        String temp = new String();
//        String temps = new String();
//
//        Set<String> relations = new HashSet<String>();
//        int n = fds.size();
//        int[] flag = new int[n];
//        for(int i=0; i<n; i++)
//            flag[i] = 0;
//        int count=0;
//        for(Map.Entry<String, String> entry : fds.entrySet())
//        {
//            x = entry.getKey();
//            for(String ck : ckset)
//            {
//                if(checksubstring(ck,x))
//                {
//                    relations.add(x.concat(entry.getValue()));
//                    flag[count] = 1;
//                    break;
//                }
//            }
//            count++;
//
//        }
//        for(String ck : ckset)
//        {
//            int f = 0;
//            for(String st : relations)
//            {
//                if(checksubstring(st,ck))
//                {
//                    f = 1;
//                    break;
//                }
//            }
//            if(f==0)
//                relations.add(ck);
//        }
//        count = 0;
//        for(Map.Entry<String, String> entry : fds.entrySet())
//        {
//            x = entry.getKey();
//            if(flag[count]==0)
//            {
//                System.out.println(entry.getValue());
//                for (String s : relations) {
//                    if(checksubstring(s,x))
//                    {
//                        temps = new String(s);
//                        temp = new String(s);
//                        for(char c : entry.getValue().toCharArray())
//                        {
//                            if(temp.indexOf(c)==-1)
//                                temp = temp + c;
//                        }
//                        break;
//                    }
//                }
//
//                relations.remove(temps);
//                relations.add(temp);
//            }
//            count++;
//        }
//
//
//        return relations;
//    }
//
//    public boolean checksubstring(String s1, String s2)
//    {
//        char[] first = s1.toCharArray();
//        char[] second = s2.toCharArray();
//        int flag = 0;
//        for(char c : second)
//        {
//            if(s1.indexOf(c)==-1)
//            {
//                flag = 1;
//            }
//        }
//        return (flag==0);
//    }
}
