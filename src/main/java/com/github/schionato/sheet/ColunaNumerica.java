package com.github.schionato.sheet;

public class ColunaNumerica implements Coluna<Double> {

    private final double raw;

    public ColunaNumerica(double raw) {
	this.raw = raw;
    }

    @Override
    public Double read() {
	return raw;
    }

}
