package edu.virginia.cs.hw1;

import java.util.HashMap;

public class HuntingtonApportionment extends HashMap<String, Integer> {

    public static HashMap<String, Integer> apportionment(HashMap<String, Integer> dataMap, Integer representatives) {

        HashMap<String, Integer> repMap = new HashMap<>();
        HashMap<String, Double> priorityMap = new HashMap<>();
        Integer repsLeft = representatives;
        Integer stateCount = 0;

        //Initializes repMap w/ every state having 1 rep & initializes priorityMap w/ data
        for(String keys : dataMap.keySet()) {

            repMap.put(keys, 1);
            stateCount++;
            repsLeft--;

            double priority = dataMap.get(keys) / (Math.sqrt(2));
            priorityMap.put(keys, priority);

        }

        if(representatives < stateCount) {
            throw new RuntimeException("You need at least as many representatives as states");
        }

        String prioState = "";

        //Allocate reps based on priority until all reps are allocated
        while(repsLeft > 0) {

            //Determines highest priority state
            for(String keys : priorityMap.keySet()) {

                if(prioState.equals("") || (priorityMap.get(keys) > priorityMap.get(prioState))) {
                    prioState = keys;
                }

            }

            //Finds current reps of priostate
            Integer reps = repMap.get(prioState);

            //Adds one rep to priostate
            repMap.put(prioState, (reps + 1));
            repsLeft--;

            reps = repMap.get(prioState);

            //Re-calculates priority and updates priorityMap
            double priority = dataMap.get(prioState) / (Math.sqrt(reps * (reps + 1)));
            priorityMap.put(prioState, priority);

        }

        return repMap;
    }
}
