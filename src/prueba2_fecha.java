import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class prueba2_fecha {
    public JPanel Panel;
    private JComboBox dia;
    private JComboBox mes;
    private JComboBox anio;
    private JButton guardarButton;

    Statement s;
    ResultSet rs;
    PreparedStatement ps;

    public prueba2_fecha(){
        JFormattedTextField d = new JFormattedTextField();
        JFormattedTextField m = new JFormattedTextField();
        JFormattedTextField a = new JFormattedTextField();
        JFormattedTextField fecha_guardar = new JFormattedTextField();
        /*Calendar fecha_guardar = new GregorianCalendar();
        prueba2_fecha fecha = new prueba2_fecha(); //fecha y hora actual
        SimpleDateFormat formato_dia = new SimpleDateFormat("M"); //formatear la fecha en una cadena
        SimpleDateFormat formato_mes = new SimpleDateFormat("d"); //formatear la fecha en una cadena
        SimpleDateFormat formato_anio = new SimpleDateFormat("y"); //formatear la fecha en una cadena*/

        try {
            Connection conexion;
            conexion = getConection();

            s = conexion.createStatement();
            rs = s.executeQuery("SELECT * FROM dia");

            dia.removeAllItems();

            dia.addItem(" ");
            while (rs.next()) {
                dia.addItem(rs.getString(1));
            }
            conexion.close();
            rs.close();
            s.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            Connection conexion;
            conexion = getConection();

            s = conexion.createStatement();
            rs = s.executeQuery("SELECT * FROM mes");

            mes.removeAllItems();

            mes.addItem(" ");
            while (rs.next()) {
                mes.addItem(rs.getString(1));
            }
            conexion.close();
            rs.close();
            s.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            Connection conexion;
            conexion = getConection();

            s = conexion.createStatement();
            rs = s.executeQuery("SELECT * FROM anio");

            anio.removeAllItems();
            anio.addItem(" ");
            while (rs.next()) {
                anio.addItem(rs.getString(1));
            }

            conexion.close();
            rs.close();
            s.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d.setText((String)dia.getSelectedItem());
                m.setText(String.valueOf((int)mes.getSelectedIndex()));
                a.setText((String)anio.getSelectedItem());

                System.out.println(d.getText());
                System.out.println(m.getText());
                System.out.println(a.getText());

                fecha_guardar.setText(a.getText()+"-"+m.getText()+"-"+d.getText());
                System.out.println(fecha_guardar.getText());

                try {
                    Connection conexion;
                    conexion = getConection();
                    ps = conexion.prepareStatement("Insert into fechas values (?)");
                    ps.setString(1, fecha_guardar.getText());

                    int res = ps.executeUpdate();
                    if(res >0){
                        JOptionPane.showMessageDialog(null,"CREACIÓN CON ÉXITO");
                    }else{
                        JOptionPane.showMessageDialog(null,"NO GUARDADO");
                    }
                    int an = Integer.parseInt(a.getText());

                    if(an%4==0){
                        JOptionPane.showMessageDialog(null,"Anio Bisiesto");
                    }

                    conexion.close();
                    rs.close();
                    s.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    public static Connection getConection()
    {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/fecha", "root", "3001a"
            );
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conexion;
    }
}
