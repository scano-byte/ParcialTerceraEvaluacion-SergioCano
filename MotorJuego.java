import java.util.ArrayList;
import java.util.List;

public class MotorJuego {
    public enum EstadoJuego { MENU, JUGANDO, GAMEOVER }
    private EstadoJuego estadoActual;
    private List<EntidadVideojuego> entidades;

    public MotorJuego() {
        this.estadoActual = EstadoJuego.MENU;
        this.entidades = new ArrayList<>();
    }

    public void cambiarEstado(EstadoJuego nuevoEstado) {
        this.estadoActual = nuevoEstado;
        System.out.println("[MOTOR] Estado del sistema: " + nuevoEstado);
    }

    public void añadirEntidad(EntidadVideojuego e) {
        entidades.add(e);
        System.out.println("[MOTOR] Entidad registrada en la arena: " + e.getTipo() + " (HP: " + e.getEnergia() + ")");
    }

    public void actualizar() {
        if (estadoActual != EstadoJuego.JUGANDO) return;

        // Comprobación de fin de partida (Si alguno llega a 0 de Vida)
        for (EntidadVideojuego e : entidades) {
            if (e.getEnergia() <= 0) {
                System.out.println("\n[PARTIDA FINALIZADA] " + e.getTipo() + " ha sido derrotado.");
                cambiarEstado(EstadoJuego.GAMEOVER);
                return;
            }
        }
    }

    // Lógica Avanzada: Sistema de Combate y Daño por proximidad espacial
    public void ejecutarAtaque(EntidadVideojuego atacante, EntidadVideojuego objetivo) {
        if (estadoActual != EstadoJuego.JUGANDO) return;

        // Detector de distancia/rango simple (Simulando colisión de rango en el plano 2D)
        int distanciaX = Math.abs(atacante.getX() - objetivo.getX());
        int distanciaY = Math.abs(atacante.getY() - objetivo.getY());

        System.out.println("\n[COMBATE] " + atacante.getTipo() + " intenta atacar a " + objetivo.getTipo() + " con " + atacante.getArmaEquipada());

        // Si están a una distancia de ataque menor o igual a 2 unidades
        if (distanciaX <= 2 && distanciaY <= 2) {
            int dañoDefinitivo = atacante.getDañoArma();
            objetivo.setEnergia(objetivo.getEnergia() - dañoDefinitivo);
            
            System.out.println("[¡IMPACTO!] Daño infligido: " + dañoDefinitivo + " pts.");
            System.out.println("[ESTADO] HP restante de " + objetivo.getTipo() + ": " + objetivo.getEnergia());
        } else {
            System.out.println("[FALLO] " + objetivo.getTipo() + " está demasiado lejos para ser alcanzado.");
        }
        
        actualizar();
    }
}