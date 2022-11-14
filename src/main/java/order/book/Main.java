package order.book;

import java.util.List;
import order.book.dao.TransactionDaoDb;
import order.book.dao.TransactionDaoDbImpl;
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

        FileReaderService fileReaderService = new FileReaderServiceImpl();
        FileWriterService fileWriterService = new FileWriterServiceImpl();
        ReportService reportService = new ReportServiceImpl();

        TypeUpdateOperationProcessing typeUpdateOperationProcessing =
                new TypeUpdateOperationProcessing(transactionDaoDb);
        TypeProcessingStrategy<TypeUpdate> updateProcessingStrategy =
                new UpdateProcessingStrategy(typeUpdateOperationProcessing);

        TypeQueryOperationProcessing typeQueryOperationProcessing =
                new TypeQueryOperationProcessing(transactionDaoDb, reportService);
        TypeProcessingStrategy<TypeQuery> queryProcessingStrategy =
                new QueryProcessingStrategy(typeQueryOperationProcessing);

        TypeOrderOperationProcessing typeOrderOperationProcessing =
                new TypeOrderOperationProcessing(transactionDaoDb);
        TypeProcessingStrategy<TypeOrder> orderProcessingStrategy =
                new OrderProcessingStrategy(typeOrderOperationProcessing);

        TypeTransactionOperationProcessing typeTransactionOperationProcessing =
                new TypeTransactionOperationProcessing(updateProcessingStrategy,
                        queryProcessingStrategy, orderProcessingStrategy);
        TypeProcessingStrategy<TypeTransaction> transactionProcessingStrategy =
                new TransactionProcessingStrategy(typeTransactionOperationProcessing);

        TransactionService transactionService =
                new TransactionServiceImpl(transactionProcessingStrategy);

        List<String> lines = fileReaderService.read(INPUT_FILE);
        transactionService.processing(lines);
        fileWriterService.write(OUTPUT_FILE, reportService.buildReport());
    }
}
