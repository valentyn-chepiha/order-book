package order.book.service.strategy.handler.impl;

import order.book.dao.TransactionDaoDb;
import order.book.model.Operation;
import order.book.model.types.TypeUpdate;
import order.book.service.strategy.handler.ProcessingHandler;

public class SpreadProcessingHandler implements ProcessingHandler {
    private static final int INDEX_OF_PRICE = 0;
    private static final int INDEX_OF_COUNT = 1;
    private final TransactionDaoDb transactionDaoDb;

    public SpreadProcessingHandler(TransactionDaoDb transactionDaoDb) {
        this.transactionDaoDb = transactionDaoDb;
    }

    @Override
    public void processing(String[] dataTransaction) {
        Operation operation = new Operation();
        operation.setPrice(Long.parseLong(dataTransaction[INDEX_OF_PRICE]));
        operation.setCount(Long.parseLong(dataTransaction[INDEX_OF_COUNT]));
        transactionDaoDb.put(TypeUpdate.SPREAD, operation);
    }
}
