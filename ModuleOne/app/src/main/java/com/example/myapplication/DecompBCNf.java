package com.example.myapplication;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

public class DecompBCNf {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Set<String> decompbcnf(HashMap<String, String> fds, String rls, Set<String> ckset){
        Set<Set<Character>> ckSetSet = ckset.stream().map(s -> s.chars().mapToObj(c -> (char) c).collect(Collectors.toSet())).collect(Collectors.toSet());
        Set<Character> ckSetCons = ckSetSet.stream().reduce(new HashSet<>(), (s, e) -> {s.addAll(e); return s;}, (s1, s2) -> {s1.addAll(s2); return s1;});


        Map<Set<Character>, Set<Character>> mspSets = new HashMap<>();
        fds.forEach((k, v) -> {
            mspSets.put(k.chars().mapToObj(c -> (char) c).collect(Collectors.toSet()), v.chars().mapToObj(c -> (char) c).collect(Collectors.toSet()));
        });

        Set<Character> rlsSet = rls.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
        Set<String> dec = new HashSet<>();

        mspSets.forEach((k,v) -> {
//            if(!ckSetCons.containsAll(v)) {
                if(ckSetSet.stream().noneMatch(e -> e.equals(k))) {
                    v.removeAll(k);
                    rlsSet.removeAll(v);
                    v.addAll(k);
                    dec.add(v.stream().map(String::valueOf).collect(Collectors.joining()));
                }
//            }
        });

        dec.add(rlsSet.stream().map(String::valueOf).collect(Collectors.joining()));

        return dec;
    }
//    {
//        Set<String> relations = new HashSet<String>();
//        Character[] rlsarr = ArrayUtils.toObject(rls.toCharArray());
//        Set<Character> rlsset = new HashSet<Character>(Arrays.asList(rlsarr));
//        String x;
//        String temp="";
//        int flag;
//        relations.add(rls);
//        for(Map.Entry<String, String> entry : fds.entrySet())
//        {
//            x = entry.getKey();
//            int flagx = 0;
//            for(String s : ckset)
//            {
//                if(checksubstring(entry.getKey(),s))
//                {
//                    flagx  = 1;
//                }
//            }
//
//            if(flagx == 0)
//            {
//                for(String s : relations)
//                {
//                    flag = 0;
//                    if(checksubstring(s,x.concat(entry.getValue())))
//                    {
//                        Set<Character> tempset = new HashSet<Character>(Arrays.asList(ArrayUtils.toObject(entry.getValue().toCharArray())));
//                        Set<Character> sset = new HashSet<>(Arrays.asList(ArrayUtils.toObject(s.toCharArray())));
//                        sset.removeAll(tempset);
//                        Character[] sarr = new Character[sset.size()];
//                        sset.toArray(sarr);
//                        temp = new String(ArrayUtils.toPrimitive(sarr));
//                        flag = 1;
//                        relations.add(x.concat(entry.getValue()));
//                    }
//                    if(flag==1)
//                    {
//                        relations.remove(s);
//                        relations.add(temp);
//                    }
//                }
//            }
//        }
//
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
//
//        System.out.println(relations.toString());
//        return relations;
//
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
