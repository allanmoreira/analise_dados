package testes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by allanmoreira on 12/09/16.
 */
public class BallFrameTeste extends JFrame {

    public BallFrameTeste() {
        super("Bouncing bolinha");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIgnoreRepaint(true);
        setSize(400, 400);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //Se apertar o x, paramos o loop.
//				loop.stop();
            }
        });
    }
}
