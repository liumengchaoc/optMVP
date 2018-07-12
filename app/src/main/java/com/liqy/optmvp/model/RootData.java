package com.liqy.optmvp.model;

import java.util.List;

/**
 * 泛型类，T 代表Data的实际类型
 * Copyright 星期四 YourCompany.
 */
public class RootData<T> {
    public String message;
    public int status;
    public List<T> data;

    @Override
    public String toString() {
        return "RootData{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}
