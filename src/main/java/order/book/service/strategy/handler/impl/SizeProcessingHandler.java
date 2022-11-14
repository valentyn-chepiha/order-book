package order.book.service.strategy.handler.impl;

import java.util.Map;
import java.util.Set;
import order.book.dao.TransactionDaoDb;
import order.book.model.types.TypeUpdate;
import order.book.service.ReportService;
import order.book.service.strategy.handler.ProcessingHandler;

public class SizeProcessingHandler implements ProcessingHandler {
    private static final int INDEX_OF_COUNT = 1;
    private final TransactionDaoDb transactionDaoDb;
    private final ReportService reportService;

    public SizeProcessingHandler(TransactionDaoDb transactionDaoDb,
                                 ReportService reportService) {
        this.transactionDaoDb = transactionDaoDb;
        this.reportService = reportService;
    }

    @Override
    public void processing(String[] params) {
        Long price = Long.parseLong(params[INDEX_OF_COUNT]);
        for (TypeUpdate typeUpdate : TypeUpdate.values()) {
            Set<Map.Entry<Long, Long>> transactions = transactionDaoDb.getAll(typeUpdate);
            for (Map.Entry<Long, Long> transaction : transactions) {
                if (transaction.getKey().equals(price)) {
                    reportService.add("" + transaction.getValue());
                }
            }
        }
    }
}
