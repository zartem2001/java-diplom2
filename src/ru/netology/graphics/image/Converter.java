package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Converter implements TextGraphicsConverter {
    private int MaxWidth;
    private int MaxHeight;
    private double maxRatio;
    private TextColorSchema schema;

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));
        int width = img.getWidth();
        int height = img.getHeight();
        if (width * height == 0) {
            throw new BadImageSizeException(width, height);
        }
        double ratio = (double) (width) / height;
        if (ratio > maxRatio) {
            throw new BadImageSizeException(ratio, maxRatio);
        }
        double widthkoef = (double) width / MaxWidth;
        double heightkoef = (double) height / MaxHeight;
        if (width > MaxWidth) {
            width = (int) (width / widthkoef);
        }
        if (height > MaxHeight) {
            height = (int) (height / heightkoef);
        }
        int newWidth = width;
        int newHeight = height;
        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        WritableRaster bwRaster = bwImg.getRaster();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bwRaster.getHeight(); i++) {
            for (int j = 0; j < bwRaster.getWidth(); j++) {
                int pixel = bwRaster.getPixel(j, i, new int[3])[0];
                sb.append(schema.convert(pixel));
                sb.append(schema.convert(pixel));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        this.MaxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.MaxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }
}


