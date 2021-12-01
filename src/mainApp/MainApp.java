package mainApp;

import java.util.ArrayList;
import java.util.Scanner;

import models.Baraja;
import models.Carta;
import models.Persona;

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

		sc.close();
	}

	public static boolean jugar(int opc) {
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

	public static void juego() {
		ArrayList<Persona> jugadores = new ArrayList<Persona>();
		ArrayList<Carta> cartas1 = new ArrayList<Carta>(), cartas2 = new ArrayList<Carta>();
		Baraja baraja = new Baraja();

		String jugador1 = "", jugador2 = "";
		boolean error = false, ia = false;

		System.out.println("\n¿Cuantas cartas se van a usar?");
		System.out.println("\n1. 40\n2. 80");

		do {
			System.out.print("->: ");
			baraja = baraja(Integer.parseInt(sc.next()), baraja);
		} while (baraja == null);

		System.out.println();
		System.out.println("\n¿Como se va a jugar?");
		System.out.println("\n1. Con el ordenador\n2. Con otro jugador");

		do {
			System.out.print("->: ");
			switch (Integer.parseInt(sc.next())) {
			case 1:
				System.out.println();
				System.out.println("### Jugador ###");
				jugador1 = nombres();
				jugador2 = "Ordenador";
				ia = true;
				error = false;
				break;

			case 2:
				System.out.println();
				System.out.println("### Jugador 1 ###");
				jugador1 = nombres();
				System.out.println();
				System.out.println("### Jugador 2 ###");
				jugador2 = nombres();
				ia = false;
				error = false;
				break;

			default:
				System.out.println("\nElige una opcion correcta\n");
				error = true;
				break;
			}
		} while (error);

		if (ia) {

			Persona persona1 = new Persona(jugador1, cartas1, baraja);
			jugadores.add(persona1);
			Persona persona2 = new Persona(jugador2, cartas2, baraja);
			jugadores.add(persona2);

			partida(ia, jugadores, baraja);

		} else {

			Persona persona1 = new Persona(jugador1, cartas1, baraja);
			jugadores.add(persona1);
			Persona persona2 = new Persona(jugador2, cartas2, baraja);
			jugadores.add(persona2);

			partida(jugadores, baraja);
		}

	}

	public static Baraja baraja(int num, Baraja baraja) {
		switch (num) {
		case 1:
			return baraja = new Baraja(num, true);

		case 2:
			return baraja = new Baraja(num, true);

		default:
			System.out.println("\nElige una opcion correcta");
			return baraja = null;
		}
	}

	public static String nombres() {
		String nom;

		System.out.print("Nombre: ");
		nom = sc.next();
		return nom;
	}

	public static void partida(boolean ia, ArrayList<Persona> jugadores, Baraja baraja) {
		boolean turno = false;

		while (!jugadores.get(0).getPasar() || !jugadores.get(1).getPasar()) {
			System.out.println("\n");
			if (!turno) {

				Persona persona = jugadores.get(0);
				turno(persona, baraja);
				turno = true;

			} else if (turno) {

				if (jugadores.get(1).getCantidadCartas() == 0) {
					jugadores.get(1).robar7yMedia();
				}

				if (jugadores.get(1).getCantidadCartas() == 1) {
					System.out.println("Turno de " + jugadores.get(1).getNombre() + ". Puntuacion: "
							+ jugadores.get(1).getVisible());
				} else {
					System.out.println("Turno de " + jugadores.get(1).getNombre() + ". Puntuacion: "
							+ jugadores.get(1).getVisible() + " + ?");
				}

				if (jugadores.get(0).getTurnos() < 3) {
					if (jugadores.get(1).getPuntos() < 4) {
						jugadores.get(1).robar7yMedia();
					} else if (jugadores.get(1).getPuntos() >= 4 && jugadores.get(1).getPuntos() < 5) {
						jugadores.get(1).robar7yMedia();
					} else if (jugadores.get(1).getPuntos() >= 5) {
						jugadores.get(1).pasar();
					}
				} else if (jugadores.get(0).getTurnos() >= 3) {
					if (jugadores.get(1).getPuntos() <= 4) {
						jugadores.get(1).robar7yMedia();
					} else if (jugadores.get(1).getPuntos() >= 5) {
						jugadores.get(1).pasar();
					}
				}
//				System.out.println(jugadores.get(1));
				turno = false;
			}
		}

		fin(jugadores);
	}

	public static void partida(ArrayList<Persona> jugadores, Baraja baraja) {
		boolean turno = false;
		int pers = 0;

		while (!jugadores.get(0).getPasar() || !jugadores.get(1).getPasar()) {
			System.out.println("\n");

			if (!turno) {

				Persona persona = jugadores.get(pers);
				turno(persona, baraja);
				pers++;
				turno = true;

			} else if (turno) {

				Persona persona = jugadores.get(pers);
				turno(persona, baraja);
				pers--;
				turno = false;

			}
		}

		fin(jugadores);
	}

	public static void turno(Persona persona, Baraja baraja) {
		boolean error = false;

		if (persona.getCantidadCartas() == 0) {
			persona.robar7yMedia();
		}

		System.out.println(
				"Turno de " + persona.getNombre() + ". Puntuacion: " + persona.getPuntos() + "\n¿Que vas a hacer?");

		System.out.println("\n1. Coger\n2. Pasar");

		do {
			System.out.print("->: ");
			switch (Integer.parseInt(sc.next())) {
			case 1:
				persona.robar7yMedia();
				break;

			case 2:
				persona.pasar();
				break;

			default:
				System.out.println("\nElige una opcion correcta\n");
				error = true;
				break;
			}
		} while (error);
	}

	public static void fin(ArrayList<Persona> jugadores) {
		System.out.println();
		System.out.println(jugadores.get(0).getNombre() + ": " + jugadores.get(0).getPuntos());
		System.out.println(jugadores.get(1).getNombre() + ": " + jugadores.get(1).getPuntos());

		if (jugadores.get(1).getPuntos() < jugadores.get(0).getPuntos() && jugadores.get(0).getPuntos() <= 7.5) {

			System.out.println("\nEl ganador es: " + jugadores.get(0).getNombre() + " con un "
					+ jugadores.get(0).getPuntos() + "\n\n");

		} else if (jugadores.get(0).getPuntos() < jugadores.get(1).getPuntos() && jugadores.get(1).getPuntos() <= 7.5) {

			System.out.println("\nEl ganador es: " + jugadores.get(1).getNombre() + " con un "
					+ jugadores.get(1).getPuntos() + "\n\n");

		} else if (jugadores.get(0).getPuntos() < jugadores.get(1).getPuntos() && jugadores.get(0).getPuntos() >= 7.5) {

			System.out.println("\nEl ganador es: " + jugadores.get(0).getNombre() + " con un "
					+ jugadores.get(0).getPuntos() + "\n\n");

		} else if (jugadores.get(1).getPuntos() < jugadores.get(0).getPuntos() && jugadores.get(1).getPuntos() >= 7.5) {

			System.out.println("\nEl ganador es: " + jugadores.get(1).getNombre() + " con un "
					+ jugadores.get(1).getPuntos() + "\n\n");

		} else if (jugadores.get(0).getPuntos() < jugadores.get(1).getPuntos()) {

			System.out.println("\nEl ganador es: " + jugadores.get(0).getNombre() + " con un "
					+ jugadores.get(0).getPuntos() + "\n\n");

		} else if (jugadores.get(1).getPuntos() < jugadores.get(0).getPuntos()) {

			System.out.println("\nEl ganador es: " + jugadores.get(1).getNombre() + " con un "
					+ jugadores.get(1).getPuntos() + "\n\n");

		} else {
			System.out.println("\nEMPATE con un " + jugadores.get(0).getPuntos() + "\n\n");
		}
	}

}
