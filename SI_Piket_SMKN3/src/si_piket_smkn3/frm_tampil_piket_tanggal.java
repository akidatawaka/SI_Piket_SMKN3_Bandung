/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si_piket_smkn3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.TreeMap;
import javafx.scene.control.Cell;
import javax.swing.JOptionPane;
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
public class frm_tampil_piket_tanggal extends javax.swing.JFrame {

    /**
     * Creates new form frm_tampil_piket_tanggal
     */
    
    //deklarasi variabel
    koneksi dbsetting;
    String driver, database, user, pass;
    Object tabel;
    
    String alamat_export;
    
    String format_tanggal = "yyyy-MM-dd";
    SimpleDateFormat fm = new SimpleDateFormat(format_tanggal);
    String tanggal;
    
    String format_tanggal_sekarang = "dd-MMM-yyyy";
    SimpleDateFormat tanggal_biasa = new SimpleDateFormat(format_tanggal_sekarang);
    String tanggal_sekarang;
    
    public static String SQL;
    
    public static String status_login;
    frm_utama utama = new frm_utama();
    
    
    public frm_tampil_piket_tanggal() {
        initComponents();
        
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        
        tbl_piket.setModel(tableModel);
        settableload();
    }
    
    //get data dari tabel
    private String getCellValue(int x,int y)
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

    private javax.swing.table.DefaultTableModel tableModel = getDefaultTabelModel();
    private javax.swing.table.DefaultTableModel getDefaultTabelModel()
    {
        //membuat judul header
        return new javax.swing.table.DefaultTableModel
        (
                new Object[][] {},
                new String[]{
                             "Tanggal",
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
                   + "from piket";
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_tanggal = new com.toedter.calendar.JDateChooser();
        btn_export_excel = new javax.swing.JButton();
        btn_tampil = new javax.swing.JButton();
        btn_laporan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_piket = new javax.swing.JTable();
        btn_kembali = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tampil Data Piket Berdasarkan Tanggal");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Data Piket");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Menampilkan Data Berdasarkan Tanggal", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Menampilkan Data Piket Dari Tanggal :");

        btn_export_excel.setText("Export Menjadi Excel");
        btn_export_excel.setEnabled(false);
        btn_export_excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_export_excelActionPerformed(evt);
            }
        });

        btn_tampil.setText("Tampil");
        btn_tampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tampilActionPerformed(evt);
            }
        });

        btn_laporan.setText("Cetak Laporan");
        btn_laporan.setEnabled(false);
        btn_laporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_laporanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_tampil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_export_excel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_laporan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_export_excel)
                    .addComponent(btn_tampil)
                    .addComponent(btn_laporan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        btn_kembali.setBackground(new java.awt.Color(255, 204, 255));
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
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1085, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_kembali)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(349, 349, 349)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tampilActionPerformed
        // TODO add your handling code here:
        
        
        tableModel.setRowCount(0);
        tanggal = new SimpleDateFormat(format_tanggal).format(txt_tanggal.getDate());
            try {
                Class.forName(driver);
                    Connection kon = DriverManager.getConnection(
                            database,
                            user,
                            pass);
                    Statement stt = kon.createStatement();
                        SQL = "SELECT tanggal,nis,nama_siswa,kelas_siswa,"
                            + "jenis_pelanggaran,keterangan,tindak_lanjut,nama_guru_1,nama_guru_2 "
                            + "FROM piket "
                            + "WHERE tanggal "
                            + "BETWEEN '"+tanggal+"' AND curdate();";
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
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
                        JOptionPane.INFORMATION_MESSAGE);
                
            }            
        btn_export_excel.setEnabled(true);
        btn_laporan.setEnabled(true);
        
    }//GEN-LAST:event_btn_tampilActionPerformed

    private void btn_export_excelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_export_excelActionPerformed
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
    }//GEN-LAST:event_btn_export_excelActionPerformed

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
             tanggal_sekarang = new SimpleDateFormat(format_tanggal_sekarang).format(txt_tanggal.getDate());
             String title = "LAPORAN PIKET SMK NEGERI 3 BANDUNG PER-TANGGAL "+tanggal_sekarang;
            JRTableModelDataSource datasource = new JRTableModelDataSource(tableModel);
            String reportSource = "./example.jrxml";

            JasperReport jr = JasperCompileManager.compileReport(reportSource);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("title1", title);

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
                /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_tampil_piket_tanggal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_export_excel;
    private javax.swing.JButton btn_kembali;
    private javax.swing.JButton btn_laporan;
    private javax.swing.JButton btn_tampil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_piket;
    private com.toedter.calendar.JDateChooser txt_tanggal;
    // End of variables declaration//GEN-END:variables
}
