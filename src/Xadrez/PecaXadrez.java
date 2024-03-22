package Xadrez;

import Tabuleiro.Peca;
import Tabuleiro.Tabuleiro;

public class PecaXadrez extends Peca {
    private  Cor cor;


    public void PecaXadrez() {

    }
    public PecaXadrez(Tabuleiro tabuleiro, Cor cor){
        super(tabuleiro);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }
}
