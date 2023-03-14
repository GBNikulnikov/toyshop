package Core;

import Model.AllToys;
import Model.Toy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
public class ViewTS {

    public enum Commands {
        NONE,
        LIST,
        READ,
        CREATE,
        UPDATE,
        DELETE,
        HELP,
        EXIT
    }

    String FILEJSON = "data/alltoys.json";
    AllToys allToys = new AllToys();
    public void run() {
        Commands com = Commands.NONE;
        showHelp();
        while (true) {
            try {
                String command = prompt("Введите команду: ");
                com = Commands.valueOf(command.toUpperCase());
                if (com == Commands.EXIT) return;
                switch (com) {
                    case CREATE -> create();
                    case READ -> read();
                    case UPDATE -> update();
                    case LIST -> list();
                    case HELP -> showHelp();
                    case DELETE -> delete();
                }
            } catch (Exception ex) {
                System.out.println("Неверный ввод");
            }
        }
    }



    private void read() throws Exception {
        String id = prompt("Идентификатор пользователя: ");
//        User user_ = userController.readUser(id);
//        System.out.println(user_);
    }

    private void update() throws Exception {
        String userid = prompt("Идентификатор пользователя: ");
        String field_name = prompt("Какое поле изменить (FIO,NAME,TELEPHONE): ").toUpperCase();
//        String param = null;
//        if (Fields.valueOf(field_name) == Fields.TELEPHONE) {
//            param = catchTelephone(param);
//            if (param == null) {
//                return;
//            }
//        } else {
//            param = prompt("Введите новое значение: ");
//        }
//        User _user = userController.readUser(userid);
//        userController.updateUser(_user, Fields.valueOf(field_name.toUpperCase()), param);
    }

    private void delete() throws Exception {
        String userid = prompt("Идентификатор пользователя: ");
//        User _user = userController.readUser(userid);
//        userController.deleteUser(_user);
    }


    private void list() throws Exception {

        readJson(FILEJSON, allToys);
        allToys.printList();
//        System.out.println("");
    }

    private void create() throws Exception {
        int id = 0;
        int quantity = 0;
        int weightFactor = 0;
        try
            { id = Integer.parseInt(prompt("Id игрушки: ")); }
        catch (NumberFormatException nfe)
            { System.out.println("NumberFormatException: " + nfe.getMessage()); }
        String name = prompt("Название: ");
        try {
            quantity = Integer.parseInt(prompt("Количество: "));
            weightFactor = Integer.parseInt(prompt("Вес(%) в розыгрыше: "));
        }
        catch (NumberFormatException nfe)
        { System.out.println("NumberFormatException: " + nfe.getMessage()); }
        Toy newToy = new Toy(id, name, quantity, weightFactor);
        allToys.listToys.put(id, newToy);
        writeJson(FILEJSON, allToys);
    }

    private void showHelp() {
        System.out.println("Список команд:");
        for (Commands c : Commands.values()) {
            System.out.println(c);
        }
    }
    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }


    public void writeJson(String fileName, AllToys at) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        FileWriter writer = new FileWriter(fileName);
        String gsonStr = gson.toJson(at.listToys);
        writer.write(gsonStr);
        writer.close();
    }

    public void readJson(String fileName, AllToys at) throws IOException {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Reader reader = Files.newBufferedReader(Paths.get(fileName));
         at.listToys = gson.fromJson(reader, at.listToys.getClass());
    }

}

