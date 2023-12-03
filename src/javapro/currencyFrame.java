package javapro;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Currency;



public class currencyFrame extends JFrame implements ActionListener, ItemListener, ListSelectionListener ,MouseListener,MouseMotionListener{

    private JTextField t1, t2, t3, t4, t5;
    private JButton b1, b2, b3, b4, b5;
    private JLabel l1, l2, l3, l4, l5, l6, l7;
    private JPanel j1, j2, j3, j4, j5, jk;
    private JComboBox c1, c2, c3, c4;
    private GridLayout g1;
    private Container n;
    private JList jl;
    private Color col1,col2;
    double currentX,currentY;
    private JScrollPane pa;
    private DefaultListModel<String> mn = new DefaultListModel<String>();

    public currencyFrame() {
        super("Currency Exchange");

        n = getContentPane();
        n.setBackground(new Color(128, 128, 128));

        g1 = new GridLayout(5, 1, 2, 2);
        setLayout(g1);
        t1 = new JTextField(5);
        t2 = new JTextField(20);
        t3 = new JTextField(10);
        t4 = new JTextField(10);
        t5 = new JTextField(10);

        c1 = new JComboBox();
        c1.setPreferredSize(new Dimension(150, 20));
        c1.addItemListener(this);
        c2 = new JComboBox();
        c2.setPreferredSize(new Dimension(150, 20));
        c2.addItemListener(this);
        c3 = new JComboBox();
        c3.setPreferredSize(new Dimension(250, 20));
        c3.addItemListener(this);
        c4 = new JComboBox();
        c4.setPreferredSize(new Dimension(250, 20));
        c4.addItemListener(this);



        l1 = new JLabel("Symbol");
        l2 = new JLabel("Currency Name");
        l3 = new JLabel("1");
        l4 = new JLabel("Rate to Update");
        l5 = new JLabel("New Rate");
        l6 = new JLabel("Amount");
        l7 = new JLabel("Rate");
        //////
        b1 = new JButton("Add Currency");
        b1.addActionListener(this);

        b2 = new JButton("Add Rate");
        b2.addActionListener(this);
        b2.setEnabled(false);
 ///////mous
        addMouseMotionListener(this);
        b3 = new JButton("Update");
        b3.setEnabled(false);
        b3.addActionListener(this);

        b4 = new JButton("Add");
        b4.setEnabled(false);
        b4.addActionListener(this);

        b5 = new JButton("Remove");
        b5.setEnabled(false);
        b5.addActionListener(this);
///////////
        jl = new JList(mn);
        jl.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        pa = new JScrollPane(jl);
        jl.setFixedCellHeight(10);
        jl.setFixedCellWidth(380);
        jl.addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        jl.getSelectedIndex();
                        b5.setEnabled(true);
                    }
                }
        );
        j1 = new JPanel();
        j1.add(l1);
        j1.add(t1);
        j1.add(l2);
        j1.add(t2);
        j1.add(b1);
        j2 = new JPanel();
        j2.add(l3);
        j2.add(c1);
        j2.add(t3);
        j2.add(c2);
        j2.add(b2);
        j3 = new JPanel();
        j3.add(l4);
        j3.add(c3);
        j3.add(l5);
        j3.add(t4);
        j3.add(b3);
        j4 = new JPanel();
        j4.add(l6);
        j4.add(t5);
        j4.add(l7);
        j4.add(c4);
        j4.add(b4);
        j5 = new JPanel();
        j5.add(pa);
        j5.add(b5);

        // jk=new JPanel();
        //jk.setLayout(5,1);

        add(j1);
        add(j2);
        add(j3);
        add(j4);
        add(j5);



        }








    public static void main(String[] args) {
        currencyFrame ss = new currencyFrame();
        ss.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ss.setSize(1200, 600);
        ss.setVisible(true);


    }
    ArrayList<currency> a = new ArrayList<>();
    @Override
    public void actionPerformed(ActionEvent e) {
        //when click add Currency.....................................................
        int z = 0;
        if (e.getSource() == b1) {
            currency cc = new currency(t2.getText(), t1.getText());

            //to chick currency is not repeat...
                boolean exist = false;

            for (int i = 0; i < a.size(); i++) {

                if (cc.equals(a.get(i))) {
                    JOptionPane.showMessageDialog(null, "This currency already exists");
                    exist = true;
                }

            }
            if(exist == false){
                a.add(cc);
                c1.addItem(cc);
                c2.addItem(cc);
            }

        }


        //when click add Rate............................................................
        if (e.getSource() == b2) {
            ExchangeRate xx1 = new ExchangeRate((currency) c1.getSelectedItem(), (currency) c2.getSelectedItem(), Double.parseDouble(t3.getText()));

            if (!(numper(t3.getText())))
                JOptionPane.showMessageDialog(null, "Please make sure the rate is a number");
            else {
                c3.addItem(xx1);
                c4.addItem(xx1);
                b3.setEnabled(true);
                b4.setEnabled(true);
            }
        }
        if (e.getSource() == b3) {
            ExchangeRate xx2 = new ExchangeRate((currency) c1.getSelectedItem(), (currency) c2.getSelectedItem(), Double.parseDouble(t4.getText()));

            if (!(numper(t4.getText())))
                JOptionPane.showMessageDialog(null, "Please make sure the rate is a number");
            else {
                c3.removeItemListener(this);
                c3.removeItemAt(c3.getSelectedIndex());
                c3.addItem(xx2);
                c4.removeItemAt(c3.getSelectedIndex());
                c4.addItem(xx2);
                c3.addItemListener(this);



            }
        }

        if(e.getSource()==b4){
            ExchangeRateWithValue xc =new ExchangeRateWithValue((ExchangeRate)c4.getSelectedItem(),Double.parseDouble(t5.getText()));

         mn.addElement(String.valueOf(xc));
        jl.setModel(mn);

        }
        if(e.getSource()==b5){
            mn.remove(jl.getSelectedIndex());
        }

    }

    boolean numper(String q) {
        try {
            Double.parseDouble(q);
        } catch (NumberFormatException exception) {
            return false;
        }
        return true;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // to chick rate is not repeat...
        if (c1.getSelectedItem().equals(c2.getSelectedItem()))
            b2.setEnabled(false);
        else b2.setEnabled(true);
  // @Override
 //   public void vlueChanged()


    }


    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        double x =e.getX();
        double y =e.getY();
        double speed = 1 ;
       //////..............................................................................

        double ratio = Math.abs((double)(currentX-x))/speed;
        double ratio2 = Math.abs((double)(currentY-y))/speed;
        try{
            col1 = new Color(255,127,255,(int)(ratio*255.0)); }
        catch(Exception v){
            col1 = new Color(183, 91, 183,255);}
        try{
            col2= new Color(164,255,251,(int)(ratio2*255.0));  }
        catch(Exception v){
            col2= new Color(105, 217, 212,255);}
        double qRatio = (double)col2.getAlpha()/((double)col2.getAlpha()+(double)col1.getAlpha()+1);
        double pRatio = (double)col1.getAlpha()/((double)col2.getAlpha()+(double)col1.getAlpha()+1);
        Color n = new Color((int)((double)col2.getRed()*qRatio + (double)col1.getRed()*pRatio) ,
                (int)((double)col2.getGreen()*qRatio + col1.getGreen()*pRatio ),
                (int)((double)col2.getBlue()*qRatio + col1.getBlue()*pRatio ),
                (col2.getAlpha() +col1.getAlpha()) /2);
        j1.setBackground(n);
        j1.setVisible(false);
        j1.setVisible(true);

        j2.setBackground(n);
        j2.setVisible(false);
        j2.setVisible(true);

        j3.setBackground(n);
        j3.setVisible(false);
        j3.setVisible(true);

        j4.setBackground(n);
        j4.setVisible(false);
        j4.setVisible(true);

        j5.setBackground(n);
        j5.setVisible(false);
        j5.setVisible(true);
        currentX = x ;
        currentY = y ;
    }
}


