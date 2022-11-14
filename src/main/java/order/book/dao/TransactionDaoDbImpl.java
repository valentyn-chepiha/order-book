package order.book.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import order.book.model.Operation;
import order.book.model.types.TypeUpdate;
import order.book.storage.Storage;

public class TransactionDaoDbImpl implements TransactionDaoDb {
    public TransactionDaoDbImpl() {
        for (TypeUpdate value : TypeUpdate.values()) {
            Storage.data.put(value, new HashMap<>());
        }
    }

    @Override
    public void put(TypeUpdate typeUpdate, Operation operation) {
        Storage.data.get(typeUpdate).put(operation.getPrice(), operation.getCount());
    }

    @Override
    public Set<Map.Entry<Long, Long>> getAll(TypeUpdate typeUpdate) {
        return Storage.data.get(typeUpdate).entrySet();
    }
}
