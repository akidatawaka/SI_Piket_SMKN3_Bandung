/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si_piket_smkn3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.TreeMap;
import javafx.scene.control.Cell;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.JFileChooser;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;



/**
 *
 * @author Akida
 */
public class frm_tampil_piket extends javax.swing.JFrame {

    /**
     * Creates new form frm_tampil_piket
     */
    
    //deklarasi variabel
    koneksi dbsetting;
    String driver, database, user, pass;
    Object tabel;
    
    String alamat_export;
    
    public static String status_login;
    frm_utama utama = new frm_utama();
    
    public frm_tampil_piket() {
        initComponents();
        
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        
        tbl_piket.setModel(tableModel);
        settableload();
    }

    private javax.swing.table.DefaultTableModel tableModel = getDefaultTabelModel();
    private javax.swing.table.DefaultTableModel getDefaultTabelModel()
    {
        //membuat judul header
        return new javax.swing.table.DefaultTableModel
        (
                new Object[][] {},
                new String[]{"Tanggal",
                             "NIS",
                             "Nama Siswa",
                             "Kelas Siswa",
                             "Jenis Pelanggaran",
                             "Keterangan",
                             "Tindak Lanjut",
                             "Nama Guru 1",
                             "Nama Guru 2"}
        )
        //disable perubahan pada grid
        {
            boolean[] canEdit = new boolean[]
            {
                false, false, false, false,
                false, false, false, false,
                false
            };
            
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit[columnIndex];
            }
        };
    }
    
    String data[] = new String[12];
    private void settableload()
    {
        String stat = "";
        try
        {
           Class.forName(driver);
           Connection kon = DriverManager.getConnection(
                   database,
                   user,
                   pass);
           Statement stt=kon.createStatement();
           String SQL = "SELECT tanggal,nis,nama_siswa,kelas_siswa,"
                   + "jenis_pelanggaran,keterangan,tindak_lanjut,nama_guru_1,nama_guru_2 "
                   + "FROM piket";
           ResultSet res = stt.executeQuery(SQL);
           while(res.next()){
               data[0] = res.getString(1);
               data[1] = res.getString(2);
               data[2] = res.getString(3);
               data[3] = res.getString(4);
               data[4] = res.getString(5);
               data[5] = res.getString(6);
               data[6] = res.getString(7);
               data[7] = res.getString(8);  
               data[8] = res.getString(9);
               tableModel.addRow(data);
               
           }
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null,
                                          ex.getMessage(),"Error",
                                          JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    
    }
    
    //mengambil data dari tabel
    private String getCellValue(int x, int y)
    {
        return tbl_piket.getValueAt(x,y).toString();
    }
    
    private void exportexcel(String alamat)
    {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet ws = wb.createSheet();
        
        //load data dari tabel ke treemap
        TreeMap<Integer,Object[]> data = new  TreeMap<>();
        data.put(0, new Object[]{tableModel.getColumnName(0),tableModel.getColumnName(1),
                      tableModel.getColumnName(2),tableModel.getColumnName(3),
                      tableModel.getColumnName(4),tableModel.getColumnName(5),
                      tableModel.getColumnName(6),tableModel.getColumnName(7),
                      tableModel.getColumnName(8)}
        );
        for (int i = 1; i < tableModel.getRowCount()+1; i++) {            
        
        data.put(i,new Object[]{getCellValue(i-1, 0),getCellValue(i-1, 1),
                                  getCellValue(i-1, 2),getCellValue(i-1, 3),
                                  getCellValue(i-1, 4),getCellValue(i-1, 5),
                                  getCellValue(i-1, 6),getCellValue(i-1, 7),
                                  getCellValue(i-1, 8),
        });
        }
        
        //menulis ke kertas
        Set<Integer> ids = data.keySet();
        XSSFRow row;
        int rowID=0;
        
        for(Integer key: ids)
        {
            row = ws.createRow(rowID++);
            
            //get data as per key
            Object[] values = data.get(key);
            
            int cellID=0;
            for(Object o: values)
            {
                XSSFCell cell = row.createCell(cellID++);
                cell.setCellValue(o.toString());
            }
        }
        
        //write excel to file system
        try
        {
            FileOutputStream fos = new FileOutputStream(new File(alamat));
            wb.write(fos);
            fos.close();
        }catch(Exception ex)
        {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null,
                                          ex.getMessage(),"Error",
                                          JOptionPane.INFORMATION_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_pencarian = new javax.swing.JTextField();
        btn_tampil = new javax.swing.JButton();
        btn_export = new javax.swing.JButton();
        btn_laporan = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_piket = new javax.swing.JTable();
        btn_kembali = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tampil Data Piket Berdasarkan Nama");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Pencarian Data Piket");

        txt_pencarian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_pencarianKeyReleased(evt);
            }
        });

        btn_tampil.setText("Tampil Data Piket");
        btn_tampil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tampilMouseClicked(evt);
            }
        });

        btn_export.setText("Export Menjadi Excel");
        btn_export.setEnabled(false);
        btn_export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exportActionPerformed(evt);
            }
        });

        btn_laporan.setText("Cetak Laporan");
        btn_laporan.setEnabled(false);
        btn_laporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_laporanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txt_pencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_tampil, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_export)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_laporan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_pencarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tampil)
                    .addComponent(btn_export)
                    .addComponent(btn_laporan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Data Piket");

        tbl_piket.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl_piket);

        btn_kembali.setBackground(new java.awt.Color(255, 204, 204));
        btn_kembali.setText("Kembali Ke Menu Utama");
        btn_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1085, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 890, Short.MAX_VALUE)
                        .addComponent(btn_kembali)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_pencarianKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pencarianKeyReleased
        // TODO add your handling code here:
        tableModel.setRowCount(0);

        //query untuk pencarian
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT tanggal,nis,nama_siswa,kelas_siswa,"
                    + "jenis_pelanggaran,keterangan,tindak_lanjut,nama_guru_1,nama_guru_2 "
                    + "FROM piket where nama_siswa like '%"
            + txt_pencarian.getText()+"%'"
            +"or nama_guru_1 like '%"+txt_pencarian.getText()+"%'"
            +"or nama_guru_2 like '%"+txt_pencarian.getText()+"%'";
            ResultSet res = stt.executeQuery(SQL);
            while (res.next())
            {
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
                data[5] = res.getString(6);
                data[6] = res.getString(7);
                data[7] = res.getString(8);
                data[8] = res.getString(9);
                

                tableModel.addRow(data);
            }
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
        }
        btn_export.setEnabled(true);
        btn_laporan.setEnabled(true);
    }//GEN-LAST:event_txt_pencarianKeyReleased

    private void btn_tampilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tampilMouseClicked
        // TODO add your handling code here:
        txt_pencarian.setText("");
        tableModel.setRowCount(0);
        settableload();
        txt_pencarian.setRequestFocusEnabled(true);
        btn_export.setEnabled(false);
        btn_laporan.setEnabled(false);
    }//GEN-LAST:event_btn_tampilMouseClicked

    private void btn_exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exportActionPerformed
        // TODO add your handling code here:
        JFileChooser buatfile = new JFileChooser();
        //menambahkan pilihan save as xlsx
        FileNameExtensionFilter xlsxFilter = new FileNameExtensionFilter("xlsx files (*.xlsx)", "xlsx");
        buatfile.addChoosableFileFilter(xlsxFilter);
        buatfile.setFileFilter(xlsxFilter);
        
        buatfile.showSaveDialog(null);
        File x_file = buatfile.getSelectedFile();
        
        alamat_export = x_file.getAbsolutePath()+".xlsx";
        
        exportexcel(alamat_export);
        JOptionPane.showMessageDialog(null, "Data Piket Berhasil Di Ekspor !");
    }//GEN-LAST:event_btn_exportActionPerformed

    private void btn_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliActionPerformed
        // TODO add your handling code here:
        switch (status_login) {
            case "piket":
                status_login = frm_utama.status_login;
                frm_utama.menu_siswa.setEnabled(false);
                frm_utama.menu_guru.setEnabled(false);
                break;
            case "admin":
                status_login = frm_utama.status_login;
                break;
        }
        utama.setVisible(true);    
        this.setVisible(false);
    }//GEN-LAST:event_btn_kembaliActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int selectedOption = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin Logout dan Keluar ?","Konfirmasi Logout dan Keluar",JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {            
                System.exit(0);
            }
        else {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);                       
        }
    }//GEN-LAST:event_formWindowClosing

    private void btn_laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_laporanActionPerformed
        // TODO add your handling code here:
        try {
            //DefaultTableModel de = (DefaultTableModel) jTable1.getModel();
            JRTableModelDataSource datasource = new JRTableModelDataSource(tableModel);
            String reportSource = "./example.jrxml";

            JasperReport jr = JasperCompileManager.compileReport(reportSource);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("title1", "LAPORAN PIKET SMK NEGERI 3 BANDUNG BERDASARKAN NAMA");

            JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);


            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_laporanActionPerformed

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
            java.util.logging.Logger.getLogger(frm_tampil_piket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_tampil_piket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_tampil_piket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_tampil_piket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_tampil_piket().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_export;
    private javax.swing.JButton btn_kembali;
    private javax.swing.JButton btn_laporan;
    private javax.swing.JButton btn_tampil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_piket;
    private javax.swing.JTextField txt_pencarian;
    // End of variables declaration//GEN-END:variables
}
