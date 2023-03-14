package FilesOperaton;

import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class jsonToys {

    public void writeJson(String fileName) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        FileWriter writer = new FileWriter(fileName);
        String gsonStr = gson.toJson(familyTree);
        writer.write(gsonStr);
        writer.close();
    }

    public ArrayList<Person> readJson(String fileName) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Reader reader = Files.newBufferedReader(Paths.get(fileName));
        ArrayList<Person> fam = gson.fromJson(reader, familyTree.getClass());
        return fam;
    }

}
