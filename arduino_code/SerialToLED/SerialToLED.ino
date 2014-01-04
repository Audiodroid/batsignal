
int inByte = 0;         // incoming serial byte

void setup()
{
  // start serial port at 9600 bps:
  Serial.begin(9600);
  
  pinMode(13, OUTPUT);  
  
  turnLEDon(1000);
}

void loop()
{  
  // if we get a valid byte, turn LED on:
  if (Serial.available() > 0) {
    
    turnLEDon(1000);
  
    //get all incoming bytes
    while(Serial.available() > 0) {
      inByte = Serial.read();
    }    
  }
  
  // delay 10ms to let the ADC recover:
  delay(10);
    
}

void turnLEDon(int time) {
  digitalWrite(13, HIGH);   // set the LED on
  delay(time);              // wait for a second
  digitalWrite(13, LOW);    // set the LED off
}

