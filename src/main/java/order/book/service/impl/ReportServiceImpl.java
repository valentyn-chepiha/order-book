package order.book.service.impl;

import java.util.ArrayList;
import java.util.List;
import order.book.service.ReportService;

public class ReportServiceImpl implements ReportService {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final List<String> report = new ArrayList<>();

    @Override
    public void add(String line) {
        report.add(line);
    }

    @Override
    public String buildReport() {
        return String.join(LINE_SEPARATOR, report);
    }
}