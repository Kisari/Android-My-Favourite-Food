package rmitcom.asm1.myfavouritefood.Model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class FoodList {
    private ArrayList<Food> foodArrayList = new ArrayList<Food>();

    public FoodList(Context context) {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context));

            JSONArray decrypted_array = obj.getJSONArray("data");

            //debugging
            System.out.println("data" + decrypted_array);
            //debugging
            for (int i = 0; i < decrypted_array.length(); i++) {
                JSONObject jo_inside = decrypted_array.getJSONObject(i);

                //get all the property of single data
                String id_value = jo_inside.getString("id");
                String name_value = jo_inside.getString("name");
                String short_name_value = jo_inside.getString("short_name");
                String description_value = jo_inside.getString("description");
                String url_value = jo_inside.getString("url");
                ArrayList<String> tags_value = this.convertJsonArrayToArrayList(jo_inside.getJSONArray("tags"));
                String image_value = jo_inside.getString("image");
                String campus_value = jo_inside.getString("campus");
                String lat_value = jo_inside.getString("lat");
                String lng_value = jo_inside.getString("lng");
                String address_value = jo_inside.getString("address");

                Food newFood = new Food(id_value, name_value, short_name_value, description_value, url_value, tags_value, image_value, campus_value, lat_value, lng_value, address_value);

                this.foodArrayList.add(newFood);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Food> getFoodArrayList() {
        return foodArrayList;
    }

    public ArrayList<String> convertJsonArrayToArrayList(JSONArray json){
        ArrayList<String> listData = new ArrayList<String>();
        try{
            JSONArray jArray = (JSONArray) json;
            if (jArray != null) {
                for (int i=0;i<jArray.length();i++){
                    listData.add(jArray.getString(i));
                }
            }
        }
        catch (Exception e) {
         e.printStackTrace();
        }
        return listData;
    }

    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
