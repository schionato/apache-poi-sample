package com.github.schionato.sheet;

public class CelulaNumerica implements Celula<Double> {

    private final double raw;

    public CelulaNumerica(double raw) {
	this.raw = raw;
    }

    @Override
    public Double read() {
	return raw;
    }

}
