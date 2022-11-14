package order.book.model;

import order.book.model.types.TypeUpdate;

public class Transaction {
    private Operation operation;
    private TypeUpdate typeUpdate;

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public TypeUpdate getTypeTransaction() {
        return typeUpdate;
    }

    public void setTypeTransaction(TypeUpdate typeUpdate) {
        this.typeUpdate = typeUpdate;
    }
}
