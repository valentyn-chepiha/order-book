package order.book.service.strategy.maps;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import order.book.model.types.TypeUpdate;
import order.book.service.strategy.handler.ProcessingHandler;

public class TypeUpdateOperationProcessing implements TypeOperationProcessing<TypeUpdate> {
    private final Map<Object, ProcessingHandler> map;

    public TypeUpdateOperationProcessing(Map<Object, ProcessingHandler> map) {
        this.map = map;
    }

    @Override
    public Map<TypeUpdate, ProcessingHandler> getMap() {
        return Arrays.stream(TypeUpdate.values())
                .collect(Collectors.toMap(Function.identity(), map::get));
    }
}
