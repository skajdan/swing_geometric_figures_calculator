package Michal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI extends JFrame
 {


    GUI() {


        this.setTitle("Projekt 2 - zadanie 2");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(new Drawing());
        this.setSize(1000, 400);
        this.setLayout(null);
        Image icon = Toolkit.getDefaultToolkit().getImage
                ("D:\\projekty\\Michal_Gurtman,s22533-GUI Projekt_II,zadanie_2.zip\\src\\rsc\\mr_robot.jpg");
        this.setIconImage(icon);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);


    }

    class Drawing extends JPanel
            implements ActionListener{

        private int x, y, x2, y2;
        private String figure;

        private final JButton rectangleButton;
        private final JButton ovalButton;
        private final JButton triangleleButton;
        private final JLabel jLabel;

        Drawing() {


            x = y = x2 = y2 = 0;
            MyMouseListener listener = new MyMouseListener();
            addMouseListener(listener);
            addMouseMotionListener(listener);



            rectangleButton = new JButton("Rectangle");
            rectangleButton.setBounds(480, 180, 500, 50);
            rectangleButton.addActionListener(this);
            rectangleButton.setFocusable(false);

            ovalButton = new JButton("Oval");
            ovalButton.setBounds(480, 240, 500, 50);
            ovalButton.addActionListener(this);
            ovalButton.setFocusable(false);

            triangleleButton = new JButton("Triangle");
            triangleleButton.setBounds(480, 300, 500, 50);
            triangleleButton.addActionListener(this);
            triangleleButton.setFocusable(false);

            jLabel = new JLabel("0px²");
            jLabel.setBounds(700,80,200,50);
            jLabel.setFont(new Font("Regular",Font.BOLD,30));
            this.add(jLabel);

            this.add(rectangleButton);
            this.add(ovalButton);
            this.add(triangleleButton);

        }

        public void setStartPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setEndPoint(int x, int y) {
            x2 = (x);
            y2 = (y);
        }

        public void drawRectangle(Graphics g, int x, int y, int x2, int y2) {
            int px = Math.min(x, x2);
            int py = Math.min(y, y2);
            int pw = Math.abs(x - x2);
            int ph = Math.abs(y - y2);
            g.drawRect(px, py, pw, ph);
        }

        public void drawOval(Graphics g, int x, int y, int x2, int y2) {

            int px = Math.min(x, x2);
            int py = Math.min(y, y2);
            int pw = Math.abs(x - x2);
            int ph = Math.abs(y - y2);
            g.drawOval(px, py, pw, ph);
        }

        public void drawTriangle(Graphics g, int x, int y, int x2, int y2) {

            int[] xPoints= {x,x2,(x2+x)/2};
            int[] yPoints = {y,y,y2};
           Polygon poly = new Polygon (xPoints, yPoints, 3);
            g.drawPolygon(poly);


        }


        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==rectangleButton){
                figure = "rectangle";
                ovalButton.setBackground(null);
                triangleleButton.setBackground(null);
                rectangleButton.setBackground(Color.CYAN);
            }
            if (e.getSource()==ovalButton){
                figure = "oval";
                rectangleButton.setBackground(null);
                triangleleButton.setBackground(null);
                ovalButton.setBackground(Color.CYAN);

            }

            if (e.getSource()==triangleleButton){
                figure = "triangle";
                rectangleButton.setBackground(null);
                ovalButton.setBackground(null);
                triangleleButton.setBackground(Color.CYAN);

            }

        }


        class MyMouseListener extends MouseAdapter {
            private int xstart, ystart;

            public void mousePressed(MouseEvent e) {

                setStartPoint(e.getX(), e.getY());
                xstart=e.getX();
                ystart=e.getY();


            }

            public void mouseDragged(MouseEvent e) {
                if (figure != null) {

                    String area;
                    setEndPoint(e.getX(), e.getY());
                    repaint();

                    if (figure.equals("rectangle")) {
                        area = Integer.toString(Math.abs((e.getX() - xstart) * (e.getY() - ystart)));
                        jLabel.setText(area + "px²");
                    }

                    if (figure.equals("oval")){
                        area = Integer.toString((int) (Math.abs(((e.getX() - xstart)/2) * ((e.getY() - ystart)/2)*(Math.PI))));
                        jLabel.setText(area + "px²");


                    }

                    if (figure.equals("triangle")){
                        area = Integer.toString((Math.abs(e.getX()-xstart) * (Math.abs(e.getY()-ystart))/2));
                        jLabel.setText(area + "px²");
                    }

                }
            }

            public void mouseReleased(MouseEvent e) {
                if (figure != null) {
                    setEndPoint(e.getX(), e.getY());
                    repaint();
                }
            }
        }

        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.RED);

            if (figure != null) {
                switch (figure) {
                    case "rectangle" -> drawRectangle(g, x, y, x2, y2);
                    case "oval" -> drawOval(g, x, y, x2, y2);
                    case "triangle" -> drawTriangle(g,x,y,x2,y2);

                }
            }

        }


    }
    }






