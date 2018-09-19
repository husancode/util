package com.husan.image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * <p>
 * 验证码生成器
 * </p>
 * 
 */
public class ImageCheckCodeCreater {

    private static final int width = 69; // 图片宽度

    private static final int height = 28; // 图片高度

    private static final int codeCount = 4; // 验证码数量

    private static final int lineCount = 20; // 干扰线数量

    private static final int fontSize = 20;

    private static final char[] codes = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
        'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static ImageCheckCode createImageCheckCode() {
        BufferedImage bufferImg = null;
        int fontWidth = 0, codeY = 0;
        int red = 0, green = 0, blue = 0;

        fontWidth = width / (codeCount + 1); // 字符宽度
        codeY = height - 4;
        // 图像buffer
        bufferImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferImg.createGraphics();
        // 生成随机数
        Random random = new Random();
        // 将图像填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        // 创建字体
        Font f = new Font(Font.DIALOG, Font.BOLD, fontSize);
        g.setFont(f);
        // 干扰线
        for (int i = 0; i < lineCount; i++) {
            int xs = random.nextInt(width);
            int ys = random.nextInt(height);
            int xe = xs + random.nextInt(width / 8);
            int ye = ys + random.nextInt(height / 8);
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawLine(xs, ys, xe, ye);
        }
        // randomCode记录随机产生的验证码
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < codeCount; i++) {
            String temp = String.valueOf(codes[random.nextInt(codes.length)]);
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.drawString(temp, (i + 0.5f) * fontWidth, codeY);
            sb.append(temp);
        }

        return new ImageCheckCode(bufferImg, sb.toString());

    }

    public static class ImageCheckCode {
        private BufferedImage bufferImg;

        private String code;

        public ImageCheckCode(BufferedImage bufferImg, String code) {
            this.bufferImg = bufferImg;
            this.code = code;
        }

        public BufferedImage getBufferImg() {
            return bufferImg;
        }

        public void setBufferImg(BufferedImage bufferImg) {
            this.bufferImg = bufferImg;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

    }

}
