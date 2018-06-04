package com.github.schionato.poi;

import com.github.schionato.sheet.Leitor;
import com.github.schionato.sheet.Tabela;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PoiLeitor implements Leitor {

    private final List<Tabela> tabelas;

    public PoiLeitor(InputStream inputStream) {

	try (POIFSFileSystem poi = new POIFSFileSystem(inputStream)) {
	    HSSFWorkbook workbook = new HSSFWorkbook(poi);
	    this.tabelas = new ArrayList<>(workbook.getNumberOfSheets());
	    for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
		HSSFSheet sheet = workbook.getSheetAt(i);
		String name = sheet.getSheetName();
		Tabela tabela = new Tabela(name);
		tabelas.add(tabela);
	    }
	} catch (Exception e) {
	    throw new IllegalArgumentException();
	}

    }

    @Override
    public List<Tabela> getTabelas() {
	return tabelas;
    }

}
