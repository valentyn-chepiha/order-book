package order.book.service.strategy.impl;

import order.book.model.types.TypeOrder;
import order.book.service.strategy.TypeProcessingStrategy;
import order.book.service.strategy.handler.ProcessingHandler;
import order.book.service.strategy.maps.TypeOrderOperationProcessing;

public class OrderProcessingStrategy implements TypeProcessingStrategy<TypeOrder> {
    private final TypeOrderOperationProcessing typeOrderOperationProcessing;

    public OrderProcessingStrategy(TypeOrderOperationProcessing typeOrderOperationProcessing) {
        this.typeOrderOperationProcessing = typeOrderOperationProcessing;
    }

    @Override
    public ProcessingHandler getHandlerByTypeProcessing(TypeOrder typeOrder) {
        return typeOrderOperationProcessing.getMap().get(typeOrder);
    }
}
