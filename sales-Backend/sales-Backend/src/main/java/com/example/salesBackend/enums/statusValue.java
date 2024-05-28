package com.example.salesBackend.enums;

public enum statusValue {
    DEACTIVE (0),
    ACTIVE(1);

    private int sts;

    statusValue(int sts)
    {
        this.sts=sts;

    }
    public int sts()
    {
        return sts;
    }
}
