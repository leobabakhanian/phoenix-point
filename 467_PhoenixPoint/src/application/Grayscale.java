package application;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class Grayscale {
	public static void toGrayScale(File input, File output) {
		try {
			ImageInputStream iis = ImageIO.createImageInputStream(input);
			Iterator<ImageReader> iterator = ImageIO.getImageReaders(iis);
			ImageReader reader = iterator.next();
			String imageFormat = reader.getFormatName();

			BufferedImage image = ImageIO.read(iis);
			int width = image.getWidth();
			int height = image.getHeight();

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					Color color = new Color(image.getRGB(x, y));
					int red = (int) (color.getRed() * 0.21);
					int green = (int) (color.getGreen() * 0.71);
					int blue = (int) (color.getBlue() * 0.0722);
					int sum = red + green + blue;
					Color shadeOfGray = new Color(sum, sum, sum);
					image.setRGB(x, y, shadeOfGray.getRGB());
				}
			}

			ImageIO.write(image, imageFormat, output);
		} catch (IOException ex) {
			ex.printStackTrace();

		}
	}
}
