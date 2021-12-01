package com.company;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Dijkstra implements Runnable {

    int start;
    Graph graph;
    Map<Integer, Set<Connection>> result;
    ReentrantLock lock = new ReentrantLock();

    Dijkstra(int s, Graph data) {
        start = s;
        graph = data;
        result = new HashMap<>();
    }

    public class ComparatorConnection implements java.util.Comparator<Connection> {
        @Override
        public int compare(Connection o1, Connection o2) {
            return Integer.compare(o1.weight, o2.weight);
        }
    }

    @Override
    public void run() {
        result.put(start, new HashSet<>());
        List<Connection> search;
        Set<Connection> f = graph.Mark(start);

        if (f == null) {
            return;
        } else {
            search = new ArrayList<>(f);
        }

        Collections.sort(search, new ComparatorConnection());

        for (int p = 0; p < search.size(); ++p) {
            if (!result.containsKey(search.get(p).nameTo)) {
                Set<Connection> n = graph.Mark(search.get(p).nameTo);
                if (n != null) {
                    List<Connection> connectSearch = new ArrayList<>(n);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.lock();
                    try {
                        for (int ns = 0; ns < connectSearch.size(); ++ns) {
                            if (connectSearch.get(ns).weight == search.get(p).weight) {
                                var temp = connectSearch.get(ns).nameTo;
                                var t = result.containsKey(Integer.valueOf(temp));
                                if (!t)
                                    break;
                                var connections = result.get(Integer.valueOf(temp));
                                if (connections == null)
                                    break;
                                connections.add(new Connection(search.get(p).nameTo, search.get(p).weight));
                                result.put(search.get(p).nameTo, new HashSet<>());
                                result.get(search.get(p).nameTo).add(new Connection(connectSearch.get(ns).nameTo, search.get(p).weight));
                                break;
                            }
                        }
                        search.addAll(n);
                        Collections.sort(search, new ComparatorConnection());
                        p = 0;
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
    }
}
