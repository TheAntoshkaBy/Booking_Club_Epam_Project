package by.epam.booking.command;

import by.epam.booking.type.PageChangeType;

public class Router {

    private PageChangeType pageFormat;
    private String page;

    public Router() {
        page = "null";
        pageFormat = PageChangeType.FORWARD;
    }

    public Router(PageChangeType pageFormat, String page)
    {
        this.page = page;
        this.pageFormat = pageFormat;
    }

    public PageChangeType getPageFormat() {
        return pageFormat;
    }

    public void setPageFormat(PageChangeType pageFormat) {
        this.pageFormat = pageFormat;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
