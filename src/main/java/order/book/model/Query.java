package order.book.model;

import java.util.Arrays;
import lombok.Getter;
import lombok.Setter;
import order.book.model.types.TypeQuery;

@Setter
@Getter
public class Query implements Model<Query> {
    private static final int TRANSACTION_TYPE_INDEX = 1;
    private static final int SIZE_INDEX = 2;
    private TypeQuery typeQuery;
    private long price;

    @Override
    public Query buildOf(String[] data) {
        typeQuery = Arrays.stream(TypeQuery.values())
                .filter(t -> t.getShortName().equals(data[TRANSACTION_TYPE_INDEX]))
                .findFirst()
                .get();
        if (typeQuery == TypeQuery.SIZE) {
            price = Long.parseLong(data[SIZE_INDEX]);
        }
        return this;
    }
}
