package game;

/*******************************************
 * ******** HUNGER GAMES - PART B **********
 * 
 * @author  Fotini Savvidou
 * 			AEM: 9657
 * 			TEL: 6909067533
 * 			EMAIL: sfoteini@ece.auth.gr 
 */


import java.awt.EventQueue;

import views.Menu;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				//new Menu();
				try {
					Menu menu = new Menu();
					menu.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
