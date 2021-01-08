package il.co.expertize.interfaces;

public class DataResult {
    public enum Operation {
        None,
        Select,
        Add,
        Delete
    }

    public enum Entity {
        None,
        Travels,
        Companies
    }

    protected Operation operation = Operation.None;
    protected Entity entity = Entity.None;
    protected Object Result;

    public DataResult() { }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Object getResult() {
        return Result;
    }

    public void setResult(Object result) {
        Result = result;
    }
}
