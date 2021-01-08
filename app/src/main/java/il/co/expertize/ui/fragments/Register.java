package il.co.expertize.ui.fragments;

import il.co.expertize.R;
import il.co.expertize.interfaces.DataResult;
import il.co.expertize.models.Customer;
import il.co.expertize.models.Travel;
import il.co.expertize.ui.viewmodels.MainViewModel;
import il.co.expertize.utils.DialogUtils;
import il.co.expertize.utils.Globals;
import il.co.expertize.utils.LocationUtils;
import il.co.expertize.utils.SharedPrefUtils;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Calendar;


public class Register extends Fragment {

    private MainViewModel viewModel;
    TextInputEditText firstName, lastName, email, tel, date, passengers, source, destination;
    SharedPrefUtils sharedPrefUtils;
    DialogUtils dialogUtils;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerUtils();
        registerViews(view);
        registerActions(view);
        loadTravel();
    }

    private void loadTravel() {
        Travel travel = sharedPrefUtils.loadTravel();
        if (travel != null)
            init(travel);
    }

    private void registerUtils() {
        sharedPrefUtils = new SharedPrefUtils(getContext());
        dialogUtils = new DialogUtils(getContext());
    }

    private void registerViews(@NonNull View view) {
        viewModel = ViewModelProviders.of(this).get(MainViewModel .class);

        firstName = view.findViewById(R.id.firstName);
        lastName = view.findViewById(R.id.lastName);
        tel = view.findViewById(R.id.tel);
        email = view.findViewById(R.id.email);
        date = view.findViewById(R.id.date);
        source = view.findViewById(R.id.source);
        destination = view.findViewById(R.id.destination);
        passengers = view.findViewById(R.id.passengers);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();

                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(String.format("%s-%s-%s",year,month,dayOfMonth));
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void registerActions(@NonNull View view) {
        view.findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Travel travel = load();
                viewModel.saveTravel(travel);
                viewModel.result().observe(getViewLifecycleOwner(), new Observer<DataResult>() {
                    @Override
                    public void onChanged(DataResult dataResult) {
                        if (dataResult.getEntity() == DataResult.Entity.Travels &&
                            dataResult.getOperation() == DataResult.Operation.Add) {
                            sharedPrefUtils.saveTravel(travel);
                            dialogUtils.showAlert("Travel Saved", travel.getKey(), "Close");
                            Globals.travel = travel;
                            Globals.NewTravel = true;
                            NavHostFragment.findNavController(Register.this)
                                           .navigate(R.id.back);
                        }
                    }
                });
            }
        });

        view.findViewById(R.id.previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Travel travel = load();
                sharedPrefUtils.saveTravel(travel);
                NavHostFragment.findNavController(Register.this)
                        .navigate(R.id.back);
            }
        });
    }

    private Travel load() {
        LocationUtils locationUtils = new LocationUtils(getContext());
        Travel travel = new Travel();

        try {
            travel.setSource(locationUtils.getLocation(source.getText().toString()));
            travel.setDestination(locationUtils.getLocation(destination.getText().toString()));

            travel.calcDistance();

            travel.setPassengers(Integer.parseInt(passengers.getText().toString()));
            travel.setDateString(date.getText().toString());
            Customer customer = travel.getCustomer();
            customer.setFirstName(firstName.getText().toString());
            customer.setLastName(lastName.getText().toString());
            customer.setEmail(email.getText().toString());
            customer.setTel(tel.getText().toString());
        } catch (Exception ex) {
            Log.d(Globals.TAG,"Error Loading travel from form, see error:" + ex.getMessage());
        }

        return travel;
    }

    private void init(Travel travel) {
        try {
            Customer customer = travel.getCustomer();
            source.setText(travel.getSource().getAddress());
            destination.setText(travel.getDestination().getAddress());
            date.setText(travel.getDateString());
            firstName.setText(customer.getFirstName());
            lastName.setText(customer.getLastName());
            passengers.setText(travel.getPassengers().toString());
            email.setText(customer.getEmail());
            tel.setText(customer.getTel());
        } catch (Exception e) {
            dialogUtils.showError(e);
        }
    }

}