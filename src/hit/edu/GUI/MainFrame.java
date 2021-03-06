/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hit.edu.GUI;

import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Noah
 */
public class MainFrame extends javax.swing.JFrame {
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        //gen_inventory_table_col_array();  //生成表格的数据
        initComponents();
        this.setLocationRelativeTo(null);   //设置为居中
    }
    //把除了自己的Tab全部disable
    private void deactive_tab(int i){
        int size = jTabbedPane1.getComponentCount();
        for (int k = 0; k < size ; k++){
            jTabbedPane1.setEnabledAt(k,false);
        }
        jTabbedPane1.setEnabledAt(i, true);
    }
    //生成1~356期的表格生成模型
    private void gen_inventory_table_col_array(){
        int period_count = (Integer)period_spinner.getModel().getValue();
            Object [][] tmpArray = new Object[1][period_count+3];
            //tmpArray = new Object[1][i+3];
            //for (int j = 0; j < period_count + 3; j++){
            //    tmpArray[0][j] = null;
            //}
            TwoDArray tmp2D = new TwoDArray(tmpArray);
            inventory_model_list = tmp2D;
            
            //int type_count = (Integer)type_spinner.getModel().getValue();
            String [] nameArray = null;
            nameArray = new String[period_count+3];
            nameArray[0] = "P-No";
            nameArray[1] = "OH";
            nameArray[2] = "AL";
            for (int j = 3; j < period_count +3 ;j++){
                nameArray[j] = (Integer.toString(j-3));
            }
            inventory_name_list = nameArray;

            Class [] classArray = null;
            classArray = new Class[period_count+3];
            classArray[0] = java.lang.String.class;
            classArray[1] = java.lang.Integer.class;
            classArray[2] = java.lang.Integer.class;
            for (int j = 3; j < period_count + 3; j ++){
                classArray[j] = java.lang.Integer.class;
            }
            inventory_class_list = classArray;
        
    }
    private synchronized void gen_inventory_table(){

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            int type_count = (Integer)type_spinner.getModel().getValue();
            final int period_count = (Integer)period_spinner.getModel().getValue();
            if(period_count != 13){
                inventory_table.setModel(new javax.swing.table.DefaultTableModel(
                inventory_model_list.array,
                inventory_name_list
                ) {
                Class[] types = inventory_class_list;
                @Override
                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }
                });
                DefaultTableModel tbm = (DefaultTableModel) inventory_table.getModel();
                while(tbm.getRowCount() < type_count){
                    tbm.addRow((Object[]) null);
                }
                inventory_table.repaint();
            }
            }
        });

    }
    /**
     * 这个方法用来生成读取结果的mps_table的数据模型，要更换一个新的数据模型
     * 例如：期数不同的时候，则仿照库存表的做法，setModel到通过这个函数生成的私有变量中正确的那个即可
     * 表格的行数不变，但是列数会变
     */
    private synchronized void gen_mps_table(){
        int period_count = (Integer)period_spinner.getModel().getValue();
            Object [][] tmpArray = new Object[7][period_count+1];

            tmpArray[0][0] = "GR";
            tmpArray[1][0] = "SR";
            tmpArray[2][0] = "POH";
            tmpArray[3][0] = "PAB";
            tmpArray[4][0] = "NR";
            tmpArray[5][0] = "PORcpt";
            tmpArray[6][0] = "POR";
            TwoDArray tmp2D = new TwoDArray(tmpArray);
            mps_model_list = tmp2D;
            
            //int type_count = (Integer)type_spinner.getModel().getValue();
            String [] nameArray = null;
            nameArray = new String[period_count+1];
            nameArray[0] = "Items";
            nameArray[1] = "Due";
            for (int j = 2; j < period_count + 1 ;j++){
                nameArray[j] = (Integer.toString(j-1));
            }
            mps_name_list = nameArray;

            Class [] classArray = null;
            classArray = new Class[period_count + 1];
            classArray[0] = java.lang.String.class;
            classArray[1] = java.lang.Integer.class;
            for (int j = 2; j < period_count + 1; j ++){
                classArray[j] = java.lang.Integer.class;
            }
            mps_class_list = classArray;
            
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            int type_count = (Integer)type_spinner.getModel().getValue();
            final int period_count = (Integer)period_spinner.getModel().getValue();
            if(period_count != 13){
                mps_table.setModel(new javax.swing.table.DefaultTableModel(
                mps_model_list.array,
                mps_name_list
                ) {
                Class[] types = mps_class_list;
                @Override
                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }
                });
                DefaultTableModel tbm = (DefaultTableModel) inventory_table.getModel();
                while(tbm.getRowCount() < type_count){
                    tbm.addRow((Object[]) null);
                }
                inventory_table.repaint();
            }
            }
        });
            
    }
    /**
     * 这个方法用来读取物料主文件中所有的物料编号用于最后显示结果时用户选择下拉框中显示
     */
    private void get_material_list(){
        int type_count = (Integer)type_spinner.getModel().getValue();
        Vector <String>vt = new Vector<String>();
        DefaultTableModel tableModel = (DefaultTableModel) item_master_table.getModel();
        for(int i = 0 ; i < type_count; i ++){    
            vt.add((String)tableModel.getValueAt(i, 0));
        }
        DefaultComboBoxModel cm = new DefaultComboBoxModel(vt);
        jComboBox1.setModel(cm);
        jComboBox1.repaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        view_past_button = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        new_mpr_button = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        type_spinner = new javax.swing.JSpinner();
        period_spinner = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        fin_init_button = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        item_master_table = new javax.swing.JTable();
        add_item_master_button = new javax.swing.JButton();
        fin_item_master_button = new javax.swing.JButton();
        del_item_master_button = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        bom_table = new javax.swing.JTable();
        add_bom_button = new javax.swing.JButton();
        del_bom_button = new javax.swing.JButton();
        fin_bom_button = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        inventory_table = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        add_inventory_button = new javax.swing.JButton();
        del_inventory_button = new javax.swing.JButton();
        fin_inventory_button = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        gr_table = new javax.swing.JTable();
        add_gr_button = new javax.swing.JButton();
        del_gr_button = new javax.swing.JButton();
        fin_gr_button = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        get_mpr_result_button = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane5 = new javax.swing.JScrollPane();
        mps_table = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        type_text = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        oh_text = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        al_text = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        lt_text = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        st_text = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        ss_text = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        lfl_text = new javax.swing.JTextField();
        home_button = new javax.swing.JButton();
        quit_button = new javax.swing.JButton();

        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel8.setText("P-No");

        jLabel9.setText("LT");

        jLabel10.setText("ST");

        jLabel11.setText("SS");

        jLabel12.setText("LLC");

        jLabel13.setText("LSR");

        jLabel14.setText("LS");

        jTextField2.setText("jTextField2");

        jTextField3.setText("jTextField3");

        jTextField4.setText("jTextField4");

        jTextField5.setText("jTextField5");

        jTextField6.setText("jTextField6");

        jTextField7.setText("jTextField7");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(57, 57, 57)
                .addComponent(jLabel10)
                .addGap(80, 80, 80)
                .addComponent(jLabel11)
                .addGap(61, 61, 61)
                .addComponent(jLabel12)
                .addGap(75, 75, 75)
                .addComponent(jLabel13)
                .addGap(69, 69, 69)
                .addComponent(jLabel14)
                .addGap(66, 66, 66))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MRP计算系统"); // NOI18N

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jLabel1.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        jLabel1.setText("请选择您需要的功能");

        view_past_button.setText("查看已有结果");
        view_past_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_past_buttonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        jLabel2.setText("欢迎使用MRP计算系统");

        new_mpr_button.setText("新的MRP计算");
        new_mpr_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new_mpr_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(view_past_button)
                        .addGap(155, 155, 155)
                        .addComponent(new_mpr_button))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))))
                .addContainerGap(227, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(jLabel2)
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addGap(75, 75, 75)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(view_past_button)
                    .addComponent(new_mpr_button))
                .addContainerGap(168, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("用户向导", jPanel1);

        jLabel3.setText("物料种类：");

        jLabel4.setText("计算期数：");

        jLabel5.setText("种");

        jLabel6.setText("期");

        type_spinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(6), Integer.valueOf(0), null, Integer.valueOf(1)));

        period_spinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(13), Integer.valueOf(0), null, Integer.valueOf(1)));
        period_spinner.setRequestFocusEnabled(false);

        jLabel7.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        jLabel7.setText("输入本次要计算的物料种类数目以及计算期数：");

        fin_init_button.setText("确认输入");
        fin_init_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fin_init_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(period_spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(type_spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(309, 309, 309)
                        .addComponent(fin_init_button)))
                .addContainerGap(361, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel7)
                .addGap(63, 63, 63)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(type_spinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(period_spinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71)
                .addComponent(fin_init_button)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("初始化设置", jPanel2);

        item_master_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"A",  new Integer(1),  new Integer(0),  new Integer(25),  new Integer(0), "FOQ",  new Integer(1)},
                {"B",  new Integer(1),  new Integer(0),  new Integer(20),  new Integer(0), "FOQ",  new Integer(1)},
                {"C",  new Integer(1),  new Integer(0),  new Integer(5),  new Integer(2), "FOQ",  new Integer(500)},
                {"D",  new Integer(1),  new Integer(0),  new Integer(5),  new Integer(1), "FOQ",  new Integer(200)},
                {"E",  new Integer(2),  new Integer(0),  new Integer(50),  new Integer(3), "FOQ",  new Integer(3)},
                {"F",  new Integer(2),  new Integer(1),  new Integer(100),  new Integer(3), "FOQ",  new Integer(2)}
            },
            new String [] {
                "P-No.", "LT", "ST", "SS", "LLC", "LSR", "LS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(item_master_table);
        item_master_table.getColumnModel().getColumn(3).setResizable(false);

        add_item_master_button.setText("添加一行");
        add_item_master_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_item_master_buttonActionPerformed(evt);
            }
        });

        fin_item_master_button.setText("完成输入");
        fin_item_master_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fin_item_master_buttonActionPerformed(evt);
            }
        });

        del_item_master_button.setText("删除一行");
        del_item_master_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                del_item_master_buttonActionPerformed(evt);
            }
        });

        jLabel21.setText("(注意删除掉您不需要的项目)");

        jLabel22.setText("物料主文件");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addGap(300, 300, 300))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(add_item_master_button)
                            .addComponent(fin_item_master_button)
                            .addComponent(del_item_master_button)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(360, 360, 360)
                        .addComponent(jLabel22)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(add_item_master_button)
                        .addGap(28, 28, 28)
                        .addComponent(del_item_master_button)
                        .addGap(32, 32, 32)
                        .addComponent(fin_item_master_button)))
                .addGap(44, 44, 44)
                .addComponent(jLabel21)
                .addContainerGap(108, Short.MAX_VALUE))
        );

        del_item_master_button.getAccessibleContext().setAccessibleDescription("删除当前选中的一行表格数据");

        jTabbedPane1.addTab("录入物料主文件", jPanel3);

        bom_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"A", "C",  new Integer(2)},
                {"A", "D",  new Integer(1)},
                {"D", "E",  new Integer(2)},
                {"D", "C",  new Integer(1)},
                {"C", "E",  new Integer(1)},
                {"C", "F",  new Integer(1)},
                {"B", "E",  new Integer(1)},
                {"B", "C", null}
            },
            new String [] {
                "Parent", "Comp", "Q-P"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(bom_table);

        add_bom_button.setText("添加一行");
        add_bom_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_bom_buttonActionPerformed(evt);
            }
        });

        del_bom_button.setText("删除一行");
        del_bom_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                del_bom_buttonActionPerformed(evt);
            }
        });

        fin_bom_button.setText("完成输入");
        fin_bom_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fin_bom_buttonActionPerformed(evt);
            }
        });

        jLabel15.setText("Bill of Material");

        jLabel16.setText("Please input BOM in this table.");

        jLabel17.setText("请删除没有用到的表格项");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel15))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel16))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel17)))
                .addGap(89, 89, 89)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(del_bom_button)
                    .addComponent(add_bom_button)
                    .addComponent(fin_bom_button))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(add_bom_button)
                        .addGap(39, 39, 39)
                        .addComponent(del_bom_button)
                        .addGap(39, 39, 39)
                        .addComponent(fin_bom_button))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17)))
                .addContainerGap(149, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("录入BOM", jPanel4);

        inventory_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"A",  new Integer(20),  new Integer(0),  new Integer(0),  new Integer(100),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0)},
                {"B",  new Integer(40),  new Integer(0),  new Integer(0),  new Integer(50),  new Integer(100),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0)},
                {"C",  new Integer(60),  new Integer(0),  new Integer(0),  new Integer(200),  new Integer(150),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0)},
                {"D",  new Integer(60),  new Integer(20),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0)},
                {"E",  new Integer(100),  new Integer(0),  new Integer(0),  new Integer(1500),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0)},
                {"F",  new Integer(100),  new Integer(0),  new Integer(0),  new Integer(1000),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0)}
            },
            new String [] {
                "P-No", "OH", "AL", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(inventory_table);

        jLabel18.setText("库存信息表");

        add_inventory_button.setText("添加一行");
        add_inventory_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_inventory_buttonActionPerformed(evt);
            }
        });

        del_inventory_button.setText("删除一行");
        del_inventory_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                del_inventory_buttonActionPerformed(evt);
            }
        });

        fin_inventory_button.setText("完成输入");
        fin_inventory_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fin_inventory_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(add_inventory_button)
                .addGap(149, 149, 149)
                .addComponent(del_inventory_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
                .addComponent(fin_inventory_button)
                .addGap(109, 109, 109))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(288, 288, 288)
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_inventory_button)
                    .addComponent(del_inventory_button)
                    .addComponent(fin_inventory_button))
                .addContainerGap(157, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("录入库存", jPanel5);

        gr_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"A",  new Integer(0)},
                {"B",  new Integer(0)},
                {null, null},
                {null, null}
            },
            new String [] {
                "P-No", "GR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(gr_table);

        add_gr_button.setText("添加一行");
        add_gr_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_gr_buttonActionPerformed(evt);
            }
        });

        del_gr_button.setText("删除一行");
        del_gr_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                del_gr_buttonActionPerformed(evt);
            }
        });

        fin_gr_button.setText("计算结果");
        fin_gr_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fin_gr_buttonActionPerformed(evt);
            }
        });

        jLabel19.setText("请在该表格中填入独立需求项物料的需求数量，注意不要重复");

        jLabel20.setText("（表格项不够可以点击添加一行按钮，输入错误可以选中该行选择删除一行）");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(196, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(113, 113, 113)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(del_gr_button)
                                    .addComponent(add_gr_button)
                                    .addComponent(fin_gr_button))))
                        .addGap(189, 189, 189))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(169, 169, 169))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel19)
                .addGap(37, 37, 37)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(add_gr_button)
                        .addGap(33, 33, 33)
                        .addComponent(del_gr_button)
                        .addGap(36, 36, 36)
                        .addComponent(fin_gr_button))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(jLabel20)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("录入GR", jPanel6);

        get_mpr_result_button.setText("查看物料计算结果");
        get_mpr_result_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                get_mpr_result_buttonActionPerformed(evt);
            }
        });

        jLabel23.setText("请输入想查看的物料项的物料编号，即P-No.");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        mps_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"GR",  new Integer(0),  new Integer(80),  new Integer(50),  new Integer(100),  new Integer(60),  new Integer(100),  new Integer(70),  new Integer(100),  new Integer(60),  new Integer(100),  new Integer(50),  new Integer(100),  new Integer(50)},
                {"SR",  new Integer(0),  new Integer(100),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0),  new Integer(0)},
                {"POH",  new Integer(0),  new Integer(40),  new Integer(-10),  new Integer(-75),  new Integer(-35),  new Integer(-75),  new Integer(-45),  new Integer(-75),  new Integer(-35),  new Integer(-75),  new Integer(-25),  new Integer(-75),  new Integer(-25)},
                {"PAB",  new Integer(0),  new Integer(40),  new Integer(25),  new Integer(25),  new Integer(25),  new Integer(25),  new Integer(25),  new Integer(25),  new Integer(25),  new Integer(25),  new Integer(25),  new Integer(25),  new Integer(25)},
                {"NR",  new Integer(0),  new Integer(0),  new Integer(35),  new Integer(100),  new Integer(60),  new Integer(100),  new Integer(70),  new Integer(100),  new Integer(60),  new Integer(100),  new Integer(50),  new Integer(100),  new Integer(50)},
                {"PORcpt",  new Integer(0),  new Integer(0),  new Integer(35),  new Integer(100),  new Integer(60),  new Integer(100),  new Integer(70),  new Integer(100),  new Integer(60),  new Integer(100),  new Integer(50),  new Integer(100),  new Integer(50)},
                {"POR",  new Integer(0),  new Integer(35),  new Integer(100),  new Integer(60),  new Integer(100),  new Integer(70),  new Integer(100),  new Integer(60),  new Integer(100),  new Integer(50),  new Integer(100),  new Integer(50),  new Integer(0)}
            },
            new String [] {
                "Items", "Due", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        mps_table.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(mps_table);
        mps_table.getColumnModel().getColumn(0).setHeaderValue("Items");
        mps_table.getColumnModel().getColumn(1).setHeaderValue("Due");
        mps_table.getColumnModel().getColumn(2).setHeaderValue("1");
        mps_table.getColumnModel().getColumn(3).setHeaderValue("2");
        mps_table.getColumnModel().getColumn(4).setHeaderValue("3");
        mps_table.getColumnModel().getColumn(5).setHeaderValue("4");
        mps_table.getColumnModel().getColumn(6).setHeaderValue("5");
        mps_table.getColumnModel().getColumn(7).setHeaderValue("6");
        mps_table.getColumnModel().getColumn(8).setHeaderValue("7");
        mps_table.getColumnModel().getColumn(9).setHeaderValue("8");
        mps_table.getColumnModel().getColumn(10).setHeaderValue("9");
        mps_table.getColumnModel().getColumn(11).setHeaderValue("10");
        mps_table.getColumnModel().getColumn(12).setHeaderValue("11");
        mps_table.getColumnModel().getColumn(13).setHeaderValue("12");

        jLabel25.setText("的MPR计算结果如下：");

        type_text.setEditable(false);
        type_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                type_textActionPerformed(evt);
            }
        });

        jLabel24.setText("OH：");

        jLabel26.setText("AL：");

        jLabel27.setText("LT:");

        jLabel28.setText("ST：");

        jLabel29.setText("SS：");

        jLabel30.setText("LFL：");

        home_button.setText("返回首页");
        home_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                home_buttonActionPerformed(evt);
            }
        });

        quit_button.setText("退出软件");
        quit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quit_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addComponent(get_mpr_result_button)
                .addGap(131, 131, 131))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(type_text, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(oh_text, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(al_text, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lt_text, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(st_text, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ss_text, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lfl_text, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(home_button)
                        .addGap(127, 127, 127)
                        .addComponent(quit_button)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(get_mpr_result_button))
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(type_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(oh_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(al_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(lt_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(st_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(ss_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(lfl_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(home_button)
                    .addComponent(quit_button))
                .addContainerGap(111, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("计算结果", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void view_past_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_past_buttonActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(6);
    }//GEN-LAST:event_view_past_buttonActionPerformed

    private void new_mpr_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_new_mpr_buttonActionPerformed
        // TODO add your handling code here:
       jTabbedPane1.setSelectedIndex(1);
       deactive_tab(1);
    }//GEN-LAST:event_new_mpr_buttonActionPerformed

    private void fin_item_master_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fin_item_master_buttonActionPerformed
        // TODO add your handling code here:
        int now_index = jTabbedPane1.getSelectedIndex();
        jTabbedPane1.setSelectedIndex(now_index+1);
        get_material_list();
        deactive_tab(now_index+1);
    }//GEN-LAST:event_fin_item_master_buttonActionPerformed

    private void fin_init_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fin_init_buttonActionPerformed
        // TODO add your handling code here:
        int now_index = jTabbedPane1.getSelectedIndex();
        gen_inventory_table_col_array();
        jTabbedPane1.setSelectedIndex(now_index+1);
        deactive_tab(now_index+1);
    }//GEN-LAST:event_fin_init_buttonActionPerformed

    private void add_item_master_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_item_master_buttonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) item_master_table.getModel();
        model.addRow(new Object[]{null, null, null, null, null, null ,null});
        
    }//GEN-LAST:event_add_item_master_buttonActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void del_item_master_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_del_item_master_buttonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) item_master_table.getModel();
        //model.addRow(new Object[]{null, null, null, null, null, null ,null});
        //model.removeRow(model.getRowCount()-1);             //减少一行
        int sr = item_master_table.getSelectedRow();
        System.out.println(sr);
        if(sr == -1){
            JOptionPane.showMessageDialog(null,"请选中您想删除的一行");
        }else{
            model.removeRow(item_master_table.getSelectedRow());    //删除选中的那一行
        }
    }//GEN-LAST:event_del_item_master_buttonActionPerformed

    private void add_bom_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_bom_buttonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) bom_table.getModel();
        model.addRow(new Object[]{null, null, null});
    }//GEN-LAST:event_add_bom_buttonActionPerformed

    private void del_bom_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_del_bom_buttonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) bom_table.getModel();
        //model.removeRow(model.getRowCount()-1);             //减少一行
        int sr = bom_table.getSelectedRow();
        System.out.println(sr);
        if(sr == -1){
            JOptionPane.showMessageDialog(null,"请选中您想删除的一行");
        }else{
            model.removeRow(bom_table.getSelectedRow());    //删除选中的那一行
        }
    }//GEN-LAST:event_del_bom_buttonActionPerformed

    private void fin_bom_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fin_bom_buttonActionPerformed
        // TODO add your handling code here:
        int now_index = jTabbedPane1.getSelectedIndex();
        jTabbedPane1.setSelectedIndex(now_index+1);
        gen_inventory_table();
        deactive_tab(now_index+1);
    }//GEN-LAST:event_fin_bom_buttonActionPerformed

    private void add_inventory_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_inventory_buttonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) inventory_table.getModel();
        //Object []m = inventory_model_list.get(model.getRowCount()-1).array;
        model.addRow((Vector)null);
    }//GEN-LAST:event_add_inventory_buttonActionPerformed

    private void del_inventory_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_del_inventory_buttonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) inventory_table.getModel();
        //model.removeRow(model.getRowCount()-1);             //减少一行
        int sr = inventory_table.getSelectedRow();
        if(sr == -1){
            JOptionPane.showMessageDialog(null,"请选中您想删除的一行");
        }else{
            model.removeRow(inventory_table.getSelectedRow());    //删除选中的那一行
        }        
    }//GEN-LAST:event_del_inventory_buttonActionPerformed

    private void fin_inventory_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fin_inventory_buttonActionPerformed
        // TODO add your handling code here:
        int now_index = jTabbedPane1.getSelectedIndex();
        jTabbedPane1.setSelectedIndex(now_index+1);
        deactive_tab(now_index+1);        
    }//GEN-LAST:event_fin_inventory_buttonActionPerformed

    private void add_gr_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_gr_buttonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) gr_table.getModel();
        //Object []m = inventory_model_list.get(model.getRowCount()-1).array;
        model.addRow((Vector)null);
    }//GEN-LAST:event_add_gr_buttonActionPerformed

    private void del_gr_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_del_gr_buttonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) gr_table.getModel();
        //model.removeRow(model.getRowCount()-1);             //减少一行
        int sr = gr_table.getSelectedRow();
        if(sr == -1){
            JOptionPane.showMessageDialog(null,"请选中您想删除的一行");
        }else{
            model.removeRow(gr_table.getSelectedRow());    //删除选中的那一行
        }   
    }//GEN-LAST:event_del_gr_buttonActionPerformed

    private void fin_gr_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fin_gr_buttonActionPerformed
        // TODO add your handling code here:
        int now_index = jTabbedPane1.getSelectedIndex();
        jTabbedPane1.setSelectedIndex(now_index+1);
        gen_mps_table();
        deactive_tab(now_index+1);
    }//GEN-LAST:event_fin_gr_buttonActionPerformed

    private void get_mpr_result_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_get_mpr_result_buttonActionPerformed
        // TODO add your handling code here:
        //取下拉菜单的数据填入
        String tmpstr = (String)jComboBox1.getModel().getSelectedItem();
        type_text.setText(tmpstr);
    }//GEN-LAST:event_get_mpr_result_buttonActionPerformed

    private void type_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_type_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_type_textActionPerformed

    private void quit_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quit_buttonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_quit_buttonActionPerformed

    private void home_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_home_buttonActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
        
    }//GEN-LAST:event_home_buttonActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                /*try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                new MainFrame().setVisible(true);
            }
        });
    }
    
   private class TwoDArray {
        Object[][] array;
        TwoDArray(Object[][] initialArray){
            array = initialArray;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_bom_button;
    private javax.swing.JButton add_gr_button;
    private javax.swing.JButton add_inventory_button;
    private javax.swing.JButton add_item_master_button;
    private javax.swing.JTextField al_text;
    private javax.swing.JTable bom_table;
    private javax.swing.JButton del_bom_button;
    private javax.swing.JButton del_gr_button;
    private javax.swing.JButton del_inventory_button;
    private javax.swing.JButton del_item_master_button;
    private javax.swing.JButton fin_bom_button;
    private javax.swing.JButton fin_gr_button;
    private javax.swing.JButton fin_init_button;
    private javax.swing.JButton fin_inventory_button;
    private javax.swing.JButton fin_item_master_button;
    private javax.swing.JButton get_mpr_result_button;
    private javax.swing.JTable gr_table;
    private javax.swing.JButton home_button;
    private javax.swing.JTable inventory_table;
    private javax.swing.JTable item_master_table;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField lfl_text;
    private javax.swing.JTextField lt_text;
    private javax.swing.JTable mps_table;
    private javax.swing.JButton new_mpr_button;
    private javax.swing.JTextField oh_text;
    private javax.swing.JSpinner period_spinner;
    private javax.swing.JButton quit_button;
    private javax.swing.JTextField ss_text;
    private javax.swing.JTextField st_text;
    private javax.swing.JSpinner type_spinner;
    private javax.swing.JTextField type_text;
    private javax.swing.JButton view_past_button;
    // End of variables declaration//GEN-END:variables
    private String[] inventory_name_list ;
    private TwoDArray inventory_model_list;
    private Class [] inventory_class_list ;
    private TwoDArray mps_model_list ;
    private Class [] mps_class_list ;
    private String[] mps_name_list  ;
    
    
}
