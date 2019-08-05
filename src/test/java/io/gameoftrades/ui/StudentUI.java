package io.gameoftrades.ui;

import io.gameoftrades.studentNN.HandelaarImpl;

/**
 * Toont de visuele gebruikersinterface.
 * 
 * Let op: dit werkt alleen als je de WereldLader hebt geimplementeerd (Anders krijg je een NullPointerException).
 */
public class StudentUI {

	public static void main(String[] args) {
		MainGui.builder()
		       .add(new HandelaarImpl())
		       // Je kan hier eventueel extra algoritmen toevoegen dmv extra 'add' aanroepen.
		       .toon(TileSet.T32, "/kaarten/voorbeeld-kaart.txt");
	}
	
}
