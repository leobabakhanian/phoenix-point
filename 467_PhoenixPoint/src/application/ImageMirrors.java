package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageMirrors {

	public static final int FLIP_vertically = 1;
	public static final int FLIP_horizontally = -1;
	public static final int FLIP_bothways = 2;

	public static void flip(File input, File output, int direction) {

		try {
			BufferedImage myImage = ImageIO.read(input);
			int width = myImage.getWidth();
			int height = myImage.getHeight();
			BufferedImage flipped = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					switch (direction) {
					case FLIP_horizontally:
						flipped.setRGB((width - 1) - x, y, myImage.getRGB(x, y));
						break;
					case FLIP_vertically:
						flipped.setRGB(x, (height - 1) - y, myImage.getRGB(x, y));
						break;
					case FLIP_bothways:
						flipped.setRGB((width - 1) - x, (height - 1) - y, myImage.getRGB(x, y));
					}
				}
			}
			ImageIO.write(flipped, "jpg", output);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
