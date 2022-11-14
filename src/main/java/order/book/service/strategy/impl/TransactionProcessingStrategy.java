package order.book.service.strategy.impl;

import order.book.model.types.TypeTransaction;
import order.book.service.strategy.TypeProcessingStrategy;
import order.book.service.strategy.handler.ProcessingHandler;
import order.book.service.strategy.maps.TypeTransactionOperationProcessing;

public class TransactionProcessingStrategy implements TypeProcessingStrategy<TypeTransaction> {
    private final TypeTransactionOperationProcessing typeTransactionOperationProcessing;

    public TransactionProcessingStrategy(TypeTransactionOperationProcessing
                                                 typeTransactionOperationProcessing) {
        this.typeTransactionOperationProcessing = typeTransactionOperationProcessing;
    }

    @Override
    public ProcessingHandler getHandlerByTypeProcessing(TypeTransaction typeTransaction) {
        return typeTransactionOperationProcessing.getMap().get(typeTransaction);
    }
}
