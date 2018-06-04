package com.github.schionato.sheet;

import java.util.Collections;
import java.util.List;

public class Linha {

    private final List<Celula> celulas;

    public Linha(List<Celula> celulas) {
	this.celulas = celulas;
    }

    List<Celula> getCelulas() {
	return Collections.unmodifiableList(celulas);
    }
}
