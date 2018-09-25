/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1projeto;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author jrram
 */
public class Game {

    private int altura, largura;
    protected long ultimoTiro = 0;
    protected long ultimoTiro2 = 0;
    protected int cont = 0;
    protected PlanoDeFundo i = new PlanoDeFundo("img/fundo.jpg");
    protected int controle = 0;
    protected int aux = 0;

    public Game(int al, int la) {
        this.altura = al;
        this.largura = la;
    }

    public void inicio(Graphics g, int status) {
        if (controle == 0) {
            i.desenhar(g);
        }

    }

    public void eventos(boolean left, boolean right, Player player) {
        if (left && player.getX() > 0) {
            player.setIncX(-2);
        } else {
            if (right && player.getX() < 1000 - player.getLargura()) {
                player.setIncX(2);
            } else {
                player.setIncX(0);
            }
        }
    }

    public int verificaNivel(ArrayList<Base> inimigos, boolean keystart) { //

        if (keystart) {
            return 1;
        } else if (inimigos.size() > 0) {
            return 1;
        } else {
            return 2;
        }

    }

    public int fim(boolean start) {
        if (start) {
            return 1;
        } else {
            return 0;
        }

    }

    public int controlador(boolean start, boolean restart) { //CONTROLA O JOGO
        if (controle == -1 && restart) {
            controle = 0;

        } else if (controle == 0) {

            if (start) {
                controle = 1;
            }
        }

        return controle;
    }

    public void jogango(Graphics g, Player p, ArrayList<Base> inimigos, ArrayList<Base> inimigos2, ArrayList<Base> tiros, ArrayList<Base> tiros2, Chefe chefe, int nivel, boolean press) {
        if (controle == -1) {
            PlanoDeFundo img = new PlanoDeFundo("img/gameover.jpg");
            img.desenhar(g);
            g.setColor(Color.red);
            g.drawString("Press 'R' to Restart", largura / 2 - 60, altura / 2 + 250);
        }

        if (controle == 0) {
            PlanoDeFundo img = new PlanoDeFundo("img/fndstart.jpg");
            img.desenhar(g);
            g.setColor(Color.red);
            g.drawString("Press 'S' to Start", largura / 2 - 60, altura / 2 + 150);
        }

        if (controle == 3) {
            PlanoDeFundo img = new PlanoDeFundo("img/fndvitoria.png");
            img.desenhar(g);
            g.setColor(Color.red);
            g.drawString("Press 'R' to Restart", largura / 2 - 60, altura / 2 + 150);
        }

        if (controle == 2) {
            PlanoDeFundo img = new PlanoDeFundo("img/nivelcompleto.png");
            img.desenhar(g);
            g.setColor(Color.red);
            g.drawString("Press 'F' to Next Level", largura / 2 - 60, altura / 2 + 150);

        }

        if (press) {
            controle = 1;
        }

        if (controle == 1) {
            if (nivel == 1) {
                i.desenhar(g);
                for (Base b : inimigos) {
                    b.desenhar(g);
                    b.mover();
                }
                for (Base b : tiros) {
                    b.desenhar(g);
                    b.mover();
                }

            } else if (inimigos.isEmpty() && aux == 0) {
                controle = 2;
                aux =1;
            } else if (controle == 1 && nivel == 2) {

                i.desenhar(g);
                for (Base b : inimigos2) {
                    b.desenhar(g);
                    b.mover();
                }

                for (Base b : tiros) {
                    b.desenhar(g);
                    b.mover();
                }

                for (Base b : tiros2) {
                    if (!inimigos2.isEmpty()) {
                        b.desenhar(g);
                        b.mover();
                    }
                }

            }

        }

    }

    public void inimigo(ArrayList<Base> inimigos, ArrayList<Base> tiros) {

        for (int i = 0; i < 36; i++) {

            Inimigo inimigo = new Inimigo("img/inipequeno.png");
            inimigo.setAltura(55);
            inimigo.setLargura(55);
            inimigo.setX(85 + i % 12 * 70);
            inimigo.setY(50 + i / 12 * 70);
            inimigos.add(inimigo);

        }

    }

    void inimigo2(ArrayList<Base> inimigos2, ArrayList<Base> tiros, Chefe chefe) {

        for (int i = 0; i < 40; i++) {

            Inimigo inimigo = new Inimigo("img/inipequeno.png");
            inimigo.setAltura(55);
            inimigo.setLargura(55);
            inimigo.setX(85 + i % 10 * 70);
            inimigo.setY(200 + i / 10 * 70);
            inimigos2.add(inimigo);

        }

        inimigos2.add(chefe);

    }

