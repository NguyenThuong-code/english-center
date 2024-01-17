package com.example.centerenglish.type;

import com.example.centerenglish.response.EnumResponse;

public enum MarkType {
    SIX_POINT_FIVE(1,"6.5"), SEVEN(2,"7.0"), SEVEN_POINT_FIVE(3,"7.5");

    private final Integer id;
    private final String name;

    MarkType(int id, String name) {
        this.id=id;
        this.name= name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static EnumResponse getInstance(Integer type) {
        for (var value : MarkType.values()) {
            if (value.getId() == type) {
                return new EnumResponse(value.getId(), value.getName());
            }
        }
        return null;
    }
}
