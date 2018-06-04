package com.github.schionato.poi;

import com.github.schionato.sheet.ColunaData;
import com.github.schionato.sheet.ColunaNumerica;
import com.github.schionato.sheet.ColunaString;
import com.github.schionato.sheet.Coluna;
import com.github.schionato.sheet.Leitor;
import com.github.schionato.sheet.Linha;
import com.github.schionato.sheet.Tabela;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
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
		Tabela tabela = new Tabela(name);

		sheet.rowIterator().forEachRemaining(row -> {
		    Linha linha = new Linha();
		    row.cellIterator().forEachRemaining(cell -> {
			Coluna coluna = new PoiConversorDeColuna(cell).get();
			linha.add(coluna);
		    });
		    tabela.add(linha);
		});

		tabelas.add(tabela);
	    }
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}

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
