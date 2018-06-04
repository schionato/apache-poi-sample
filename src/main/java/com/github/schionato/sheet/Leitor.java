package com.github.schionato.sheet;

import java.util.List;

public interface Leitor {

    List<Tabela> getTabelas();

    Tabela getTabelaPeloNome(String nome);
}
