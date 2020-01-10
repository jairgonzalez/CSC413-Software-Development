package sample;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;


public class Application extends JPanel {

    private int lives;
    private int score;

    private final int FPS =60;

    private Shell player;
    private JFrame window;
    private Graphics2D g2d;
    private BufferedImage buf;
    public static final int ScreenHeight = 480, ScreenWidth = 640;
    private Image background, ShellImg, StarImg, Block1Img, Block2Img, Block3Img, OctoImg;

    private GameEvents gameEvents;

    boolean newgame;

    ArrayList<Star> Stars = new ArrayList<>();
    ArrayList<Brick> Bricks = new ArrayList<>();

    ArrayList<Brick> octo = new ArrayList<>();

    private void ResourcesInit() {
        //load resources
        try{
            background = ImageIO.read(new File("resources/Background1.bmp"));
            ShellImg = ImageIO.read(new File("resources/Katch.png"));
            StarImg = ImageIO.read(new File("resources/Pop.png"));
            Block1Img = ImageIO.read(new File("resources/Block3.gif"));
            Block2Img = ImageIO.read(new File("resources/Block2.gif"));
            Block3Img = ImageIO.read(new File("resources/Block1.gif"));
            OctoImg = ImageIO.read(new File("resources/Bigleg.png"));
        } catch (Exception e) {
            System.out.print(e.getStackTrace() + " Error loading resources");
        }

        window = new JFrame();
        window.addWindowListener(new WindowAdapter() {
        });
        window.add(this);
        window.pack();
        window.setVisible(true);
        window.setTitle("Rainbow Reef!");
        window.setSize(ScreenWidth, ScreenHeight);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.getContentPane().setFocusable(true);
    }

    private void LevelOneInit() {
        this.score = 0;
        this.lives = 3;

        player = new Shell(ShellImg, 280, 400, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);

        gameEvents = new GameEvents();
        gameEvents.addObserver(player);
        ShellControl playerKeys = new ShellControl(gameEvents);

        window.getContentPane().requestFocusInWindow();
        window.getContentPane().addKeyListener(playerKeys);

        octo.add(new Brick(OctoImg, 280, 0, 1000));

        for(int i = 0; i < 15; i ++) {
            Bricks.add(new Brick(Block1Img, 20 + 40 * i, 80, 10));
        }

        for(int i = 0; i < 15; i ++) {
            Bricks.add(new Brick(Block2Img, 20 + 40 * i, 100, 20));
        }

        for(int i = 0; i < 15; i ++) {
            Bricks.add(new Brick(Block3Img, 20 + 40 * i, 120, 30));
        }

        Stars.add(new Star(StarImg, 200, 240));
        Stars.add(new Star(StarImg, 320, 240));
    }

    private void LevelTwoInit() {

        player = new Shell(ShellImg, 280, 400, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);

        gameEvents = new GameEvents();
        gameEvents.addObserver(player);
        ShellControl playerKeys = new ShellControl(gameEvents);

        window.getContentPane().requestFocusInWindow();
        window.getContentPane().addKeyListener(playerKeys);

        octo.add(new Brick(OctoImg, 200, 0, 1000));
        octo.add(new Brick(OctoImg, 200, 0, 1000));
        octo.add(new Brick(OctoImg, 400, 0, 1000));

        for(int i = 0; i < 15; i ++) {
            Bricks.add(new Brick(Block1Img, 20 + 40 * i, 80, 10));
        }

        for(int i = 0; i < 15; i ++) {
            Bricks.add(new Brick(Block2Img, 20 + 40 * i, 100, 20));
        }

        for(int i = 0; i < 15; i ++) {
            Bricks.add(new Brick(Block3Img, 20 + 40 * i, 120, 30));
        }

        Stars.add(new Star(StarImg, 200, 240));
        Stars.add(new Star(StarImg, 320, 240)); //testing

    }

