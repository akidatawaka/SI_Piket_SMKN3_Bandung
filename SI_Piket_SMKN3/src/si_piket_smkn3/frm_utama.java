/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si_piket_smkn3;



/**
 *
 * @author tawakida
 */
public class frm_utama extends javax.swing.JFrame {

    /**
     * Creates new form frm_utama
     */
    
    frm_ubah_guru ubah_guru = new frm_ubah_guru();
    
    public frm_utama() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem_tampil_siswa = new javax.swing.JMenuItem();
        jMenuItem_tambah_siswa = new javax.swing.JMenuItem();
        jMenuItem_ubah_siswa = new javax.swing.JMenuItem();
        jMenuItem_hapus_siswa = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem_tampil_guru = new javax.swing.JMenuItem();
        jMenuItem_tambah_guru = new javax.swing.JMenuItem();
        jMenuItem_ubah_guru = new javax.swing.JMenuItem();
        jMenuItem_hapus_guru = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem_tambah_piket = new javax.swing.JMenuItem();
        jMenuItem_ubah_piket = new javax.swing.JMenuItem();
        jMenuItem_hapus_piket = new javax.swing.JMenuItem();

        jMenuItem4.setText("jMenuItem4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistem Informasi Piket SMKN 3 Bandung");

        jMenu1.setText("Siswa");

        jMenuItem_tampil_siswa.setText("Tampil Data Siswa");
        jMenuItem_tampil_siswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_tampil_siswaActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem_tampil_siswa);

        jMenuItem_tambah_siswa.setText("Tambah Data Siswa");
        jMenuItem_tambah_siswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_tambah_siswaActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem_tambah_siswa);

        jMenuItem_ubah_siswa.setText("Ubah Data Siswa");
        jMenuItem_ubah_siswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_ubah_siswaActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem_ubah_siswa);

        jMenuItem_hapus_siswa.setText("Hapus Data Siswa");
        jMenuItem_hapus_siswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_hapus_siswaActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem_hapus_siswa);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Guru");

        jMenuItem_tampil_guru.setText("Tampil Data Guru");
        jMenuItem_tampil_guru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_tampil_guruActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem_tampil_guru);

        jMenuItem_tambah_guru.setText("Tambah Data Guru");
        jMenuItem_tambah_guru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_tambah_guruActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem_tambah_guru);

        jMenuItem_ubah_guru.setText("Ubah Data Guru");
        jMenuItem_ubah_guru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_ubah_guruActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem_ubah_guru);

        jMenuItem_hapus_guru.setText("Hapus Data Guru");
        jMenuItem_hapus_guru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_hapus_guruActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem_hapus_guru);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Piket");

        jMenuItem_tambah_piket.setText("Tambah Data Piket");
        jMenuItem_tambah_piket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_tambah_piketActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem_tambah_piket);

        jMenuItem_ubah_piket.setText("Ubah Data Piket");
        jMenuItem_ubah_piket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_ubah_piketActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem_ubah_piket);

        jMenuItem_hapus_piket.setText("Hapus Data Piket");
        jMenuItem_hapus_piket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_hapus_piketActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem_hapus_piket);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem_tampil_siswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_tampil_siswaActionPerformed
        // TODO add your handling code here:
        //menampilkan frame siswa
        frm_tampil_siswa siswa = new frm_tampil_siswa();
        siswa.setVisible(true);
        
        //menutup frame utama
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem_tampil_siswaActionPerformed

    private void jMenuItem_tambah_siswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_tambah_siswaActionPerformed
        // TODO add your handling code here:
        frm_tambah_siswa siswa = new frm_tambah_siswa();
        siswa.setVisible(true);
        
        //menutup frame utama
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem_tambah_siswaActionPerformed

    private void jMenuItem_ubah_siswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_ubah_siswaActionPerformed
        // TODO add your handling code here:
        frm_ubah_siswa ubah_siswa = new frm_ubah_siswa();
        ubah_siswa.setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem_ubah_siswaActionPerformed

    private void jMenuItem_hapus_siswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_hapus_siswaActionPerformed
        // TODO add your handling code here:
        frm_hapus_siswa hapus_siswa = new frm_hapus_siswa();
        hapus_siswa.setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem_hapus_siswaActionPerformed

    private void jMenuItem_tampil_guruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_tampil_guruActionPerformed
        // TODO add your handling code here:
        frm_tampil_guru tampil_guru = new frm_tampil_guru();
        tampil_guru.setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem_tampil_guruActionPerformed

    private void jMenuItem_tambah_guruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_tambah_guruActionPerformed
        // TODO add your handling code here:
        frm_tambah_guru tambah_guru = new frm_tambah_guru();
        tambah_guru.setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem_tambah_guruActionPerformed

    private void jMenuItem_ubah_guruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_ubah_guruActionPerformed
        // TODO add your handling code here:
        
        ubah_guru.setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem_ubah_guruActionPerformed

    private void jMenuItem_hapus_guruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_hapus_guruActionPerformed
        // TODO add your handling code here:
        frm_hapus_guru hapus_guru = new frm_hapus_guru();
        hapus_guru.setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem_hapus_guruActionPerformed

    private void jMenuItem_tambah_piketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_tambah_piketActionPerformed
        // TODO add your handling code here:
        frm_tambah_piket tambah_piket = new frm_tambah_piket();
        tambah_piket.setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem_tambah_piketActionPerformed

    private void jMenuItem_ubah_piketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_ubah_piketActionPerformed
        // TODO add your handling code here:
        frm_ubah_piket ubah_piket = new frm_ubah_piket();
        ubah_piket.setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem_ubah_piketActionPerformed

    private void jMenuItem_hapus_piketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_hapus_piketActionPerformed
        // TODO add your handling code here:
        frm_hapus_piket hapus_piket = new frm_hapus_piket();
        hapus_piket.setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem_hapus_piketActionPerformed

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
            java.util.logging.Logger.getLogger(frm_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_utama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem_hapus_guru;
    private javax.swing.JMenuItem jMenuItem_hapus_piket;
    private javax.swing.JMenuItem jMenuItem_hapus_siswa;
    private javax.swing.JMenuItem jMenuItem_tambah_guru;
    private javax.swing.JMenuItem jMenuItem_tambah_piket;
    private javax.swing.JMenuItem jMenuItem_tambah_siswa;
    private javax.swing.JMenuItem jMenuItem_tampil_guru;
    private javax.swing.JMenuItem jMenuItem_tampil_siswa;
    private javax.swing.JMenuItem jMenuItem_ubah_guru;
    private javax.swing.JMenuItem jMenuItem_ubah_piket;
    private javax.swing.JMenuItem jMenuItem_ubah_siswa;
    // End of variables declaration//GEN-END:variables
}
