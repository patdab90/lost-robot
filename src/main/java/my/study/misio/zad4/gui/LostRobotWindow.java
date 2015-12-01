package my.study.misio.zad4.gui;

import my.study.misio.zad4.agents.MoveDirection;
import my.study.misio.zad4.agents.Agent;
import my.study.misio.zad4.env.Environment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class LostRobotWindow extends JFrame {

    private static final long serialVersionUID = 8955017289201366142L;

    public LostRobotWindow(final EnvironmentCanvas canvas, final Environment env,
                           final Agent agent) throws HeadlessException {
        super("Zagubiony robot");
        setLocationByPlatform(true);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());

        Container frameContainer = this.getContentPane();
        frameContainer.setPreferredSize(new Dimension(env
                .getWidth(), env.getHeight()));

        canvas.setLocation(0, 0);

        this.add(canvas);
        this.pack();

        this.addKeyListener(new KeyListener() {
            Set<Integer> pressedKeys = new HashSet<>();

            @Override
            public void keyTyped(KeyEvent arg0) {
            }

            @Override
            public void keyReleased(KeyEvent event) {
                pressedKeys.remove(event.getKeyCode());
            }

            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                    pressedKeys.add(event.getKeyCode());
                }
                if (event.getKeyCode() == KeyEvent.VK_UP) {
                    pressedKeys.add(event.getKeyCode());
                }
                if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                    pressedKeys.add(event.getKeyCode());
                }
                if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                    pressedKeys.add(event.getKeyCode());
                }

                if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
                    agent.move(MoveDirection.DOWN);
                }
                if (pressedKeys.contains(KeyEvent.VK_UP)) {
                    agent.move(MoveDirection.UP);
                }
                if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
                    agent.move(MoveDirection.RIGHT);
                }
                if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
                    agent.move(MoveDirection.LEFT);
                }
                canvas.repaint();
            }
        });
    }
}
