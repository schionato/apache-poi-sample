package com.github.schionato.sheet;

import java.util.Collections;
import java.util.List;

public class Tabela {

    private final String name;
    private final List<Linha> linhas;

    public Tabela(String name, List<Linha> linhas) {
	this.name = name;
	this.linhas = linhas;
    }

    List<Linha> getLinhas() {
	return Collections.unmodifiableList(linhas);
    }

    public String getName() {
	return name;
    }

    public static class NotFound extends RuntimeException {}
}
