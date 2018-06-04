package com.github.schionato.sheet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Linha {

    private final List<Coluna> colunas;

    public Linha() {
	this.colunas = new ArrayList<>();
    }

    public void add(Coluna coluna) {
        this.colunas.add(coluna);
    }

    public List<Coluna> getColunas() {
	return Collections.unmodifiableList(colunas);
    }
}
