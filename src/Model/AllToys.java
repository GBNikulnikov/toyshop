package Model;

import java.util.HashMap;
import java.util.Map;

public class AllToys {

    public Map<Integer, Toy> listToys = new HashMap<>();

    public Map<Integer, Toy> getListToys() {
        return listToys;
    }

    public void setListToys(Map<Integer, Toy> listToys) {
        this.listToys = listToys;
    }

    public void printList(){
//        Set<Integer> keys = listToys.keySet();
//        for (Integer k: keys) {
//            System.out.printf("id: ");
//        }

        for (Map.Entry<Integer, Toy> item : listToys.entrySet()){

            System.out.printf("Id: %d  Игрушка: %s \n", item.getKey(), item.getValue());
        }
    }
    public void saveListToysJSON(){

    }

}

