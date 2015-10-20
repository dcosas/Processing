import processing.pdf.*;          // Import PDF code
int recordOn = 0;
int H =300;
int W =1280;
int TABLE_SIZE = 320;
int x_axis_increment = 4;
int prev_y=0, current_x_axis;
int[] data_table;
int data_table_counter;


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
}

void draw() {
  int i,data=0;
  data = (int)random(H/2);
  //graph_add(data);
  graph_add_buffer(data);
  draw_buffer();
  delay(10);
    
}
