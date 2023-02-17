
int r;
int g;

void setup() {
  size (254, 254);
  background(0);
  r=0;
  g=254;
  
}

void draw() {
  if (r<255) { 
    drawRed(r); 
    r++;
  }
  if (g>0) {
    drawGreen(g, r);
    g--;
  }
  
  
}

void drawRed(int i) {
  noStroke(); fill(i, 0, 0);
  rect(i, 0, 1, i);
  
}

void drawGreen(int g, int r) {
  noStroke(); fill(0, g, 0);
  rect(g, height, 1, -r); 
}
