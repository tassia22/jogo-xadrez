package Tabuleiro;

public class Posicao {
    private int linha;
    private int coluna;

    public Posicao(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
    }

    public String toString(){
        return linha+", "+coluna;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }
    public void setValores(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColunas(int colunas) {
        this.coluna = coluna;
    }


}
