package com.github.schionato.sheet;

public class CelulaString implements Celula<String> {

    private final String value;

    public CelulaString(String value) {
	this.value = value;
    }

    @Override
    public String read() {
	return value;
    }

}
