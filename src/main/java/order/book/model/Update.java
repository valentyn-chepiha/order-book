package order.book.model;

import java.util.Arrays;
import lombok.Getter;
import lombok.Setter;
import order.book.model.types.TypeUpdate;

@Setter
@Getter
public class Update implements Model<Update> {
    private static final int TRANSACTION_TYPE_INDEX = 3;
    private static final int PRICE_INDEX = 1;
    private static final int SIZE_INDEX = 2;
    private TypeUpdate typeUpdate;
    private long price;
    private long size;

    @Override
    public Update buildOf(String[] data) {
        typeUpdate = Arrays.stream(TypeUpdate.values())
                .filter(t -> t.getShortName().equals(data[TRANSACTION_TYPE_INDEX]))
                .findFirst()
                .get();
        price = Long.parseLong(data[PRICE_INDEX]);
        size = Long.parseLong(data[SIZE_INDEX]);
        return this;
    }
}
