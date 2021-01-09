package il.co.expertize.sources;

import il.co.expertize.interfaces.IDataSource;
import il.co.expertize.sources.firebase.FireBase;

public class Repository {
    public static FireBase fireBase = new FireBase();
    public enum Type{
        FireBase
    }
    public static IDataSource getDataSource(Type type) {
        switch (type) {
            case FireBase:
                return fireBase;
        }
        return  fireBase;
    }
}
