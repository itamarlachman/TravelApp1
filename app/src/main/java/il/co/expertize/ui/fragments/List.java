package il.co.expertize.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;

import il.co.expertize.R;
import il.co.expertize.interfaces.IDataSource;
import il.co.expertize.models.Travel;
import il.co.expertize.sources.Repository;
import il.co.expertize.utils.SharedPrefUtils;

public class List extends Fragment {

    SharedPrefUtils sharedPrefUtils;
    IDataSource dataSource;
    ListView listTravels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerUtils();
        registerViews(view);
        registerList(view);
    }

    private void registerUtils() {
        sharedPrefUtils = new SharedPrefUtils(getContext());
        dataSource = Repository.getDataSource(Repository.Type.FireBase);
    }

    private void registerList(View view) {
        Travel travel = sharedPrefUtils.loadTravel();
        if (travel != null) {
            dataSource.getTravels(travel.getCustomer());
        } else {
            dataSource.getTravels(null);
        }
        dataSource.liveTravels().observe(getViewLifecycleOwner(), new Observer<ArrayList<Travel>>() {
            @Override
            public void onChanged(final ArrayList<Travel> travels) {
                ArrayAdapter<Travel> adapter = new ArrayAdapter<Travel>( getContext(), R.layout.travel, travels) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        if (convertView == null)
                            convertView = View.inflate(getContext(),
                                    R.layout.travel,
                                    null);

                        Travel travel = travels.get(position);
                        TextView customer = (TextView) convertView.findViewById(R.id.customer);
                        customer.setText(travel.toString());
                        return convertView;
                    }
                };

                listTravels.setAdapter(adapter);
                listTravels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Travel travel =  (Travel) listTravels.getAdapter().getItem(position);
                        sharedPrefUtils.saveTravel(travel);
                        NavHostFragment.findNavController(List.this)
                                .navigate(R.id.action_FirstFragment_to_SecondFragment);

                    }
                });

            }
        });
    }

    private void registerViews(@NonNull View view) {
        listTravels = view.findViewById(R.id.listTravels);
        view.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(List.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
}