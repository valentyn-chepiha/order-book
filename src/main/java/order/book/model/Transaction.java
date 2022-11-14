package order.book.model;

import lombok.Getter;
import lombok.Setter;
import order.book.model.types.TypeUpdate;

@Getter
@Setter
public class Transaction {
    private Operation operation;
    private TypeUpdate typeUpdate;
}
