package order.book.service.strategy;

import order.book.model.types.TypeUpdate;
import order.book.service.strategy.handler.ProcessingHandler;
import order.book.service.strategy.maps.TypeUpdateOperationProcessing;

public class UpdateProcessingStrategy implements TypeProcessingStrategy<TypeUpdate> {
    private final TypeUpdateOperationProcessing typeUpdateOperationProcessing;

    public UpdateProcessingStrategy(TypeUpdateOperationProcessing typeUpdateOperationProcessing) {
        this.typeUpdateOperationProcessing = typeUpdateOperationProcessing;
    }

    @Override
    public ProcessingHandler getHandlerByTypeProcessing(TypeUpdate typeProcessing) {
        return typeUpdateOperationProcessing.getMap().get(typeProcessing);
    }
}
