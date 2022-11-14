package order.book.service.strategy;

import order.book.model.types.TypeQuery;
import order.book.service.strategy.handler.ProcessingHandler;
import order.book.service.strategy.maps.TypeQueryOperationProcessing;

public class QueryProcessingStrategy implements TypeProcessingStrategy<TypeQuery> {
    private final TypeQueryOperationProcessing typeQueryOperationProcessing;

    public QueryProcessingStrategy(TypeQueryOperationProcessing typeQueryOperationProcessing) {
        this.typeQueryOperationProcessing = typeQueryOperationProcessing;
    }

    @Override
    public ProcessingHandler getHandlerByTypeProcessing(TypeQuery typeProcessing) {
        return typeQueryOperationProcessing.getMap().get(typeProcessing);
    }
}
