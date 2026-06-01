public class GestorEntradas {
    public void procesarComando(String comando, EntidadVideojuego jugador, EntidadVideojuego enemigo, MotorJuego motor) {
        String cmd = comando.toUpperCase();
        System.out.println("[INPUT] Acción de " + jugador.getTipo() + ": " + cmd);
        
        switch (cmd) {
            case "MOVER_DERECHA": 
                jugador.mover(2, 0); 
                System.out.println("[MOVIMIENTO] " + jugador.getTipo() + " se desplaza a la posición X: " + jugador.getX());
                break;
            case "MOVER_IZQUIERDA": 
                jugador.mover(-2, 0); 
                System.out.println("[MOVIMIENTO] " + jugador.getTipo() + " se desplaza a la posición X: " + jugador.getX());
                break;
            case "ATACAR": 
                motor.ejecutarAtaque(jugador, enemigo);
                break;
            default: 
                System.out.println("[INPUT ERROR] Comando de acción inválido.");
        }
    }
}