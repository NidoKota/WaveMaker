package wavemaker;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.util.ArrayList;
import java.util.function.Consumer;

/*
* UIに関する処理
*/
public class View extends Object
{
    public View()
    {
        return;
    }

    private ArrayList<Consumer<InputData>> generateCallBackConsumers = new ArrayList<>();

    /*
    * 生成ボタンを押した際に呼ぶメソッドを登録する
    */
    public void generateAddListener(Consumer<InputData> consumer)
    {
        generateCallBackConsumers.add(consumer);
    }

    /*
    * 生成ボタンを押した際に呼ぶメソッドの登録を解除する
    */
    public void generateRemoveListener(Consumer<InputData> consumer)
    {
        generateCallBackConsumers.remove(consumer);
    }

    /*
    * ウィンドウを表示する
    */
    public void open()
    {
        JFrame aWindow = new JFrame("WaveMaker");

        aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aWindow.addNotify();
        int titleBarHeight = aWindow.getInsets().top;
        Point aPoint = new Point(600, 400);
        aWindow.setMinimumSize(new Dimension(aPoint.x, aPoint.y + titleBarHeight));
        aWindow.setResizable(true);
        aWindow.setSize(aPoint.x, aPoint.y + titleBarHeight);
        aWindow.setLocation(100, 100);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        addContents(mainPanel);
        aWindow.add(mainPanel);

        aWindow.setVisible(true);
        aWindow.toFront();

        return;
    }

    /*
    * ContainerにUIのコンテンツを追加する
    */
    private void addContents(Container container)
    {
        GridBagConstraints c = new GridBagConstraints();
        c.gridheight = 1;
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.WEST;

        setGridBagConstraints(c, 0, 0, 0, 0, GridBagConstraints.NONE);
        container.add(new JLabel("nを入力(正弦波は無効)"), c);

        JTextField nField = new JTextField();
        setGridBagConstraints(c, 1, 0, 1, 0, GridBagConstraints.HORIZONTAL);
        container.add(nField, c);
        
        setGridBagConstraints(c, 0, 2, 0, 0, GridBagConstraints.NONE);
        container.add(new JLabel("foを入力"), c);

        JTextField foField = new JTextField();
        setGridBagConstraints(c, 1, 2, 1, 0, GridBagConstraints.HORIZONTAL);
        container.add(foField, c);

        setGridBagConstraints(c, 0, 3, 0, 0, GridBagConstraints.NONE);
        container.add(new JLabel("波を選択"), c);

        JComboBox<WaveType> waveTypeComboBox = new JComboBox<>(new WaveType[]{WaveType.Sin, WaveType.Tri, WaveType.Sqr, WaveType.Saw});
        setGridBagConstraints(c, 1, 3, 1, 0, GridBagConstraints.HORIZONTAL);
        container.add(waveTypeComboBox, c);

        setGridBagConstraints(c, 3, 5, 0, 0, GridBagConstraints.NONE);
        JButton generateButton = new JButton("生成");
        container.add(generateButton, c);
        
        generateButton.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent event)
                {
                    int n = 1;
                    int fo = 1;
                    try 
                    {
                        n = Integer.parseInt(nField.getText());
                    } 
                    catch (NumberFormatException e) { }
                    try 
                    {
                        fo = Integer.parseInt(foField.getText());
                    } 
                    catch (NumberFormatException e) { }

                    InputData waveData = new InputData(n, fo, (WaveType)waveTypeComboBox.getSelectedItem());
                    for (Consumer<InputData> consumer : generateCallBackConsumers) consumer.accept(waveData);
                }
            });
    }

    /*
    * GridBagConstraintsのパラメーターを一気に設定する
    */
    private void setGridBagConstraints(GridBagConstraints c, int gridx, int gridy, double weightx, double weighty, int fill)
    {
        c.gridx = gridx;
        c.gridy = gridy;
        c.weightx = weightx;
        c.weighty = weighty;
        c.fill = fill;
    }
}
