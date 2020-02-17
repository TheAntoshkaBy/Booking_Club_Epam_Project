package by.epam.booking.format;

import by.epam.booking.enumeration.PageFormatList;

public class PageFormat {

    private PageFormatList pageFormat;
    private String page;

    public PageFormat() {
        page = "null";
        pageFormat = PageFormatList.FORWARD;
    }

    public PageFormat(PageFormatList pageFormat, String page)
    {
        this.page = page;
        this.pageFormat = pageFormat;
    }

    public PageFormatList getPageFormat() {
        return pageFormat;
    }

    public void setPageFormat(PageFormatList pageFormat) {
        this.pageFormat = pageFormat;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
