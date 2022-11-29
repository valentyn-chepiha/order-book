package order.book.service.strategy.handler.impl;

import order.book.model.Model;
import order.book.model.Query;
import order.book.model.types.TypeQuery;
import order.book.service.strategy.TypeProcessingStrategy;
import order.book.service.strategy.handler.ProcessingHandler;

public class QueryProcessingHandler implements ProcessingHandler {
    private final TypeProcessingStrategy<TypeQuery> queryProcessingStrategy;

    public QueryProcessingHandler(TypeProcessingStrategy<TypeQuery> queryProcessingStrategy) {
        this.queryProcessingStrategy = queryProcessingStrategy;
    }

    @Override
    public void processing(Model transaction) {
        queryProcessingStrategy
                .getHandlerByTypeProcessing(((Query) transaction).getTypeQuery())
                .processing(transaction);
    }
}
