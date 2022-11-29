package order.book.service.strategy.handler.impl;

import order.book.dao.TransactionDaoDb;
import order.book.model.Model;
import order.book.model.Update;
import order.book.service.strategy.handler.ProcessingHandler;

public class BidProcessingHandler implements ProcessingHandler {
    private final TransactionDaoDb transactionDaoDb;

    public BidProcessingHandler(TransactionDaoDb transactionDaoDb) {
        this.transactionDaoDb = transactionDaoDb;
    }

    @Override
    public void processing(Model transaction) {
        transactionDaoDb.put((Update) transaction);
    }
}
