
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class FontVisual extends JFrame{
	
	private  JTextArea textBox;
	private JScrollPane scrollPane;
	private  JComboBox<String> fontSelect;
	private  JCheckBox bold;
	private JCheckBox italic;
	private  JButton increase;
	private  JButton decrease;
	
	private static final String[] f = {"Serif","Sans-Serif","Monospaced"};
	private int dFont = 18; // default font size at 18
	private int dFont2 = 20; //default font size 20 for jcombobox, jbuttons, jcheckbox
	private String fontType = "Serial";
	
	public FontVisual() {
		//constructor and setting up flowlayout
		super("Font Tester");
		setLayout(new FlowLayout());
		setSize(new Dimension(500,500));
		
		
		
		//JcomboBox
		fontSelect = new JComboBox<String>(f);
		fontSelect.setFont(new Font(fontType,Font.PLAIN,dFont2));
		fontSelect.setMaximumRowCount(3);
		
		//JTextArea
		textBox = new JTextArea("Type here",20,40);
		Font font1 = new Font(fontType,Font.PLAIN,dFont);
		textBox.setFont(font1);
		scrollPane = new JScrollPane(textBox);
		
		//JCheckBox
		bold = new JCheckBox("Bold");
		bold.setFont(new Font(fontType,Font.PLAIN,dFont2));
		italic = new JCheckBox("Italic");
		italic.setFont(new Font(fontType,Font.PLAIN,dFont2));
		
		//incease and decrease buttons
		increase = new JButton("Increase Font");
		decrease = new JButton("Decrease Font");
		increase.setFont(new Font(fontType,Font.PLAIN,dFont2));
		decrease.setFont(new Font(fontType,Font.PLAIN,dFont2));
		
		Handler handler = new Handler();
		bold.addItemListener(handler);
		italic.addItemListener(handler);
		increase.addActionListener(handler);
		decrease.addActionListener(handler);
		
		fontSelect.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if(event.getStateChange()==ItemEvent.SELECTED) {
					String item = (String)fontSelect.getSelectedItem();
					int font = textBox.getFont().getStyle();
					textBox.setFont(new Font(item,font,dFont));
					fontType = item;
				}
		}
		});
		
		//setting tool tips
		scrollPane.setToolTipText("Type your text here!");
		fontSelect.setToolTipText("Select your Font here!");
		bold.setToolTipText("Check to make text BOLD!");
		increase.setToolTipText("Click to increase font size!");
		decrease.setToolTipText("Click to decrease font size!");
		
		//adding all the components
		add(fontSelect);
		add(increase);
		add(decrease);
		add(bold);
		add(italic);
		add(scrollPane);
	}
	
	// ItemListener and ActionListener
	private class Handler implements ItemListener,ActionListener{
		Font f = null; // font variable that will be used to change the font of the jTextbox
		
		//itemStateChanged method
		@Override
		public void itemStateChanged(ItemEvent event) {
		if(bold.isSelected()&&italic.isSelected())
			f = new Font(fontType,Font.BOLD+Font.ITALIC,dFont);
		else if(bold.isSelected()&&(!italic.isSelected()))
			f = new Font(fontType,Font.BOLD,dFont);
		else if(!bold.isSelected()&&(italic.isSelected()))
			f = new Font(fontType,Font.ITALIC,dFont);
		else
			f = new Font(fontType,Font.PLAIN,dFont);
		textBox.setFont(f);
		}
		
		//actionPerformed method
		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getSource()==increase) {
				dFont++;
				int fontStyle = textBox.getFont().getStyle();
				f = new Font(fontType,fontStyle,dFont);
			}
			else if(event.getSource()==decrease) {
				dFont--;
				int fontStyle = textBox.getFont().getStyle();
				f = new Font(fontType,fontStyle,dFont);
			}
			textBox.setFont(f);
			
		}
		
		
		
	}
}
