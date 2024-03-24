package Tabuleiro;

public abstract class Peca {
    protected Posicao posicao;
    private Tabuleiro tabuleiro;

    public Peca(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        posicao = null;
    }

    protected Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
    public abstract  boolean[][] possivelMovimento();

    public boolean possivelMovimento(Posicao posicao){
        return  possivelMovimento()[posicao.getLinha()][posicao.getColunas()];
    }
    public  boolean existePossivelMovimento(){
        boolean[][] mat = possivelMovimento();
        for (int i = 0; i < mat.length; i++){
            for (int j = 0; j < mat.length; j++){
                if (mat[i][j]){
                    return  true;
                }
            }

        }
        return false;

    }

}
