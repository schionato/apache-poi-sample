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

    @Test
    void lendoAPrimeiraLinhaColunaPorColuna() {
	Linha linha = new PoiLeitor(xls).getTabelaPeloNome("tb01")
			.getLinhas()
			.get(0);

	assertEquals("First name", linha.getColunas().get(0).read());
	assertEquals("Last name", linha.getColunas().get(1).read());
	assertEquals("Age", linha.getColunas().get(2).read());
	assertEquals("Birthdate", linha.getColunas().get(3).read());
	assertEquals("Dinheiro", linha.getColunas().get(4).read());
	assertEquals("Porcent", linha.getColunas().get(5).read());
    }

    @Test
    void lendoUmNumero() {
	int ageColumnIndex = 2;

	Linha linha = new PoiLeitor(xls).getTabelaPeloNome("tb01")
			.getLinhas()
			.get(1);

	assertEquals(30.0, linha.getColunas().get(ageColumnIndex).read());
    }

    @Test
    void lendoUmaData() throws ParseException {
	int birthDateColumnIndex = 3;
	Date data = new SimpleDateFormat("dd/MM/yyyy").parse("22/09/1987");

	Linha linha = new PoiLeitor(xls).getTabelaPeloNome("tb01")
			.getLinhas()
			.get(1);

	assertEquals(data, linha.getColunas().get(birthDateColumnIndex).read());
    }

    @Test
    void lendoValorMonetario() {
	int currencyColumnIndex = 4;

	Linha linha = new PoiLeitor(xls).getTabelaPeloNome("tb01")
			.getLinhas()
			.get(1);

	assertEquals(100000.0, linha.getColunas().get(currencyColumnIndex).read());
    }

    @Test
    void lendoValorPorcentagem() {
	int currencyColumnIndex = 5;

	Linha linha = new PoiLeitor(xls).getTabelaPeloNome("tb01")
			.getLinhas()
			.get(1);

	assertEquals(1.1, linha.getColunas().get(currencyColumnIndex).read());
    }
}
