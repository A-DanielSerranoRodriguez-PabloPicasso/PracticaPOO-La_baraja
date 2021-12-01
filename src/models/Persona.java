package models;

import java.util.ArrayList;

public class Persona {
	Carta carta;
	ArrayList<Carta> cartas;
	Baraja baraja;
	String nombre;
	double puntos, visible;
	int turnos;
	boolean pasar;

	// Const
	public Persona() {

	}

	public Persona(String nombre, ArrayList<Carta> cartas, Baraja baraja) {
		super();
		this.nombre = nombre;
		this.cartas = cartas;
		this.baraja = baraja;
		this.carta = null;
		this.puntos = 0;
		this.visible = 0;
		this.turnos = 0;
		this.pasar = false;
	}

	// Getters - Setters
	public String getNombre() {
		return this.nombre;
	}

	public double getPuntos() {
		return this.puntos;
	}

	public double getVisible() {
		return this.visible;
	}

	public int getCantidadCartas() {
		return this.cartas.size();
	}

	public int getTurnos() {
		return this.turnos;
	}

	public boolean getPasar() {
		return this.pasar;
	}

	public String getUltimaCarta() {
		return this.carta.getNombreCarta();
	}

	// Methods
	public void robar() {
		this.cartas.add(this.baraja.robar());
	}

	public void robar7yMedia() {
		this.pasar = false;
		this.cartas.add(this.baraja.robar());
		this.carta = this.cartas.get(this.cartas.size() - 1);
		if (this.cartas.size() == 1) {
			this.visible = this.carta.getValor7yMedia();
			System.out.println("Primera carta: " + this.carta.getNombreCarta() + "\n");
			this.turnos--;
		} else {
			System.out.println("\nHas robado: " + this.carta.getNombreCarta());
		}
		this.puntos += this.carta.getValor7yMedia();
		this.turnos++;
	}

	public void pasar() {
		this.pasar = true;
	}

	// toString
	@Override
	public String toString() {
		return "Persona [carta=" + carta + ", cartas=" + cartas + ", baraja=" + baraja + ", nombre=" + nombre
				+ ", puntos=" + puntos + ", visible=" + visible + ", turnos=" + turnos + ", pasar=" + pasar + "]";
	}

}
