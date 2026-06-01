# Cyber Duel 2D - Motor de Videojuegos

## 1. Temática Elegida
**Cyber Duel 2D** es un simulador por consola de la lógica interna de un videojuego de duelos por turnos en un plano 2D. Dos jugadores se enfrentan en una arena con **200 puntos de vida (HP)** cada uno. Cada personaje cuenta con un arma única equipada que inflige un daño diferenciado, obligando al sistema a calcular de manera matemática la distancia coordinada en el eje espacial antes de validar el impacto del ataque.

## 2. Arquitectura del Software
Para respetar estrictamente el límite máximo de 6 clases y evitar la sobreingeniería, el sistema se ha estructurado en 4 clases altamente cohesionadas:
* **EntidadVideojuego**: Clase base abstracta que encapsula los atributos espaciales ($x, y, w, h$), el estado de la energía/vida (`energia`) y las propiedades de las armas mediante encapsulación (`private`) con sus respectivos getters y setters.
* **MotorJuego**: Clase cerebro encargada de controlar la máquina de estados del juego (MENU, JUGANDO, GAMEOVER), gestionar la lista de entidades y procesar la lógica avanzada del cálculo de distancias y decremento de vida.
* **GestorEntradas**: Actúa como la capa controladora del motor, traduciendo cadenas de comandos de texto simulados ("MOVER_DERECHA", "ATACAR") en ejecuciones lógicas directas sobre las entidades.
* **Main**: Clase conductora que orquesta la inicialización de la partida, simula el flujo del ciclo por turnos y muestra las salidas de log detalladas por la consola del sistema.

## 3. Diagramas UML (Formato Mermaid)

### Diagrama de Clases
```mermaid
classDiagram
    direction UT
    class MotorJuego {
        -EstadoJuego estadoActual
        -List~EntidadVideojuego~ entidades
        +cambiarEstado(EstadoJuego nuevoEstado) void
        +añadirEntidad(EntidadVideojuego e) void
        +actualizar() void
        +ejecutarAtaque(EntidadVideojuego atacante, EntidadVideojuego objetivo) void
    }
    class EntidadVideojuego {
        -int x
        -int y
        -int w
        -int h
        -String tipo
        -int energia
        -String armaEquipada
        -int dañoArma
        +mover(int dx, int dy) void
        +getX() int
        +getY() int
        +getEnergia() int
        +setEnergia(int energia) void
    }
    class GestorEntradas {
        +procesarComando(String comando, EntidadVideojuego jugador, EntidadVideojuego enemigo, MotorJuego motor) void
    }
    class Main {
        +main(String[] args) void
    }

    Main --> MotorJuego : inicializa
    Main --> GestorEntradas : invoca
    MotorJuego --> EntidadVideojuego : administra
