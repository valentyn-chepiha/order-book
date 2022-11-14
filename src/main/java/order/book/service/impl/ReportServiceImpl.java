package order.book.service.impl;

import java.util.ArrayList;
import java.util.List;
import order.book.service.ReportService;

public class ReportServiceImpl implements ReportService {
    private static final String LINE_SEPARATOR = "\n";
    private static final List<String> report = new ArrayList<>();

    @Override
    public void add(String line) {
        report.add(line + LINE_SEPARATOR);
    }

    @Override
    public String buildReport() {
        return String.join("", report);
    }
}
