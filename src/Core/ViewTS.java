package Core;

import Model.AllToys;
import Model.Toy;

import java.io.IOException;
import java.util.Scanner;

public class ViewTS {

    AllToys allToys = new AllToys();

    public void run() throws IOException {
        allToys.listToys = allToys.readJson(allToys.FILEJSON);
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
                    case DRAW -> draw();
                    case HELP -> showHelp();
                    case DELETE -> delete();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println("Неверный ввод");
            }
        }
    }

    private void read() throws Exception {
        int id = promptInt("Id игрушки: ");
        allToys.readToy(id);

//        User user_ = userController.readUser(id);
//        System.out.println(user_);
    }

    private void update() throws Exception {
        int id = promptInt("Id игрушки: ");
        int fieldNumber = promptInt("Какое поле изменить (1-Название,2-Кол-во, 3-вес(%)): ");
        String param = prompt("Введите новое значение: ");
        allToys.updateToy(id, fieldNumber, param);
    }

    private void delete() throws Exception {
        int id = promptInt("Id игрушки: ");
        allToys.deleteToy(id);
    }

    private void list() throws Exception {
        allToys.printList();
//        System.out.println("");
    }

    private void create() throws IOException {
        int id = 0;
        int quantity = 0;
        int weightFactor = 0;
        id = promptInt("Id игрушки: ");
        String name = prompt("Название: ");
        quantity = promptInt("Количество: ");
        weightFactor = Integer.parseInt(prompt("Вес(%) в розыгрыше: "));
        Toy newToy = new Toy(id, name, quantity, weightFactor);
        allToys.listToys.add(newToy);
        allToys.saveList();

    }

    private void showHelp() {
        System.out.println("Список команд:");
        for (Commands c : Commands.values()) {
            System.out.println(c);
        }
    }

    private void draw() throws IOException {
        System.out.println("!!!Розыгрыш призов!!!");
        String name = prompt("Имя участника: ");
        allToys.drawPrize(name);
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private int promptInt(String inputPrompt) {
        int intValue = 0;
        try {
            intValue = Integer.parseInt(prompt(inputPrompt));
        } catch (NumberFormatException nfe) {
            System.out.println("Неверный формат ввода числа: " + nfe.getMessage());
        }
        return intValue;
    }

    public enum Commands {
        NONE,
        LIST,
        READ,
        CREATE,
        UPDATE,
        DELETE,
        DRAW,
        HELP,
        EXIT
    }

}

