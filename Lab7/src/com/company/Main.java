package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\DELL\\IdeaProjects\\CrossPlatformProgramming\\CrossPlatformProgramming\\Lab7\\src\\Resources\\graph1.txt"));

        String sCurrentLine;
        Map<Integer, Set<Connection>> graphMap = new HashMap<>();

        for (int i = 0; (sCurrentLine = br.readLine()) != null; ++i) {
            String[] arrOfStr = sCurrentLine.split("=");
            Set<Connection> l = new HashSet<>();

            for(int s = 2; s < arrOfStr.length; s+=3){
                l.add(new Connection( Integer.parseInt(arrOfStr[s]), Integer.parseInt(arrOfStr[s+1])));
            }

            graphMap.put(Integer.parseInt(arrOfStr[0]), l);
        }
        System.out.println("Input graph looks like this:");

        Graph.printGraph(graphMap);
        Scanner scanner = new Scanner(System.in);

            System.out.println("Run multithreading?(y/n): ");
            if(scanner.next().equals("n")){
                Graph graph = new Graph(new HashMap<>(graphMap));
                Dijkstra start0 = new Dijkstra(0, graph);
                Thread Dijkstra0 = new Thread(start0);
                Monitor mest0 = new Monitor(Dijkstra0);
                mest0.start();
                mest0.join();

                double timeElapsed = System.currentTimeMillis();
                Dijkstra0.start();
                Dijkstra0.join();
                timeElapsed -= System.currentTimeMillis();
                System.out.println(start0.result.size());
                System.out.println("Elapsed time - " + Math.abs(timeElapsed) + " milliseconds.");
                System.out.println("Result graph: ");
                Graph.printGraph(start0.result);
            } else {
                Graph graph = new Graph(new HashMap<>(graphMap));

                Dijkstra dijkstra1 = new Dijkstra(0, graph);
                Dijkstra dijkstra2 = new Dijkstra(1, graph);

                Thread dijkstraThread1 = new Thread(dijkstra1);
                Thread dijkstraThread2 = new Thread(dijkstra2);

                Monitor monitor1 = new Monitor(dijkstraThread1);
                Monitor monitor2 = new Monitor(dijkstraThread2);

                monitor1.start();
                monitor1.join();
                monitor2.start();
                monitor2.join();

                double timeElapsed = System.currentTimeMillis();

                dijkstraThread1.start();
                dijkstraThread2.start();
                dijkstraThread1.join();
                dijkstraThread2.join();

                timeElapsed -= System.currentTimeMillis();
                System.out.println("Elapsed time: " + Math.abs(timeElapsed));

                System.out.println("\nFirst thread graph  will be: ");
                Graph.printGraph(dijkstra1.result);
                System.out.println("\nSecond thread graph will be: ");
                Graph.printGraph(dijkstra2.result);

                List<Connection> search = new ArrayList<>()     ;
                dijkstra1.result.forEach((k,v) -> {
                    search.addAll(graphMap.get(k));
                });

                Comparator<Connection> comparatorName = (a, b) -> Integer.compare(a.weight, b.weight);
                Collections.sort(search, comparatorName);

                for(int i = 0; i < search.size(); ++i){
                    if(!dijkstra1.result.containsKey(search.get(i).nameTo)){
                        List<Connection> connectSearch = new ArrayList<>(graphMap.get(search.get(i).nameTo));
                        for(int ns = 0; ns < connectSearch.size(); ++ns){
                            if(connectSearch.get(ns).weight == search.get(i).weight ) {
                                dijkstra1.result.get(connectSearch.get(ns).nameTo).add(new Connection(search.get(i).nameTo, search.get(i).weight));
                                dijkstra2.result.get(search.get(i).nameTo).add(new Connection(connectSearch.get(ns).nameTo, search.get(i).weight));
                                break;
                            }
                        }
                        break;
                    }
                }

                System.out.println("\nFinal graph: ");
                Graph.printGraph(dijkstra1.result);
                Graph.printGraph(dijkstra2.result);
            }
    }
}
