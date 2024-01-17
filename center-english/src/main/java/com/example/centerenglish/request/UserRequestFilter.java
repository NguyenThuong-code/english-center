package com.example.centerenglish.request;

public class UserRequestFilter extends PageRequestDTO{
    private String nameFilter;

    public String getName() {
        return nameFilter;
    }

    public void setName(String name) {
        this.nameFilter = name;
    }
}
