/**
 *
 */
package my.study.misio.zad4.gui;

import my.study.misio.zad4.env.Environment;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Patryk
 */
public class EnvironmentCanvas extends JComponent {

    private static final long serialVersionUID = -145272502327806458L;
    private final List<IDrawable> drawables = new LinkedList<>();
    private final IBackground background;

    public EnvironmentCanvas(IBackground background) {
        this.background = background;
        this.setDoubleBuffered(true);
        this.setBackground(Color.WHITE);
        this.setSize(background.getWidth(), background.getHeight());
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        Graphics2D g = (Graphics2D) g1;
        background.draw(g);
        for (IDrawable d : drawables) {
            d.draw(g);
        }
    }

    public void addDrawable(IDrawable d) {
        if (!(d instanceof Environment)) {
            drawables.add(d);
        }
    }

}
