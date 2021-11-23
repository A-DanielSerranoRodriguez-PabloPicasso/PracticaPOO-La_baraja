package models;

public class Carta {
	int numero, palo;

	// Const
	public Carta(int numero, int palo) {
		this.numero = numero;
		this.palo = palo;
	}

	public Carta(int id) {
		this.numero = id % 10;
		this.palo = id / 10;
	}

	// Getters - Setters
	public int getId() {
		return ((this.palo * 10) + this.numero);
	}

	public int getNumero() {
		return this.numero;
	}

	public int getPalo() {
		return this.palo;
	}

	// Methods
	public String getNombreNumero() {
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

		case 10:
			return "Rey";

		default:
			return "Algo a pasado";
		}
	}

	public String getNombrePalo() {
		switch (this.palo) {
		case 1:
			return "Oros";

		case 2:
			return "Copas";

		case 3:
			return "Espadas";

		case 4:
			return "Bastos";

		default:
			return "Algo ha pasado";
		}
	}

	public String getNombreCarta() {
		return this.getNombreNumero() + " de " + this.getNombrePalo();
	}

	public int getValorTute() {
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
			return 0;
		}
	}

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

	public double getValor7yMedia() {
		switch (this.numero) {
		case 8:
		case 9:
		case 10:
			return 0.5;
		}

		return this.numero;
	}

	// toString
	@Override
	public String toString() {
		return "Carta [numero=" + numero + ", palo=" + palo + "]";
	}

}
