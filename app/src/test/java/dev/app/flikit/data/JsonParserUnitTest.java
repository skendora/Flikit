package dev.app.flikit.data;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

public class JsonParserUnitTest {

    private String validJSON;

    @Before
    public void setUp()
    {
        this.validJSON = "{\n" +
                "    \"photos\": {\n" +
                "        \"page\": 5,\n" +
                "        \"pages\": 3117,\n" +
                "        \"perpage\": 100,\n" +
                "        \"total\": \"311664\",\n" +
                "        \"photo\": [\n" +
                "            {\n" +
                "                \"id\": \"30062709357\",\n" +
                "                \"owner\": \"7300896@N07\",\n" +
                "                \"secret\": \"b64dd23dca\",\n" +
                "                \"server\": \"1978\",\n" +
                "                \"farm\": 2,\n" +
                "                \"title\": \"IMG_20180929_182902\",\n" +
                "                \"ispublic\": 1,\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"30062707597\",\n" +
                "                \"owner\": \"7300896@N07\",\n" +
                "                \"secret\": \"181a1ec1a6\",\n" +
                "                \"server\": \"1954\",\n" +
                "                \"farm\": 2,\n" +
                "                \"title\": \"IMG_20180929_190851\",\n" +
                "                \"ispublic\": 1,\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "\n" +
                "    \"stat\": \"ok\"\n" +
                "}";
    }


    @Test
    public void isValidResponse()
    {
        assertNotNull(JSONParser.convertResponse(validJSON));
    }
}
