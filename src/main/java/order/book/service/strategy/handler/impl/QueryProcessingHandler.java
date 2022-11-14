package order.book.service.strategy.handler.impl;

import java.util.Arrays;
import order.book.model.types.TypeQuery;
import order.book.service.strategy.TypeProcessingStrategy;
import order.book.service.strategy.handler.ProcessingHandler;

public class QueryProcessingHandler implements ProcessingHandler {
    private static final int INDEX_TYPE_QUERY = 0;
    private final TypeProcessingStrategy<TypeQuery> queryProcessingStrategy;

    public QueryProcessingHandler(TypeProcessingStrategy<TypeQuery> queryProcessingStrategy) {
        this.queryProcessingStrategy = queryProcessingStrategy;
    }

    @Override
    public void processing(String[] params) {
        TypeQuery typeQuery = Arrays.stream(TypeQuery.values())
                .filter(o -> o.getShortName().equals(params[INDEX_TYPE_QUERY]))
                .findFirst()
                .orElse(null);
        if (typeQuery == null) {
            return;
        }
        ProcessingHandler handler = queryProcessingStrategy.getHandlerByTypeProcessing(typeQuery);
        handler.processing(params);
    }
}
