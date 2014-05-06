/**
 * 
 */
package my.study.misio.zad4.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;

import my.study.misio.zad4.env.Environment;

/**
 * @author Patryk
 * 
 */
public class EnvironmentCanvas extends JComponent {

	private static final long serialVersionUID = -145272502327806458L;
	private List<IDrawable> drawables = new LinkedList<>();
	private IBackground background;

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
