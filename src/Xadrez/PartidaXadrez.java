package Xadrez;

import PecasXadrez.Rei;
import PecasXadrez.Torre;
import Tabuleiro.Tabuleiro;
import Tabuleiro.Posicao;
import Tabuleiro.Peca;
import Xadrez.Cor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartidaXadrez {
    private Tabuleiro tabuleiro;
    private int vez;
    private Cor jogadorAtual;
    private boolean check;

    private List<Peca> pecaDoTabuleiro = new ArrayList<>();
    private List<Peca> pecaCapturadas = new ArrayList<>();

    public PartidaXadrez(){
        tabuleiro = new Tabuleiro(8,8);
        vez = 1;
        jogadorAtual = Cor.BRANCO;
        iniciaPartida();
    }

    public int getVez() {
        return vez;
    }

    public Cor getJogadorAtual() {
        return jogadorAtual;
    }

   public boolean getCheck(){
        return check;
   }

    public static PecaXadrez[][] getPecas(){
        PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i = 0; i < tabuleiro.getLinhas(); i++){
            for (int j = 0; j < tabuleiro.getColunas(); j++){
                mat[i][j] = (PecaXadrez) tabuleiro.peca(i,j);

            }
        }
        return mat;
    }
    public boolean[][] possivelMovimento(XadrezPosicao origemPosicao){
        Posicao posicao = origemPosicao.toPosicao();
        validaOrigemPosicao(posicao);
        return tabuleiro.peca(posicao).possivelMovimento();
    }
    public PecaXadrez executarMovimentoXadrez(XadrezPosicao origemPosicao, XadrezPosicao destinoPosicao){
        Posicao origem = origemPosicao.toPosicao();
        Posicao destino = destinoPosicao.toPosicao();
        validaOrigemPosicao(origem);
        validaAlvoPosicao(origem, destino);
        Peca capturaPeca = makeMove(origem, destino);

        if (testeCheck(jogadorAtual)){
            desfazerMove(origem,destino,capturaPeca);
            throw new XadrezException("voce nao pode se colocar em check");
        }
        check = (testeCheck(oponente(jogadorAtual))) ? true: false;

        trocaVez();
        return (PecaXadrez) capturaPeca;
    }
    private Peca makeMove(Posicao origem, Posicao destino){
        Peca p = tabuleiro.removePeca(origem);
        Peca capturaPeca = tabuleiro.removePeca(destino);
        tabuleiro.lugarPeca(p, destino);

        if (capturaPeca != null){
            pecaDoTabuleiro.remove(pecaCapturadas);
            pecaCapturadas.add(capturaPeca);
        }
        return capturaPeca;
    }
    private void desfazerMove(Posicao origem, Posicao destino, Peca capturaPeca){
        Peca p = tabuleiro.removePeca(destino);
        tabuleiro.lugarPeca(p, origem);

        if (capturaPeca != null){
            tabuleiro.lugarPeca(capturaPeca, destino);
            pecaCapturadas.remove(capturaPeca);
            pecaDoTabuleiro.add(capturaPeca);

        }
    }
    public void  validaOrigemPosicao(Posicao posicao){
        if (!tabuleiro.existePosicao(posicao)){
            throw new XadrezException("nao existe peca na posicao de origem");
        }
        if (jogadorAtual != (PecaXadrez) tabuleiro.peca(posicao).getCor()){
            throw  new XadrezException("a peça escolhida nao é sua");
        }
        if (!tabuleiro.peca(posicao).existePossivelMovimento()){
            throw  new XadrezException("nao existe movimentos possiveis para a peça escolhida");
        }
    }
    private void validaAlvoPosicao( Posicao origem, Posicao destino){
        if (!tabuleiro.peca(origem).possivelMovimento(destino)){
            throw  new XadrezException("a peca escolhida nao pode se mover para a posicao de destino");
        }
    }
    private void trocaVez(){
        vez++;
        jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO: Cor.BRANCO;
    }
    private Cor oponente(Cor cor){
        return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
    }
    private PecaXadrez Rei(Cor cor){
        List<Peca> list = pecaDoTabuleiro.stream().filter(x ->((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
        for (Peca p: list){
            if (p instanceof  Rei){
                return (PecaXadrez) p;
            }
        }
        throw new IllegalStateException("não existe "+cor+"no rei no do tabuleiro");
    }

    private boolean testeCheck(Cor cor){
        Posicao ReiPosicao = Rei(cor).getXadrezPosicao().toPosicao();
        List<Peca> PecasOponentes =pecaDoTabuleiro.stream().filter(x ->((PecaXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());

        for (Peca p: PecasOponentes){
            boolean[][] mat = p.possivelMovimento();
            if(mat[ReiPosicao.getLinha()][ReiPosicao.getColuna()]){
                return true;
            }
        }
        return false;
    }

    private void lugarNovaPeca(char colunas, int linha, PecaXadrez peca){
        tabuleiro.lugarPeca(peca, new XadrezPosicao(colunas, linha).toPosicao());
        pecaDoTabuleiro.add(peca);
    }
    private void iniciaPartida(){
        lugarNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
        lugarNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
        lugarNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
        lugarNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
        lugarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

        lugarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
        lugarNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
        lugarNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
        lugarNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
        lugarNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
        lugarNovaPeca('d', 8, new Torre(tabuleiro, Cor.PRETO));
    }
}
