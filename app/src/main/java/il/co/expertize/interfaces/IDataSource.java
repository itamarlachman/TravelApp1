package il.co.expertize.interfaces;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import il.co.expertize.models.Customer;
import il.co.expertize.models.Travel;

public interface IDataSource {
    void saveTravel(Travel travel);
    void getTravels(Customer customer);
    MutableLiveData<ArrayList<Travel>> liveTravels();

}
