package order.book.dao;

import java.util.Map;
import java.util.Set;
import order.book.model.Operation;
import order.book.model.Update;
import order.book.model.types.TypeUpdate;

public interface TransactionDaoDb {
    void put(TypeUpdate typeUpdate, Operation operation);

    void put(Update updateModel);

    Map.Entry<Long, Long> getMin(TypeUpdate typeUpdate);

    Map.Entry<Long, Long> getMax(TypeUpdate typeUpdate);

    Set<Map.Entry<Long, Long>> getAll(TypeUpdate typeUpdate);
}
