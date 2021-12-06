package models;

import java.util.ArrayList;
import java.util.Collections;

public class Baraja {
	private ArrayList<Carta> lista_cartas;

	// Const
	/**
	 * Constructor que crea un objeto 'Baraja' con una lista de cartas vacia
	 */
	public Baraja() {
		this.lista_cartas = new ArrayList<Carta>(40);
	}

	/**
	 * Constructor que crea un objeto 'Baraja' con una lista de cartas.
	 * 
	 * @param tipoBaraja Entero que indica si se usaran 40 cartas (1) u 80 (2).
	 */
	public Baraja(int tipoBaraja) {
		if (tipoBaraja == 1) {
			this.lista_cartas = new ArrayList<Carta>(40);
			for (int i = 1; i <= 40; i++) {
				try {
					this.lista_cartas.add(new Carta(i));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (tipoBaraja == 2) {
			int cont = 0;
			this.lista_cartas = new ArrayList<Carta>(80);

			while (cont < 2) {
				for (int i = 1; i <= 40; i++) {
					try {
						this.lista_cartas.add(new Carta(i));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				cont++;
			}
		}
	}

	/**
	 * Constructor que crea un objeto 'Baraja' con una lista de cartas que estara
	 * barajada o no.
	 * 
	 * @param tipoBaraja Entero que indica si se usaran 40 cartas (1) u 80 (2).
	 * @param barajar    Indica si se quiere barajar la baraja o no.
	 */
	public Baraja(int tipoBaraja, boolean barajar) {
		this.lista_cartas = new ArrayList<Carta>(40);
		if (tipoBaraja == 1) {
			for (int i = 1; i <= 40; i++) {
				try {
					this.lista_cartas.add(new Carta(i));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (tipoBaraja == 2) {
			this.lista_cartas = new ArrayList<Carta>(80);
			int cont = 0, j = 1;

			while (cont < 2) {
				for (int i = 1; i <= 40; i++) {
					try {
						this.lista_cartas.add(new Carta(j));
						j++;
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				cont++;
			}
		}
		if (barajar)
			barajar();
	}

	// Methods
	/**
	 * Nos permite barajar la baraja, desordenando de forma aleatoria las cartas.
	 */
	public void barajar() {
		// Barajar aleatorio - Metodo propio
//		ArrayList<Carta> copia_lista = (ArrayList) this.lista_cartas.clone();
//		ArrayList<Carta> barajada = new ArrayList<Carta>();
//		for (int i = 0; i < copia_lista.size(); i++) {
//			int cont = copia_lista.size() - 1;
//			int posicion = (int) (Math.random() * cont);
//			while (cont > 0) {
//				barajada.add(copia_lista.get(posicion));
//				copia_lista.remove(posicion);
//				posicion = (int) (Math.random() * cont--);
//			}
//		}		
//		this.lista_cartas = barajada;

		// Barajar aleatorio - Metodo shuffle
		Collections.shuffle(this.lista_cartas);
	}

	/**
	 * Nos permite cortar la baraja, poniendo los primeros numeros detras de los
	 * siguientes.
	 * 
	 * @param posicion Entero que representa el lugar desde el que cortar.
	 */
	public void cortar(int posicion) {
		ArrayList<Carta> cortada = new ArrayList<Carta>();
		for (int i = posicion; i < this.lista_cartas.size(); i++) {
			cortada.add(this.lista_cartas.get(i));
		}
		for (int i = 0; i < posicion; i++) {
			cortada.add(this.lista_cartas.get(i));
		}

		this.lista_cartas = cortada;
	}

	/**
	 * Nos permite robar una carta de la baraja, eliminando esa carta de la baraja.
	 * 
	 * @return Objeto 'Carta' con la carta robada.
	 */
	public Carta robar() {
		if (this.lista_cartas.size() == 0) {
			System.out.println("No quedan cartas");
			return null;
		} else {
			Carta carta = this.lista_cartas.get(0);
			this.lista_cartas.remove(0);
			return carta;
		}
	}

	/**
	 * Nos permite insertar una carta al final de la baraja.
	 * 
	 * @param id_carta Entero que representa el ID de la carta que vamos a insertar.
	 */
	public void insertaCartaFinal(int id_carta) {
		try {
			if (this.lista_cartas.size() == 40 || this.lista_cartas.size() == 80) {
				System.out.println("No puedes introducir más cartas");
			} else {
				this.lista_cartas.add(new Carta(id_carta));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Nos permite insertar una carta al principio de la baraja.
	 * 
	 * @param id_carta Entero que representa el ID de la carta que vamos a insertar.
	 */
	public void insertaCartaPrincipio(int id_carta) {
		try {
			if (this.lista_cartas.size() == 40 || this.lista_cartas.size() == 80) {
				System.out.println("No puedes introducir más cartas");
			} else {
				this.lista_cartas.add(0, new Carta(id_carta));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Nos permite insertar una carta al final de la baraja.
	 * 
	 * @param carta Objeto 'Carta' que representa el la carta que vamos a insertar.
	 */
	public void insertaCartaFinal(Carta carta) {
		try {
			if (this.lista_cartas.size() == 40 || this.lista_cartas.size() == 80) {
				System.out.println("No puedes introducir más cartas");
			} else {
				this.lista_cartas.add(carta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Nos permite insertar una carta al principio de la baraja.
	 * 
	 * @param carta Objeto 'Carta' que representa el la carta que vamos a insertar.
	 */
	public void insertaCartaPrincipio(Carta carta) {
		try {
			if (this.lista_cartas.size() == 40 || this.lista_cartas.size() == 80) {
				System.out.println("No puedes introducir más cartas");
			} else {
				this.lista_cartas.add(0, carta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Devuelve cuantas cartas quedan en la baraja.
	 */
	public void getNumeroCartas() {
		System.out.println("Quedan " + this.lista_cartas.size() + " cartas");
	}

	/**
	 * Devuelve si la baraja esta vacia o no.
	 * 
	 * @return Boleano que dice si esta vacia (true) o no (false).
	 */
	public boolean isVacia() {
		if (this.lista_cartas.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	// toString
	@Override
	public String toString() {
		return "Baraja [lista_cartas=" + lista_cartas + "]";
	}

}