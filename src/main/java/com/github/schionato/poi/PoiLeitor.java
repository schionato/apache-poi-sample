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
import java.util.Iterator;
import java.util.List;

public class PoiLeitor implements Leitor {

    private final List<Tabela> tabelas;

    public PoiLeitor(InputStream inputStream) {
        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
	    this.tabelas = new ArrayList<>(workbook.getNumberOfSheets());

	    for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
		Sheet sheet = workbook.getSheetAt(i);

		String name = sheet.getSheetName();
		Tabela tabela = new Tabela(name);

		sheet.rowIterator().forEachRemaining(row -> {
		    List<Celula> celulas = toCelulas(row);
		    tabela.add(new Linha(celulas));
		});

		tabelas.add(tabela);
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
