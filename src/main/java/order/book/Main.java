package order.book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import order.book.dao.TransactionDaoDb;
import order.book.dao.TransactionDaoDbImpl;
import order.book.model.Model;
import order.book.model.Order;
import order.book.model.Query;
import order.book.model.Update;
import order.book.model.types.TypeOrder;
import order.book.model.types.TypeQuery;
import order.book.model.types.TypeTransaction;
import order.book.model.types.TypeUpdate;
import order.book.service.FileReaderService;
import order.book.service.FileWriterService;
import order.book.service.ReportService;
import order.book.service.TransactionService;
import order.book.service.impl.FileReaderServiceImpl;
import order.book.service.impl.FileWriterServiceImpl;
import order.book.service.impl.ReportServiceImpl;
import order.book.service.impl.TransactionServiceImpl;
import order.book.service.strategy.TypeProcessingStrategy;
import order.book.service.strategy.handler.ProcessingHandler;
import order.book.service.strategy.handler.impl.AskProcessingHandler;
import order.book.service.strategy.handler.impl.BestAskProcessingHandler;
import order.book.service.strategy.handler.impl.BestBidProcessingHandler;
import order.book.service.strategy.handler.impl.BidProcessingHandler;
import order.book.service.strategy.handler.impl.BuyProcessingHandler;
import order.book.service.strategy.handler.impl.OrderProcessingHandler;
import order.book.service.strategy.handler.impl.QueryProcessingHandler;
import order.book.service.strategy.handler.impl.SellProcessingHandler;
import order.book.service.strategy.handler.impl.SizeProcessingHandler;
import order.book.service.strategy.handler.impl.UpdateProcessingHandler;
import order.book.service.strategy.impl.OrderProcessingStrategy;
import order.book.service.strategy.impl.QueryProcessingStrategy;
import order.book.service.strategy.impl.TransactionProcessingStrategy;
import order.book.service.strategy.impl.UpdateProcessingStrategy;
import order.book.service.strategy.maps.TypeOrderOperationProcessing;
import order.book.service.strategy.maps.TypeQueryOperationProcessing;
import order.book.service.strategy.maps.TypeTransactionOperationProcessing;
import order.book.service.strategy.maps.TypeUpdateOperationProcessing;

public class Main {
    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    public static void main(String[] args) {
        TransactionDaoDb transactionDaoDb = new TransactionDaoDbImpl();
        FileWriterService fileWriterService = new FileWriterServiceImpl(OUTPUT_FILE);
        ReportService reportService = new ReportServiceImpl(fileWriterService);

        Map<Object, ProcessingHandler> map = new HashMap<>();
        map.put(TypeUpdate.ASK, new AskProcessingHandler(transactionDaoDb));
        map.put(TypeUpdate.BID, new BidProcessingHandler(transactionDaoDb));
        map.put(TypeQuery.BEST_BID, new BestBidProcessingHandler(transactionDaoDb, reportService));
        map.put(TypeQuery.BEST_ASK, new BestAskProcessingHandler(transactionDaoDb, reportService));
        map.put(TypeQuery.SIZE, new SizeProcessingHandler(transactionDaoDb, reportService));
        map.put(TypeOrder.BUY, new BuyProcessingHandler(transactionDaoDb));
        map.put(TypeOrder.SELL, new SellProcessingHandler(transactionDaoDb));

        TypeUpdateOperationProcessing typeUpdateOperationProcessing =
                new TypeUpdateOperationProcessing(map);
        TypeProcessingStrategy<TypeUpdate> updateProcessingStrategy =
                new UpdateProcessingStrategy(typeUpdateOperationProcessing);

        TypeQueryOperationProcessing typeQueryOperationProcessing =
                new TypeQueryOperationProcessing(map);
        TypeProcessingStrategy<TypeQuery> queryProcessingStrategy =
                new QueryProcessingStrategy(typeQueryOperationProcessing);

        TypeOrderOperationProcessing typeOrderOperationProcessing =
                new TypeOrderOperationProcessing(map);
        TypeProcessingStrategy<TypeOrder> orderProcessingStrategy =
                new OrderProcessingStrategy(typeOrderOperationProcessing);

        map.put(TypeTransaction.UPDATE, new UpdateProcessingHandler(updateProcessingStrategy));
        map.put(TypeTransaction.QUERY, new QueryProcessingHandler(queryProcessingStrategy));
        map.put(TypeTransaction.ORDER, new OrderProcessingHandler(orderProcessingStrategy));

        TypeTransactionOperationProcessing typeTransactionOperationProcessing =
                new TypeTransactionOperationProcessing(map);
        TypeProcessingStrategy<TypeTransaction> transactionProcessingStrategy =
                new TransactionProcessingStrategy(typeTransactionOperationProcessing);

        Map<TypeTransaction, Model> mapModels = new HashMap<>();
        mapModels.put(TypeTransaction.ORDER, new Order());
        mapModels.put(TypeTransaction.QUERY, new Query());
        mapModels.put(TypeTransaction.UPDATE, new Update());

        TransactionService transactionService =
                new TransactionServiceImpl(transactionProcessingStrategy, mapModels);

        FileReaderService fileReaderService = new FileReaderServiceImpl();
        List<String> lines = fileReaderService.read(INPUT_FILE);
        transactionService.processing(lines);
    }
}
