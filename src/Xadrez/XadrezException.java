package Xadrez;

import Tabuleiro.TabuleiroException;

public class XadrezException extends TabuleiroException {
    private static final long seriaLVersionUID = 1L;

    public XadrezException(String msg){
        super(msg);
    }
}
