package com.coffee.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.coffee.chart.Chart;
import com.coffee.chart.ModelChart;
import com.coffee.custom.ComboBoxSuggestion;
import com.coffee.custom.MaterialTabbed;
import com.coffee.custom.PanelBorder;
import com.coffee.service.BillService;
import com.coffee.serviceimpl.BillServiceImpl;

/**
 * @author Nguyen Phuc Thinh
 */
public class Form_Chart extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8241203289839226936L;

	private BillService billService;


	private Chart chartOne;
	private MaterialTabbed tabbedPane;
	private JPanel tabOne;
	private JLabel lblLoiSnPhm;
	private ComboBoxSuggestion<String> cmbAnalysisType;
	private JLabel lblChn;
	private ComboBoxSuggestion<String> cmbValueYear;
	private JButton btnAnalysis;
	private JPanel pChartOne;
	private JPanel pChartTwo;
	private ComboBoxSuggestion<String> cmbValueMonth;
	private JLabel lblChn_1;

	/**
	 * Create the panel.
	 * 
	 * @
	 */
	public Form_Chart() {
		billService = new BillServiceImpl();

		tabbedPane = new MaterialTabbed();

		tabOne = new JPanel();
		tabbedPane.addTab("Doanh thu", null, tabOne, null);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1133, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE).addContainerGap()));
		setLayout(groupLayout);

		initTabOne();

	}

	/**
	 * 
	 */

	/**
	 * 
	 */
	private void initTabOne() {
		// TODO Auto-generated method stub
		lblLoiSnPhm = new JLabel();
		lblLoiSnPhm.setText("Thống kê theo:");
		lblLoiSnPhm.setForeground(new Color(127, 127, 127));
		lblLoiSnPhm.setFont(new Font("SansSerif", Font.BOLD, 12));

		cmbAnalysisType = new ComboBoxSuggestion<String>();

		cmbAnalysisType.setBackground(SystemColor.menu);
		cmbAnalysisType.addItem("Tháng");
		cmbAnalysisType.addItem("Năm");

		lblChn = new JLabel();
		lblChn.setText("Chọn năm:");
		lblChn.setForeground(new Color(127, 127, 127));
		lblChn.setFont(new Font("SansSerif", Font.BOLD, 12));

		cmbValueYear = new ComboBoxSuggestion<String>();
		cmbValueYear.setBackground(SystemColor.menu);
		for (int i = 2018; i <= 2030; i++) {
			cmbValueYear.addItem("Năm " + i);
		}

		btnAnalysis = new JButton("Thống kê");
		btnAnalysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if (cmbAnalysisType.getSelectedIndex() == 1) {
					try {
						int index = cmbValueYear.getSelectedIndex();
						int year = 2018 + index;
						AnalysisByOverByYear(year);
						TurnOverLineChartByYear(year);

						pChartOne.updateUI();
						pChartTwo.updateUI();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					int month = (cmbValueMonth.getSelectedIndex() + 1);
					int index = cmbValueYear.getSelectedIndex();
					int year = 2018 + index;
					
					try {
						AnalysisByOverByMonth(year, month);
						TurnOverLineChartByMonth(year, month);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					pChartOne.updateUI();
					pChartTwo.updateUI();
				}

			}
		});
		btnAnalysis.setFont(new Font("SansSerif", Font.PLAIN, 10));

		pChartOne = new PanelBorder();
		pChartOne.setBackground(Color.WHITE);

		pChartTwo = new PanelBorder();
		pChartTwo.setBackground(Color.WHITE);

		cmbValueMonth = new ComboBoxSuggestion<String>();
		cmbValueMonth.setBackground(SystemColor.menu);
		for (int i = 1; i <= 12; i++) {
			cmbValueMonth.addItem("Tháng " + i);
		}

		lblChn_1 = new JLabel();
		lblChn_1.setText("Chọn tháng:");
		lblChn_1.setForeground(new Color(127, 127, 127));
		lblChn_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		GroupLayout gl_tabOne = new GroupLayout(tabOne);
		gl_tabOne.setHorizontalGroup(
			gl_tabOne.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tabOne.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_tabOne.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tabOne.createSequentialGroup()
							.addComponent(lblLoiSnPhm, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(cmbAnalysisType, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addGroup(gl_tabOne.createParallelGroup(Alignment.LEADING)
								.addComponent(lblChn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_tabOne.createSequentialGroup()
									.addGap(74)
									.addComponent(cmbValueYear, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
							.addGap(30)
							.addGroup(gl_tabOne.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_tabOne.createSequentialGroup()
									.addGap(80)
									.addComponent(cmbValueMonth, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblChn_1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
							.addGap(44)
							.addComponent(btnAnalysis, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_tabOne.createSequentialGroup()
							.addComponent(pChartOne, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
							.addGap(6)
							.addComponent(pChartTwo, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)))
					.addGap(10))
		);
		gl_tabOne.setVerticalGroup(
			gl_tabOne.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tabOne.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_tabOne.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLoiSnPhm, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbAnalysisType, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblChn, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbValueYear, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbValueMonth, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblChn_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAnalysis, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addGroup(gl_tabOne.createParallelGroup(Alignment.LEADING)
						.addComponent(pChartOne, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addComponent(pChartTwo, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
					.addGap(10))
		);
		tabOne.setLayout(gl_tabOne);
		// TurnOver();

	}

	private void TurnOverLineChartByYear(int year) throws ParseException {
		
		pChartTwo.removeAll();
		
		DefaultCategoryDataset dataset = createDataset(year);
		// Create chart

		JFreeChart chart = ChartFactory.createLineChart("Site Traffic", // Chart title
				"Tháng", // X-Axis Label
				"Number of Visitor", // Y-Axis Label
				dataset, PlotOrientation.VERTICAL, isBackgroundSet(), getIgnoreRepaint(),
				getFocusTraversalKeysEnabled());
		ChartPanel panel = new ChartPanel(chart);
		panel.setPreferredSize(new Dimension(530, 500));
		GroupLayout gl_pChartTwo = new GroupLayout(pChartTwo);
		gl_pChartTwo.setHorizontalGroup(gl_pChartTwo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pChartTwo.createSequentialGroup().addGap(7)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(8)));
		gl_pChartTwo.setVerticalGroup(gl_pChartTwo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pChartTwo.createSequentialGroup().addGap(5)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(6)));
		pChartTwo.setLayout(gl_pChartTwo);
		pChartTwo.updateUI();
		pChartTwo.validate();
	}

	private DefaultCategoryDataset createDataset(int year) throws ParseException {

		String series1 = "Doanh thu năm " + year;


		billService = new BillServiceImpl();
		Map<Integer, Double> map = billService.statisicalByYear(year);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		if (map != null) {
			map.entrySet().forEach(entry->{
				dataset.addValue(entry.getValue(), series1,
							entry.getKey());
			});
			
		} else {
			return null;
		}
		return dataset;
	}
/////////////////////////////////////////////////////
	private void AnalysisByOverByYear(int year) throws ParseException {
		pChartOne.removeAll();

		chartOne = new Chart();
		chartOne.addLegend("Doanh thu", new Color(245, 189, 135));

		billService = new BillServiceImpl();
		Map<Integer, Double> map = billService.statisicalByYear(year);

		if (map != null) {
		
			
			map.entrySet().forEach(entry->{
				chartOne.addData(new ModelChart("Tháng "+entry.getKey(), new double[] { entry.getValue() }));
			});
			
		} else {
			chartOne.removeAll();
		}

		GroupLayout gl_pChartOne = new GroupLayout(pChartOne);
		gl_pChartOne.setHorizontalGroup(
				gl_pChartOne.createParallelGroup(Alignment.LEADING).addGroup(gl_pChartOne.createSequentialGroup()
						.addGap(10).addComponent(chartOne, GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE).addGap(10)));
		gl_pChartOne.setVerticalGroup(
				gl_pChartOne.createParallelGroup(Alignment.LEADING).addGroup(gl_pChartOne.createSequentialGroup()
						.addGap(10).addComponent(chartOne, GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE).addGap(9)));
		pChartOne.setLayout(gl_pChartOne);
		
		pChartOne.updateUI();
		pChartOne.validate();

	}
////////////////////////////////////////////////////////////////////
	private void AnalysisByOverByMonth(int year, int month) throws ParseException {

		pChartOne.removeAll();
	
		chartOne = new Chart();
		
		
		chartOne.addLegend("Doanh thu", new Color(245, 189, 135));


		boolean isLeap = false;
		int day = 0;
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0) {
					isLeap = true;
				} else {
					isLeap = false;
				}
			} else {
				isLeap = true;
			}
		} else {
			isLeap = false;
		}

		if (month == 2) {
			if (isLeap) {
				day = 29;
			} else {
				day = 28;
			}
		} else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
				|| month == 12) {
			day =31;
		}else {
			day = 30;
		}
		
		
		billService = new BillServiceImpl();
		Map<Integer, Double> map = billService.statisicalByMonth(year, month, day);
		if (map != null) {
	
			
			map.entrySet().forEach(entry->{
				chartOne.addData(new ModelChart("Ngày "+entry.getKey(), new double[] { entry.getValue() }));
			});
		

		} else {
			chartOne.removeAll();
		}

		GroupLayout gl_pChartOne = new GroupLayout(pChartOne);
		gl_pChartOne.setHorizontalGroup(
				gl_pChartOne.createParallelGroup(Alignment.LEADING).addGroup(gl_pChartOne.createSequentialGroup()
						.addGap(10).addComponent(chartOne, GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE).addGap(10)));
		gl_pChartOne.setVerticalGroup(
				gl_pChartOne.createParallelGroup(Alignment.LEADING).addGroup(gl_pChartOne.createSequentialGroup()
						.addGap(10).addComponent(chartOne, GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE).addGap(9)));
		pChartOne.setLayout(gl_pChartOne);
		pChartOne.updateUI();
		pChartOne.validate();
	}
	
	
	/////////////////////////////////////////////////////////////////////////////
	private void TurnOverLineChartByMonth(int year, int month) throws ParseException {
		pChartTwo.removeAll();
		DefaultCategoryDataset dataset = createDatasetMonth(year,month);
		// Create chart

		JFreeChart chart = ChartFactory.createLineChart("Site Traffic", // Chart title
				"Ngày", // X-Axis Label
				"Number of Visitor", // Y-Axis Label
				dataset, PlotOrientation.VERTICAL, isBackgroundSet(), getIgnoreRepaint(),
				getFocusTraversalKeysEnabled());
		ChartPanel panel = new ChartPanel(chart);
		panel.setPreferredSize(new Dimension(530, 500));
		GroupLayout gl_pChartTwo = new GroupLayout(pChartTwo);
		gl_pChartTwo.setHorizontalGroup(gl_pChartTwo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pChartTwo.createSequentialGroup().addGap(7)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(8)));
		gl_pChartTwo.setVerticalGroup(gl_pChartTwo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pChartTwo.createSequentialGroup().addGap(5)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(6)));
		pChartTwo.setLayout(gl_pChartTwo);
		pChartTwo.updateUI();
		pChartTwo.validate();
	}

	private DefaultCategoryDataset createDatasetMonth(int year, int month) throws ParseException {

		String series1 = "Doanh thu Tháng " + month;

		boolean isLeap = false;
		int day = 0;
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0) {
					isLeap = true;
				} else {
					isLeap = false;
				}
			} else {
				isLeap = true;
			}
		} else {
			isLeap = false;
		}

		if (month == 2) {
			if (isLeap) {
				day = 29;
			} else {
				day = 28;
			}
		} else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
				|| month == 12) {
			day =31;
		}else {
			day = 30;
		}

		billService = new BillServiceImpl();
		Map<Integer, Double> map = billService.statisicalByMonth(year, month, day);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		if (map != null) {
			map.entrySet().forEach(entry->{
				dataset.addValue(entry.getValue(), series1,
							entry.getKey());
			});
			
		} else {
			return null;
		}
		return dataset;
	}
}
