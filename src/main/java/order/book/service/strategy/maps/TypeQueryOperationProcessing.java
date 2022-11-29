package order.book.service.strategy.maps;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import order.book.model.types.TypeQuery;
import order.book.service.strategy.handler.ProcessingHandler;

public class TypeQueryOperationProcessing implements TypeOperationProcessing<TypeQuery> {
    private final Map<Object, ProcessingHandler> map;

    public TypeQueryOperationProcessing(Map<Object, ProcessingHandler> map) {
        this.map = map;
    }

    @Override
    public Map<TypeQuery, ProcessingHandler> getMap() {
        return Arrays.stream(TypeQuery.values())
                .collect(Collectors.toMap(Function.identity(), map::get));
    }
}
