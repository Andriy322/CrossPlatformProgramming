package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\DELL\\IdeaProjects\\CrossPlatformProgramming\\CrossPlatformProgramming\\Lab7_java\\src\\Resources\\graph1.txt"));

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
                Prima start0 = new Prima(0, graph);
                Thread prim0 = new Thread(start0);
                Monitor mest0 = new Monitor(prim0);
                mest0.start();
                mest0.join();

                double timeElapsed = System.currentTimeMillis();
                prim0.start();
                prim0.join();
                timeElapsed -= System.currentTimeMillis();
                System.out.println(start0.result.size());
                System.out.println("Elapsed time - " + Math.abs(timeElapsed) + " milliseconds.");
                System.out.println("Result graph: ");
                Graph.printGraph(start0.result);
            } else {
                Graph graph = new Graph(new HashMap<>(graphMap));

                Prima prima1 = new Prima(0, graph);
                Prima prima2 = new Prima(1, graph);

                Thread primaThread1 = new Thread(prima1);
                Thread primaThread2 = new Thread(prima2);

                Monitor monitor1 = new Monitor(primaThread1);
                Monitor monitor2 = new Monitor(primaThread2);

                monitor1.start();
                monitor1.join();
                monitor2.start();
                monitor2.join();

                double timeElapsed = System.currentTimeMillis();

                primaThread1.start();
                primaThread2.start();
                primaThread1.join();
                primaThread2.join();

                timeElapsed -= System.currentTimeMillis();
                System.out.println("Elapsed time: " + Math.abs(timeElapsed));

                System.out.println("\nFirst thread graph  will be: ");
                Graph.printGraph(prima1.result);
                System.out.println("\nSecond thread graph will be: ");
                Graph.printGraph(prima2.result);

                List<Connection> search = new ArrayList<>()     ;
                prima1.result.forEach((k,v) -> {
                    search.addAll(graphMap.get(k));
                });

                Comparator<Connection> comparatorName = (a, b) -> Integer.compare(a.weight, b.weight);
                Collections.sort(search, comparatorName);

                for(int i = 0; i < search.size(); ++i){
                    if(!prima1.result.containsKey(search.get(i).nameTo)){
                        List<Connection> connectSearch = new ArrayList<>(graphMap.get(search.get(i).nameTo));
                        for(int ns = 0; ns < connectSearch.size(); ++ns){
                            if(connectSearch.get(ns).weight == search.get(i).weight ) {
                                    prima1.result.get(connectSearch.get(ns).nameTo).add(new Connection(search.get(i).nameTo, search.get(i).weight));
                                    prima2.result.get(search.get(i).nameTo).add(new Connection(connectSearch.get(ns).nameTo, search.get(i).weight));
                                break;
                            }
                        }
                        break;
                    }

                }

                System.out.println("\nFinal graph: ");
                Graph.printGraph(prima1.result);
                Graph.printGraph(prima2.result);
            }
    }
}
