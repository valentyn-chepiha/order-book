package order.book.service.strategy.maps;

import java.util.Map;
import order.book.service.strategy.handler.ProcessingHandler;

public interface TypeOperationProcessing<T> {
    Map<T, ProcessingHandler> getMap();
}
