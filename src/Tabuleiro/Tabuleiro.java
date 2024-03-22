package Tabuleiro;

public class Tabuleiro {
    private int linhas;
    private int colunas;
    private Peca[][] pecas;

    public Tabuleiro(int linhas, int colunas){
        if(linhas < 1 || colunas < 1){
            throw  new TabuleiroException("erro criando tabuleiro: é necessario que haja pelo menos uma linha e uma coluna");
        }
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Peca[linhas][colunas];
    }
    public Peca peca(int linha, int coluna){
        return pecas[linha][coluna];

    }
    public Peca peca(Posicao posicao){
        if (!existePosicao(posicao)){
            throw  new TabuleiroException("posicao nao tem no tabuleiro");
        }
        return pecas[posicao.getLinha()][posicao.getColunas()];

    }
    public void lugarPeca(Peca peca, Posicao posicao){
        if (temPeca(posicao)){
            throw  new TabuleiroException("ja existe uma peça nessa posicao"+posicao);
        }

        pecas[posicao.getLinha()][posicao.getColunas()] = peca;
        peca.posicao = posicao;
    }
    private boolean existePosicao(int linha, int coluna){
       return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }

    public  boolean existePosicao(Posicao posicao){
        return  existePosicao(posicao.getLinha(), posicao.getColunas());
    }
    public boolean temPeca(Posicao posicao){
        if (!existePosicao(posicao)){
            throw  new TabuleiroException("posicao nao tem no tabuleiro");
        }
        return  peca(posicao) != null;
    }


    public int getLinhas() {
        return linhas;
    }


    public int getColunas() {
        return colunas;
    }



}
