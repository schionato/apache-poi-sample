package com.github.schionato.sheet;

import java.util.Date;

public class CelulaData implements Celula<Date> {

    private final Date raw;

    public CelulaData(Date raw) {
        this.raw = raw;
    }

    @Override
    public Date read() {
        return raw;
    }
}
