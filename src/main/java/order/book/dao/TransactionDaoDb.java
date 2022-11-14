package order.book.dao;

import java.util.Map;
import java.util.Set;
import order.book.model.Operation;
import order.book.model.types.TypeUpdate;

public interface TransactionDaoDb {
    void put(TypeUpdate typeUpdate, Operation operation);

    Set<Map.Entry<Long, Long>> getAll(TypeUpdate typeUpdate);
}
