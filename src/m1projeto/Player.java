/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1projeto;

/**
 *
 * @author jrram
 */
import java.awt.Graphics;

/**
 *
 * @author jrram
 */
public class Player extends Base{

    public Player()
    {
        
    }
    
    public Player(String url, int lar,int alt){
        super(url);
        this.setLargura(79);
        this.setAltura(70);
        this.setIncX(0);
        this.setIncY(0);
        this.setX((lar - this.getLargura())/2);
        this.setY(alt - this.getAltura() - 25); 
    }

    @Override
    public void mover() {
        x = x + incX;
        y = y + incY;
        this.rect.x = x;
        this.rect.y = y;
    }
    
    public void update(Graphics g)
    {
        this.desenhar(g);
        this.mover();
    }
   
}
