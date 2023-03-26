package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class AllToys {

    public ArrayList<Toy> listToys = new ArrayList<>();
    String FILEJSON = "data/alltoys.json";

    public ArrayList<Toy> getListToys() {
        return listToys;
    }

    public void setListToys(ArrayList<Toy> listToys) {
        this.listToys = listToys;
    }

    public void printList() throws IOException {
        listToys = readJson(FILEJSON);

        for (Toy toy : listToys) {
            String nam = toy.getName();
            int idt = toy.getId();
            int q = toy.quantity;
            int w = toy.weightFactor;
            String st = String.format("Id: %d; Название: %s; Кол-во: %d; Вес(%%): %d", idt, nam, q, w);
            System.out.println(st);
            saveList();
        }
    }

    public void readToy(final int idToy) throws IOException {
        for (Toy toy : listToys)
            if (idToy == toy.getId()) {
                String nam = toy.getName();
                int idt = toy.getId();
                int q = toy.quantity;
                int w = toy.weightFactor;
                String st = String.format("Id: %d; Название: %s; Кол-во: %d; Вес(%%): %d", idt, nam, q, w);
                System.out.println(st);
            }
    }

    public void updateToy(int idToy, int fieldNumber, String newValue) throws IOException {
        for (Toy toy : listToys)
            if (idToy == toy.getId()) {
                switch (fieldNumber) {
                    case (1):
                        toy.setName(newValue);
                        break;
                    case (2):
                        toy.setQuantity(Integer.parseInt(newValue));
                        break;
                    case (3):
                        toy.setWeightFactor(Integer.parseInt(newValue));
                        break;
                }
                ;
                String st = String.format("Id: %d; Название: %s; Кол-во: %d; Вес(%%): %d",
                        toy.getId(), toy.getName(), toy.getQuantity(), toy.getWeightFactor());
                System.out.println(st);
                saveList();
            }
    }

    public void deleteToy(int idToy) throws IOException {
        for (Toy toy : listToys)
            if (idToy == toy.getId()) {
                listToys.remove(toy);
                saveList();
                break;
            }
    }

    public void drawPrize() throws IOException {
//        List<Toy> sortedList = listToys.stream()
//            .sorted(Comparator
//                .comparingInt(Toy::getWeightFactor)).toList();
//        sortedList.forEach(System.out::println);
//        sortedList.ins
        int countSteps = listToys.size();
        int countSteps1 = countSteps;
        int aSteps[][] = new int[countSteps][3];
        int sumWeight = 0;
        int min = -1;
        int max = 0;
        int idToy = 0;
        int currentIndex = 0;
        Random rnd = new Random();

        for (Toy toy : listToys) {
            sumWeight += toy.getWeightFactor();
        }
        for (int i = 0; i < listToys.size(); i++) {
            aSteps[i][0] = listToys.get(i).getId();
            aSteps[i][1] = min + 1;
            aSteps[i][2] = min + listToys.get(i).getWeightFactor();
            min = aSteps[i][2];
        }
        int randomFactor = rnd.nextInt(sumWeight - 1);
        for (int i = 0; i < countSteps; i++) {
            if (randomFactor >= aSteps[i][1] && randomFactor <= aSteps[i][2] ) {
                idToy = aSteps[i][0];
                break;
            }
        }
        Toy currentToy = getToyById(idToy);
        currentIndex = listToys.indexOf(currentToy);
        int currentQuantity = listToys.get(currentIndex).getQuantity();
        if (currentQuantity > 1){
            listToys.get(currentIndex).setQuantity(currentQuantity - 1);
        }
        else {
            listToys.remove(currentIndex);
        }
        saveList();
    }

    private Toy getToyById(int idToy) {
        for (Toy toy : listToys)
            if (idToy == toy.getId()) {
                return toy;
            }
        return null;
    }

    public void saveList() throws IOException {
        writeJson(FILEJSON);
    }

    public void writeJson(String fileName) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer = new FileWriter(fileName);
        String gsonStr = gson.toJson(listToys);
        writer.write(gsonStr);
        writer.close();
    }

    public ArrayList<Toy> readJson(String fileName) throws IOException {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Reader reader = Files.newBufferedReader(Paths.get(fileName));
        Type type = new TypeToken<ArrayList<Toy>>() {
        }.getType(); // Определяем тип читаемых данных
        ArrayList<Toy> read = gson.fromJson(reader, type);
        return read;
    }
}

