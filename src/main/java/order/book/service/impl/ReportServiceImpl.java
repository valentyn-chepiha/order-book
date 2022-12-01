package order.book.service.impl;

import order.book.service.FileWriterService;
import order.book.service.ReportService;

public class ReportServiceImpl implements ReportService {
    private static final String LINE_SEPARATOR = "\n";
    private final FileWriterService fileWriterService;

    public ReportServiceImpl(FileWriterService fileWriterService) {
        this.fileWriterService = fileWriterService;
    }

    @Override
    public void add(String line) {
        fileWriterService.write(line + LINE_SEPARATOR);
    }
}