    public void moveInimigos(ArrayList<Base> inimigos, ArrayList<Base> inimigos2, int altura, int largura, ArrayList<Base> tiros, Chefe chefe, int nivel) {

        if (nivel == 1) {
            for (Base i : inimigos) {

                if (i.getX() <= 20 || i.getX() >= largura - i.getLargura() - 20) {
                    for (Base in : inimigos) {
                        in.trocaDir();
                    }
                    break;

                }
                if (i.getY() > altura - 30) {
                    controle = -1;
                }

            }
        } else if (nivel == 2) {

            if (chefe.getX() <= 20 || chefe.getX() >= largura - chefe.getLargura() - 25) { //move chefe
                chefe.trocaDir();
            }

            for (Base i : inimigos2) {

                if (i.getX() <= 10 || i.getX() >= largura - i.getLargura() - 10 && i != chefe) {
                    for (Base in : inimigos2) {
                        if (in != chefe) {
                            in.trocaDir();
                        }
                    }
                    break;
                }

            }
        }

    }

    public int colidePlayer(ArrayList<Base> inimigos, ArrayList<Base> inimigos2, ArrayList<Base> tiros2, ArrayList<Base> lixo, Chefe chefe, Player player, int nivel) {

        for (Base b : inimigos) {
            if (player.colide(b)) {
                controle = -1;
            }
        }
        for (Base b : inimigos2) {
            if (player.colide(b)) {
                controle = -1;
            }
        }

        if (nivel == 2) {
            for (Base t : tiros2) {
                if (player.colide(t) == true) {
                    lixo.add(t);
                    controle = -1;
                    return 1;
                }

            }

        }
        return 0;
    }

    public void colideInimigo(ArrayList<Base> tiros, ArrayList<Base> inimigos, ArrayList<Base> inimigos2, ArrayList<Base> lixo, Chefe chefe, int nivel) {

        if (nivel == 1) {
            for (Base b : inimigos) // colisão
            {
                for (Base t : tiros) {
                    if (b.colide(t) == true) {
                        //Inimigo inimigo = new Inimigo("img/explosion1.png");
                        //inimigo.setX(b.getX());
                        //inimigo.setY(b.getY());
                        //inimigos.add(inimigo);  
                        lixo.add(b);
                        lixo.add(t);
                    }

                }
            }
        } else if (nivel == 2) {
            for (Base b : inimigos2) // colisão
            {
                for (Base t : tiros) {
                    if (b.colide(t) == true && b != chefe) {
                        //Inimigo inimigo = new Inimigo("img/explosion1.png");
                        //inimigo.setX(b.getX());
                        //inimigo.setY(b.getY());
                        //inimigos.add(inimigo);  
                        lixo.add(b);
                        lixo.add(t);
                    }
                    if (b.colide(t) == true && b == chefe) {
                        lixo.add(t);
                        cont++;
                    }
                    if (cont == 20 && b == chefe) {
                        lixo.add(chefe);
                        controle = 3;
                        break;
                    }
                }
            }
        }

    }

    public void shot(ArrayList<Base> tiros, boolean tiro, Base player) {
        long tempo = System.currentTimeMillis();

        if (tiro && tempo > ultimoTiro + 400) {
            ultimoTiro = tempo;
            Tiro t = new Tiro("img/tiroplayer.png");
            t.setIncX(0);
            t.setIncY(-4);
            t.setX(player.getX() + (player.getLargura() - 15) / 2);
            t.setY(player.getY() - player.getAltura() / 2 + 10);
            t.setAltura(40);
            t.setLargura(17);
            tiros.add(t);

        }

    }

    public void shotChefe(ArrayList<Base> tiros, Base chefe, int nivel) {
        long tempo = System.currentTimeMillis();

        if (nivel == 2) {
            if (tempo > ultimoTiro2 + 1800) {
                ultimoTiro2 = tempo;
                for (int i = 0; i < 3; i++) {
                    Tiro t = new Tiro("img/tiroenemy.png");
                    t.setIncX(0);
                    t.setIncY(+2);
                    t.setX(chefe.getX() + i % 3 * 125);
                    t.setY(chefe.getY() + 150 + i / 3 * 100);
                    t.setAltura(33);
                    t.setLargura(30);
                    tiros.add(t);
                }

            }
        }

    }

    public void removeMortos(ArrayList<Base> lixo, ArrayList<Base> inimigos, ArrayList<Base> inimigos2, ArrayList<Base> tiros, int nivel) {
        tiros.removeAll(lixo);
        inimigos.removeAll(lixo);
        inimigos2.removeAll(lixo);
        lixo.clear();
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

}
