package order.book.model;

import java.util.Arrays;
import lombok.Getter;
import lombok.Setter;
import order.book.model.types.TypeOrder;

@Setter
@Getter
public class Order implements Model<Order> {
    private static final int TRANSACTION_TYPE_INDEX = 1;
    private static final int SIZE_INDEX = 2;
    private TypeOrder typeOrder;
    private long size;

    @Override
    public Order buildOf(String[] data) {
        typeOrder = Arrays.stream(TypeOrder.values())
                .filter(t -> t.getShortName().equals(data[TRANSACTION_TYPE_INDEX]))
                .findFirst()
                .get();
        size = Long.parseLong(data[SIZE_INDEX]);
        return this;
    }
}
