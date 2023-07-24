

void setup() {
  size(1000, 1000);  
}

void draw() {
  background(#BB00FF);
  noStroke();
  fill(#BBBB00);
  ellipse(width/2, height/2, 300, 300);
  
  rect(width/2 - 50, 0, 100, height);
}
