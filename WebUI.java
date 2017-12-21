package fyp;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.filechooser.*;

import org.jfree.chart.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.jdbc.JDBCCategoryDataset;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LinearRegression;




public class WebUI extends JApplet{

	
	JLabel file = new JLabel("Trainning Dataset (only csv):");
	JLabel file2 = new JLabel("Predict Dataset (only csv) :");
	
	JLabel file5 = new JLabel("WEKA Prediction");
	JLabel title = new JLabel("Agriculture Monitoring via IoT");
	JLabel traindata = new JLabel("Train Dataset :");
	JLabel predictdata = new JLabel("Predict Dataset :");
	JLabel finaloutput = new JLabel("Prediction Output :");
	JLabel analysis = new JLabel(" Analysis");

	
	JButton graph = new JButton("Time-Temp");
	JButton graph1 = new JButton("Time-Moisture");
	JButton graph2 = new JButton("Temp-Moisture");
	//JButton graph3 = new JButton("Moisture-Temp");
	//JButton graph4 = new JButton("Month-Disaster");
	JButton file6 = new JButton("Get Data from Database");
	//JButton yield = new JButton("Yield");
	JButton temp = new JButton("Temperature");
	JButton soil = new JButton("Moisture");
	//JButton disaster = new JButton("Disaster");
	JButton b = new JButton("Choose File");
	JButton b1 = new JButton("Choose File");
	//JButton show = new JButton("Show Data");
	JTextArea outputtrain = new JTextArea (300,200);
	JTextArea outputpredict = new JTextArea ();
	JTextArea outputfinal = new JTextArea ();
	
	JScrollPane sp = new JScrollPane (outputtrain);
	JScrollPane sp1 = new JScrollPane (outputpredict);
	JScrollPane sp2 = new JScrollPane (outputfinal);
	JFrame f = new JFrame ();
	JFrame f1 = new JFrame ();
	JFrame f2 = new JFrame ();
	
