package order.book.service.strategy.maps;

import order.book.model.types.TypeOrder;
import order.book.model.types.TypeQuery;
import order.book.model.types.TypeTransaction;
import order.book.model.types.TypeUpdate;
import order.book.service.strategy.TypeProcessingStrategy;
import order.book.service.strategy.handler.impl.OrderProcessingHandler;
import order.book.service.strategy.handler.impl.QueryProcessingHandler;
import order.book.service.strategy.handler.impl.UpdateProcessingHandler;

public class TypeTransactionOperationProcessing extends TypeOperationProcessing<TypeTransaction> {
    public TypeTransactionOperationProcessing(
            TypeProcessingStrategy<TypeUpdate> updateProcessingStrategy,
            TypeProcessingStrategy<TypeQuery> queryProcessingStrategy,
            TypeProcessingStrategy<TypeOrder> orderProcessingStrategy) {
        map.put(TypeTransaction.UPDATE, new UpdateProcessingHandler(updateProcessingStrategy));
        map.put(TypeTransaction.QUERY, new QueryProcessingHandler(queryProcessingStrategy));
        map.put(TypeTransaction.ORDER, new OrderProcessingHandler(orderProcessingStrategy));
    }
}
