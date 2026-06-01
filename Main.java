public class Main {
    public static void main(String[] args) {
        MotorJuego motor = new MotorJuego();
        GestorEntradas inputManager = new GestorEntradas();

        // 1. Inicializar los dos jugadores con 200 de vida y armas diferenciadas.
        // Formato entidad: Tipo, X, Y, W, H, Energía(HP), NombreArma, DañoArma
        // Jugador 1 lleva una Espada de Plasma (Daño alto de cerca)
        EntidadVideojuego jugador1 = new EntidadVideojuego("JUGADOR_1", 0, 0, 1, 1, 200, "Espada Plasma", 45) {};
        // Jugador 2 lleva un Blaster Láser (Daño moderado)
        EntidadVideojuego jugador2 = new EntidadVideojuego("JUGADOR_2", 6, 0, 1, 1, 200, "Blaster Láser", 30) {};

        motor.añadirEntidad(jugador1);
        motor.añadirEntidad(jugador2);
        
        // 2. Arrancar el motor de juego
        motor.cambiarEstado(MotorJuego.EstadoJuego.JUGANDO);

        System.out.println("\n===== COMIENZA LA SIMULACIÓN DE COMBATE =====");

        // TURNO 1: El Jugador 1 intenta atacar desde lejos (Debe fallar)
        inputManager.procesarComando("ATACAR", jugador1, jugador2, motor);

        // TURNO 2: El Jugador 1 se acerca al Jugador 2
        inputManager.procesarComando("MOVER_DERECHA", jugador1, jugador2, motor);
        inputManager.procesarComando("MOVER_DERECHA", jugador1, jugador2, motor); // Ahora X del J1 es 4 (Distancia con J2 es 2)

        // TURNO 3: El Jugador 1 ataca con éxito (Usa daño de Espada Plasma: 45)
        inputManager.procesarComando("ATACAR", jugador1, jugador2, motor);

        // TURNO 4: El Jugador 2 responde al ataque (Usa daño de Blaster Láser: 30)
        inputManager.procesarComando("ATACAR", jugador2, jugador1, motor);

        // TURNO 5: Intercambio crítico final simulado (Para comprobar cambio de estado)
        System.out.println("\n===== SIMULACIÓN DE RONDAS CRÍTICAS CONTINUAS =====");
        inputManager.procesarComando("ATACAR", jugador1, jugador2, motor); // J2 queda con 110 HP
        inputManager.procesarComando("ATACAR", jugador1, jugador2, motor); // J2 queda con 65 HP
        inputManager.procesarComando("ATACAR", jugador1, jugador2, motor); // J2 queda con 20 HP
        inputManager.procesarComando("ATACAR", jugador1, jugador2, motor); // J2 queda con 0 HP -> Provoca GAMEOVER
        
        // Intento de acción posterior al fin del juego (Bloqueado)
        inputManager.procesarComando("ATACAR", jugador2, jugador1, motor);
    }
}