package order.book.service.strategy.maps;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import order.book.model.types.TypeTransaction;
import order.book.service.strategy.handler.ProcessingHandler;

public class TypeTransactionOperationProcessing implements
        TypeOperationProcessing<TypeTransaction> {
    private final Map<Object, ProcessingHandler> map;

    public TypeTransactionOperationProcessing(Map<Object, ProcessingHandler> map) {
        this.map = map;
    }

    @Override
    public Map<TypeTransaction, ProcessingHandler> getMap() {
        return Arrays.stream(TypeTransaction.values())
                .collect(Collectors.toMap(Function.identity(), map::get));
    }
}
