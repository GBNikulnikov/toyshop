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

        for(Toy toy : listToys){
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
        for(Toy toy : listToys)
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
        for(Toy toy : listToys)
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
                };
                String st = String.format("Id: %d; Название: %s; Кол-во: %d; Вес(%%): %d",
                        toy.getId(), toy.getName(), toy.getQuantity(), toy.getWeightFactor());
                System.out.println(st);
                saveList();
            }
    }

    public void deleteToy(int idToy) throws IOException {
        for(Toy toy : listToys)
            if (idToy == toy.getId()) {
                listToys.remove(toy);
                saveList();
                break;
            }
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

