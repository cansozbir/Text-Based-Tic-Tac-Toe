import com.sun.xml.internal.bind.v2.TODO;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {
    Random r = new Random ();
    Scanner s = new Scanner (System.in);

    private Board board = new Board ();
    private boolean p1 , win1=false,win2=false , tie=false , vsPC;
    private int satir,sutun;

    public void setPlayer () {
        int x = r.nextInt (2);
        if (x==1)
            p1=true;
        else
            p1=false;
    }

    public String getPlayer () {
        if (p1)
            return "Player1";
        else
            return "Player2";
    }

    public void waitFunc (int x) {
        try
        {
            Thread.sleep(x);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public void gameMenu ( ) {
        System.out.println (
                "*************************************\n" +
                        "*************************************\n" +
                        "***********  TIC TOC TOE  ***********\n" +
                        "*************************************\n" +
                        "*************************************\n" +
                        "1: for single player\n"+
                        "2: for two player\n"+
                        "3: for quit\n"
        );
        int menu = Integer.parseInt (s.nextLine ());
        if (menu == 1)
            this.vsPC=true;
        else if (menu == 2)
            this.vsPC=false;
        else if (menu== 3) {
            System.out.println ("Cikis yapiliyor..." );
            System.exit (1);
        }
        else {
            System.out.println ("Gecersiz giris araligi..." );
            gameMenu ();
        }
        System.out.println ("Ilk kimin baslayacagini belirlemek icin enter a basin..." );

        String ilk = s.nextLine ();
        setPlayer ();
        System.out.println (getPlayer () + " baslayacak");
        waitFunc (1500);

    }

    public void hamleAl (){

        if (!p1 && vsPC) {
            satir = r.nextInt (3);
            sutun = r.nextInt (3);
            if (satir<0 || satir>2 || sutun<0 || sutun>2 || !(board.getBoxes ( )[satir][sutun].getBoxStat ( ).equals (" ")))
                hamleAl ();
            else
                System.out.println ("Player2: " );
        }
        else {
            System.out.print (p1 ? "\nPlayer1: " : "\nPlayer2: ");
            System.out.print ("\nHamle yapmak istediginiz satir :");
            satir = s.nextInt ( ) - 1;
            if ( satir < 0 || satir > 2 ) {
                System.out.print ("\nGecersiz hamle ! Satir sayisi [1,3]eZ olmalidir");
                hamleAl ( );
            }

            System.out.print ("Hamle yapmak istediginiz sutun :\n");
            sutun = s.nextInt ( ) - 1;

            if ( sutun < 0 || sutun > 2 ) {
                System.out.print ("\nGecersiz hamle ! Sutun sayisi [1,3]eZ olmalidir");
                hamleAl ( );
            }

            if ( !board.getBoxes ( )[satir][sutun].getBoxStat ( ).equals (" ") ) {
                System.out.print ("\nGecersiz hamle. Zaten oynanmis hamleyi oynadiniz.");
                hamleAl ( );
            }

        }
    }

    public void hamleYap () {
        hamleAl ();
        if (vsPC && !p1)
            waitFunc (2500);
        if (p1)
             board.getBoxes ()[satir][sutun].setBoxStat ("X");
        else
            board.getBoxes ()[satir][sutun].setBoxStat ("O");
        p1 = !p1;
    }

    public void kazanan  () {
        for (int i = 0; i < 3; i++) {
            if ( (board.getBoxes ( )[i][0].getBoxStat ( ).equals (board.getBoxes ( )[i][1].getBoxStat ( ))) &&
                    board.getBoxes ( )[i][1].getBoxStat ( ).equals (board.getBoxes ( )[i][2].getBoxStat ( )) ) {
                if ( board.getBoxes ( )[i][0].getBoxStat ( ).equals ("X") ) {
                    win1 = true;
                    break;
                } else if ( board.getBoxes ( )[i][0].getBoxStat ( ).equals ("O") ) {
                    win2 = true;
                    break;
                }
            } else if ( (board.getBoxes ( )[0][i].getBoxStat ( ).equals (board.getBoxes ( )[1][i].getBoxStat ( ))) &&
                    board.getBoxes ( )[1][i].getBoxStat ( ).equals (board.getBoxes ( )[2][i].getBoxStat ( )) ) {
                if ( board.getBoxes ( )[0][i].getBoxStat ( ).equals ("X") ) {
                    win1 = true;
                    break;
                } else if ( board.getBoxes ( )[0][i].getBoxStat ( ).equals ("O") ) {
                    win2 = true;
                    break;
                }
            } else if ( ((board.getBoxes ( )[0][0].getBoxStat ( ).equals (board.getBoxes ( )[1][1].getBoxStat ( ))) &&
                    board.getBoxes ( )[1][1].getBoxStat ( ).equals (board.getBoxes ( )[2][2].getBoxStat ( ))) ||
                    ((board.getBoxes ( )[0][2].getBoxStat ( ).equals (board.getBoxes ( )[1][1].getBoxStat ( ))) &&
                            board.getBoxes ( )[1][1].getBoxStat ( ).equals (board.getBoxes ( )[2][0].getBoxStat ( ))) ) {

                if ( board.getBoxes ( )[1][1].getBoxStat ( ).equals ("X") ) {
                    win1 = true;
                    break;
                } else if ( board.getBoxes ( )[1][1].getBoxStat ( ).equals ("O") ) {
                    win2 = true;
                    break;
                }
            }
        }

        tie=true;
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getBoxes ()[i][j].getBoxStat ().equals(" "))
                    tie =false;
            }
         }
    }

    public void play () {
        gameMenu ();
        while ( !win1 && !win2 && !tie ) {
            System.out.println ("\n\n\n\n\n\n\n\n"+this.board);
            hamleYap ();
            kazanan ();
        }
    }

    public static void main ( String[] args ) {
        Game game = new Game ();
        game.play ();
    }
}