	JPanel j = new JPanel ();
	
	
	public static JFileChooser choose = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	public static JFileChooser chooser1 = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	//String[] description = { "Naive Bayes", "J48", "SMO" ,"LinearRegression" };
	//String[] description1 = { "Hurricane ","SoilWater", "MaxTemp", "MinTemp" ,"Yield" };
	//JComboBox class1 = new JComboBox (description1);
	//JComboBox algo = new JComboBox (description);
	//JButton run = new JButton ("Run Algorithm");
	//JTextField t = new JTextField(15);
	int count = 0;
	
	
	public void init(){
		
		 
		  add(file5);
		  file5.setFont(new Font("ARIAL", Font.ROMAN_BASELINE, 18));
		  file5.setBounds(20,20,500,20);
		  setLayout(null);
		  
		  
		  add(analysis);
		  analysis.setFont(new Font("ARIAL", Font.ROMAN_BASELINE, 18));
		  analysis.setBounds(400,150,500,20);
		  setLayout(null);
		  
		  add(file6);
		  file6.setFont(new Font("ARIAL", Font.ROMAN_BASELINE, 15));
		  file6.setBounds(20,50,200,20);
		  setLayout(null);
		 
		  add(file);
		  file.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		  file.setBounds(20,100,200,20);
		  setLayout(null);
		  
		  add(traindata);
	      traindata.setBounds(20,130,200,20);
	      setLayout(null);
		  
	      add(b);
	      b.setBounds(200,100,100,20);
	      setLayout(null);
	      
	      f.getContentPane().add(sp);
		  add(sp);
		  sp.setBounds(20,150,300,200);
	      setLayout(null);
	      outputtrain.setEditable(false);
	      
	      add(file2);
		  file2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		  file2.setBounds(20,370,200,20);
		  setLayout(null);
		  
		  add(predictdata);
	      predictdata.setBounds(20,400,200,20);
	      setLayout(null);
		  
		  add(b1);
	      b1.setBounds(200,370,100,20);
	      setLayout(null);
	      
	      f1.getContentPane().add(sp1);
	      add(sp1);
	      sp1.setBounds(20,420,300,200);
	      setLayout(null);
	      outputpredict.setEditable(false);
	      
	      
	     // add(yield);
	      //yield.setBounds(400,370,150,20);
	      //setLayout(null);
	      
	      add(temp);
	      temp.setBounds(400,420,150,20);
	      setLayout(null);
	      
	      add(soil);
	      soil.setBounds(400,495,150,20);
	      setLayout(null);
	      
	      //add(disaster);
	      //disaster.setBounds(400,545,150,20);
	      //setLayout(null);
	      
	      add(finaloutput);
	      finaloutput.setBounds(600,330,200,20);
	      setLayout(null);
	      
	      f2.getContentPane().add(sp2);
	      add(sp2);
	      sp2.setBounds(600,370,500,200);
	      setLayout(null);
	      outputfinal.setEditable(false);
	      
	      
	      add(graph);
		  graph.setBounds(400,180,200,20);
		  setLayout(null);
		  

	      add(graph1);
		  graph1.setBounds(550,230,200,20);
		  setLayout(null);
		  

	      add(graph2);
		  graph2.setBounds(700,180,200,20);
		  setLayout(null);
		  

	      /*add(graph3);
		  graph3.setBounds(700,230,200,20);
		  setLayout(null);
	      */
	      
		  add(title);
		  title.setFont(new Font("ARIAL", Font.LAYOUT_LEFT_TO_RIGHT, 30));
		  title.setBounds(500,20,500,100);
		 
	      
	    
		   
	      
	      
	    
		        
		  
	      b.addActionListener( new ActionListener(){
	    	  public void actionPerformed( ActionEvent ae )
	    	  {
	    		   
	    		  
	    			int returnValue = choose.showOpenDialog(null);
	    			// int returnValue = jfc.showSaveDialog(null);

	    			if (returnValue == JFileChooser.APPROVE_OPTION) {
	    				File f = choose.getSelectedFile();
	    				String traindata = f.getAbsolutePath();
	    			
	    				
	    				System.out.println(f.getAbsolutePath());
	    				
	    				
	    			    		  try{
	    		  					FileReader reader = new FileReader(traindata);
	    		  					BufferedReader br = new BufferedReader (reader);
	    		  					outputtrain.read(br,null);
	    		  					br.close();
	    		  					outputtrain.requestFocus();
	    		  				} 
	    		  				catch (Exception e){
	    		  					JOptionPane.showMessageDialog(null, e);
	    		  				}
	    		  					}
	    			    	  };
	    				
	    				
	    	  
	    	  });
	    
	      	b1.addActionListener( new ActionListener(){
	      		public void actionPerformed( ActionEvent ae )
	    	  {
	    		  

	    			int returnValue = chooser1.showOpenDialog(null);
	    			// int returnValue = jfc.showSaveDialog(null);

	    			if (returnValue == JFileChooser.APPROVE_OPTION) {
	    				File t1 = chooser1.getSelectedFile();
	    				String predictdata = t1.getAbsolutePath();
	    				System.out.println(t1.getAbsolutePath());
	    			
	    				try{
		  					FileReader reader = new FileReader(predictdata);
		  					BufferedReader br = new BufferedReader (reader);
		  					outputpredict.read(br,null);
		  					br.close();
		  					outputpredict.requestFocus();
		  				} 
		  				catch (Exception e){
		  					JOptionPane.showMessageDialog(null, e);
		  				}
	    					}
	    	  }
	    	  });
	    
	      	
	      		/*
	      			algo.addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent ae) {
			    	  
			    	
			        t.setText("index: " + algo.getSelectedIndex() + "   "
			            + ((JComboBox) ae.getSource()).getSelectedItem());
			        System.out.println(t.getText());
			        System.out.println(choose.getSelectedFile());
			        System.out.println(chooser1.getSelectedFile());
			        
			      }
			      
			      
			      
			      });
	      			
	      			
	      			class1.addActionListener(new ActionListener() {
	  			      public void actionPerformed(ActionEvent ae) {
	  			    	  
	  			    	
	  			        t.setText("index: " + class1.getSelectedIndex() + "   "
	  			            + ((JComboBox) ae.getSource()).getSelectedItem());
	  			        System.out.println(t.getText());
	  			        
	  			        
	  			      }
	  			      
	  			      
	  			      
	  			      });
	      			*/
	      	
	      			file6.addActionListener(new ActionListener(){
	      				public void actionPerformed(ActionEvent ae){
	      					
	      					try{
	      						
	      						String filename = "/Users/Administrator/Desktop/latestdata.csv";
	      						FileWriter fw = new FileWriter(filename);
	      						
	      						
	      						Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/database_ippi", "root", "ippifyp123");
	      						
	      						PreparedStatement statement = (PreparedStatement) con.prepareStatement("select * from sensor");
	      						
	      						ResultSet rs = statement.executeQuery();
	      						
	      						
	      						fw.append("sid");
	      				        fw.append(',');
	      				        fw.append("Channel ID");
	      				        fw.append(',');
	      				        fw.append("Moisture");
	      				        fw.append(',');
	      				        fw.append("Temperature");
	      				        fw.append(',');
	      				        fw.append("Time");
	      				        fw.append('\n');
	      						
	      						while(rs.next())
	      						{
	      							fw.append(rs.getString(1));
	      				            fw.append(',');
	      				            fw.append(rs.getString(2));
	      				            fw.append(',');
	      				            fw.append(rs.getString(3));
	      				            fw.append(',');
	      				            fw.append(rs.getString(4));
	      				            fw.append(',');
	      				            fw.append(rs.getString(5));
	      				            fw.append('\n');
	      						}
	      						 fw.flush();
	      				         fw.close();
	      				         con.close();
	      						
	      						
	      					}catch (Exception e){
	      						
	      					}
	      				}
	      			});
	      			
	      			
	      			/*
	      		yield.addActionListener(new ActionListener(){
	      			public void actionPerformed(ActionEvent ae) {
	      				try{
	      				//load dataset
								File trainfile = choose.getSelectedFile();
	      						String data = trainfile.getAbsolutePath();
	      						DataSource source = new DataSource(data);
	      						Instances trainDataset = source.getDataSet();	
								//set class index to the last attribute
								trainDataset.setClassIndex(trainDataset.numAttributes()-4);
								
								//build model
								LinearRegression lr = new LinearRegression();
								lr.buildClassifier(trainDataset);
								
								//output model
								//System.out.println(lr);
								
								
								
								//load new dataset
								File newfile = chooser1.getSelectedFile();
	      						String data1 = newfile.getAbsolutePath();
	      						DataSource source1 = new DataSource(data1);
	      						Instances testDataset = source1.getDataSet();
								//set class index to the last attribute
								testDataset.setClassIndex(testDataset.numAttributes()-4);
								
								
								//loop through the new dataset and make predictions
								outputfinal.append(String.valueOf("========================================" + "\n\r"));
								outputfinal.append(String.valueOf("Your predicted yield for next month is :" + "\n\r"));
								for (int i = 0; i < testDataset.numInstances(); i++) {
									
									//get class double value for current instance
									//double actualValue = testDataset.instance(i).classValue();
									//get Instance object of current instance
									Instance newInst = testDataset.instance(i);
									
									//call classifyInstance, which returns a double value for the class
									double predLR = lr.classifyInstance(newInst);
									outputfinal.append(String.valueOf(predLR/100)+ " t ha-1"  + "\n\r");
									
	      					
	      				}
								}
	      				catch (Exception e){
	      					JOptionPane.showMessageDialog(null, e);
	      				}
	      			}
	      		});
	      		
	      		*/
	      		
	      		temp.addActionListener(new ActionListener(){
	      			public void actionPerformed(ActionEvent ae) {
	      				try{
	      				//load dataset
								File trainfile = choose.getSelectedFile();
	      						String data = trainfile.getAbsolutePath();
	      						DataSource source = new DataSource(data);
	      						Instances trainDataset = source.getDataSet();	
								//set class index to the last attribute
								trainDataset.setClassIndex(trainDataset.numAttributes()-2);
								
								//build model
								LinearRegression lr = new LinearRegression();
								lr.buildClassifier(trainDataset);
								
								//output model
								//System.out.println(lr);
								
								
								
								//load new dataset
								File newfile = chooser1.getSelectedFile();
	      						String data1 = newfile.getAbsolutePath();
	      						DataSource source1 = new DataSource(data1);
	      						Instances testDataset = source1.getDataSet();
								//set class index to the last attribute
								testDataset.setClassIndex(testDataset.numAttributes()-2);
								
								
								//loop through the new dataset and make predictions
								outputfinal.append(String.valueOf("========================================" + "\n\r"));
							
								for (int i = 0; i < testDataset.numInstances(); i++) {
									
									//get class double value for current instance
									//double actualValue = testDataset.instance(i).classValue();
									//get Instance object of current instance
									Instance newInst = testDataset.instance(i);
									
									//call classifyInstance, which returns a double value for the class
									double predLR = lr.classifyInstance(newInst);
									
									if(predLR > 29){
									outputfinal.append(String.valueOf("Your predicted temperature is :" + "\n\r"));
									outputfinal.append(String.valueOf(predLR) + "\n\r");
									outputfinal.append("Temperature of your crops is going to be HIGH! " + "\n\r");
									outputfinal.append("Suggestion : " + "\n\r");
									outputfinal.append("1. Prepare more water for your crops " + "\n\r");
									outputfinal.append("2. Use a mulch to protect seedlings " + "\n\r");
									outputfinal.append("3. Reduce soil moisture loss " + "\n\r");
									outputfinal.append("4. Avoid moisture stress " + "\n\r");
									outputfinal.append("***************************** " + "\n\r");
									
									} else if (predLR <27){
										outputfinal.append(String.valueOf("Your predicted temperature is :" + "\n\r"));
										outputfinal.append(String.valueOf(predLR) + "\n\r");
										outputfinal.append("Temperature of your crops is going to be LOW! " + "\n\r");
										outputfinal.append("Suggestion : " + "\n\r");
										outputfinal.append("1. Locate the plant where it will have some shelter " + "\n\r");
										outputfinal.append("2. Apply mulch around the base of plants to protect the root zone " + "\n\r");
										outputfinal.append("***************************** " + "\n\r");
									}else {
										outputfinal.append(String.valueOf("Your predicted temperature is :" + "\n\r"));
										outputfinal.append(String.valueOf(predLR) + "\n\r");
										outputfinal.append("Temperature of your crops is going to be OPTIMUM! " + "\n\r");
										outputfinal.append("Keep up the good work!" + "\n\r");
										outputfinal.append("***************************** " + "\n\r");
									}
	      				}
								}
	      				catch (Exception e){
	      					JOptionPane.showMessageDialog(null, e);
	      				}
	      			}
	      		});
	      		
	      		soil.addActionListener(new ActionListener(){
	      			public void actionPerformed(ActionEvent ae) {
	      				try{
	      				//load dataset
								File trainfile = choose.getSelectedFile();
	      						String data = trainfile.getAbsolutePath();
	      						DataSource source = new DataSource(data);
	      						Instances trainDataset = source.getDataSet();	
								//set class index to the last attribute
								trainDataset.setClassIndex(trainDataset.numAttributes()-3);
								
								//build model
								LinearRegression lr = new LinearRegression();
								lr.buildClassifier(trainDataset);
								
								//output model
								//System.out.println(lr);
								
								
								
								//load new dataset
								File newfile = chooser1.getSelectedFile();
	      						String data1 = newfile.getAbsolutePath();
	      						DataSource source1 = new DataSource(data1);
	      						Instances testDataset = source1.getDataSet();
								//set class index to the last attribute
								testDataset.setClassIndex(testDataset.numAttributes()-3);
								
								
								//loop through the new dataset and make predictions
								outputfinal.append(String.valueOf("========================================" + "\n\r"));
								
								for (int i = 0; i < testDataset.numInstances(); i++) {
									
									//get class double value for current instance
									//double actualValue = testDataset.instance(i).classValue();
									//get Instance object of current instance
									Instance newInst = testDataset.instance(i);
									
									//call classifyInstance, which returns a double value for the class
									double predLR = lr.classifyInstance(newInst);

									if(predLR > 600){
										outputfinal.append(String.valueOf("Your predicted Moisture is :" + "\n\r"));
										outputfinal.append(String.valueOf(predLR) + "\n\r");									
										outputfinal.append("Moisture of your crops is going to be High! " + "\n\r");
										outputfinal.append("Suggestion : " + "\n\r");
										outputfinal.append("1. Redirecting the flow of water " + "\n\r");
										outputfinal.append("2. Breaking up compacted soil " + "\n\r");
										outputfinal.append("***************************** " + "\n\r");
									}else if (predLR < 500){
										outputfinal.append(String.valueOf("Your predicted Moisture is :" + "\n\r"));
										outputfinal.append(String.valueOf(predLR) + "\n\r");
										outputfinal.append("Moisture of your crops is going to be LOW! " + "\n\r");
										outputfinal.append("Suggestion : " + "\n\r");
										outputfinal.append("1. Boost water retention by adding organic matter " + "\n\r");
										outputfinal.append("2. Prevent evaportion with mulches"+ "\n\r");
										outputfinal.append("3. Eliminate weeds "+ "\n\r");
										outputfinal.append("***************************** " + "\n\r");
									}else{
										outputfinal.append(String.valueOf("Your predicted Moisture is :" + "\n\r"));
										outputfinal.append(String.valueOf(predLR) + "\n\r");
										outputfinal.append("Moisture of your crops is going to be OPTIMUM! " + "\n\r");
										outputfinal.append("Keep up the good work ! "+ "\n\r");
										outputfinal.append("***************************** " + "\n\r");
									}
	      				}
								
	      				}
	      				catch (Exception e){
	      					JOptionPane.showMessageDialog(null, e);
	      				}
	      			}
	      		});

	      		/*
	      		disaster.addActionListener(new ActionListener(){
	      			public void actionPerformed(ActionEvent ae) {
	      				try{
	      				//load training dataset
      						File trainfile = choose.getSelectedFile();
      						String data = trainfile.getAbsolutePath();
      						DataSource source = new DataSource(data);
      						Instances trainDataset = source.getDataSet();	
      						//set class index to the last attribute
      						trainDataset.setClassIndex(trainDataset.numAttributes()-1);
      						//get number of classes
      						//int numClasses = trainDataset.numClasses();
      						//print out class values in the training dataset
      						
      						//create and build the classifier
      						NaiveBayes nb = new NaiveBayes();
      						nb.buildClassifier(trainDataset);
      						//load new dataset
      						File newfile = chooser1.getSelectedFile();
      						String data1 = newfile.getAbsolutePath();
      						DataSource source1 = new DataSource(data1);
      						Instances testDataset = source1.getDataSet();	
      						//set class index to the last attribute
      						testDataset.setClassIndex(testDataset.numAttributes()-1);
      						//loop through the new dataset and make predictions
      						
      						outputfinal.append(String.valueOf("===================" + "\n\r" ));
      						outputfinal.append(String.valueOf("NB Predicted" + "\n\r" ));
      						for (int i = 0; i < testDataset.numInstances(); i++) {
      							//get class double value for current instance
      							//double actualClass = testDataset.instance(i).classValue();
      							//get class string value using the class index using the class's int value
      							//String actual = testDataset.classAttribute().value((int) actualClass);
      							//get Instance object of current instance
      							Instance newInst = testDataset.instance(i);
      							//call classifyInstance, which returns a double value for the class
      							double predNB = nb.classifyInstance(newInst);
      							//use this value to get string value of the predicted class
      							String predString = testDataset.classAttribute().value((int) predNB);
      							
      							
      							outputfinal.append(String.valueOf(predString) + "\n\r");
      							
								
	      				}
								}
	      				catch (Exception e){
	      					JOptionPane.showMessageDialog(null, e);
	      				}
	      			}
	      		});
	      			
	      			*/
	      		
	      			graph.addActionListener(new ActionListener(){
	      				public void actionPerformed(ActionEvent ae) {
	      					try{
	      						Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/database_ippi", "root", "ippifyp123");
	      						String query = "select time,temperature from sensor";
	      						JDBCCategoryDataset dataset = new JDBCCategoryDataset(con,query);
	      						JFreeChart chart = ChartFactory.createLineChart("Time-Temperature", "Time" , "Temperature", dataset ,PlotOrientation.VERTICAL , false , true, true);
	      						BarRenderer renderer = null;
	      						CategoryPlot plot = null;
	      						renderer = new BarRenderer();
	      						ChartFrame frame = new ChartFrame("Time-Temperature",chart);
	      						frame.setVisible(true);
	      						frame.setSize(400,650);
	      						
	      						
	      						
	      					}
	      					catch (Exception e){
		      					JOptionPane.showMessageDialog(null, e);
		      				}
	      					
	      				}
	      			});
	      			
	      			graph1.addActionListener(new ActionListener(){
	      				public void actionPerformed(ActionEvent ae) {
	      					try{
	      						Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/database_ippi", "root", "ippifyp123");
	      						String query = "select time,moisture from sensor";
	      						JDBCCategoryDataset dataset = new JDBCCategoryDataset(con,query);
	      						JFreeChart chart = ChartFactory.createLineChart("Date-Moisture", "Date" , "Moisture", dataset ,PlotOrientation.VERTICAL , false , true, true);
	      						BarRenderer renderer = null;
	      						CategoryPlot plot = null;
	      						renderer = new BarRenderer();
	      						ChartFrame frame = new ChartFrame("Date-Moisture",chart);
	      						frame.setVisible(true);
	      						frame.setSize(400,650);
	      						
	      						
	      						
	      					}
	      					catch (Exception e){
		      					JOptionPane.showMessageDialog(null, e);
		      				}
	      					
	      				}
	      			});
	      			
	      			graph2.addActionListener(new ActionListener(){
	      				public void actionPerformed(ActionEvent ae) {
	      					try{
	      						Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/database_ippi", "root", "ippifyp123");
	      						String query = "select temperature,moisture from sensor";
	      						JDBCCategoryDataset dataset = new JDBCCategoryDataset(con,query);
	      						JFreeChart chart = ChartFactory.createLineChart("Temp-Moisture", "Temperature" , "Moisture", dataset ,PlotOrientation.VERTICAL , false , true, true);
	      						BarRenderer renderer = null;
	      						CategoryPlot plot = null;
	      						renderer = new BarRenderer();
	      						ChartFrame frame = new ChartFrame("Temp-Moisture",chart);
	      						frame.setVisible(true);
	      						frame.setSize(400,650);
	      						
	      						
	      						
	      					}
	      					catch (Exception e){
		      					JOptionPane.showMessageDialog(null, e);
		      				}
	      					
	      				}
	      			});
	      			
	      			/*
	      			graph3.addActionListener(new ActionListener(){
	      				public void actionPerformed(ActionEvent ae) {
	      					try{
	      						Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/database_ippi", "root", "ippifyp123");
	      						String query = "select moisture,temperature from sensor";
	      						JDBCCategoryDataset dataset = new JDBCCategoryDataset(con,query);
	      						JFreeChart chart = ChartFactory.createLineChart("Moisture-Temp", "Moisture" , "Temperature", dataset ,PlotOrientation.VERTICAL , false , true, true);
	      						BarRenderer renderer = null;
	      						CategoryPlot plot = null;
	      						renderer = new BarRenderer();
	      						ChartFrame frame = new ChartFrame("Moisture-Temp",chart);
	      						frame.setVisible(true);
	      						frame.setSize(400,650);
	      						
	      						
	      						
	      					}
	      					catch (Exception e){
		      					JOptionPane.showMessageDialog(null, e);
		      				}
	      					
	      				}
	      			});
	      			
	    */
	    
	    
	    
	    
	    
	    
	    

		
		
	}
	
}
	



