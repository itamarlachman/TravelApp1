package il.co.expertize.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import il.co.expertize.R;
import il.co.expertize.interfaces.DataResult;
import il.co.expertize.interfaces.IClickEvent;
import il.co.expertize.models.Travel;
import il.co.expertize.ui.adapters.TravelsAdapter;
import il.co.expertize.ui.viewmodels.MainViewModel;
import il.co.expertize.utils.SharedPrefUtils;

public class List extends Fragment {

    protected SharedPrefUtils sharedPrefUtils;
    protected MainViewModel viewModel;

    protected TravelsAdapter travelsAdapter;
    protected RecyclerView.LayoutManager layoutManager;
    protected RecyclerView listTravels;


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
    }

    private void registerList(View view) {
        Travel travel = sharedPrefUtils.loadTravel();
        if (travel != null)
            viewModel.getTravels(travel.getCustomer());
         else
            viewModel.getTravels(null);

        listTravels =  view.findViewById(R.id.listTravels);
        listTravels.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        listTravels.setLayoutManager(layoutManager);
        listTravels.setItemAnimator(new DefaultItemAnimator());
        travelsAdapter = new TravelsAdapter(null);
        listTravels.setAdapter(travelsAdapter);

        viewModel.result().observe(getViewLifecycleOwner(), new Observer<DataResult>() {
            @Override
            public void onChanged(DataResult dataResult) {
                if (dataResult.getOperation() == DataResult.Operation.Select &&
                    dataResult.getEntity() == DataResult.Entity.Travels) {
                    final ArrayList<Travel> travels = ( ArrayList<Travel>) dataResult.getResult();
                    travelsAdapter.setTravels(travels);
                    travelsAdapter.setClickEvent(new IClickEvent() {
                        @Override
                        public void addOnClickListener(Object result) {
                            Travel travel =  (Travel) result;
                            sharedPrefUtils.saveTravel(travel);
                            NavHostFragment.findNavController(List.this)
                                    .navigate(R.id.action_FirstFragment_to_SecondFragment);

                        }
                    });
                    travelsAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void registerViews(@NonNull View view) {
        viewModel = ViewModelProviders.of(this).get(MainViewModel .class);
        listTravels = view.findViewById(R.id.listTravels);
        view.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPrefUtils.removeTravel();
                NavHostFragment.findNavController(List.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
}