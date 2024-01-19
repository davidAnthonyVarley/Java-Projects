

void setup() {
  size(400, 400);  
}

void draw() {
  background(#5b006e);
  noStroke();
  int val =  #FFCC00;//#A4FF92;//#BBBB00
  
  fill(val);
  stroke(0);
  ellipse(width/2, height/2, 200, 200);
  
  rect(width/2 - 40, 0, 80, height);
  noStroke();
  ellipse(width/2, height/2, 200, 200);
  
  noLoop();
}
