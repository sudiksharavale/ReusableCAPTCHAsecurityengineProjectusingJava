import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class CaptchaGenerator {
    private static final int CAPTCHA_WIDTH = 200;
    private static final int CAPTCHA_HEIGHT = 80;
    private static final int CAPTCHA_LENGTH = 6;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateCaptchaText() {
        StringBuilder captchaText = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            captchaText.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return captchaText.toString();
    }

    public static void createCaptchaImage(String captchaText, String filePath) throws IOException {
        BufferedImage captchaImage = new BufferedImage(CAPTCHA_WIDTH, CAPTCHA_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = captchaImage.createGraphics();

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, CAPTCHA_WIDTH, CAPTCHA_HEIGHT);

        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial", Font.BOLD, 40));
        graphics.drawString(captchaText, 40, 50);

        File output = new File(filePath);
        ImageIO.write(captchaImage, "png", output);
    }

    public static void main(String[] args) {
        String captchaText = generateCaptchaText();
        System.out.println("Generated CAPTCHA: " + captchaText);

        String filePath = "captcha.png";
        try {
            createCaptchaImage(captchaText, filePath);
            System.out.println("CAPTCHA image saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
