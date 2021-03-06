package util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Rui Guo
 *
 * Loading data utility tool
 */
public class JsonDataLoader {
    private static Logger logger = LogManager.getLogger(JsonDataLoader.class);
    private static JsonObject testData;
    private static JsonObject expectContext;
    private static Object locker = new Object();
    //todo: The function that needs to be added is only allow create one json instance
    static {
        try {
            testData = JsonParser.parseReader(new FileReader(Constants.JSON_TEST_DATA_PATH)).getAsJsonObject();
            expectContext = JsonParser.parseReader(new FileReader(Constants.JSON_EXPECT_CONTENT_PATH)).getAsJsonObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    /**
     * Load data from Json object.
     *
     * @param method
     * @return Object[][]
     */
    //Todo: Need do first -- supdate the load data method becasue no JsonArray right now.
    @DataProvider(name = "testData")
    public static Object[][] loadData(Method method) {
        String methodName = method.getName();
        JsonObject jsonObject = testData.getAsJsonObject(methodName);
        Object[][] testData = new Object[1][1];
        testData[0][0] = jsonObject;
        logger.info("Load test data from json file to method: " + methodName);
        return testData;
    }

    /**
     * Load data from Json object array
     *
     * @param method
     * @return
     */
    @DataProvider(name = "testDataArray")
    public static Object[][] loadDataArray(Method method) {
        String methodName = method.getName();
        JsonArray jsonArray = testData.getAsJsonArray(methodName);
        Object[][] testDataList = new Object[jsonArray.size()][1];
        for (int i = 0; i < jsonArray.size(); i++) {
            testDataList[i][0] = jsonArray.get(i);
        }
        logger.info("Load test data from json file to method: " + methodName);
        return testDataList;
    }

    /**
     *  Load data for prepare data
     *
     * @return
     */
    public static JsonArray prepareData() {
        //load Json object array first
        JsonArray jsonArray = testData.getAsJsonArray("prepare_data");
        //add the customer and account number after save all of data
        logger.info("Load prepare data from json file.");
        return jsonArray;
    }

    public static JsonArray getDataArray(String objectName) {
        return testData.getAsJsonArray(objectName);
    }

    public static JsonObject getDataObject(String objectName) {
        return testData.getAsJsonObject(objectName);
    }

    /**
     * Update the value and wirte to Json file.
     *
     * @param jsonArrayName
     * @param map
     */
    public static void updateDataToFile(String jsonArrayName, HashMap<String, String> map, int index) {
        JsonArray jsonArray = testData.getAsJsonArray(jsonArrayName);
        JsonObject update = (JsonObject) jsonArray.get(index);
        synchronized (locker) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                update.addProperty(entry.getKey(), entry.getValue());
            }
            try {
                Writer out = new FileWriter(Constants.JSON_TEST_DATA_PATH);
                out.write(testData.toString());
                out.flush();
                logger.info("Update data to Json File");
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e);
            }
        }
    }

    /**
     *  Update value of JsonObject and write to the file.
     *
     * @param target The JsonObject that need to be update
     * @param map The new value.
     */
    public static void updateDataToFile(JsonObject target, HashMap<String, String> map) {
        synchronized (locker) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                target.addProperty(entry.getKey(), entry.getValue());
            }
            try {
                Writer out = new FileWriter(Constants.JSON_TEST_DATA_PATH);
                out.write(testData.toString());
                out.flush();
                logger.info("Update data to Json File");
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e);
            }
        }
    }

    /**
     *  Update value of JsonObject and write to the file.
     *
     * @param ObjectName The Object name that need to be update
     * @param map The new value.
     */
        public static void updateDataToFile(String ObjectName, HashMap<String, String> map) {
            JsonObject target = getDataObject(ObjectName);
            synchronized (locker) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                target.addProperty(entry.getKey(), entry.getValue());
            }
            try {
                Writer out = new FileWriter(Constants.JSON_TEST_DATA_PATH);
                out.write(testData.toString());
                out.flush();
                logger.info("Update data to Json File");
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e);
            }
        }
    }

    /**
     * get the expect string from json file.
     *
     * @param clazz
     * @param key
     * @return
     */
    public static String getExpectContent(Class clazz, String key) {
        JsonObject jsonObject = expectContext.getAsJsonObject(clazz.getSimpleName());
        return jsonObject.get(key).getAsString();
    }

    /**
     * load test account from json file
     * @return
     */
    public static String[] getTestAccouts() {
        JsonArray data = JsonDataLoader.getDataArray("prepare_data");
        String[] accountList = new String[2];
        for (int i = 0; i < data.size(); i++) {
            JsonObject object = (JsonObject) data.get(i);
            String account = object.get("accountId").getAsString();
            accountList[i] = account;
        }
        return accountList;
    }
}
