package order.book.service.strategy.handler.impl;

import java.util.Arrays;
import order.book.model.types.TypeUpdate;
import order.book.service.strategy.TypeProcessingStrategy;
import order.book.service.strategy.handler.ProcessingHandler;

public class UpdateProcessingHandler implements ProcessingHandler {
    private static final int INDEX_TYPE_OPERATION = 2;
    private final TypeProcessingStrategy<TypeUpdate> updateProcessingStrategy;

    public UpdateProcessingHandler(TypeProcessingStrategy<TypeUpdate> updateProcessingStrategy) {
        this.updateProcessingStrategy = updateProcessingStrategy;
    }

    @Override
    public void processing(String[] operation) {
        TypeUpdate typeUpdate = Arrays.stream(TypeUpdate.values())
                .filter(o -> o.getShortName().equals(operation[INDEX_TYPE_OPERATION]))
                .findFirst()
                .orElse(null);
        if (typeUpdate == null) {
            return;
        }
        ProcessingHandler handler = updateProcessingStrategy.getHandlerByTypeProcessing(typeUpdate);
        handler.processing(Arrays.copyOfRange(operation, 0, INDEX_TYPE_OPERATION));
    }
}
