package com.company;

import java.util.Map;
import java.util.Set;

public class Graph{

    Map<Integer, Set<Connection>> graph;

    public Graph( Map<Integer, Set<Connection>> data){
        graph = data;
    }
    synchronized Set<Connection> Mark(int num) {
        if (!graph.containsKey(num)) {
            return null;
        } else {
            Set<Connection> r = graph.get(num);
            graph.remove(num);
            return r;
        }
    }

    public static void printGraph(Map<Integer, Set<Connection>> graph){
        StringBuilder list = new StringBuilder();
        graph.forEach((k,v) -> {
            list.append(k).append(": ");
            v.forEach(connection -> {
                list.append("(").append(connection.nameTo).append(", ").append(connection.weight).append("), ");
            });
            list.append('\n');
        });
        list.deleteCharAt(list.length()-1);
        System.out.println(list);
    }
}