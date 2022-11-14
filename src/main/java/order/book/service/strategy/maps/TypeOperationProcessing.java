package order.book.service.strategy.maps;

import java.util.HashMap;
import java.util.Map;
import order.book.service.strategy.handler.ProcessingHandler;

public class TypeOperationProcessing<T> {
    protected final Map<T, ProcessingHandler> map = new HashMap<>();

    public Map<T, ProcessingHandler> getMap() {
        return map;
    }
}
