package com.github.schionato.sheet;

import java.util.Date;

public class ColunaData implements Coluna<Date> {

    private final Date raw;

    public ColunaData(Date raw) {
        this.raw = raw;
    }

    @Override
    public Date read() {
        return raw;
    }
}
