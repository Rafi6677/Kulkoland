import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Kula extends JPanel
{
    private ArrayList<Kulka> kulkaList;

    private Timer timer;
    private final int DELAY = 16;

    public Kula()
    {

        kulkaList = new ArrayList<>();
        addMouseListener(new kListener());
        addMouseMotionListener(new kListener());
        addMouseWheelListener(new kListener());
        timer = new Timer(DELAY, new kListener());
        setBackground(Color.BLACK);

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        for(Kulka k:kulkaList){
            g.fillOval(k.x, k.y, k.size, k.size);
            g.setColor(k.color);
        }

        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 2F);
        g.setFont(newFont);
        g.setColor(Color.white);
        g.drawString("Liczba kulek: "+kulkaList.size(), 15, 25);
    }

    private class kListener implements MouseListener, ActionListener, MouseMotionListener, MouseWheelListener
    {
        private int size = 20;

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            kulkaList.add(new Kulka(e.getX(),e.getY(), size));
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            timer.start();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            timer.stop();
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            for(Kulka k:kulkaList){
                k.update();
            }
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            kulkaList.add(new Kulka(e.getX(),e.getY(), size));
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            for(Kulka k : kulkaList)
            {
                int addToSize = 0;
                addToSize += e.getWheelRotation();
                k.size += addToSize;
                if(k.size <= 5 || k.size >= 70)
                {
                    k.size -= addToSize;
                }
            }
    }
    }




    private class Kulka{
        public int x;
        public int y;
        public int size;
        public Color color;
        private final int MAXSPEED = 5;
        public int xspeed;
        public int yspeed;

        public Kulka(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;

            Random rand = new Random();
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            this.color = new Color(r,g,b);

            xspeed = (int)(Math.random()* MAXSPEED *2 - MAXSPEED);
            yspeed = (int)(Math.random()* MAXSPEED *2 - MAXSPEED);
            if(xspeed == 0 && yspeed == 0){
                xspeed = 1;
                yspeed = 1;
            }

        }

        public void update(){
            x+=xspeed;
            y+=yspeed;

            //ewentualne zaciecie sie kulek przy scianach powodowane jest zmiana rozmiaru kulek
            if(x<0 || x>(getWidth() - this.size)){
                xspeed = -xspeed;
            }
            if(y<0 || y>(getHeight() - this.size)){
                yspeed = -yspeed;
            }


            //kolizja kulek
            //dla malej malej liczby kulek dziala prawie dobrze
            /*
            for(Kulka k: kulkaList)
            {
                if(Math.sqrt((Math.pow((k.x - this.x),2)) + Math.pow((k.y - this.y), 2)) <= (this.size/2 + k.size/2))
                {

                    if((this.xspeed > 0 && k.xspeed > 0 && this.x < k.x)
                            || (this.xspeed < 0 && k.xspeed < 0 && this.x > k.x)
                            || (this.yspeed < 0 && k.yspeed < 0 && this.x > k.x)
                            || (this.yspeed > 0 && k.yspeed > 0 && this.x < k.x))
                    {
                        this.xspeed = -this.xspeed;
                        this.yspeed = -this.xspeed;
                    }
                    if((this.xspeed > 0 && k.xspeed < 0) || (this.xspeed < 0 && k.xspeed > 0))
                    {
                        this.xspeed = -this.xspeed;
                        this.yspeed = -this.yspeed;
                        k.xspeed = -k.xspeed;
                        k.yspeed = -k.yspeed;
                    }
                }
            }
            */
        }
    }
}
