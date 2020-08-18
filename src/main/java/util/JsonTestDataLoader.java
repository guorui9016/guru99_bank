package util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Rui Guo
 *
 * Loading data utility tool
 */

public class JsonTestDataLoader {
    private static Logger logger;
    private static JsonObject jsonObject;
    static {
        try {
            jsonObject = JsonParser.parseReader(new FileReader(Constants.JSON_TEST_DATA_PATH)).getAsJsonObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    /**
     * Load data from Json file.
     *
     * @param method
     * @return Object[][]
     */
    @DataProvider(name = "testData")
    public static Object[][] loadTestData(Method method) {
        logger = LogManager.getLogger(JsonTestDataLoader.class);
        String methodName = method.getName();
        JsonArray jsonArray = jsonObject.getAsJsonArray(methodName);
        Object[][] testDataList = new Object[jsonArray.size()][1];
        for (int i = 0; i < jsonArray.size(); i++) {
            testDataList[i][0] = jsonArray.get(i);
        }
        logger.info("Load test data from json file to method: " + methodName);
        return testDataList;
    }

    public static JsonObject getJsonObject(String objectName) {
        JsonArray jsonArray = jsonObject.getAsJsonArray(objectName);
        return (JsonObject)jsonArray.get(0);
    }

    /**
     * Update the value and wirte to Json file.
     *
     * @param objectName
     * @param map
     */
    public static void updataJsonObject(String objectName, HashMap<String, String> map) {
        JsonArray jsonArray = jsonObject.getAsJsonArray(objectName);
        JsonObject update = (JsonObject) jsonArray.get(0);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            update.addProperty(entry.getKey(), entry.getValue());
        }
        System.out.println(update.toString());
        System.out.println(jsonObject.toString());
        try {
            Writer out = new FileWriter(Constants.JSON_TEST_DATA_PATH);
            out.write(jsonObject.toString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
