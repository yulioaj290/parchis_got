int main() {
	int i ;
    int x = 196;
    int y = 0;

    xpos = new int[4];
    ypos = new int[4];
    int i;
	for (i = 0; i < 7; i++) {
        xpos[0] = seed.x;            ypos[0] = seed.y + i * 28;
        xpos[1] = seed.x + 68;       ypos[1] = seed.y + i * 28;
        xpos[2] = seed.x;            ypos[2] = seed.y + (i + 1) * 28;
        xpos[3] = seed.x + 68;       ypos[3] = seed.y + (i + 1) * 28;

        mapa[i] = new CCasillaNormal(i, xpos, ypos, null);
     }
     i ++;
     xpos[0] = seed.x;            ypos[0] = seed.y + i * 28;
     xpos[1] = seed.x + 68;       ypos[1] = seed.y + i * 28;
     xpos[2] = seed.x;            ypos[2] = seed.y + (i + 1) * 28;
     xpos[3] = seed.x + 68;       ypos[3] = seed.y + (i + 1) * 28;
     mapa[i] = new CCasillaNormal(i, xpos, ypos, null);

	}
