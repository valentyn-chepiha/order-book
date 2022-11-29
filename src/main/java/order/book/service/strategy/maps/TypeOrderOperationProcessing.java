package order.book.service.strategy.maps;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import order.book.model.types.TypeOrder;
import order.book.service.strategy.handler.ProcessingHandler;

public class TypeOrderOperationProcessing implements TypeOperationProcessing<TypeOrder> {
    private final Map<Object, ProcessingHandler> map;

    public TypeOrderOperationProcessing(Map<Object, ProcessingHandler> map) {
        this.map = map;
    }

    @Override
    public Map<TypeOrder, ProcessingHandler> getMap() {
        return Arrays.stream(TypeOrder.values())
                .collect(Collectors.toMap(Function.identity(), map::get));
    }
}
