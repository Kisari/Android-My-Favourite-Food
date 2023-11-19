package rmitcom.asm1.myfavouritefood;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


import rmitcom.asm1.myfavouritefood.Activity.View.FoodListView;
import rmitcom.asm1.myfavouritefood.Model.FoodList;

public class Main extends AppCompatActivity {

    private final ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
                    //handle something ?

                } else {
                    Log.e("Error", "Something went wrong");
                }
            });

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button navigateToList = findViewById(R.id.startBtn);
        TextView navigateTextView = findViewById(R.id.startTxt);

        navigateTextView.setText("Explore my favourite restaurant");

        navigateToList.setOnClickListener((v) -> {
            Intent intent = new Intent(Main.this, FoodListView.class);
            activityResultLaunch.launch(intent);
            finish();
        });

    }
}