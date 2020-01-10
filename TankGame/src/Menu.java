import java.awt.*;

public class Menu {


    public static void drawImage(Graphics grph) {
        Font f = new Font("arial", Font.BOLD, 50);
        grph.setColor(Color.BLACK);
        grph.setFont(f);
        grph.drawString("BEGIN!", 380, 470);
        grph.drawString("EXIT", 397, 650);
        grph.setColor(Color.white);
        grph.drawRoundRect(360, 416, 210, 70, 20, 20);
        grph.drawRoundRect(360, 416 + 90 + 90, 210, 70, 20, 20);
    }
}
