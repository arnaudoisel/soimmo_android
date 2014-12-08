package fr.soat.soimmo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import fr.soat.soimmo.R;
import fr.soat.soimmo.models.Accommodation;

public class AccommodationAdapter extends RecyclerView.Adapter<AccommodationAdapter.AccommodationViewHolder> {

    private List<Accommodation> accommodationList;

    public AccommodationAdapter(List<Accommodation> accommodationList) {
        this.accommodationList = accommodationList;
    }

    @Override
    public int getItemCount() {
        return accommodationList.size();
    }

    @Override
    public void onBindViewHolder(AccommodationViewHolder accommodationViewHolder, int i) {
        Accommodation accommodation = accommodationList.get(i);
        accommodationViewHolder.typeView.setText(accommodation.getType().toString());
        accommodationViewHolder.cityView.setText(accommodation.getAddress().getCity());
        accommodationViewHolder.surfaceView.setText(accommodation.getSurface().toString());
        accommodationViewHolder.rentView.setText(accommodation.getRent().toString());
        accommodationViewHolder.roomsView.setText(String.valueOf(accommodation.getRooms()));
    }

    @Override
    public AccommodationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.layout_accommodation_card, viewGroup, false);

        return new AccommodationViewHolder(itemView);
    }

    public static class AccommodationViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.type)
        TextView typeView;

        @InjectView(R.id.city)
        TextView cityView;

        @InjectView(R.id.surface)
        TextView surfaceView;

        @InjectView(R.id.rent)
        TextView rentView;

        @InjectView(R.id.rooms)
        TextView roomsView;

        public AccommodationViewHolder(View v) {
            super(v);

            ButterKnife.inject(this, v);
        }
    }
}