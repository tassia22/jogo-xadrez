package PecasXadrez;

import Tabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PecaXadrez;
import Tabuleiro.Posicao;

public class Rei extends PecaXadrez {

    public Rei(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    public String toString(){
        return  "R";
    }

   private boolean podeMover(Posicao posicao ){
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return  p == null || p.getCor() != getCor();
   }

    @Override
    public boolean[][] possivelMovimento(){
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0,0);

        //a cima
        p.setValores(posicao.getLinha() - 1, posicao.getColuna());
        if (getTabuleiro().existePosicao(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //a baixo
        p.setValores(posicao.getLinha() + 1, posicao.getColuna());
        if (getTabuleiro().existePosicao(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //a esquerda
        p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //a direita
        p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //noro-oeste
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        //nordeste
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //sudo-oeste
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }


        //sudeste
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        if (getTabuleiro().existePosicao(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        return mat;

    }

}
