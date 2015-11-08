import processing.serial.*; //Import serial code
import processing.pdf.*;          // Import PDF code

Serial myPort;    // The serial port

//variables used for graph
int recordOn = 0;
int H =300;
int W =1280;
int x_axis_increment = 1;
int prev_y=0, current_x_axis;
int[] data_table;
int data_table_counter;
int drawCounter;
int TABLE_SIZE = W/x_axis_increment;

void init_serial()
{
  int lf = 10;      // ASCII linefeed
  printArray(Serial.list()); 
  myPort = new Serial(this, Serial.list()[6], 9600); 
  myPort.bufferUntil(lf);
}

void serialEvent(Serial p) {  
  graph_add_buffer(Integer.parseInt(p.readString().trim()));  
} 

void graph_add(int data)
{
   stroke(26,255,64);   
   line(current_x_axis-x_axis_increment, prev_y, current_x_axis, data);
   current_x_axis+=x_axis_increment;   
   prev_y = data;
   if(current_x_axis == W)
   {
     current_x_axis = 0;
     drawAxis();
   }
}

void graph_add_buffer(int data)
{
  data_table[data_table_counter++] = data;
  if(data_table_counter==TABLE_SIZE)
    {
      data_table_counter = 0;     
    }
}

void draw_buffer()
{
 int i=0;
 current_x_axis = x_axis_increment;
 drawAxis();
 while(i<TABLE_SIZE-1)
 {
   stroke(26,255,64);   
   line(current_x_axis-x_axis_increment, data_table[i], current_x_axis, data_table[i+1]);
   current_x_axis += x_axis_increment;
   i++;
 }   
}

void init_buffer()
{
   for(int i=0;i<TABLE_SIZE;i+=1)
  {
    data_table[i] = 0;
  }   
}

void drawAxis()
{
  int i=0;
  size(W, H); 
  if(recordOn==1)
     beginRecord(PDF, "line.pdf");
  background(64);
  
  //draw axis
  stroke(115,115,115);
  for(i=1;i<H;i+=10)
    line(0,i,W,i);
  
  if(recordOn==1)
     endRecord();
}

void setup() {
  data_table = new int[TABLE_SIZE];
  drawAxis();
  init_buffer();
  init_serial();
}

void draw() {
  int start, finish;
  start = millis();  
  //int data=0;
  //data = (int)random(H/2);
  draw_buffer();
  finish = millis();
  println(finish-start);
  //delay(10);  
}
