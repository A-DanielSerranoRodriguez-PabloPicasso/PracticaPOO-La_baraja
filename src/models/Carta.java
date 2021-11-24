package models;

public class Carta {
	int numero, palo;

	// Const
	/**
	 * Constructor que crea un objeto 'Carta' recogiendo el numero y el palo de la
	 * carta.
	 * 
	 * @param numero Numero de la carta (1 - 10).
	 * @param palo   Palo de la carta (0 - 3).
	 * @throws Exception Excepcion que salta cuando las condiciones son incumplidas.
	 */
	public Carta(int numero, int palo) throws Exception {
		if (numero > 0 && numero < 11)
			this.numero = numero;
		else
			throw new Exception("El numero ha de ser mayor o igual a 1 y menor o igual a 10");

		if (palo >= 0 && palo < 4)
			this.palo = palo;
		else
			throw new Exception("El palo ha de ser mayor o igual a 0 y menor o igual a 3");
	}

	/**
	 * Constructor que crea un objeto 'Carta' recogiendo el ID que representa la
	 * carta.
	 * 
	 * @param id Numero que representa la carta (1 - 40).
	 * @throws Exception Excepcion que salta si se incumplen las condiciones.
	 */
	public Carta(int id) throws Exception {
		if (id > 0) {
			if (id % 10 == 0)
				this.numero = 10;
			else
				this.numero = id % 10;

			if (id / 10 == 4)
				this.palo = (id / 10) - 1;
			else if (id % 10 == 0)
				this.palo = (id / 10) - 1;
			else
				this.palo = id / 10;
		} else
			throw new Exception("El ID ha de ser desde 1 hasta 40");
	}

	// Getters - Setters / Methods
	/**
	 * Devuelve el ID de la carta.
	 * 
	 * @return Entero que representa el ID.
	 */
	public int getId() {
		return ((this.palo * 10) + this.numero);
	}

	/**
	 * Devuelve el numero de la carta.
	 * 
	 * @return Entero que representa el numero.
	 */
	public int getNumero() {
		return this.numero;
	}

	/**
	 * Devuelve el numero del palo.
	 * 
	 * @return Entero que representa el palo.
	 */
	public int getPalo() {
		return this.palo;
	}

	/**
	 * Devuelve el nombre del numero.
	 * 
	 * @return String que representa el nombre.
	 * @throws Exception En caso de obtener un numero extraño, se lanza una
	 *                   excepcion.
	 */
	public String getNombreNumero() throws Exception {
		switch (this.numero) {
		case 1:
			return "As";

		case 2:
			return "Dos";

		case 3:
			return "Tres";

		case 4:
			return "Cuatro";

		case 5:
			return "Cinco";

		case 6:
			return "Seis";

		case 7:
			return "Siete";

		case 8:
			return "Sota";

		case 9:
			return "Caballo";

		case 0:
		case 10:
			return "Rey";

		default:
			throw new Exception("ERROR: getNombreNumero");
		}
	}

	/**
	 * Devuelve el nombre del palo.
	 * 
	 * @return String que representa el nombre del palo.
	 * @throws Exception En caso de obtener un numero extraño, se lanza una
	 *                   excepcion.
	 */
	public String getNombrePalo() throws Exception {
		switch (this.palo) {
		case 0:
			return "Oros";

		case 1:
			return "Copas";

		case 2:
			return "Espadas";

		case 3:
		case 4:
			return "Bastos";

		default:
			throw new Exception("ERROR: getNombrePalo");
		}
	}

	/**
	 * Devuelve el nombre completo de la carta del objeto 'Carta' actual.
	 * 
	 * @return String que representa el nombre de la carta.
	 */
	public String getNombreCarta() {
		try {
			return this.getNombreNumero() + " de " + this.getNombrePalo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Devuelve el valor de la carta con respecto al juego del Tute.
	 * 
	 * @return Entero que representa el valor de la carta.
	 * @throws Exception En caso de obtener un numero que no pertenezca al juego, se
	 *                   lanza una excepcion.
	 */
	public int getValorTute() throws Exception {
		switch (this.numero) {
		case 1:
			return 11;

		case 3:
			return 10;

		case 10:
			return 4;

		case 9:
			return 3;

		case 8:
			return 2;

		default:
			throw new Exception("No tiene valor en el juego del tute");
		}
	}

	/**
	 * Devuelve el valor de la carta con respecto al juego del Mus.
	 * 
	 * @return Entero que representa el valor de la carta.
	 */
	public int getValorMus() {
		switch (this.numero) {
		case 1:
		case 2:
			return 1;

		case 3:
		case 8:
		case 9:
		case 10:
			return 10;

		default:
			return this.numero;
		}
	}

	/**
	 * Devuelve el valor de la carta con respecto al juego del "7 y medio".
	 * 
	 * @return Doble que representa el valor de la carta.
	 */
	public double getValor7yMedia() {
		switch (this.numero) {
		case 8:
		case 9:
		case 10:
			return 0.5;
		default:
			return this.numero;
		}
	}

	// toString
	@Override
	public String toString() {
		return "Carta [numero=" + numero + ", palo=" + palo + "]";
	}

}
