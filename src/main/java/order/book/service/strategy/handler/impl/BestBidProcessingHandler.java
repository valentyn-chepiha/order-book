package order.book.service.strategy.handler.impl;

import java.util.Map;
import java.util.Set;
import order.book.dao.TransactionDaoDb;
import order.book.model.Model;
import order.book.model.Operation;
import order.book.model.types.TypeUpdate;
import order.book.service.ReportService;
import order.book.service.strategy.handler.ProcessingHandler;
import order.book.util.AnaliseTransaction;

public class BestBidProcessingHandler implements ProcessingHandler {
    private final TransactionDaoDb transactionDaoDb;
    private final ReportService reportService;

    public BestBidProcessingHandler(TransactionDaoDb transactionDaoDb,
                                    ReportService reportService) {
        this.transactionDaoDb = transactionDaoDb;
        this.reportService = reportService;
    }

    @Override
    public void processing(Model transaction) {
//        Set<Map.Entry<Long, Long>> allBids = transactionDaoDb.getAll(((Update) transaction).getTypeUpdate());
        Set<Map.Entry<Long, Long>> allBids = transactionDaoDb.getAll(TypeUpdate.BID);
        Operation operation = AnaliseTransaction.getBestBid(allBids);
        // todo
        //      подумати як прибрати умовний оператор
        if (operation != null) {
            reportService.add(operation.toReport());
        }
    }
}
