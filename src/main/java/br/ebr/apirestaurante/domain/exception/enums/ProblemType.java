package br.ebr.apirestaurante.domain.exception.enums;

import lombok.Getter;

@Getter
public enum ProblemType {

    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
    REGRA_NEGOCIO("/bad-request", "Regra de negócio não atendida");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://apirestaurante.com.br" + path;
        this.title = title;
    }

}
