package com.github.schionato.sheet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tabela {

    private final String name;
    private final List<Linha> linhas;

    public Tabela(String name) {
	this.linhas = new ArrayList<>();
	this.name = name;
    }

    public void add(Linha linha) {
        this.linhas.add(linha);
    }

    public List<Linha> getLinhas() {
	return Collections.unmodifiableList(linhas);
    }

    public String getName() {
	return name;
    }

    public static class NotFound extends RuntimeException {}
}
