package order.book.dao;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import order.book.model.Operation;
import order.book.model.Update;
import order.book.model.types.TypeUpdate;
import order.book.storage.Storage;

public class TransactionDaoDbImpl implements TransactionDaoDb {
    public TransactionDaoDbImpl() {
        for (TypeUpdate value : TypeUpdate.values()) {
            Storage.data.put(value, new TreeMap<>());
        }
    }

    @Override
    public void put(TypeUpdate typeUpdate, Operation operation) {
        Storage.data.get(typeUpdate).put(operation.getPrice(), operation.getCount());
    }

    @Override
    public void put(Update updateModel) {
        Storage.data.get(updateModel.getTypeUpdate())
                .put(updateModel.getPrice(), updateModel.getSize());
    }

    // todo
    //      maybe delete
    @Override
    public Map.Entry<Long, Long> getMin(TypeUpdate typeUpdate) {
        return Storage.data.get(typeUpdate).firstEntry();
    }

    // todo
    //      maybe delete
    @Override
    public Map.Entry<Long, Long> getMax(TypeUpdate typeUpdate) {
        return Storage.data.get(typeUpdate).lastEntry();
    }

    @Override
    public Set<Map.Entry<Long, Long>> getAll(TypeUpdate typeUpdate) {
        return Storage.data.get(typeUpdate).entrySet();
    }
}
