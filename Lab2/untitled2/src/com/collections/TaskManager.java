package com.collections;

import java.io.*;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskManager {
    private static List<Car> firstFileData;
    private static List<Car> secondFileData;

    private static final File firstFile = new File(System.getProperty("user.dir")+"\\data.txt");
    private static final File secondFile = new File(System.getProperty("user.dir")+"\\data2.txt");

    public void task1(int n) {
        if (firstFileData == null) {
            firstFileData = readData(firstFile);
        }
        Map<Integer, List<String>> map = new HashMap<>();

        for (var car : firstFileData) {
            if (!map.containsKey(car.getspeed())) {
                map.put(car.getspeed(), new LinkedList<>());
            }
            map.get(car.getspeed()).add(String.valueOf(car));
        }


        System.out.println("Map: ");
        System.out.println(map);



        System.out.println("Result: ");
        map.forEach((key, value) -> System.out.println("Speed = " + key + " Cars: "+ value.stream().limit(n).toList()));


    }

    public void task2() {
        if (firstFileData == null) {
            firstFileData = readData(firstFile);
        }
        Map<Integer, List<String>> map = new HashMap<>();

        for (var car : firstFileData) {
            if (!map.containsKey(car.getspeed())) {
                map.put(car.getspeed(), new LinkedList<>());
            }
            map.get(car.getspeed()).add(String.valueOf(car));
        }


        System.out.println("Map: ");
        System.out.println(map);

        List<String> carModels = Arrays.asList("Mazda");

        System.out.println("I want to delete model "+ carModels +" from my map");


       for (var key : map.keySet()) {

            for (int i = 0; i < map.get(key).size(); ++i) {
                    if (carModels.contains(firstFileData.stream().map(Car::getmodel) )) {
                    System.out.println(firstFileData.stream().map(Car::getmodel));
                    map.get(key).remove(i);
                    --i;
                }
            }
        }
       //
        for (var key : map.keySet()) {
           int i = map.get(key).size() - 1;
            for (; i < map.get(key).size();) {
                   map.get(key).remove(i);
                    break;
            }
            break;
        }

       Set<Integer> elementsToDelete = new HashSet<>();
        for (Integer key : map.keySet()) {
            if (map.get(key).isEmpty()) {
                elementsToDelete.add(key);
            }
        }
        for(Integer key : elementsToDelete) {
            map.remove(key);
        }

        System.out.println("New map: ");
        System.out.println(map);

    }

    public void task3(int min,int max) {
        if (firstFileData == null) {
            firstFileData = readData(firstFile);
        }
        if (secondFileData == null) {
            secondFileData = readData(secondFile);
        }
        List<Car> sharedList = Stream.concat(firstFileData.stream(), secondFileData.stream()).collect(Collectors.toList());

        Collections.sort(sharedList, new Comparator<Car>() {
            @Override
            public int compare(final Car object1, final Car object2) {
                return object1.getmodel().compareTo(object2.getmodel());
            }
        });
        Collections.reverse(sharedList);
        System.out.println(sharedList);

        int count = 0;

        for (var car : sharedList) {
            if (car.getprice() >= min && car.getprice() <= max) {
                ++count;
            }
        }
        System.out.println("Number of cars in price range " + min + "-" + max + " = " + count);
    }

    private static List<Car> readData(File file) {
        int lineNumber = 0;
        List<Car> list = new LinkedList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                ++lineNumber;
                String[] data = line.split(",");
                list.add(new Car(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2])));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            System.exit(1);
        } catch (NumberFormatException ex) {
            System.out.printf("Sorry, but you have invalid data in %d line. Fix it and try again", lineNumber);
            System.exit(1);
        } catch (IOException ex) {
            System.out.println("Sorry, but you get unexpected error while reading file");
            System.exit(1);
        }

        return list;
    }

}



