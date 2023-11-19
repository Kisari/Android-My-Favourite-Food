package rmitcom.asm1.myfavouritefood.Activity.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

import rmitcom.asm1.myfavouritefood.Activity.Utils.AsyncImage;
import rmitcom.asm1.myfavouritefood.Adapter.BadgeRecycleViewAdapter;
import rmitcom.asm1.myfavouritefood.Model.Food;
import rmitcom.asm1.myfavouritefood.R;

public class FoodView extends AppCompatActivity implements OnMapReadyCallback {

    private BadgeRecycleViewAdapter badgeListAdapter;
    private GoogleMap Gmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_view);

        Intent intent = getIntent();
        Food foodItem =(Food) intent.getSerializableExtra("food");

        if(foodItem != null){
            Toast.makeText(getApplicationContext(),  foodItem.getName(), Toast.LENGTH_LONG).show();
        }

        //Set up the map view
        try{
            SupportMapFragment mapFragment = SupportMapFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.mapView, mapFragment)
                    .commit();
            mapFragment.getMapAsync(this);
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }

        //Loading all the tags with custom adapter
        RecyclerView badgeView = findViewById(R.id.badgeListView);
        badgeView.setLayoutManager(new GridLayoutManager(this, 3));
        if(foodItem != null){
            badgeListAdapter = new BadgeRecycleViewAdapter(this, foodItem.getTags().toArray(new String[0]));
        }

        badgeView.setAdapter(badgeListAdapter);

        //set the data to text view
        TextView foodTitle = findViewById(R.id.foodTitle);
        TextView foodContent = findViewById(R.id.foodContent);
        TextView foodAddress = findViewById(R.id.foodAddress);
        ImageView imageView = findViewById(R.id.foodIllustration);

        if(foodItem != null){
            foodTitle.setText(foodItem.getName());
            foodContent.setText(foodItem.getDescription());
            foodAddress.setText(foodItem.getAddress());

            try{
                if(!this.hasImage(imageView)){
                    new AsyncImage(imageView).execute(foodItem.getImage());
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        //set up back button
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap goggleMap){
        this.Gmap = goggleMap;

        //Add a marker and move the camera
        LatLng sydney = new LatLng(-33.852, 151.211);
        this.Gmap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        this.Gmap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            setResult(RESULT_OK);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    protected boolean hasImage(@NonNull ImageView view) {
        Drawable drawable = view.getDrawable();
        boolean hasImage = (drawable != null);

        if (hasImage && (drawable instanceof BitmapDrawable)) {
            hasImage = ((BitmapDrawable)drawable).getBitmap() != null;
        }

        return hasImage;
    }
}