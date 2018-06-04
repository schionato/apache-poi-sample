package com.github.schionato.sheet;

public class ColunaString implements Coluna<String> {

    private final String value;

    public ColunaString(String value) {
	this.value = value;
    }

    @Override
    public String read() {
	return value;
    }

}
