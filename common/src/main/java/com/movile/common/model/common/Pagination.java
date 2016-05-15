package com.movile.common.model.common;

import java.util.List;

/**
 * This is the pagination model. This class helps to manage the pagination
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class Pagination<T> {

    /** Current page **/
    private Integer page;

    /** Items per page **/
    private Integer itemsPerPage;

    /** Pages count **/
    private Integer pagesCount;

    /** Total number of items **/
    private Integer itemsCount;

    /** Items of the pagination **/
    private List<T> items;

    /**
     * @return the page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @return page the page to set
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * @return the itemsPerPage
     */
    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    /**
     * @return itemsPerPage the itemsPerPage to set
     */
    public void setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    /**
     * @return the pagesCount
     */
    public Integer getPagesCount() {
        return pagesCount;
    }

    /**
     * @return pagesCount the pagesCount to set
     */
    public void setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
    }

    /**
     * @return the itemsCount
     */
    public Integer getItemsCount() {
        return itemsCount;
    }

    /**
     * @return itemsCount the itemsCount to set
     */
    public void setItemsCount(Integer itemsCount) {
        this.itemsCount = itemsCount;
    }

    /**
     * @return the items
     */
    public List<T> getItems() {
        return items;
    }

    /**
     * @return items the items to set
     */
    public void setItems(List<T> items) {
        this.items = items;
    }
}
