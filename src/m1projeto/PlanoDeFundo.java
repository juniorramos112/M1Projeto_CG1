/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1projeto;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author jrram
 */
public class PlanoDeFundo extends Base{

    
    
    public PlanoDeFundo(String url) {
        
        super(url);
        this.setIncX(0);
        this.setIncY(0);
        this.setX(0);
        this.setY(0); 
    }

    
    
    
    
    @Override
    public void mover() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
