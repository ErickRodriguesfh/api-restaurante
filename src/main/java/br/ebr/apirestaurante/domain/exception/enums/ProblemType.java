package br.ebr.apirestaurante.domain.exception.enums;

import lombok.Getter;

@Getter
public enum ProblemType {

    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://apirestaurante.com.br" + path;
        this.title = title;
    }

}
