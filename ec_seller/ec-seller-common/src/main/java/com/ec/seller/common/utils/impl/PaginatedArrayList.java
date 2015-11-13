package com.ec.seller.common.utils.impl;

import java.util.ArrayList;

import com.ec.seller.common.utils.PaginatedList;

public class PaginatedArrayList<T> extends ArrayList<T> implements PaginatedList<T> {

    public static final int PAGESIZE_DEFAULT = 20;

    private int pageSize;

    private int index;


    private int totalItem;

    private int totalPage;


    private int startRow;

    private int endRow;


    public PaginatedArrayList() {
          repaginate();
    }


    public PaginatedArrayList(int index, int pageSize) {
        this.index = index;
        this.pageSize = pageSize;
        repaginate();
    }


      public boolean isFirstPage(){
          return index <= 1;
      }


    public boolean isMiddlePage() {
         return !(isFirstPage() || isLastPage());
    }


    public boolean isLastPage() {
        return index >= totalPage;
    }


    public boolean isNextPageAvailable() {
        return !isLastPage();
    }

    public boolean isPreviousPageAvailable() {
          return !isFirstPage();
    }


    public int getNextPage() {
        if(isLastPage()) {
            return totalItem;
        } else {
            return index+1;
        }
    }

    public int getPreviousPage() {
        if(isFirstPage()) {
            return 1;
        } else {
            return index - 1;
        }
    }


    public int getPageSize() {
        return pageSize;
    }



    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        repaginate();
    }



    public int getIndex() {
        return index;
    }



    public void setIndex(int index) {
        this.index = index;
        repaginate();
    }



    public int getTotalItem() {
        return totalItem;
    }



    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
        repaginate();
    }



    public int getTotalPage() {
        return totalPage;
    }


    public int getStartRow() {
        return startRow;
    }
    


    public int getEndRow() {
        return endRow;
    }


    private void repaginate() {
        if (pageSize < 1) { 
            pageSize = PAGESIZE_DEFAULT;
        }
        if (index < 1) {
            index = 1;
        }
        if (totalItem > 0) {
            totalPage = totalItem / pageSize + (totalItem % pageSize > 0 ? 1 : 0);
            if(index > totalPage) {
                index = totalPage; 
            }
            endRow = index * pageSize;
            startRow = endRow - pageSize + 1;
            if(endRow>totalItem) {
                endRow = totalItem;
            }
        }
    }
    
}

