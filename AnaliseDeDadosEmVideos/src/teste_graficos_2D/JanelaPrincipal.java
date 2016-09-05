/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste_graficos_2D;

import BouncingBallWithBufferStrategy.Ball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author 12111151
 */
public class JanelaPrincipal extends JFrame {

    private Ball ball;

    public JanelaPrincipal(){
        super("Bouncing ball");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIgnoreRepaint(true);
        setSize(400, 400);
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                //Se apertar o x, paramos o loop.
//                loop.stop();
            }
        });
    }

    public void renderGraphics() {
        Graphics g = getBufferStrategy().getDrawGraphics();

        //Criamos um contexto gr�fico que n�o leva em conta as bordas
        Graphics g2 = g.create(getInsets().right,
                getInsets().top,
                getWidth() - getInsets().left,
                getHeight() - getInsets().bottom);
        //Limpamos a tela
//		g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());


        ball.draw((Graphics2D) g2); //Desenhamos a bola

        //Liberamos os contextos criados.
        g.dispose();
        g2.dispose();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
