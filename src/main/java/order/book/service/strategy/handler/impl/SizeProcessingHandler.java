package order.book.service.strategy.handler.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import order.book.dao.TransactionDaoDb;
import order.book.model.Model;
import order.book.model.Query;
import order.book.model.types.TypeUpdate;
import order.book.service.ReportService;
import order.book.service.strategy.handler.ProcessingHandler;

public class SizeProcessingHandler implements ProcessingHandler {
    private final TransactionDaoDb transactionDaoDb;
    private final ReportService reportService;

    public SizeProcessingHandler(TransactionDaoDb transactionDaoDb,
                                 ReportService reportService) {
        this.transactionDaoDb = transactionDaoDb;
        this.reportService = reportService;
    }

    @Override
    public void processing(Model transaction) {
        List<Map.Entry<Long, Long>> items = Arrays.stream(TypeUpdate.values())
                .map(transactionDaoDb::getAll)
                .flatMap(Collection::stream)
                .filter(e -> e.getKey().equals(((Query)transaction).getPrice()))
                .collect(Collectors.toList());
        if (items.size() == 0) {
            reportService.add("0");
            return;
        }
        items.forEach(e -> reportService.add("" + e.getValue()));
    }
}
