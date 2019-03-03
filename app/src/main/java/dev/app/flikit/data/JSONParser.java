package dev.app.flikit.data;

import dev.app.flikit.model.Photo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    public static ApiResponse convertResponse(String data) {
        ApiResponse response = null;

        try {
            List<Photo> photoList = new ArrayList<>();

            JSONObject json = new JSONObject(data);
            json = json.getJSONObject("photos");

            int page = json.getInt("page");
            int pages = json.getInt("pages");
            int perpage = json.getInt("perpage");
            int total = json.getInt("total");

            JSONArray array = json.getJSONArray("photo");

            for(int i=0; i<array.length(); i++)
            {
                JSONObject photoObj = array.getJSONObject(i);

                String id     = photoObj.getString("id");
                String owner  = photoObj.getString("owner");
                String secret = photoObj.getString("secret");
                String server = photoObj.getString("server");
                int farm      = photoObj.getInt("farm");
                String title  = photoObj.getString("title");
                int ispublic  = photoObj.getInt("ispublic");

                photoList.add(new Photo(id, owner, secret, server, farm, title, ispublic));
            }
            response = new ApiResponse(page, pages, perpage, total, photoList);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return response;
    }
}
