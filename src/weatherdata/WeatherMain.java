package weatherdata;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JTextArea;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class WeatherMain extends JFrame {
	private static CheckLocation location;
	private static String coordinates;
	// colours
	private static Color darkGray = Color.decode("#272b33");
	private static Color blueGray = Color.decode("#565F72");

	public WeatherMain(String coordinates, int check, int timeStamp) throws Exception {
		if (check == 1) {
			ForecastRequest request = new ForecastRequest(coordinates, location.getFormattedAddress());
			Date today = request.getDate();
			new WeatherMain(today);
		} else if (check == 2) {
			PastWeather dateRequest = new PastWeather(coordinates, "1287619200", location.getFormattedAddress());
			Date today = dateRequest.getDate();
			new WeatherMain(today);
		}
	}

	/**
	 * @wbp.parser.constructor
	 */
	public WeatherMain(Date today) throws Exception {

		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_RIGHT);
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);

		getContentPane().setForeground(Color.BLACK);
		setTitle("WeatherData");
		setResizable(false);
		setSize(900, 900);

		JPanel northPanel = new JPanel();
		northPanel.setBackground(blueGray);
		getContentPane().add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));

		JLabel lblWeclomeUser = new JLabel("WeatherData");
		lblWeclomeUser.setHorizontalAlignment(SwingConstants.LEFT);
		lblWeclomeUser.setForeground(SystemColor.text);
		lblWeclomeUser.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 92));
		northPanel.add(lblWeclomeUser);

		JLabel lblSetYourLocation = new JLabel("Set a new location");
		lblSetYourLocation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				String locationCheck = JOptionPane.showInputDialog("Enter location");

				coordinates = getCoordinates(locationCheck);
				if (coordinates == "null") {
					coordinates = getCoordinates(locationCheck);
				}

				try {
					new WeatherMain(coordinates, 1, 0);
				} catch (Exception e2) {
					System.out.println("Could not find the requested location");
				}

			}
		});
		lblSetYourLocation.setVerticalAlignment(SwingConstants.TOP);
		lblSetYourLocation.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetYourLocation.setForeground(SystemColor.inactiveCaptionBorder);
		northPanel.add(lblSetYourLocation);

		JPanel westPanel = new JPanel();
		getContentPane().add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel west = new JPanel();
		west.setBackground(new Color(51, 51, 51));
		westPanel.add(west);
		west.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblSearchDates = new JLabel("         Search Date");
		lblSearchDates.setForeground(SystemColor.textHighlight);
		lblSearchDates.setBackground(blueGray);
		lblSearchDates.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("hi folks");
				new ShowGraph();

			}
		});
		west.add(lblSearchDates);

		JLabel label_3 = new JLabel("____________________");
		label_3.setForeground(Color.WHITE);
		label_3.setBackground(blueGray);
		west.add(label_3);

		JLabel lblGraph = new JLabel("           See Graph ");
		lblGraph.setForeground(SystemColor.textHighlight);
		lblGraph.setBackground(SystemColor.textHighlight);
		west.add(lblGraph);

		JLabel lblFiller = new JLabel("____________________");
		lblFiller.setForeground(Color.WHITE);
		lblFiller.setBackground(blueGray);
		west.add(lblFiller);

		JLabel lblSearchRange = new JLabel("   Search Past Weather");
		lblSearchRange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {

					String locationCheck = JOptionPane.showInputDialog("Enter location");
					// replaces white space with % so API can read input

					new WeatherMain(location.getCoordinates(), 2, 0);
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		lblSearchRange.setForeground(SystemColor.textHighlight);
		lblSearchRange.setBackground(blueGray);
		west.add(lblSearchRange);

		JLabel label = new JLabel("____________________");
		label.setForeground(Color.WHITE);
		label.setBackground(blueGray);
		west.add(label);

		JLabel lblFiller2 = new JLabel("");
		lblFiller2.setBackground(blueGray);
		west.add(lblFiller2);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(blueGray);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);

		JLabel lblDesignedBy = new JLabel("");
		lblDesignedBy.setIcon(new ImageIcon(WeatherMain.class.getResource("/resources/darkSky.png")));
		lblDesignedBy.setForeground(Color.WHITE);
		lblDesignedBy.setHorizontalAlignment(SwingConstants.LEFT);
		bottomPanel.add(lblDesignedBy);

		JPanel middlePanel = new JPanel();

		middlePanel.setBackground(darkGray);
		getContentPane().add(middlePanel, BorderLayout.CENTER);
		middlePanel.setLayout(null);

		JLabel lblLocation = new JLabel(today.getLocation());
		lblLocation.setForeground(Color.WHITE);
		lblLocation.setVerticalAlignment(SwingConstants.TOP);
		lblLocation.setFont(new Font("Bahnschrift", Font.PLAIN, 25));
		lblLocation.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocation.setBounds(31, 25, 715, 55);
		middlePanel.add(lblLocation);

		JLabel lblSummary = new JLabel(today.getSummary());
		lblSummary.setForeground(SystemColor.textHighlight);
		lblSummary.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblSummary.setBounds(31, 77, 715, 29);
		middlePanel.add(lblSummary);

		JTextArea infoLeft = new JTextArea();
		infoLeft.setForeground(Color.WHITE);
		infoLeft.setBackground(darkGray);
		infoLeft.setEditable(false);
		infoLeft.setLineWrap(true);
		infoLeft.setFont(new Font("Roboto Condensed", Font.PLAIN, 18));
		infoLeft.setText(
				"Currently feels like:\r\n\r\nHumidity:\r\n\r\nPressure:\r\n\r\nWind Speed:\r\n\r\nVisibility:");
		infoLeft.setBounds(31, 314, 212, 222);
		infoLeft.setVisible(true);
		middlePanel.add(infoLeft);

		JLabel numbersLeft = new JLabel();
		numbersLeft.setEnabled(true);
		numbersLeft.setForeground(SystemColor.textHighlight);
		numbersLeft.setVerticalAlignment(SwingConstants.TOP);
		numbersLeft.setHorizontalAlignment(SwingConstants.RIGHT);
		numbersLeft.setText("<HTML>" + today.getFeelsLike() + "\u00b0<br><br>" + today.getHumidity() + "%<br><br>"
				+ today.getPressure() + " hPa<br><br>" + today.getWindSpeed() + " km/h<br><br>" + today.getVisibility()
				+ "km<br><br></HTML>");

		numbersLeft.setFont(new Font("Roboto Condensed", Font.BOLD, 18));
		numbersLeft.setBounds(278, 315, 86, 222);
		middlePanel.add(numbersLeft);

		JLabel lblTemp = new JLabel(today.getTemp() + "\u00b0");
		lblTemp.setForeground(Color.WHITE);
		lblTemp.setEnabled(true);
		lblTemp.setFont(new Font("Tw Cen MT", Font.BOLD, 150));
		lblTemp.setBounds(31, 125, 253, 164);
		middlePanel.add(lblTemp);

		ImageIcon image = new ImageIcon(WeatherMain.class.getResource(today.getIcon()));

		JLabel icon = new JLabel(image);

		icon.setBounds(235, 117, 232, 172);
		middlePanel.add(icon);

		Image map = null;
		String key = "AIzaSyDeBq4FfKSZhXUdvSmMgGE2WQ3BSuJ6fqo";
		String zoom = "12";

		try {
			URL url = new URL("https://maps.googleapis.com/maps/api/staticmap?center="
					+ URLEncoder.encode(coordinates, "UTF-8") + "&zoom=" + URLEncoder.encode(zoom, "UTF-8") + "&size=300x250&markers=color:red%7Clabel:L%7C"
					+ URLEncoder.encode(coordinates, "UTF-8") + "&maptype=hybrid&key=" + URLEncoder.encode(key, "UTF-8"));
			map = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon mapIcon = new ImageIcon(map);

		JLabel mapImage = new JLabel(mapIcon);
		mapImage.setBounds(429, 314, 300, 250);
		middlePanel.add(mapImage);

		setVisible(true);

	}

	public static void main(String[] args) {

		String locationCheck = JOptionPane.showInputDialog("Enter location");
		// replaces white space with % so API can read input

		coordinates = getCoordinates(locationCheck);
		if (coordinates == "null") {
			coordinates = getCoordinates(locationCheck);
		}

		try {
			new WeatherMain(coordinates, 1, 0);
		} catch (Exception e2) {
			System.out.println("Could not find the requested location");
		}

	}

	public static String getCoordinates(String address) {

		location = new CheckLocation(address);
		address = location.getCoordinates();

		return address;
	}
}
