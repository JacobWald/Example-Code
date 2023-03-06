package edu.virginia.cs.hw3;

import java.util.*;

public class HuntingtonHillApportionmentStrategy extends ApportionmentStrategy {
    private List<State> stateList;
    private int repsLeft;
    private double priority;
    private Map<State, Double> priorityMap;
    private Apportionment apportionment;

    @Override
    public Apportionment getApportionment(List<State> stateList, int representatives) {

        initializeFields(stateList, representatives);
        initializeMaps();
        completeApportionment();
        return apportionment;

    }

    private void completeApportionment() {

        Optional<Map.Entry<State, Double>> topPrioEntry;
        while(repsLeft > 0) {

            topPrioEntry = priorityMap.entrySet().stream()
                    .max((e1, e2) -> (Double.compare(e2.getValue(), e1.getValue())));

            State topPrioState = topPrioEntry.get().getKey();

            addRepresentative(topPrioState);

            setPriority(topPrioState);
            addToPriorityMap(topPrioState);
        }
    }

    private void initializeMaps() {
        for(State state : stateList) {
            addRepresentative(state);

            setPriority(state);

            addToPriorityMap(state);

        }
    }

    private void addToPriorityMap(State state) {
        priorityMap.put(state, priority);
    }

    private void addRepresentative(State state) {
        apportionment.addRepresentativesToState(state, 1);
        repsLeft--;
    }

    private void setPriority(State state) {
        int stateReps = apportionment.getRepresentativesForState(state);
        priority = state.getPopulation() / (Math.sqrt(stateReps * (stateReps + 1)));
    }

    private void initializeFields(List<State> stateList, int representatives) {
        this.stateList = stateList;
        repsLeft = representatives;
    }
}