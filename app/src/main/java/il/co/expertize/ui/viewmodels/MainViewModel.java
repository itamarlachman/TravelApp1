package il.co.expertize.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import il.co.expertize.interfaces.DataResult;
import il.co.expertize.interfaces.IDataSource;
import il.co.expertize.models.Customer;
import il.co.expertize.models.Travel;
import il.co.expertize.sources.Repository;

public class MainViewModel extends ViewModel implements  IDataSource {
    protected IDataSource _dataSource =  Repository.getDataSource(Repository.Type.FireBase);

    @Override
    public void saveTravel(Travel travel) {
        _dataSource.saveTravel(travel);
    }

    @Override
    public void getTravels(Customer customer) {
        _dataSource.getTravels(customer);
    }

    @Override
    public MutableLiveData<DataResult> result() {
        return  _dataSource.result();
    }

}
