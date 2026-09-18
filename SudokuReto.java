import java.util.Scanner;

public class SudokuReto {

    public static void main(String[] args) {
        // Tablero inicial (0 representa vacio)
        int[][] tablero = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        }; // CORREGIDO 1: Faltaba el punto y coma

        Scanner scanner = new Scanner(System.in);

        System.out.println("¡Bienvenido al Sudoku!");

        while (true) {
            imprimirTablero(tablero);

            System.out.println("Ingresa Fila (1-9), Columna (1-9) y Número (1-9).");
            System.out.println("Ejemplo: 5 5 1 (Para salir escribe: -1 -1 -1)");

            int fila = scanner.nextInt();
            int col = scanner.nextInt();
            int num = scanner.nextInt();

            if (fila == -1) {
                System.out.println("¡Adiós!");
                break;
            }

            // CORREGIDO 2: Ajustamos índices (Usuario 1-9 -> Java 0-8)
            fila = fila - 1;
            col = col - 1;

            if (esMovimientoValido(tablero, fila, col, num)) {
                tablero[fila][col] = num;
                System.out.println("¡Número colocado con éxito!");
            } else {
                System.out.println("Movimiento inválido (ya existe en fila, columna o caja).");
            }
        }
        scanner.close();
    }

    public static void imprimirTablero(int[][] tablero) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                // CORREGIDO 3a: Usamos println para la barra separadora horizontal
                System.out.println("---------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                // CORREGIDO 3b: Usamos print (sin ln) para que los números salgan en fila
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println(); // Salto de línea al final de cada fila del tablero
        }
    }

    public static boolean esMovimientoValido(int[][] tablero, int fila, int col, int num) {
        // Verificar Fila
        for (int i = 0; i < 9; i++) {
            if (tablero[fila][i] == num) {
                return false; // CORREGIDO 4: Si existe, devolvemos false
            }
        }

        // Verificar Columna
        for (int i = 0; i < 9; i++) {
            // CORREGIDO 5: Usamos [i][col] para verificar verticalmente
            if (tablero[i][col] == num) {
                return false;
            }
        }

        // Verificar Caja 3x3
        int inicioFila = fila - (fila % 3);
        int inicioCol = col - (col % 3);

        for (int i = inicioFila; i < inicioFila + 3; i++) {
            for (int j = inicioCol; j < inicioCol + 3; j++) {
                if (tablero[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}