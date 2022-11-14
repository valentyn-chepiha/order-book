package order.book.service.strategy.maps;

import order.book.dao.TransactionDaoDb;
import order.book.model.types.TypeOrder;
import order.book.service.strategy.handler.impl.BuyProcessingHandler;
import order.book.service.strategy.handler.impl.SellProcessingHandler;

public class TypeOrderOperationProcessing extends TypeOperationProcessing<TypeOrder> {
    public TypeOrderOperationProcessing(TransactionDaoDb transactionDaoDb) {
        map.put(TypeOrder.BUY, new BuyProcessingHandler(transactionDaoDb));
        map.put(TypeOrder.SELL, new SellProcessingHandler(transactionDaoDb));
    }
}
