package il.co.expertize.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import il.co.expertize.interfaces.IDataSource;
import il.co.expertize.models.Travel;
import il.co.expertize.sources.Repository;

public class MainVM extends ViewModel {
    protected IDataSource _dataSource =  Repository.getDataSource(Repository.Type.FireBase);
    protected MutableLiveData<ArrayList<Travel>> travels;

}
