package Xadrez;

import Tabuleiro.Peca;
import Tabuleiro.Tabuleiro;
import Tabuleiro.Posicao;

public abstract class PecaXadrez extends Peca {
    private  Cor cor;
    private  int movieCount;


    public PecaXadrez(Tabuleiro tabuleiro, Cor cor){
        super(tabuleiro);
        this.cor = cor;
    }
    protected boolean existePecaAdv(Posicao posicao){
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p != null && p.getCor() != cor;
    }

    public void incrementaMoveCount(){
        movieCount++;
    }
    public void decrementaMoveCount(){
        movieCount--;
    }

    public Cor getCor() {
        return cor;
    }


    public XadrezPosicao getXadrezPosicao(){
        return  XadrezPosicao.fromPosicao(posicao);
    }

    public int getMovieCount() {
        return movieCount;
    }
}
