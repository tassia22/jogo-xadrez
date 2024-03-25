package Xadrez;

import Tabuleiro.Posicao;

public class XadrezPosicao {
    private char colunas;
    private int linha;

    public XadrezPosicao(char coluna, int linha){
        if (coluna < 'a'|| coluna > 'h' || linha < 1 || linha > 8){
            throw  new XadrezException("erro instanciando XadrezException. valores validos sao de a1 ate h8. ");
        }
        this.colunas = coluna;
        this.linha = linha;
    }


    public char getColunas() {
        return colunas;
    }

    public int getLinha() {
        return linha;
    }

    protected Posicao toPosicao(){
        return  new Posicao(8 - linha, colunas - 'a');
    }
    protected static XadrezPosicao fromPosicao(Posicao posicao){
        return  new XadrezPosicao((char) ((char) 'a' + posicao.getColunas()), 8 - posicao.getLinha());
    }
    public String toString(){
        return  ""+colunas + linha;
    }
}
