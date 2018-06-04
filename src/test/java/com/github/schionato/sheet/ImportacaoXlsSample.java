package com.github.schionato.sheet;

import com.github.schionato.poi.PoiLeitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImportacaoXlsSample {

    private InputStream xls;

    @BeforeEach
    void setUp() {
        this.xls = getClass().getResourceAsStream("/sample-poi.xls");
    }

    @Test
    void lendoTodasTabelas() {
        Leitor leitor = new PoiLeitor(xls);

        assertEquals(2, leitor.getTabelas().size());
        assertEquals("tb01", leitor.getTabelas().get(0).getName());
        assertEquals("tb02", leitor.getTabelas().get(1).getName());
    }

    @Test
    void buscandoUmaTabelaPeloNome() {
	Leitor leitor = new PoiLeitor(xls);
	Tabela tabela = leitor.getTabelaPeloNome("tb02");

	assertEquals("tb02", tabela.getName());
    }

    @Test
    void buscandoUmaTabelaPorUmNomeInvalido() {
        Leitor leitor = new PoiLeitor(xls);
        assertThrows(Tabela.NotFound.class, ()-> leitor.getTabelaPeloNome("abc"));
    }

    @Test
    void verificandoQuantidadeLinhas() {
	Tabela tabela = new PoiLeitor(xls).getTabelaPeloNome("tb01");

	assertEquals(3, tabela.getLinhas().size());
    }

    @Test
    void leAsColunasDaPrimeiraLinha() {
	Linha linha = new PoiLeitor(xls).getTabelaPeloNome("tb01")
			.getLinhas()
			.get(0);

	assertEquals(6, linha.getColunas().size());
    }
}
