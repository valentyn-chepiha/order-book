package order.book.service.strategy.handler.impl;

import order.book.model.Model;
import order.book.model.Update;
import order.book.model.types.TypeUpdate;
import order.book.service.strategy.TypeProcessingStrategy;
import order.book.service.strategy.handler.ProcessingHandler;

public class UpdateProcessingHandler implements ProcessingHandler {
    private final TypeProcessingStrategy<TypeUpdate> updateProcessingStrategy;

    public UpdateProcessingHandler(TypeProcessingStrategy<TypeUpdate> updateProcessingStrategy) {
        this.updateProcessingStrategy = updateProcessingStrategy;
    }

    @Override
    public void processing(Model transaction) {
        updateProcessingStrategy
                .getHandlerByTypeProcessing(((Update) transaction).getTypeUpdate())
                .processing(transaction);
    }
}
