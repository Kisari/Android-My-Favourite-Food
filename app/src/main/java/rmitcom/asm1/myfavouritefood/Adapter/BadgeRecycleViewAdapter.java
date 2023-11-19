package rmitcom.asm1.myfavouritefood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

import rmitcom.asm1.myfavouritefood.R;

// ref: https://stackoverflow.com/questions/40587168/simple-android-grid-example-using-recyclerview-with-gridlayoutmanager-like-the

public class BadgeRecycleViewAdapter extends RecyclerView.Adapter<BadgeRecycleViewAdapter.ViewHolder> {
    private final String[] mData;
    private final LayoutInflater mInflater;

    public BadgeRecycleViewAdapter(Context context, String[] data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.badge_item, parent, false);
        return new ViewHolder(view);
    }

    // set the badge description to text view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.badgeItem.setText(mData[position].toUpperCase(Locale.ROOT));
    }

    // get total number of badge items
    @Override
    public int getItemCount() {
        return mData.length;
    }


    // stores the current data if the user scroll out of the vuew
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView badgeItem;

        ViewHolder(View itemView) {
            super(itemView);
            badgeItem = itemView.findViewById(R.id.badge);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(badgeItem.getContext(), "This is badge", Toast.LENGTH_SHORT).show();
        }
    }


}
