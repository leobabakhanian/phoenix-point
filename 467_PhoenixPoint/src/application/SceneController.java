package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class SceneController {
	@FXML
	private Button verticalButton;
	@FXML
	private Button horizontalButton;
	@FXML
	private Button bothVHButton;
	@FXML
	private Button greyscaleButton;
	@FXML
	private ImageView imageView;

	@FXML
	private ImageView imageViewAltered;

	// Crop stuff

	@FXML
	private Image myImage1;

	BufferedImage ogImage1;

	private Button selectFileButton;

	@FXML
	private Label fileName; // where the selected file name will be displayed
	@FXML
	private Button cropImageButton;
	@FXML
	private Image theImage;

	BufferedImage origImage;

	// File Browser
	@FXML
	public void selectImage() throws IOException {

		// Browse Files and select one
		FileChooser chosenFile = new FileChooser();
		File selectedFile = chosenFile.showOpenDialog(null);
		String pathName = selectedFile.getAbsolutePath();

		// Grab image as a buffered image, convert it to swing image for display
		origImage = ImageIO.read(new File(pathName));
		theImage = SwingFXUtils.toFXImage(origImage, null);

		if (selectedFile != null) {
			fileName.setText(selectedFile.getName()); // Print the file name in the text label
			imageView.setImage(theImage); // display the image selected
		}
	}

	// Reset Image
	@FXML
	public void resetImage() throws IOException {

		imageViewAltered.setImage(null); // display the image selected
	}

	// Vertical Mirror
	@FXML
	public void verticalMirror() throws IOException {

		int width = (int) theImage.getWidth();
		int height = (int) theImage.getHeight();
		BufferedImage verticalImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				verticalImg.setRGB(x, (height - 1) - y, origImage.getRGB(x, y));
			}

		}
		theImage = SwingFXUtils.toFXImage(verticalImg, null);
		imageViewAltered.setImage(theImage);

	}

	// Horizontal Mirror
	@FXML
	public void horizontalMirror() throws IOException {

		int width = (int) theImage.getWidth();
		int height = (int) theImage.getHeight();
		BufferedImage horizontalImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				horizontalImg.setRGB((width - 1) - x, y, origImage.getRGB(x, y));
			}

		}
		theImage = SwingFXUtils.toFXImage(horizontalImg, null);
		imageViewAltered.setImage(theImage);

	}

	// Both Vertical & Horizontal Mirrors
	@FXML
	public void bothMirror() throws IOException {

		int width = (int) theImage.getWidth();
		int height = (int) theImage.getHeight();
		BufferedImage bothImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				bothImg.setRGB((width - 1) - x, (height - 1) - y, origImage.getRGB(x, y));
			}

		}
		theImage = SwingFXUtils.toFXImage(bothImg, null);
		imageViewAltered.setImage(theImage);

	}

	// Crop Function
	@FXML
	public void cropImage() throws IOException {

		// Dimensions for original image and the new cropped image
		int drawLocationX = 0, drawLocationY = 0, drawWidth = 30, drawHeight = 30, ogDrawLocX = 30, ogDrawLocY = 30,
				ogDrawWidth = 90, ogDrawHeight = 90;

		// New buffered image where the crop will be placed
		BufferedImage cropImg = new BufferedImage(drawWidth, drawHeight, BufferedImage.TYPE_INT_ARGB);

		// Cropping original image and placing into new buffered image
		cropImg.getGraphics().drawImage(origImage, drawLocationX, drawLocationY, drawWidth, drawHeight, ogDrawLocX,
				ogDrawLocY, ogDrawWidth, ogDrawHeight, null);

		// converting the result to a swing image to be displayed
		theImage = SwingFXUtils.toFXImage(cropImg, null);
		imageViewAltered.setImage(theImage);
	}

	// GreyScale
	@FXML
	public void greyscale() throws IOException {

		int width = (int) theImage.getWidth();
		int height = (int) theImage.getHeight();
		BufferedImage greyscaleImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Color color = new Color(origImage.getRGB(x, y));
				int red = (int) (color.getRed() * 0.21);
				int green = (int) (color.getGreen() * 0.71);
				int blue = (int) (color.getBlue() * 0.0722);

				
				int sum = red + green + blue;
				Color origImage = new Color(sum, sum, sum);
				greyscaleImg.setRGB(x, y, origImage.getRGB());
			}
		}
		theImage = SwingFXUtils.toFXImage(greyscaleImg, null);
		imageViewAltered.setImage(theImage);

	}

	// Negative
	@FXML
	public void negative() throws IOException {

		int width = (int) theImage.getWidth();
		int height = (int) theImage.getHeight();
		BufferedImage negativeImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				Color color = new Color(origImage.getRGB(x, y));

				// Negative
				int red = 255 - color.getRed();
				int green = 255 - color.getGreen();
				int blue = 255 - color.getBlue();

				Color origImage = new Color(red, green, blue);

				negativeImg.setRGB(x, y, origImage.getRGB());

			}
		}
		theImage = SwingFXUtils.toFXImage(negativeImg, null);
		imageViewAltered.setImage(theImage);

	}

	// Sepia
	@FXML
	public void sepiaTint() throws IOException {

		int width = (int) theImage.getWidth();
		int height = (int) theImage.getHeight();
		BufferedImage sepiaImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				int p = origImage.getRGB(x, y);

				int a = (p >> 24) & 0xff;
				int R = (p >> 16) & 0xff;
				int G = (p >> 8) & 0xff;
				int B = p & 0xff;

				// calculate newRed, newGreen, newBlue
				int newRed = (int) (0.393 * R + 0.769 * G + 0.189 * B);
				int newGreen = (int) (0.349 * R + 0.686 * G + 0.168 * B);
				int newBlue = (int) (0.272 * R + 0.534 * G + 0.131 * B);

				// check condition
				if (newRed > 255)
					R = 255;
				else
					R = newRed;

				if (newGreen > 255)
					G = 255;
				else
					G = newGreen;

				if (newBlue > 255)
					B = 255;
				else
					B = newBlue;

				// set new RGB value
				p = (a << 24) | (R << 16) | (G << 8) | B;

				origImage.setRGB(x, y, p);

				sepiaImg.setRGB(x, y, origImage.getRGB(x, y));

			}
		}
		theImage = SwingFXUtils.toFXImage(sepiaImg, null);
		imageViewAltered.setImage(theImage);

	}

}
