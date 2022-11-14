package order.book.service.strategy.handler.impl;

import order.book.dao.TransactionDaoDb;
import order.book.model.Operation;
import order.book.model.types.TypeUpdate;
import order.book.service.strategy.handler.ProcessingHandler;
import order.book.util.AnaliseTransaction;

public class SellProcessingHandler implements ProcessingHandler {
    private static final int INDEX_OF_COUNT = 0;
    private final TransactionDaoDb transactionDaoDb;

    public SellProcessingHandler(TransactionDaoDb transactionDaoDb) {
        this.transactionDaoDb = transactionDaoDb;
    }

    @Override
    public void processing(String[] data) {
        Long value = Long.parseLong(data[INDEX_OF_COUNT]);
        Operation operation;
        do {
            operation = AnaliseTransaction.getBestBid(transactionDaoDb.getAll(TypeUpdate.BID));
            if (operation != null && operation.getCount() > 0) {
                value = operation.getCount() - value;
                if (value >= 0) {
                    operation.setCount(value);
                    value = 0L;
                } else {
                    operation.setCount(0);
                    value *= -1;
                }
                transactionDaoDb.put(TypeUpdate.BID, operation);
            } else {
                operation = null;
            }
        } while (value > 0 && operation != null);
    }
}