    @Override
    public void paintComponent(Graphics g) {
        if (buf == null) {
            buf = (BufferedImage) createImage(ScreenWidth, ScreenHeight);
        }
        Graphics2D gtemp = (Graphics2D) g;
        g2d = buf.createGraphics();
        super.paintComponent(gtemp);

        g2d.drawImage(background, 0, 0, this);

        for(int i = 0; i < octo.size(); i ++) {
            g2d.drawImage(octo.get(i).getImg(), octo.get(i).getX(), octo.get(i).getY(), this);
        }

        for(int i = 0; i < Stars.size(); i ++)
        {
            g2d.drawImage(Stars.get(i).getImg(), Stars.get(i).getX(), Stars.get(i).getY(), this);
        }

        for(int i = 0; i < Bricks.size(); i ++)
        {
            g2d.drawImage(Bricks.get(i).getImg(), Bricks.get(i).getX(), Bricks.get(i).getY(), this);
        }

        g2d.drawImage(player.getImg(), player.getX(), player.getY(), this);

        gtemp.drawImage(buf, 0, 0, this);

        String s = "LIVES x" + this.lives;
        gtemp.setColor(Color.BLACK);
        gtemp.setFont((new Font("Arial Black", Font.BOLD, 16)));
        gtemp.drawString(s, 10, 450);

        s = "SCORE: " + this.score;
        gtemp.setColor(Color.BLACK);
        gtemp.drawString(s, 500, 450);

        if(lives == 0){
            gtemp.setFont((new Font("Arial Black", Font.PLAIN, 25)));
            gtemp.setColor(Color.RED);
            gtemp.drawString("GAME OVER!", 250, 250);
        }

        gtemp.dispose();

    }

    private void run() {
        long currTime;
        long targetTime = 1000000000 / FPS;

        while ((lives > 0)) {// && Octos > 0) {
            currTime = System.nanoTime();

            UpdateGame();
            repaint();

            if ((currTime - System.nanoTime() + targetTime) > 0) {
                try {
                    Thread.sleep((currTime - System.nanoTime() + targetTime) / 1000000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    //update stars/shell here
    private void UpdateGame() {
        player.move();

        if(Stars.isEmpty()) {
            Stars.add(new Star(StarImg, 200, 240));
        }

        for(int i = 0; i < Stars.size(); i++)
        {
            checkCollisions();
            Stars.get(i).update();

            if(Stars.get(i).getY() > ScreenHeight-35) {
                Stars.remove(i);
                i--;
                if(Stars.isEmpty())
                    lives--;
            }
        }
    }

    public void checkCollisions() {
        for(int i = 0; i < Stars.size(); i++) {
            Rectangle starRect = Stars.get(i).getRect();
            Rectangle paddleRect = player.getRect();

            if(starRect.intersects(paddleRect)) {
                Stars.get(i).setVerticalSpeed(-Stars.get(i).getVerticalSpeed());
                System.out.println("Collision Paddle");
            }

            for(int j = 0; j < Bricks.size(); j++) {
                Rectangle brickRect = Bricks.get(j).getRect();

                if(starRect.intersects(brickRect)) {
                    Stars.get(i).setVerticalSpeed(-Stars.get(i).getVerticalSpeed());
                    this.score += Bricks.get(j).getPoints();
                    Bricks.remove(j);
                    System.out.println("Collision Brick");
                }
            }

            for(int k = 0; k < octo.size(); k++) {
                Rectangle octoRect = octo.get(k).getRect();

                if(starRect.intersects(octoRect)) {
                    Stars.get(i).setVerticalSpeed(-Stars.get(i).getVerticalSpeed());
                    octo.remove(k);
                    k--;

                    if(octo.isEmpty()) { //onto the next level
                        this.LevelTwoInit();
                        Stars.remove(i);
                    }

                    System.out.println("Collision Octo");
                }
            }

        }

    }

    private void start()
    {
        newgame = false;
        this.LevelOneInit();
        this.run();

        if(octo.isEmpty())
        {
            this.LevelTwoInit();
            this.run();
        }
    }

    public static void main(String[] args) {
        Application game = new Application();
        game.ResourcesInit();
        game.start();
    }

}
