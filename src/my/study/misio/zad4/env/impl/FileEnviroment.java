package my.study.misio.zad4.env.impl;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.sampled.Line;

import my.study.misio.zad4.env.Environment;

public class FileEnviroment extends Environment {

	public FileEnviroment(String fileName) {
		super();
		this.width = 900;
		this.height = 900;
		this.creaeGraduation();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			this.width = Integer.parseInt(br.readLine());
			this.height = Integer.parseInt(br.readLine());
			String str = null;
			while ((str = br.readLine()) != null) {
				String[] s = str.split(" ");
				if (s[0].equals("Rectangle")) {
					int x = Integer.parseInt(s[1]);
					int y = Integer.parseInt(s[2]);
					int width = Integer.parseInt(s[3]);
					int height = Integer.parseInt(s[4]);
					this.addObstacle(new Rectangle2D.Double(x, y, width, height));
				} else if (s[0].equals("Line")) {
					int x1 = Integer.parseInt(s[1]);
					int y1 = Integer.parseInt(s[2]);
					int x2 = Integer.parseInt(s[3]);
					int y2 = Integer.parseInt(s[4]);
					this.addObstacle(new Line2D.Double(x1, y1, x2, y2));
				} else if (s[0].equals("Circle")) {
					int x = Integer.parseInt(s[1]);
					int y = Integer.parseInt(s[2]);
					int r = Integer.parseInt(s[3]);
					this.addObstacle(new Ellipse2D.Double(x, y, r, r));
				}

			}
		} catch (FileNotFoundException e) {
			System.out.print("File \"" + fileName + "\" not found.");
		} catch (NumberFormatException e) {
			System.out.print("File \"" + fileName + "\" has bad format.");
		} catch (IOException e) {
			// ignore
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				// ignore
			}

		}
		
	}



}
