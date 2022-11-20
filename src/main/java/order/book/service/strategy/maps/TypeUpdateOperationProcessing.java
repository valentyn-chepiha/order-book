package order.book.service.strategy.maps;

import order.book.dao.TransactionDaoDb;
import order.book.model.types.TypeUpdate;
import order.book.service.strategy.handler.impl.AskProcessingHandler;
import order.book.service.strategy.handler.impl.BidProcessingHandler;

public class TypeUpdateOperationProcessing extends TypeOperationProcessing<TypeUpdate> {
    public TypeUpdateOperationProcessing(TransactionDaoDb transactionDaoDb) {
        map.put(TypeUpdate.ASK, new AskProcessingHandler(transactionDaoDb));
        map.put(TypeUpdate.BID, new BidProcessingHandler(transactionDaoDb));
    }
}
