package com.github.schionato.sheet;

import com.github.schionato.poi.PoiLeitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ImportacaoXlsSample {

    private InputStream xls;

    @BeforeEach
    void setUp() {
        this.xls = getClass().getResourceAsStream("/sample-poi.xls");
    }

    @Test
    void readTabelas() {
        Leitor leitor = new PoiLeitor(xls);

        assertEquals(2, leitor.getTabelas().size());
        assertEquals("tb01", leitor.getTabelas().get(0).getName());
        assertEquals("tb02", leitor.getTabelas().get(1).getName());
    }
}
