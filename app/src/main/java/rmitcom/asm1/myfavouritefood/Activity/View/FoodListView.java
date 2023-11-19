package rmitcom.asm1.myfavouritefood.Activity.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rmitcom.asm1.myfavouritefood.Adapter.FoodListViewAdapter;
import rmitcom.asm1.myfavouritefood.Model.Food;
import rmitcom.asm1.myfavouritefood.Model.FoodList;
import rmitcom.asm1.myfavouritefood.R;

public class FoodListView extends AppCompatActivity {

    private FoodList foodList;
    private FoodListViewAdapter adapter;
    private ListView foodListView;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list_view);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        foodListView = findViewById(R.id.foodListView);
        searchView = findViewById(R.id.searchBar);

        ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
                        //handle something ?

                    } else {
                        Log.e("Error", "Something went wrong");
                    }
                });

        executor.execute(() -> {


            runOnUiThread(() -> {
                this.foodList = new FoodList(FoodListView.this);

                this.adapter = new FoodListViewAdapter(foodList.getFoodArrayList());

                this.foodListView.setAdapter(adapter);


                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        filteringFoods(query);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        filteringFoods(newText);
                        return false;
                    }
                });

                foodListView.setOnItemClickListener((parent, view, position, id) -> {
                    Food foodItem = (Food) adapter.getItem(position);

                    Intent intent = new Intent(FoodListView.this, FoodView.class);
                    intent.putExtra("food", foodItem);
                    activityResultLaunch.launch(intent);

                });
            });
        });
    }

    public void filteringFoods(String text){
        adapter.getFilter().filter(text);
        TextView message = findViewById(R.id.filteringMessage);
        if(adapter.getListFood().size() > 0){
            message.setVisibility(View.GONE);
        }
        else{
            message.setVisibility(View.VISIBLE);
        }
    }
}