package order.book.service.strategy.handler.impl;

import order.book.dao.TransactionDaoDb;
import order.book.model.Model;
import order.book.model.Operation;
import order.book.model.Order;
import order.book.model.types.TypeUpdate;
import order.book.service.strategy.handler.ProcessingHandler;
import order.book.util.AnaliseTransaction;

public class BuyProcessingHandler implements ProcessingHandler {
    private final TransactionDaoDb transactionDaoDb;

    public BuyProcessingHandler(TransactionDaoDb transactionDaoDb) {
        this.transactionDaoDb = transactionDaoDb;
    }

    @Override
    public void processing(Model transaction) {
        long value = ((Order) transaction).getSize();
        Operation operation;
        do {
            operation = AnaliseTransaction.getBestAsk(transactionDaoDb.getAll(TypeUpdate.ASK));
            if (operation != null) {
                value = operation.getCount() - value;
                if (value >= 0) {
                    operation.setCount(value);
                    value = 0L;
                } else {
                    operation.setCount(0L);
                    value *= -1;
                }
                transactionDaoDb.put(TypeUpdate.ASK, operation);
            }
        } while (value > 0 && operation != null);
    }
}
