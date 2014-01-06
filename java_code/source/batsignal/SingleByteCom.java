package batsignal;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

public class SingleByteCom implements SerialPortEventListener, Observer{
		
		/** Milliseconds to block while waiting for port open */
		private static final int TIME_OUT = 2000;
		
		/** Default bits per second for COM port. */
		private static final int DATA_RATE = 9600;

		/** The port we're normally going to use. */
		private static final String PORT_NAMES[] = {
			"/dev/tty.usbserial-A9007UX1", // Mac OS X
			//"/dev/ttyACM0", // Linux
			"/dev/ttyS80", // Linux /** sudo ln -s /dev/ttyACM0 /dev/ttyS80 */
			"COM6", // Windows
		};
		
		/** the serial port */
		private SerialPort serialPort;
	    
		/** buffered input stream from the port */
		private InputStream input;
		
		/** output stream to the port */
		private OutputStream output;
		
		public void start() {
			
			CommPortIdentifier portId = null;
			Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

			/// iterate through, looking for the port
			while (portEnum.hasMoreElements()) {
				CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
				for (String portName : PORT_NAMES) {
					if (currPortId.getName().equals(portName)) {
						portId = currPortId;
						break;
					}
				}
			}

			if (portId == null) {
				System.out.println("Could not find COM port.");
				return;
			}else{
				System.out.println("Found your Port");
			}

			try {
				// open serial port, and use class name for the appName.
				serialPort = (SerialPort) portId.open(this.getClass().getName(),TIME_OUT);

				// set port parameters
				serialPort.setSerialPortParams(DATA_RATE,
						SerialPort.DATABITS_8,
						SerialPort.STOPBITS_1,
						SerialPort.PARITY_NONE);

				// open the streams
				input = serialPort.getInputStream();
				output = serialPort.getOutputStream();

				// add event listeners
				serialPort.addEventListener(this);
				serialPort.notifyOnDataAvailable(true);
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}

		/**
		 * This should be called when you stop using the port.
		 * This will prevent port locking
		 */
		public synchronized void close() {
			if (serialPort != null) {
				serialPort.removeEventListener();
				serialPort.close();
			}
		}

		/**
		 * This Method can be called to print a single byte
		 * to the serial connection
		 */
		public void sendSingleByte(byte myByte){
			try {
				output.write(myByte);
				output.flush();
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}

		/**
		 * This method is called when serial data is received
		 */
		public synchronized void serialEvent (SerialPortEvent oEvent) {
			if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
				try {
					int myByte=input.read();
					int value = myByte & 0xff;//byte to int conversion:0...127,-127...0 -> 0...255
					if(value>=0 && value<256){//make shure everything is ok
						System.out.println(value);
						sendSingleByte((byte)myByte);
					}
				} catch (Exception e) {
					System.err.println(e.toString());
				}
			}
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			sendSingleByte((byte) 0x1);			
		}

//		/**
//		 * Main Method is called when the Java Application starts
//		 */
//		public static void main(String[] args) throws Exception {
//			SingleByteCommunication main = new SingleByteCommunication();
//			main.init();
//			System.out.println("Started");
//
//		}
	}