package il.co.expertize.sources;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import il.co.expertize.interfaces.IDataSource;
import il.co.expertize.models.Customer;
import il.co.expertize.models.Travel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FireBase implements IDataSource {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference travels = db.getReference("travels");
    MutableLiveData<ArrayList<Travel>> _liveTravels = new MutableLiveData<>();

    @Override
    public void saveTravel(Travel travel) {
        travels.child(travel.getKey())
                .setValue(travel);
    }

    @Override
    public void getTravels(final Customer customer) {
        travels.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Travel> result = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Travel travel = child.getValue(Travel.class);
                    if (customer == null)
                        result.add(travel);
                    else if(travel.getCustomer().getFullName().equals(customer.getFullName())){
                        result.add(travel);
                    }
                }
                _liveTravels.postValue(result);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public MutableLiveData<ArrayList<Travel>> liveTravels() {
        return _liveTravels;
    }


}
