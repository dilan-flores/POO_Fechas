import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame=new JFrame("FARMACIA");
        frame.setContentPane(new prueba2_fecha().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(0,0,400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}