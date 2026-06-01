public abstract class EntidadVideojuego {
    private int x, y, w, h;
    private String tipo;
    private int energia; // Representa los Puntos de Vida (HP)
    private String armaEquipada;
    private int dañoArma;

    public EntidadVideojuego(String tipo, int x, int y, int w, int h, int energia, String arma, int dañoArma) {
        this.tipo = tipo;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.energia = energia;
        this.armaEquipada = arma;
        this.dañoArma = dañoArma;
    }

    // Getters y Setters para Encapsulación
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getW() { return w; }
    public int getH() { return h; }
    public String getTipo() { return tipo; }
    
    public int getEnergia() { return energia; }
    public void setEnergia(int energia) { 
        this.energia = Math.max(0, energia); // Evita vida negativa
    }

    public String getArmaEquipada() { return armaEquipada; }
    public int getDañoArma() { return dañoArma; }

    public void mover(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
}