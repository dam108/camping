package Grafica;

import Ficheros.*;
import static Ficheros.Ficheros.rutaProperties;
import Logica.*;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class MainGrafico extends javax.swing.JFrame {

    JButton[] tablero;
    public Param p = new Param();
    int numBoton;
    String nomBoton;

    public MainGrafico() {
        initComponents();
        crearBotones();
    }

    public void crearBotones() {

        setLocationRelativeTo(null);  //ventana centrada en pantalla
        tablero = new JButton[Camping.parcelas.size()]; //definimos los 16 botones
        for (int i = 0; i < Camping.parcelas.size(); i++) {
            tablero[i] = new JButton();
            tablero[i].addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    FActionPerformed(evt);
                }

            });
            tablero[i].setName("jButton" + Integer.toString(i));
            tablero[i].setFont(new java.awt.Font("Tahoma", 0, 25).deriveFont(Font.BOLD));
            nomBoton = tablero[i].getName();
            numBoton = Integer.parseInt(nomBoton.substring(7,
                    nomBoton.length()));
            if (Camping.parcelas.get(i).ocupado) {
                tablero[i].setBackground(Color.red);
            } else {
                tablero[i].setBackground(Color.DARK_GRAY/*new Color(54, 187, 253)*/);
            }

            tablero[i].setVerticalTextPosition(SwingConstants.CENTER);
            tablero[i].setHorizontalTextPosition(SwingConstants.CENTER);
            tablero[i].setForeground(Color.black);
            tablero[i].setText(Integer.toString(i + 1));
            if (numBoton >= 0 && numBoton < 10) {
                tablero[i].setIcon(new javax.swing.ImageIcon("img\\Tienda.PNG"));
            } else if (numBoton >= 10 && numBoton < 30) {
                tablero[i].setIcon(new javax.swing.ImageIcon("img\\Caravana.PNG"));
            } else {
                tablero[i].setIcon(new javax.swing.ImageIcon("img\\Bungalow.PNG"));
            }
            Botonera.add(tablero[i]);
        }
    }

    private void FActionPerformed(java.awt.event.ActionEvent evt) {
        nomBoton = ((JButton) evt.getSource()).getName();
        numBoton = Integer.parseInt(nomBoton.substring(7, nomBoton.length()));
        boolean electricidad;
        String ad;
        double precio;
        String dni;
        //Tienda
        if (Camping.parcelas.get(numBoton) instanceof Tienda) {
            if (!Camping.parcelas.get(numBoton).ocupado) {
                try {
                    do {
                        dni = JOptionPane.showInputDialog(this, "Introduce el DNI"
                                + " del huesped");
                    } while (dni.equals(""));
                    int opcion = JOptionPane.showOptionDialog(
                            null, "Quieres electricidad?", "Selector de opciones",
                            //ventana, mensaje, título
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, // icono: null para icono por defecto
                            new Object[]{"Si", "No"}, // opciones
                            //null para YES, NO y CANCEL
                            "Si" //opción con el foco
                    );
                    if (opcion == 0) {
                        electricidad = true;
                    } else {
                        electricidad = false;
                    }
                    tablero[numBoton].setBackground(Color.red);
                    ((Tienda) Camping.parcelas.get(numBoton)).checkIn(dni,
                            electricidad);

                } catch (NullPointerException ex) {
                    System.err.printf("%nError: %s", ex.getMessage());
                }

            } else {
                precio = Camping.parcelas.get(numBoton).checkOut(p);
                JOptionPane.showMessageDialog(this, "El importe es de " + precio);
                tablero[numBoton].setBackground(Color.DARK_GRAY/*new Color(54, 187, 253)*/);
            }
        }
        // Caravana
        if (Camping.parcelas.get(numBoton) instanceof Caravana) {
            //no se permite checkout de menos de 10 dias
            if (!Camping.parcelas.get(numBoton).ocupado) {
                try {
                    do {
                        dni = JOptionPane.showInputDialog(this, "Introduce el DNI"
                                + " del huesped");
                    } while (dni.equals(""));
                    tablero[numBoton].setBackground(Color.red);
                    ((Caravana) Camping.parcelas.get(numBoton)).checkIn(dni);
                } catch (NullPointerException ex) {
                    System.err.printf("%nError: %s", ex.getMessage());
                }
            } else {
                precio = ((Caravana) Camping.parcelas.get(numBoton)).checkOut(p);
                if (precio == -1) {
                    JOptionPane.showMessageDialog(this,
                            "No se puede hacer checkOut antes de 10 dias de"
                            + " estancia");
                } else {
                    tablero[numBoton].setBackground(Color.DARK_GRAY/*new Color(54, 187, 253)*/);
                    JOptionPane.showMessageDialog(this, "El importe es de "
                            + precio);
                }
            }
        }
        // bungalow
        if (Camping.parcelas.get(numBoton) instanceof Bungalow) {

            if (!Camping.parcelas.get(numBoton).ocupado) {
                try {
                    do {
                        dni = JOptionPane.showInputDialog(this, "Introduce el DNI"
                                + " del huesped");
                    } while (dni.equals(""));
                    try {
                        do {
                            ad = JOptionPane.showInputDialog(this, "Introduce el numero de"
                                    + " adultos");
                        } while (Integer.valueOf(ad) == 0);

                        tablero[numBoton].setBackground(Color.red);
                        ((Bungalow) Camping.parcelas.get(numBoton)).checkIn(dni,
                                Integer.valueOf(ad));
                    } catch (NullPointerException ex) {
                        System.err.printf("%nError: %s", ex.getMessage());
                    } catch (NumberFormatException nex) {
                        System.err.printf("%nError: %s", nex.getMessage());
                    }
                } catch (NullPointerException ex) {
                    System.err.printf("%nError: %s", ex.getMessage());
                }
            } else {
                System.out.println(
                        ((Bungalow) Camping.parcelas.get(numBoton)).adultos);
                precio = ((Bungalow) Camping.parcelas.get(numBoton)).checkOut(p);
                System.out.println(precio);
                JOptionPane.showMessageDialog(this, "El importe es de " + precio);
                tablero[numBoton].setBackground(Color.DARK_GRAY/*new Color(54, 187, 253)*/);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Botonera = new javax.swing.JPanel();
        Panel_Inferior = new javax.swing.JPanel();
        JB_Edit_Param = new javax.swing.JButton();
        JB_Update_Param = new javax.swing.JButton();
        JB_Ayuda = new javax.swing.JButton();
        JB_About = new javax.swing.JButton();
        JB_Salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión Camping");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMaximumSize(new java.awt.Dimension(800, 850));
        setMinimumSize(new java.awt.Dimension(800, 850));
        setPreferredSize(new java.awt.Dimension(800, 850));
        getContentPane().setLayout(new java.awt.BorderLayout(1, 1));

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.darcula.color1"));
        Header.setMaximumSize(new java.awt.Dimension(800, 50));
        Header.setMinimumSize(new java.awt.Dimension(800, 50));
        Header.setPreferredSize(new java.awt.Dimension(800, 50));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(92, 94, 57));
        jLabel1.setText("Camping De Betanzos");
        Header.add(jLabel1);

        getContentPane().add(Header, java.awt.BorderLayout.PAGE_START);

        Botonera.setBackground(new java.awt.Color(255, 255, 255));
        Botonera.setMinimumSize(new java.awt.Dimension(800, 750));
        Botonera.setPreferredSize(new java.awt.Dimension(800, 750));
        Botonera.setLayout(new java.awt.GridLayout(10, 8));
        getContentPane().add(Botonera, java.awt.BorderLayout.CENTER);

        Panel_Inferior.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Inferior.setMinimumSize(new java.awt.Dimension(800, 50));
        Panel_Inferior.setPreferredSize(new java.awt.Dimension(800, 50));

        JB_Edit_Param.setText("Editar Parámetros");
        JB_Edit_Param.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_Edit_ParamActionPerformed(evt);
            }
        });
        Panel_Inferior.add(JB_Edit_Param);

        JB_Update_Param.setText("Actualizar Parámetros");
        JB_Update_Param.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_Update_ParamActionPerformed(evt);
            }
        });
        Panel_Inferior.add(JB_Update_Param);

        JB_Ayuda.setText("Ayuda");
        JB_Ayuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_AyudaActionPerformed(evt);
            }
        });
        Panel_Inferior.add(JB_Ayuda);

        JB_About.setText("About");
        JB_About.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_AboutActionPerformed(evt);
            }
        });
        Panel_Inferior.add(JB_About);

        JB_Salir.setText("Salir");
        JB_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_SalirActionPerformed(evt);
            }
        });
        Panel_Inferior.add(JB_Salir);

        getContentPane().add(Panel_Inferior, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JB_Edit_ParamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Edit_ParamActionPerformed
        JOptionPane.showMessageDialog(this, "Tienes que modificar el archivo"
                + " a continuacion y despues de modificarlo darle al boton de actualizar.");
        abrirFichero(rutaProperties);
    }//GEN-LAST:event_JB_Edit_ParamActionPerformed

    private void JB_Update_ParamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Update_ParamActionPerformed
        p = new Param();
        JOptionPane.showMessageDialog(this, "El programa ha actualizado los"
                + " parametros correctamente.");
    }//GEN-LAST:event_JB_Update_ParamActionPerformed

    private void JB_AyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_AyudaActionPerformed
        JOptionPane.showMessageDialog(this, "Funcionamiento:\n"
                + "Este programa permite la gestión de las entradas y salidas del camping.\n"
                + "Cuando deseamos hacer un Check In en alguna de las parcelas"
                + " simplemente presionamos el boton que corresponde\na esa"
                + " parcela y introduciomos los datos que se nos solicitan.\n"
                + "Para realizar un Check Out simplemente presionamos en la"
                + " parcela a desocupar y se nos muestra el importe a cobrar.\n"
                + "El color gris se corresponde con una parcela vacia.\n"
                + "El color rojo se corresponde con una parcela ocupada.\n"
                + "Por ultimo tenemos varios botones en la parte inferior:\n"
                + "Editar Parametros: Se nos abre un archivo con la configuracion para que podemos modificarla en caso de necesitarlo.\n"
                + "Actualizar Parametros: Sirve para que el programa recargue la configuracion del archivo anterior justo despues de la modificacion.\n"
                + "Ayuda: Esto mismo que estas consultando.\n"
                + "About: Información sobre el autor del programa.\n"
                + "Salir: Cierra el programa.");
    }//GEN-LAST:event_JB_AyudaActionPerformed

    private void JB_AboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_AboutActionPerformed
        JOptionPane.showMessageDialog(this, "Datos del Programa:\n"
                + "Autor: Jose Angel Doval Fraga\n"
                + "Email de contacto: dovaldam108@gmail.com\n"
                + "Fecha de creación: 17/04/2020\n"
                + "Versión: 1.0\n"
                + "Repositorio en GitHub: https://github.com/dam108/camping.git");
    }//GEN-LAST:event_JB_AboutActionPerformed

    private void JB_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_SalirActionPerformed
        dispose();
    }//GEN-LAST:event_JB_SalirActionPerformed

    public static void abrirFichero(String ruta) {
        Desktop ficheroAEjecutar = Desktop.getDesktop();
        try {
            ficheroAEjecutar.open(new File(ruta));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGrafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGrafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGrafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGrafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGrafico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Botonera;
    private javax.swing.JPanel Header;
    private javax.swing.JButton JB_About;
    private javax.swing.JButton JB_Ayuda;
    private javax.swing.JButton JB_Edit_Param;
    private javax.swing.JButton JB_Salir;
    private javax.swing.JButton JB_Update_Param;
    private javax.swing.JPanel Panel_Inferior;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
    public static Camping camp = new Camping();
}
