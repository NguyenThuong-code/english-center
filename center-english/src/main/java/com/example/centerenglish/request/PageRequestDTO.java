package com.example.centerenglish.request;

public class PageRequestDTO {
    int page;
    int size;
    String sortedBy= null;
    String sortDirection = null;

    public PageRequestDTO() {
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String getSortedBy() {
        return sortedBy;
    }

    public String getSortDirection() {
        return sortDirection;
    }
}
