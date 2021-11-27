package com.company;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LabManager implements ILabManager {
    private List<Car> dataFromFirstFile;
    private List<Car> dataFromSecondFile;
    private List<String> carModelsFromFile;

    private final File firstFileWithCarData = new File(System.getProperty("user.dir")+"\\dataFile1.txt");
    private final File secondFileWithCarData = new File(System.getProperty("user.dir")+"\\dataFile2.txt");
    private final File fileWithCarModels = new File(System.getProperty("user.dir")+"\\carModels.txt");

    Map<Integer, List<Car>> mapOfCars;

    LabManager() {
        dataFromFirstFile = readCarDataFromFile(firstFileWithCarData);
        dataFromSecondFile = readCarDataFromFile(secondFileWithCarData);

        carModelsFromFile = readStringDataFromFile(fileWithCarModels);

        mapOfCars = new HashMap<Integer, List<Car>>();
    }

    public void firstTask(int n) {
        for (var car : dataFromFirstFile) {
            if (!mapOfCars.containsKey(car.getMaxSpeed())) {
                mapOfCars.put(car.getMaxSpeed(), new LinkedList<Car>());
            }
            mapOfCars.get(car.getMaxSpeed()).add(car);
        }

        printNFirstCars(n);
    }

    private void printNFirstCars(int n) {
        mapOfCars.forEach((key, value) -> System.out.println("Speed = " + key + " @ Cars: " + value.stream().limit(n).toList()));
    }

    public void secondTask() {
        for (var key : mapOfCars.keySet()) {
            for (int i = 0; i < mapOfCars.get(key).size(); ++i) {
                if (carModelsFromFile.contains(mapOfCars.get(key).get(i).getModel())) {
                    mapOfCars.get(key).remove(i);
                    --i;
                }
            }
        }

        Set<Integer> elementsToDelete = new HashSet<>();
        for (Integer key : mapOfCars.keySet()) {
            if (mapOfCars.get(key).isEmpty()) {
                elementsToDelete.add(key);
            }
        }
        for(Integer key : elementsToDelete) {
            mapOfCars.remove(key);
        }

        System.out.println("New map: ");
        System.out.println(mapOfCars);
    }

    public void thirdTask(int minPrice, int maxPrice) {

        List<Car> sharedList = Stream.concat(dataFromFirstFile.stream(), dataFromSecondFile.stream()).collect(Collectors.toList());

        Collections.sort(sharedList, new Comparator<Car>() {
            @Override
            public int compare(final Car object1, final Car object2) {
                return object1.getModel().compareTo(object2.getModel());
            }
        });
        Collections.reverse(sharedList);
        System.out.println(sharedList);

        int count = 0;

        for (var car : sharedList) {
            if (car.getPrice() >= minPrice && car.getPrice() <= maxPrice) {
                ++count;
            }
        }
        System.out.println("Number of cars in price range " + minPrice + "-" + maxPrice + " = " + count);
    }

    private List<String> readStringDataFromFile(File file) {
        List<String> listOfCarModels = new LinkedList<String>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                listOfCarModels.add(line);
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }

        return listOfCarModels;
    }

    private List<Car> readCarDataFromFile(File file) {
        List<Car> listOfCars = new LinkedList<Car>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                String[] carData = line.split(",");
                listOfCars.add(new Car(carData[0], Integer.parseInt(carData[1]), Integer.parseInt(carData[2])));

            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }

        return listOfCars;
    }
}
