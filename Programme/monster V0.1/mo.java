package acl;
import java.util.Random;
public class mo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int[][] labyrinthe = {
		            {1, 1, 1, 1, 1},
		            {1, 0, 0, 0, 1},
		            {1, 0, 1, 0, 1},
		            {1, 0, 0, 0, 1},
		            {1, 1, 1, 1, 1}
		        };
		 Hero hero = new Hero(1, 1,100);
	     Monstre monstre = new Monstre(3, 3);
	 while (true) {
		 System.out.println(hero.geth());
		 hero.deplacer(0, 1, labyrinthe);
		 System.out.println("Nouvelle position du hero: (" + hero.getX() + ", " + hero.getY() + ")");
         
         monstre.deplacerAleatoire(labyrinthe);   
         System.out.println("Nouvelle position du monstre: (" + monstre.getX() + ", " + monstre.getY() + ")");
         labyrinthe[monstre.getX()][monstre.getY()]=2;
         if (hero.detecterCollision(monstre)) {
             System.out.println("Collision! Le héros perd des points de vie.");
             hero.perdreVie();}
         if (hero.geth()<0 ) {break;}
         for (int i = 0; i < labyrinthe.length; i++) { 
             for (int j = 0; j < labyrinthe[i].length; j++) { 
                 System.out.print(labyrinthe[i][j] + " ");
             }
             System.out.println();
         }
         labyrinthe[monstre.getX()][monstre.getY()]=0;
         try {
             Thread.sleep(1000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }}}
     