package com.github.schionato.poi;

import com.github.schionato.sheet.Coluna;
import com.github.schionato.sheet.ColunaData;
import com.github.schionato.sheet.ColunaNumerica;
import com.github.schionato.sheet.ColunaString;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

class PoiConversorDeColuna {

    private final Coluna coluna;

    PoiConversorDeColuna(Cell cell) {
	this.coluna = converte(cell);
    }

    private static Coluna converte(Cell cell) {
	CellType type = cell.getCellTypeEnum();

	if (type.equals(CellType.STRING)) {
	    return new ColunaString(cell.getStringCellValue());
	}
	if (type.equals(CellType.NUMERIC)) {
	    if (HSSFDateUtil.isCellDateFormatted(cell)) {
		return new ColunaData(cell.getDateCellValue());
	    }
	    return new ColunaNumerica(cell.getNumericCellValue());
	}
	return null;
    }

    Coluna get() {
	return coluna;
    }
}
