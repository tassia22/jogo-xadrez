package Xadrez;

import PecasXadrez.*;
import Tabuleiro.Tabuleiro;
import Tabuleiro.Posicao;
import Tabuleiro.Peca;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartidaXadrez {
    private Tabuleiro tabuleiro;
    private int vez;
    private Cor jogadorAtual;
    private boolean check;
    private boolean checkMate;
    private PecaXadrez enPassanteVulneravel;

    private List<Peca> pecaDoTabuleiro = new ArrayList<>();
    private List<Peca> pecaCapturadas = new ArrayList<>();

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
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

    public boolean getCheck() {
        return check;
    }

    public boolean getCheckMate() {
        return checkMate;
    }

    public PecaXadrez getEnPassanteVulneravel() {
        return enPassanteVulneravel;
    }

    public static PecaXadrez[][] getPecas() {
        PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);

            }
        }
        return mat;
    }

    public boolean[][] possivelMovimento(XadrezPosicao origemPosicao) {
        Posicao posicao = origemPosicao.toPosicao();
        validaOrigemPosicao(posicao);
        return tabuleiro.peca(posicao).possivelMovimento();
    }

    public PecaXadrez executarMovimentoXadrez(XadrezPosicao origemPosicao, XadrezPosicao destinoPosicao) {
        Posicao origem = origemPosicao.toPosicao();
        Posicao destino = destinoPosicao.toPosicao();
        validaOrigemPosicao(origem);
        validaAlvoPosicao(origem, destino);
        Peca capturaPeca = makeMove(origem, destino);

        if (testeCheck(jogadorAtual)) {
            desfazerMove(origem, destino, capturaPeca);
            throw new XadrezException("voce nao pode se colocar em check");
        }
        PecaXadrez movedPeca = (PecaXadrez) tabuleiro.peca(destino);

        check = (testeCheck(oponente(jogadorAtual))) ? true : false;

        if (testeCheckMate(oponente(jogadorAtual))) {
            checkMate = true;
        } else {
            trocaVez();
        }

        //movimento especial
        if (movedPeca instanceof Peao && (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2)) {
            enPassanteVulneravel = movedPeca;
        } else {
            enPassanteVulneravel = null;
        }
        return (PecaXadrez) capturaPeca;
    }

    private Peca makeMove(Posicao origem, Posicao destino) {
        PecaXadrez p = (PecaXadrez) tabuleiro.removePeca(origem);
        p.incrementaMoveCount();
        Peca capturaPeca = tabuleiro.removePeca(destino);
        tabuleiro.lugarPeca(p, destino);

        if (capturaPeca != null) {
            pecaDoTabuleiro.remove(pecaCapturadas);
            pecaCapturadas.add(capturaPeca);
        }
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1)
            PartidaXadrez Roque = (PartidaXadrez) tabuleiro.removePeca(destinoT);
            tabuleiro.lugarPeca(Roque, origemT);
            Roque.incrementataMoveCount();
        }
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1)
            PartidaXadrez Roque = (PartidaXadrez) tabuleiro.removePeca(destinoT);
            tabuleiro.lugarPeca(Roque, origemT);
            Roque.incrementataMoveCount();
        }
        //movimento especial em passante
        if (p instanceof Peao) {
            if (origem.getColuna() != destino.getColuna() && capturaPeca == null) {
                Posicao peaoPosicao;
                if (p.getCor() == Cor.BRANCO) {
                    peaoPosicao = new Posicao(destino.getLinha() + 1, destino.getColuna());

                } else {
                    peaoPosicao = new Posicao(destino.getLinha() - 1, destino.getColuna());

                }
                capturaPeca = tabuleiro.removePeca(peaoPosicao);
                capturaPeca.add(capturaPeca);
                pecaDoTabuleiro.remove(capturaPeca);
            }
        }
        return capturaPeca;
    }

    private boolean desfazerMove(Posicao origem, Posicao destino, Peca capturaPeca) {
        PecaXadrez p = (PecaXadrez) tabuleiro.removePeca(destino);
        p.decrementaMoveCount();
        tabuleiro.lugarPeca(p, origem);

        if (capturaPeca != null) {
            tabuleiro.lugarPeca(capturaPeca, destino);
            pecaCapturadas.remove(capturaPeca);
            pecaDoTabuleiro.add(capturaPeca);

        }

        if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1)
            PartidaXadrez Roque = (PartidaXadrez) tabuleiro.removePeca(origemT);
            tabuleiro.lugarPeca(Roque, destinoT);
            Roque.incrementaMoveCount();
        }
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1)
            PartidaXadrez Roque = (PartidaXadrez) tabuleiro.removePeca(origemT);
            tabuleiro.lugarPeca(Roque, destinoT);
            Roque.incrementaMoveCount();
        }
        //movimento especial en passant
        if (p instanceof Peao) {
            if (origem.getColuna() != destino.getColuna() && capturaPeca == enPassanteVulneravel) {
                PecaXadrez peao = (PecaXadrez) tabuleiro.removePeca(destino);
                Posicao peaoPosicao;
                if (p.getCor() == Cor.BRANCO) {
                    peaoPosicao = new Posicao(3, destino.getColuna());

                } else {
                    peaoPosicao = new Posicao(4, destino.getColuna());

                }
                tabuleiro.lugarPeca(peao, peaoPosicao);

            }
        }
        public void validaOrigemPosicao (Posicao posicao){

            if (!tabuleiro.existePosicao(posicao)) {
                throw new XadrezException("nao existe peca na posicao de origem");
            }
            if (jogadorAtual != (PecaXadrez) tabuleiro.peca(posicao).getCor()) {
                throw new XadrezException("a peça escolhida nao é sua");
            }
            if (!tabuleiro.peca(posicao).existePossivelMovimento()) {
                throw new XadrezException("nao existe movimentos possiveis para a peça escolhida");
            }
        }
        private void validaAlvoPosicao (Posicao origem, Posicao destino){
            if (!tabuleiro.peca(origem).possivelMovimento(destino)) {
                throw new XadrezException("a peca escolhida nao pode se mover para a posicao de destino");
            }
        }
        private void trocaVez () {
            vez++;
            jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
        }
        private Cor oponente (Cor cor){
            return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
        }
        private PecaXadrez Rei (Cor cor){
            List<Peca> list = pecaDoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor).collect(Collectors.toList());
            for (Peca p : list) {
                if (p instanceof Rei) {
                    return (PecaXadrez) p;
                }
            }
            throw new IllegalStateException("não existe " + cor + "no rei no do tabuleiro");
        }

        private boolean testeCheck (Cor cor){
            Posicao ReiPosicao = Rei(cor).getXadrezPosicao().toPosicao();
            List<Peca> PecasOponentes = pecaDoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == oponente(cor)).collect(Collectors.toList());

            for (Peca p : PecasOponentes) {
                boolean[][] mat = p.possivelMovimento();
                if (mat[ReiPosicao.getLinha()][ReiPosicao.getColuna()]) {
                    return true;
                }
            }
            return false;
        }

        private boolean testeCheckMate (Cor cor){
            if (!testeCheck(cor)) {
                return false;

            }
            List<Peca> list = pecaDoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor).collect(Collectors.toList());
            for (Peca p : list) {
                boolean[][] mat = p.possivelMovimento();
                for (int i = 0; i < tabuleiro.getLinhas(); i++) {
                    for (int j = 0; j < tabuleiro.getColunas(); j++) {
                        if (mat[i][j]) {
                            Posicao origem = (PecaXadrez) p.getXadrezPosicao().toPoisition();
                            Posicao destino = new Posicao(i, j);
                            Peca capturaPeca = makeMove(origem, destino);
                            boolean testeCheck = testeCheck(cor);
                            desfazerMove(origem, destino, capturaPeca);
                            if (!testeCheck) {
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }


        private void lugarNovaPeca ( char colunas, int linha, PecaXadrez peca){
            tabuleiro.lugarPeca(peca, new XadrezPosicao(colunas, linha).toPosicao());
            pecaDoTabuleiro.add(peca);
        }
        private void iniciaPartida () {
            lugarNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
            lugarNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
            lugarNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
            lugarNovaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
            lugarNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
            lugarNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
            lugarNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
            lugarNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
            lugarNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
            lugarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO, this));
            lugarNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO, this));
            lugarNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO, this));
            lugarNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO, this));
            lugarNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO, this));

            lugarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
            lugarNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
            lugarNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
            lugarNovaPeca('d', 8, new Rainha(tabuleiro, Cor.PRETO));
            lugarNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO, this));
            lugarNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
            lugarNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
            lugarNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
            lugarNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
            lugarNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
            lugarNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
            lugarNovaPeca('d', 8, new Torre(tabuleiro, Cor.PRETO));
            lugarNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETO, this));
            lugarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
            lugarNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETO, this));
            lugarNovaPeca('w', 7, new Torre(tabuleiro, Cor.PRETO));
        }
    }
}