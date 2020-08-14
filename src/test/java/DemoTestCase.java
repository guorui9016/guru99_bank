import util.JsonTestDataLoader;

import java.util.HashMap;

public class DemoTestCase{
    public static void main(String[] args) {
        HashMap<String, String> test = new HashMap();
        test.put("oldPassword","@Test001");
        test.put("newPassword","@Test002");
        test.put("confirmPassword","@Test002");

        JsonTestDataLoader.updataJsonObject("sm2_CorrectOldPassword", test);

    }

}


