package order.book.service.strategy.handler.impl;

import order.book.model.Model;
import order.book.model.Order;
import order.book.model.types.TypeOrder;
import order.book.service.strategy.TypeProcessingStrategy;
import order.book.service.strategy.handler.ProcessingHandler;

public class OrderProcessingHandler implements ProcessingHandler {
    private final TypeProcessingStrategy<TypeOrder> orderProcessingStrategy;

    public OrderProcessingHandler(TypeProcessingStrategy<TypeOrder> orderProcessingStrategy) {
        this.orderProcessingStrategy = orderProcessingStrategy;
    }

    @Override
    public void processing(Model transaction) {
        orderProcessingStrategy
                .getHandlerByTypeProcessing(((Order) transaction).getTypeOrder())
                .processing(transaction);
    }
}
