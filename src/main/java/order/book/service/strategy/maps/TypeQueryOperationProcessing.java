package order.book.service.strategy.maps;

import order.book.dao.TransactionDaoDb;
import order.book.model.types.TypeQuery;
import order.book.service.ReportService;
import order.book.service.strategy.handler.impl.BestAskProcessingHandler;
import order.book.service.strategy.handler.impl.BestBidProcessingHandler;
import order.book.service.strategy.handler.impl.SizeProcessingHandler;

public class TypeQueryOperationProcessing extends TypeOperationProcessing<TypeQuery> {
    public TypeQueryOperationProcessing(TransactionDaoDb transactionDaoDb,
                                        ReportService reportService) {
        map.put(TypeQuery.BEST_BID, new BestBidProcessingHandler(transactionDaoDb, reportService));
        map.put(TypeQuery.BEST_ASK, new BestAskProcessingHandler(transactionDaoDb, reportService));
        map.put(TypeQuery.SIZE, new SizeProcessingHandler(transactionDaoDb, reportService));
    }
}
