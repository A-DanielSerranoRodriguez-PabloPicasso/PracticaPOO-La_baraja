package mainApp;

import java.util.ArrayList;
import java.util.Scanner;

import models.Baraja;
import models.Carta;

public class MainApp {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int opc;

		do {
			System.out.println("Este es el juego de las 7 y media. ¿Quieres jugar?");
			System.out.println("\n1. Sí\n2. No");
			System.out.print("->: ");
			opc = Integer.parseInt(sc.next());
		} while (jugar(opc));
	}

	private static boolean jugar(int opc) {
		switch (opc) {
		case 1:
			System.out.println();
			juego();
			break;

		case 2:
			System.out.println("Ok 😢");
			return false;

		default:
			System.out.println("Introduce una opcion valida.\n\n");
			break;
		}

		return true;
	}

	private static void juego() {
		Baraja baraja = new Baraja();
		Scanner sc = new Scanner(System.in);
		String jugador1 = "", jugador2 = "";
		boolean error;
		boolean ia = false;

		System.out.println();
		System.out.println("¿Como quieres jugar?");
		System.out.println("\n1. Con el ordenador\n2. Con otro jugador");
		do {
			System.out.print("->: ");
			switch (Integer.parseInt(sc.next())) {
			case 1:
				ia = true;
				error = false;
				System.out.println();
				System.out.println("### Jugador ###");
				jugador1 = nombres();
				jugador2 = "Ordenador";
				break;

			case 2:
				ia = false;
				error = false;
				System.out.println();
				System.out.println("### Jugador 1 ###");
				jugador1 = nombres();
				System.out.println();
				System.out.println("### Jugador 2 ###");
				jugador2 = nombres();
				break;

			default:
				System.out.println("\nElige una opcion correcta\n");
				error = true;
				break;
			}
		} while (error);

		System.out.println("\n\n¿Cuantas cartas quereis usar?");
		System.out.println("\n1. 40\n2. 80");
		do {
			System.out.print("->: ");
			baraja = baraja(Integer.parseInt(sc.next()), baraja);
		} while (baraja == null);

		partida(jugador1, jugador2, ia, baraja);
	}

	private static Baraja baraja(int num, Baraja baraja) {
		switch (num) {
		case 1:
			return baraja = new Baraja(num, true);

		case 2:
			return baraja = new Baraja(num, true);

		default:
			System.out.println("\nElige una opcion correcta\n");
			return baraja = null;
		}
	}

	private static String nombres() {
		String nom;
		Scanner sc = new Scanner(System.in);

		System.out.print("Nombre: ");
		nom = sc.next();
		return nom;
	}

	private static void partida(String jugador1, String jugador2, boolean ia, Baraja baraja) {
		ArrayList<Carta> cartas1 = new ArrayList<Carta>(), cartas2 = new ArrayList<Carta>();
		Carta carta = null;
		boolean turno = false, pasar1 = false, pasar2 = false;
		double puntos1 = 0, puntos2 = 0, visible1 = 0, visible2 = 0;
		int opc = 0, robado1 = 0, robado2 = 0;

//		cartas1.add(baraja.robar());
//		cartas2.add(baraja.robar());

		while (!pasar1 || !pasar2) {
			System.out.println("\n");
			if (!turno) {
				pasar1 = false;
				
				if (cartas1.size() == 0) {
					puntos1 = puntos(puntos1, carta, cartas1, baraja);

					visible1 = puntos1;
				}

				System.out.println("Turno de " + jugador1 + ". Puntuacion: " + puntos1 + "\n¿Que vas a hacer?");

				System.out.println("\n1. Coger\n2. Pasar");

				do {
					System.out.print("->: ");
					opc = Integer.parseInt(sc.next());
				} while (!accion(opc));

				do {
					switch (opc) {
					case 1:
						puntos1 = puntos(puntos1, carta, cartas1, baraja);
						robado1++;
						break;

					case 2:
						pasar1 = true;
						break;

					default:
						opc = -1;
						break;
					}
				} while (opc == -1);

				turno = true;

			} else if (turno && !ia) {
				pasar2 = false;

				if (cartas2.size() == 0) {
					puntos2 = puntos(puntos2, carta, cartas2, baraja);

					visible2 = puntos2;
				}

				System.out.println("Turno de " + jugador2 + ". Puntuacion: " + puntos2 + "\n¿Que vas a hacer?");

				System.out.println("\n1. Coger\n2. Pasar");

				do {
					System.out.print("->: ");
					opc = Integer.parseInt(sc.next());
				} while (!accion(opc));

				do {
					switch (opc) {
					case 1:
						puntos2 = puntos(puntos2, carta, cartas2, baraja);
						robado2++;
						break;

					case 2:
						pasar2 = true;
						break;

					default:
						opc = -1;
						break;
					}
				} while (opc == -1);

				turno = false;

			} else if (turno && ia) {
				pasar2 = false;

				if (cartas2.size() == 0) {
					puntos2 = puntos(puntos2, carta, cartas2, baraja);

					visible2 = puntos2;
					System.out.println("Turno de " + jugador2 + ". Puntuacion: " + visible2);
				} else {
					System.out.println("Turno de " + jugador2 + ". Puntuacion: " + visible2 + " + ?");
				}

				if (robado1 < 3) {
					if (puntos2 < 4 && visible1 < 4) {
						puntos2 = puntos(puntos2, carta, cartas2, baraja);
						robado2++;
					} else if (puntos2 >= 4 && puntos2 <= 5) {
						puntos2 = puntos(puntos2, carta, cartas2, baraja);
						robado2++;
					} else if (puntos2 > 5) {
						pasar2 = true;
					} else if (pasar1 = true){
						puntos2 = puntos(puntos2, carta, cartas2, baraja);
						robado2++;
					}
				} else if (robado1 >= 3) {
					if (puntos2 <= 4) {
						puntos2 = puntos(puntos2, carta, cartas2, baraja);
						robado2++;
					} else {
						if (pasar1) {
							puntos2 = puntos(puntos2, carta, cartas2, baraja);
							robado2++;
						} else {
							pasar2 = true;
						}
					}
				}
				
				turno = false;
			}
		}
		
		System.out.println();
		System.out.println(jugador1 + ": " + puntos1);
		System.out.println(jugador2 + ": " + puntos2);

		if (puntos2 < puntos1 && puntos1 <= 7.5) {
			System.out.println("\nEl ganador es: " + jugador1 + " con un " + puntos1 + "\n\n");
		} else if (puntos1 < puntos2 && puntos2 <= 7.5) {
			System.out.println("\nEl ganador es: " + jugador2 + " con un " + puntos2 + "\n\n");
		} else if (puntos1 < puntos2 && puntos1 >= 7.5) {
			System.out.println("\nEl ganador es: " + jugador1 + " con un " + puntos1 + "\n\n");
		} else if (puntos2 < puntos1 && puntos2 >= 7.5) {
			System.out.println("\nEl ganador es: " + jugador2 + " con un " + puntos2 + "\n\n");
		} else if (puntos1 < puntos2) {
			System.out.println("\nEl ganador es: " + jugador1 + " con un " + puntos1 + "\n\n");
		} else if (puntos2 < puntos1) {
			System.out.println("\nEl ganador es: " + jugador2 + " con un " + puntos2 + "\n\n");
		} else {
			System.out.println("\nEMPATE con un " + puntos1 + "\n\n");
		}
	}
	
	private static boolean accion(int opc) {
		switch (opc) {
		case 1:
		case 2:
			return true;

		default:
			System.out.println("\nElige una opcion correcta\n");
			return false;
		}
	}

	private static double puntos(double puntos, Carta carta, ArrayList<Carta> cartas, Baraja baraja) {
		cartas.add(baraja.robar());
		carta = cartas.get(cartas.size() - 1);
		puntos += carta.getValor7yMedia();
		return puntos;
	}
	
}
