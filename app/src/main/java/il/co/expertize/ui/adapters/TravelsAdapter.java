package il.co.expertize.ui.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import il.co.expertize.R;
import il.co.expertize.interfaces.IClickEvent;
import il.co.expertize.models.Customer;
import il.co.expertize.models.Travel;


public class TravelsAdapter extends  RecyclerView.Adapter<TravelsAdapter.TravelViewHolder> {
    protected ArrayList<Travel> travels;
    protected IClickEvent clickEvent;

    public ArrayList<Travel> getTravels() {
        return travels;
    }

    public IClickEvent getClickEvent() {
        return clickEvent;
    }

    public void setClickEvent(IClickEvent clickEvent) {
        this.clickEvent = clickEvent;
    }

    public void setTravels(ArrayList<Travel> travels) {
        this.travels = travels;
    }

    public class TravelViewHolder extends RecyclerView.ViewHolder{
        TextView date,name,email,tel,source,destination,status;
        IClickEvent clickEvent;
        Button open;
        View view;

        public void setClickEvent(IClickEvent clickEvent) {
            this.clickEvent = clickEvent;
        }

        public TravelViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            open = itemView.findViewById(R.id.open);
            date = itemView.findViewById(R.id.date);
            name = itemView.findViewById(R.id.name);
            tel = itemView.findViewById(R.id.tel);
            email = itemView.findViewById(R.id.email);
            source = itemView.findViewById(R.id.source);
            destination = itemView.findViewById(R.id.destination);
            status = itemView.findViewById(R.id.status);
        }
    }

    public TravelsAdapter(ArrayList<Travel> travels) {
        this.travels = travels;
    }

    @NonNull
    @Override
    public TravelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.travel, parent, false);

        TravelViewHolder holder = new TravelViewHolder((view));
        holder.setClickEvent(this.clickEvent);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TravelViewHolder holder, int position) {
        if (travels == null)
            return;

        Travel travel = travels.get(position);
        Customer customer = travel.getCustomer();
        holder.date.setText(travel.getDateString());
        holder.name.setText(customer.getFullName());
        holder.tel.setText(customer.getTel());
        holder.email.setText(customer.getTel());
        holder.source.setText(travel.getSource().getAddress());
        holder.destination.setText(travel.getDestination().getAddress());
        if (travel.getStatus() != null)
            holder.status.setText(travel.getStatus().name());
        else
            holder.status.setText("Undefined");

        holder.open.setTag(travel);

        holder.open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Travel  travel = (Travel) v.getTag();
                holder.clickEvent.addOnClickListener(travel);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (travels == null)
            return 0;
        else
            return travels.size();
    }

}
