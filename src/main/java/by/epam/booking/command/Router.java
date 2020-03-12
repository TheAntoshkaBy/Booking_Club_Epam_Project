package by.epam.booking.command;

import by.epam.booking.type.PageChangeType;

/**
 * The type Router.
 */
public class Router {

    private PageChangeType pageFormat;
    private String page;

    /**
     * Instantiates a new Router.
     */
    public Router() {
        page = "null";
        pageFormat = PageChangeType.FORWARD;
    }

    /**
     * Instantiates a new Router.
     *
     * @param pageFormat the page format
     * @param page       the page
     */
    public Router(PageChangeType pageFormat, String page)
    {
        this.page = page;
        this.pageFormat = pageFormat;
    }

    /**
     * Gets page format.
     *
     * @return the page format
     */
    public PageChangeType getPageFormat() {
        return pageFormat;
    }

    /**
     * Sets page format.
     *
     * @param pageFormat the page format
     */
    public void setPageFormat(PageChangeType pageFormat) {
        this.pageFormat = pageFormat;
    }

    /**
     * Gets page.
     *
     * @return the page
     */
    public String getPage() {
        return page;
    }

    /**
     * Sets page.
     *
     * @param page the page
     */
    public void setPage(String page) {
        this.page = page;
    }
}
