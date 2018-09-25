/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1projeto;

/**
 *
 * @author jrramos
 */
public class Inimigo extends Base{
    int op = 0;
    
    public Inimigo(String url){
        super(url);
        this.setIncX(1);
    }

    public void mover() {
        
        x = x + incX;
        y = y + incY;
        this.rect.x = x;
        this.rect.y = y;
    }
    
    
   

  
  
    
}
