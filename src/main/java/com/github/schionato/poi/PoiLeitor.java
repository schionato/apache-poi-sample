package com.github.schionato.poi;

import com.github.schionato.sheet.Celula;
import com.github.schionato.sheet.Leitor;
import com.github.schionato.sheet.Linha;
import com.github.schionato.sheet.Tabela;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PoiLeitor implements Leitor {

    private final List<Tabela> tabelas;

    public PoiLeitor(InputStream inputStream) {
        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
	    this.tabelas = new ArrayList<>(workbook.getNumberOfSheets());

	    for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
		Sheet sheet = workbook.getSheetAt(i);
		String name = sheet.getSheetName();
		List<Linha> linhas = toLinhas(sheet);
		tabelas.add(new Tabela(name, linhas));
	    }
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }

    private static List<Celula> toCelulas(Row apacheLinha) {
        List<Celula> celulas = new ArrayList<>();
	for (Cell apacheCell : apacheLinha) {
	    celulas.add(new PoiConversorDeCelula(apacheCell).get());
	}
	return celulas;
    }

    private static List<Linha> toLinhas(Sheet apacheSheet) {
	List<Linha> linhas = new ArrayList<>();
        for (Row row: apacheSheet) {
	    List<Celula> celulas = toCelulas(row);
	    linhas.add(new Linha(celulas));
	}
	return linhas;
    }

    @Override
    public List<Tabela> getTabelas() {
	return tabelas;
    }

    @Override
    public Tabela getTabelaPeloNome(String nome) {
	return tabelas.stream().filter(t -> t.getName().equals(nome))
			.findAny()
			.orElseThrow(Tabela.NotFound::new);
    }

}
