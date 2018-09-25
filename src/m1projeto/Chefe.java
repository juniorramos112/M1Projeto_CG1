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
public class Chefe extends Base{

    
    public Chefe()
    {
        
    }
    
    public Chefe(String url, int lar,int alt)
    {
        super(url);
        this.setLargura(300);
        this.setAltura(150);
        this.setIncX(2);
        this.setIncY(0);
        this.setX((lar - this.getLargura())/2);
        this.setY(50);
    }
    
    public void trocaDir()
    {
        this.incX = this.incX * -1;
    }
    
    @Override
    public void mover() {
        
        x = x + incX;
        this.rect.x = x;
        this.rect.y = y;
        if(this.x + this.largura/2 > 1000)
        {
            this.trocaDir();
        }
        if(this.x < 0)
        {
            this.trocaDir();
        }
    }
    
}
