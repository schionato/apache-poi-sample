package com.github.schionato.poi;

import com.github.schionato.sheet.Celula;
import com.github.schionato.sheet.CelulaData;
import com.github.schionato.sheet.CelulaNumerica;
import com.github.schionato.sheet.CelulaString;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

class PoiConversorDeCelula {

    private final Celula celula;

    PoiConversorDeCelula(Cell cell) {
	this.celula = converte(cell);
    }

    private static Celula converte(Cell cell) {
	CellType type = cell.getCellTypeEnum();

	if (type.equals(CellType.STRING)) {
	    return new CelulaString(cell.getStringCellValue());
	}
	if (type.equals(CellType.NUMERIC)) {
	    if (HSSFDateUtil.isCellDateFormatted(cell)) {
		return new CelulaData(cell.getDateCellValue());
	    }
	    return new CelulaNumerica(cell.getNumericCellValue());
	}
	return null;
    }

    Celula get() {
	return celula;
    }
}
