package com.github.schionato.sheet;

import com.github.schionato.poi.PoiLeitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImportacaoXlsxSample {

    private InputStream xlsx;

    @BeforeEach
    void setUp() {
        this.xlsx = getClass().getResourceAsStream("/sample-poi.xlsx");
    }

    @Test
    void lendoTodasTabelas() {
        Leitor leitor = new PoiLeitor(xlsx);

        assertEquals(2, leitor.getTabelas().size());
        assertEquals("tb01", leitor.getTabelas().get(0).getName());
        assertEquals("tb02", leitor.getTabelas().get(1).getName());
    }

    @Test
    void buscandoUmaTabelaPeloNome() {
	Leitor leitor = new PoiLeitor(xlsx);
	Tabela tabela = leitor.getTabelaPeloNome("tb02");

	assertEquals("tb02", tabela.getName());
    }

    @Test
    void buscandoUmaTabelaPorUmNomeInvalido() {
        Leitor leitor = new PoiLeitor(xlsx);
        assertThrows(Tabela.NotFound.class, ()-> leitor.getTabelaPeloNome("abc"));
    }

    @Test
    void verificandoQuantidadeLinhas() {
	Tabela tabela = new PoiLeitor(xlsx).getTabelaPeloNome("tb01");

	assertEquals(3, tabela.getLinhas().size());
    }

    @Test
    void leAsColunasDaPrimeiraLinha() {
	Linha linha = new PoiLeitor(xlsx).getTabelaPeloNome("tb01")
			.getLinhas()
			.get(0);

	assertEquals(6, linha.getCelulas().size());
    }

    @Test
    void lendoAPrimeiraLinhaColunaPorColuna() {
	Linha linha = new PoiLeitor(xlsx).getTabelaPeloNome("tb01")
			.getLinhas()
			.get(0);

	assertEquals("First name", linha.getCelulas().get(0).read());
	assertEquals("Last name", linha.getCelulas().get(1).read());
	assertEquals("Age", linha.getCelulas().get(2).read());
	assertEquals("Birthdate", linha.getCelulas().get(3).read());
	assertEquals("Dinheiro", linha.getCelulas().get(4).read());
	assertEquals("Porcent", linha.getCelulas().get(5).read());
    }

    @Test
    void lendoUmNumero() {
	int ageColumnIndex = 2;

	Linha linha = new PoiLeitor(xlsx).getTabelaPeloNome("tb01")
			.getLinhas()
			.get(1);

	assertEquals(30.0, linha.getCelulas().get(ageColumnIndex).read());
    }

    @Test
    void lendoUmaData() throws ParseException {
	int birthDateColumnIndex = 3;
	Date data = new SimpleDateFormat("dd/MM/yyyy").parse("22/09/1987");

	Linha linha = new PoiLeitor(xlsx).getTabelaPeloNome("tb01")
			.getLinhas()
			.get(1);

	assertEquals(data, linha.getCelulas().get(birthDateColumnIndex).read());
    }

    @Test
    void lendoValorMonetario() {
	int currencyColumnIndex = 4;

	Linha linha = new PoiLeitor(xlsx).getTabelaPeloNome("tb01")
			.getLinhas()
			.get(1);

	assertEquals(100000.0, linha.getCelulas().get(currencyColumnIndex).read());
    }

    @Test
    void lendoValorPorcentagem() {
	int currencyColumnIndex = 5;

	Linha linha = new PoiLeitor(xlsx).getTabelaPeloNome("tb01")
			.getLinhas()
			.get(1);

	assertEquals(1.1, linha.getCelulas().get(currencyColumnIndex).read());
    }
}
