package rmitcom.asm1.myfavouritefood.Adapter;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import rmitcom.asm1.myfavouritefood.Activity.Utils.AsyncImage;
import rmitcom.asm1.myfavouritefood.Model.Food;
import rmitcom.asm1.myfavouritefood.R;
import java.util.ArrayList;

//ref: https://xuanthulab.net/su-dung-listview-hien-thi-du-lieu-dang-danh-sach-trong-android.html

public class FoodListViewAdapter extends BaseAdapter implements Filterable {

    private ArrayList<Food> listFood;
    private ArrayList<Food> listFoodContent;

    public FoodListViewAdapter(ArrayList<Food> listFood) {
        this.listFood = listFood;
        this.listFoodContent = listFood;
    }

    public ArrayList<Food> getListFood() {
        return listFood;
    }

    @Override
    public int getCount() {
        return listFood.size();
    }

    @Override
    public Object getItem(int position) {
        return listFood.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(listFood.get(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View viewFoodList;
        if (convertView == null) {
            viewFoodList = View.inflate(parent.getContext(), R.layout.list_item, null);
        } else viewFoodList = convertView;

        Food foodItem = (Food) getItem(position);

        ImageView imageView = viewFoodList.findViewById(R.id.foodImage);
        TextView titleView = viewFoodList.findViewById(R.id.foodName);
        TextView desView = viewFoodList.findViewById(R.id.foodDes);

        //Try to load the image from the Internet with Bitmap
        try{
            if(!this.hasImage(imageView)){
                new AsyncImage(imageView).execute(foodItem.getImage());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //Set the specific value for view
        try{
            titleView.setText(foodItem.getName());
            desView.setText(foodItem.getDescription());
        }
        catch (Exception e){
            e.printStackTrace();
        }


        return viewFoodList;
    }

    public boolean hasImage(@NonNull ImageView view) {
        Drawable drawable = view.getDrawable();
        boolean hasImage = (drawable != null);

        if (hasImage && (drawable instanceof BitmapDrawable)) {
            hasImage = ((BitmapDrawable)drawable).getBitmap() != null;
        }

        return hasImage;
    }

    @Override
    public Filter getFilter(){
        Filter filter = new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if(results.count == 0){
                    listFood = (ArrayList<Food>) results.values;
                    notifyDataSetInvalidated();
                }
                else{
                    listFood = (ArrayList<Food>) results.values;
                    notifyDataSetChanged();
                }

            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<Food> FilteredArrayNames = new ArrayList<>();

                constraint = constraint.toString().toLowerCase();

                for (int i = 0; i < listFoodContent.size(); i++) {
                    String dataNames = listFoodContent.get(i).getName();
                    if (dataNames.toLowerCase().startsWith(constraint.toString()))  {
                        FilteredArrayNames.add(listFoodContent.get(i));
                    }
                }



                results.count = FilteredArrayNames.size();
                results.values = FilteredArrayNames;

                return results;
            }


        };

        return filter;
    }
}
