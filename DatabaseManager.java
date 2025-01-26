import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DatabaseManager {
    private int sizeOfField;
    private int numOfFarmers;
    private int numOfRabbits;
    private long durationTime;
    private static final String FILE_PATH = "history.json";

    public DatabaseManager(int sizeOfField, int numOfFarmers, int numOfRabbits, long durationTime){
        this.sizeOfField = sizeOfField;
        this.numOfFarmers = numOfFarmers;
        this.numOfRabbits = numOfRabbits;
        this.durationTime = durationTime;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sizeOfField", sizeOfField);
        jsonObject.put("numOfFarmers", numOfFarmers);
        jsonObject.put("numOfRabbits", numOfRabbits);
        jsonObject.put("durationTime", durationTime);
        return jsonObject;
    }

    public void saveSimulationData(){
        JSONObject jsonObject = this.toJson();
        JSONArray jsonArray = new JSONArray();

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileReader reader = new FileReader(FILE_PATH)) {
            jsonArray = (JSONArray) new JSONParser().parse(reader);
        } catch (IOException | ParseException e) {
            System.err.println("Error reading existing data: " + e.getMessage());
        }

        jsonArray.add(jsonObject);

        try (FileWriter fileWriter = new FileWriter(FILE_PATH)) {
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
