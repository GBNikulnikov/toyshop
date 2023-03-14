package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AllToys {

    public Map<Integer, Toy> listToys = new HashMap<>();

    public Map<Integer, Toy> getListToys() {
        return listToys;
    }

    public void setListToys(Map<Integer, Toy> listToys) {
        this.listToys = listToys;
    }

    public void printList(){
        Set<Integer> keys = listToys.keySet();
        for (Integer k: keys) {
            System.out.printf("id: ");
        }

        //        for (int i=0; i<listToys.size(); i++)
//        {
//
//            Блок операторов;
//        }
    }
    public void saveListToysJSON(){

    }

}

