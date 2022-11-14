package order.book.service.strategy;

import order.book.service.strategy.handler.ProcessingHandler;

public interface TypeProcessingStrategy<T> {
    ProcessingHandler getHandlerByTypeProcessing(T typeProcessing);
}
