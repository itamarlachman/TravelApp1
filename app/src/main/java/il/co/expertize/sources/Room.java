package il.co.expertize.sources;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import il.co.expertize.interfaces.IDataSource;
import il.co.expertize.models.Customer;
import il.co.expertize.models.Travel;

public class Room implements IDataSource {
    @Override
    public void saveTravel(Travel travel) {
        // TODO: implement method.
    }

    @Override
    public void getTravels(Customer customer) {
        // TODO: implement method.
    }

    @Override
    public MutableLiveData<ArrayList<Travel>> liveTravels() {
        return null;
    }
}
