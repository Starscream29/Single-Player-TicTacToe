package com.hoxton.tictactoesingleplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;



public class MainActivity extends AppCompatActivity {


    //For boardState, 0 and 1 are tiles, 2 is not claimed yet
    int[] boardState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    //See if the AI has taken it's turn already
    Boolean turn = true;

    //List of all possible winning combinations
    int[][] winningStates = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    //Make no one has won yet
    boolean gameOver = false;

    public void startGame(View view) {
        Button start = findViewById(R.id.startGameButton);
        start.setVisibility(View.INVISIBLE);

        //Coin flip for who goes first
        Random ran = new Random();
        int x = ran.nextInt(2);

        if (x == 0) {
            Toast.makeText(this, Html.fromHtml("<big><big><big>Computer goes first</big></big></big>"), Toast.LENGTH_SHORT).show();
            //Computer goes first, always places tile in the middle
            ImageView tile = findViewById(R.id.tile4);
            tile.setTranslationY(-1500f);
            tile.setImageResource(R.drawable.chanka);
            boardState[4] = 1;
            tile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(250);
        }
        else {
            Toast.makeText(this, Html.fromHtml("<big><big><big>Human goes first</big></big></big>"), Toast.LENGTH_SHORT).show();
        }
    }


    public void placeTile(View view) {

        turn = false;

        ImageView tile = (ImageView) view;

        //Read tag of tile tapped
        int tileID = Integer.parseInt(tile.getTag().toString());
        if (boardState[tileID] == 2 && gameOver == false) {
            tile.setTranslationY(-1500f);
            tile.setImageResource(R.drawable.green);
            boardState[tileID] = 0;

            //Set animation speed based on tile location
            int speed = 0;

            if (tileID == 0 || tileID == 1 || tileID == 2) {
                speed = 200;
            }
            if (tileID == 3 || tileID == 4 || tileID == 5) {
                speed = 250;
            }
            if (tileID == 6 || tileID == 7 || tileID == 8) {
                speed = 300;
            }

            tile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(speed);

            //Check if game has been won
            for (int[] threes : winningStates) {

                if (boardState[threes[0]] == boardState[threes[1]]
                        && boardState[threes[1]] == boardState[threes[2]]
                        && boardState[threes[0]] != 2){

                    //Check identity of winner
                    if (boardState[threes[0]] == 1){

                        //Make end tile appear
                        LinearLayout ending = findViewById(R.id.ending);
                        ending.setVisibility(View.VISIBLE);
                        //Set up winner message
                        TextView endCard = findViewById(R.id.gameOver);
                        endCard.setText("GAME OVER COMPUTER WINS");
                        gameOver = true;

                    } else if(boardState[threes[0]] == 0){

                        //Make end tile appear
                        LinearLayout ending = findViewById(R.id.ending);
                        ending.setVisibility(View.VISIBLE);
                        //Set up winner message
                        TextView endCard = findViewById(R.id.gameOver);
                        endCard.setText("GAME OVER HUMAN WINS");
                        gameOver = true;
                    }
                }
            }

            //Check for a tie
            boolean tie = true;
            for (int i : boardState) {
                if (i == 2) {
                    tie = false;
                }
            }
            if (tie == true) {
                //Make end tile appear
                LinearLayout ending = findViewById(R.id.ending);
                ending.setVisibility(View.VISIBLE);

                //Set up winner message
                TextView endCard = findViewById(R.id.gameOver);
                endCard.setText("GAME OVER TIE GAME");
                gameOver = true;
            }

            // Now define the AI's move, as a reaction to the human's move
            if (gameOver == false) {
                //First, check if a potential win is present

                if (((boardState[3] == boardState[6] && boardState[3] == 1) ||
                        (boardState[4] == boardState[8] && boardState[4] == 1) ||
                        (boardState[1] == boardState[2] && boardState[1] == 1))
                        && boardState[0] == 2) {

                    ImageView computerTile = findViewById(R.id.tile0);
                    computerTile.setTranslationY(-1500f);
                    computerTile.setImageResource(R.drawable.chanka);
                    boardState[0] = 1;
                    computerTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(200);
                    turn = true;
                } else if (((boardState[0] == boardState[2] && boardState[2] == 1) ||
                        (boardState[4] == boardState[7] && boardState[7] == 1))
                        && boardState[1] == 2) {
                    ImageView computerTile = findViewById(R.id.tile1);
                    computerTile.setTranslationY(-1500f);
                    computerTile.setImageResource(R.drawable.chanka);
                    boardState[1] = 1;
                    computerTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(200);
                    turn = true;
                } else if (((boardState[0] == boardState[1] && boardState[1] == 1) ||
                        (boardState[6] == boardState[4] && boardState[4] == 1) ||
                        (boardState[5] == boardState[8] && boardState[8] == 1))
                        && boardState[2] == 2) {

                    ImageView computerTile = findViewById(R.id.tile2);
                    computerTile.setTranslationY(-1500f);
                    computerTile.setImageResource(R.drawable.chanka);
                    boardState[2] = 1;
                    computerTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(200);
                    turn = true;
                } else if (((boardState[0] == boardState[6] && boardState[6] == 1) ||
                        (boardState[4] == boardState[5] && boardState[5] == 1))
                        && boardState[3] == 2) {

                    ImageView computerTile = findViewById(R.id.tile3);
                    computerTile.setTranslationY(-1500f);
                    computerTile.setImageResource(R.drawable.chanka);
                    boardState[3] = 1;
                    computerTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(200);
                    turn = true;
                } else if (((boardState[0] == boardState[8] && boardState[8] == 1) ||
                        (boardState[2] == boardState[6] && boardState[6] == 1) ||
                        (boardState[3] == boardState[5] && boardState[5] == 1) ||
                        (boardState[1] == boardState[7] && boardState[7] == 1))
                        && boardState[4] == 2) {

                    ImageView computerTile = findViewById(R.id.tile4);
                    computerTile.setTranslationY(-1500f);
                    computerTile.setImageResource(R.drawable.chanka);
                    boardState[4] = 1;
                    computerTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(200);
                    turn = true;
                } else if (((boardState[2] == boardState[8] && boardState[8] == 1) ||
                        (boardState[3] == boardState[4] && boardState[4] == 1))
                        && boardState[5] == 2) {

                    ImageView computerTile = findViewById(R.id.tile5);
                    computerTile.setTranslationY(-1500f);
                    computerTile.setImageResource(R.drawable.chanka);
                    boardState[5] = 1;
                    computerTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(200);
                    turn = true;
                } else if (((boardState[0] == boardState[3] && boardState[3] == 1) ||
                        (boardState[2] == boardState[4] && boardState[4] == 1) ||
                        (boardState[7] == boardState[8] && boardState[8] == 1))
                        && boardState[6] == 2) {

                    ImageView computerTile = findViewById(R.id.tile6);
                    computerTile.setTranslationY(-1500f);
                    computerTile.setImageResource(R.drawable.chanka);
                    boardState[6] = 1;
                    computerTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(200);
                    turn = true;
                } else if (((boardState[1] == boardState[4] && boardState[4] == 1) ||
                        (boardState[6] == boardState[8] && boardState[8] == 1))
                        && boardState[7] == 2) {

                    ImageView computerTile = findViewById(R.id.tile7);
                    computerTile.setTranslationY(-1500f);
                    computerTile.setImageResource(R.drawable.chanka);
                    boardState[7] = 1;
                    computerTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(200);
                    turn = true;
                } else if (((boardState[0] == boardState[4] && boardState[4] == 1) ||
                        (boardState[2] == boardState[5] && boardState[5] == 1) ||
                        (boardState[7] == boardState[6] && boardState[6] == 1))
                        && boardState[8] == 2) {

                    ImageView computerTile = findViewById(R.id.tile8);
                    computerTile.setTranslationY(-1500f);
                    computerTile.setImageResource(R.drawable.chanka);
                    boardState[8] = 1;
                    computerTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(200);
                    turn = true;
                }


                // If no potential win is present, check for any necessary blocks
                else if (((boardState[3] == boardState[6] && boardState[3] == 0) ||
                        (boardState[4] == boardState[8] && boardState[4] == 0) ||
                        (boardState[1] == boardState[2] && boardState[1] == 0))
                        && boardState[0] == 2) {

                    ImageView computerTile = findViewById(R.id.tile0);
                    computerTile.setTranslationY(-1500f);
                    computerTile.setImageResource(R.drawable.chanka);
                    boardState[0] = 1;
                    computerTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(200);
                    turn = true;
                } else if (((boardState[0] == boardState[2] && boardState[2] == 0) ||
                        (boardState[4] == boardState[7] && boardState[7] == 0))
                        && boardState[1] == 2) {

                    ImageView computerTile = findViewById(R.id.tile1);
                    computerTile.setTranslationY(-1500f);
                    computerTile.setImageResource(R.drawable.chanka);
                    boardState[1] = 1;
                    computerTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(200);
                    turn = true;
                } else if (((boardState[0] == boardState[1] && boardState[1] == 0) ||
                        (boardState[6] == boardState[4] && boardState[4] == 0) ||
                        (boardState[5] == boardState[8] && boardState[8] == 0))
                        && boardState[2] == 2) {

                    ImageView computerTile = findViewById(R.id.tile2);
                    computerTile.setTranslationY(-1500f);
                    computerTile.setImageResource(R.drawable.chanka);
                    boardState[2] = 1;
                    computerTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(200);
                    turn = true;
                } else if (((boardState[0] == boardState[6] && boardState[6] == 0) ||
                        (boardState[4] == boardState[5] && boardState[5] == 0))
                        && boardState[3] == 2) {

                    ImageView computerTile = findViewById(R.id.tile3);
                    computerTile.setTranslationY(-1500f);
                    computerTile.setImageResource(R.drawable.chanka);
                    boardState[3] = 1;
                    computerTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(200);
                    turn = true;
                } else if (((boardState[0] == boardState[8] && boardState[8] == 0) ||
                        (boardState[2] == boardState[6] && boardState[6] == 0) ||
                        (boardState[3] == boardState[5] && boardState[5] == 0) ||
                        (boardState[1] == boardState[7] && boardState[7] == 0))
                        && boardState[4] == 2) {

                    ImageView computerTile = findViewById(R.id.tile4);
                    computerTile.setTranslationY(-1500f);
                    computerTile.setImageResource(R.drawable.chanka);
                    boardState[4] = 1;
                    computerTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(200);
                    turn = true;
                } else if (((boardState[2] == boardState[8] && boardState[8] == 0) ||
                        (boardState[3] == boardState[4] && boardState[4] == 0))
                        && boardState[5] == 2) {

                    ImageView computerTile = findViewById(R.id.tile5);
                    computerTile.setTranslationY(-1500f);
                    computerTile.setImageResource(R.drawable.chanka);
                    boardState[5] = 1;
                    computerTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(200);
                    turn = true;
                } else if (((boardState[0] == boardState[3] && boardState[3] == 0) ||
                        (boardState[2] == boardState[4] && boardState[4] == 0) ||
                        (boardState[7] == boardState[8] && boardState[8] == 0))
                        && boardState[6] == 2) {

                    ImageView computerTile = findViewById(R.id.tile6);
                    computerTile.setTranslationY(-1500f);
                    computerTile.setImageResource(R.drawable.chanka);
                    boardState[6] = 1;
                    computerTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(200);
                    turn = true;
                } else if (((boardState[1] == boardState[4] && boardState[4] == 0) ||
                        (boardState[6] == boardState[8] && boardState[8] == 0))
                        && boardState[7] == 2) {

                    ImageView computerTile = findViewById(R.id.tile7);
                    computerTile.setTranslationY(-1500f);
                    computerTile.setImageResource(R.drawable.chanka);
                    boardState[7] = 1;
                    computerTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(200);
                    turn = true;
                } else if (((boardState[0] == boardState[4] && boardState[4] == 0) ||
                        (boardState[2] == boardState[5] && boardState[5] == 0) ||
                        (boardState[7] == boardState[6] && boardState[6] == 0))
                        && boardState[8] == 2) {

                    ImageView computerTile = findViewById(R.id.tile8);
                    computerTile.setTranslationY(-1500f);
                    computerTile.setImageResource(R.drawable.chanka);
                    boardState[8] = 1;
                    computerTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(200);
                    turn = true;
                }

                if (turn == false && gameOver == false) {
                    //If there are no potential blocks or wins, just put a tile in a random square
                    Random ran = new Random();
                    int x = ran.nextInt(9);
                    while (boardState[x] != 2) {
                        x = ran.nextInt(9);
                    }

                    if (x == 0) {
                        ImageView randomTile = findViewById(R.id.tile0);
                        randomTile.setTranslationY(-1500f);
                        randomTile.setImageResource(R.drawable.chanka);
                        boardState[x] = 1;
                        randomTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(250);
                    } else if (x == 1) {
                        ImageView randomTile = findViewById(R.id.tile1);
                        randomTile.setTranslationY(-1500f);
                        randomTile.setImageResource(R.drawable.chanka);
                        boardState[x] = 1;
                        randomTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(250);
                    } else if (x == 2) {
                        ImageView randomTile = findViewById(R.id.tile2);
                        randomTile.setTranslationY(-1500f);
                        randomTile.setImageResource(R.drawable.chanka);
                        boardState[x] = 1;
                        randomTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(250);
                    } else if (x == 3) {
                        ImageView randomTile = findViewById(R.id.tile3);
                        randomTile.setTranslationY(-1500f);
                        randomTile.setImageResource(R.drawable.chanka);
                        boardState[x] = 1;
                        randomTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(250);
                    } else if (x == 4) {
                        ImageView randomTile = findViewById(R.id.tile4);
                        randomTile.setTranslationY(-1500f);
                        randomTile.setImageResource(R.drawable.chanka);
                        boardState[x] = 1;
                    } else if (x == 5) {
                        ImageView randomTile = findViewById(R.id.tile5);
                        randomTile.setTranslationY(-1500f);
                        randomTile.setImageResource(R.drawable.chanka);
                        boardState[x] = 1;
                        randomTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(250);
                    } else if (x == 6) {
                        ImageView randomTile = findViewById(R.id.tile6);
                        randomTile.setTranslationY(-1500f);
                        randomTile.setImageResource(R.drawable.chanka);
                        boardState[x] = 1;
                        randomTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(250);
                    } else if (x == 7) {
                        ImageView randomTile = findViewById(R.id.tile7);
                        randomTile.setTranslationY(-1500f);
                        randomTile.setImageResource(R.drawable.chanka);
                        boardState[x] = 1;
                        randomTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(250);
                    } else if (x == 8) {
                        ImageView randomTile = findViewById(R.id.tile8);
                        randomTile.setTranslationY(-1500f);
                        randomTile.setImageResource(R.drawable.chanka);
                        boardState[x] = 1;
                        randomTile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(250);
                    }
                }
            }

            //Check if game has been won
            for (int[] threes : winningStates) {

                if (boardState[threes[0]] == boardState[threes[1]]
                        && boardState[threes[1]] == boardState[threes[2]]
                        && boardState[threes[0]] != 2){

                    //Check identity of winner
                    if (boardState[threes[0]] == 1){

                        //Make end tile appear
                        LinearLayout ending = findViewById(R.id.ending);
                        ending.setVisibility(View.VISIBLE);
                        //Set up winner message
                        TextView endCard = findViewById(R.id.gameOver);
                        endCard.setText("GAME OVER COMPUTER WINS");
                        gameOver = true;


                    } else if(boardState[threes[0]] == 0){

                        //Make end tile appear
                        LinearLayout ending = findViewById(R.id.ending);
                        ending.setVisibility(View.VISIBLE);
                        //Set up winner message
                        TextView endCard = findViewById(R.id.gameOver);
                        endCard.setText("GAME OVER HUMAN WINS");
                        gameOver = true;


                    }
                }
            }
            //Check for a tie
            tie = true;
            for (int i : boardState) {
                if (i == 2) {
                    tie = false;
                }
            }
            if (tie == true) {
                //Make end tile appear
                LinearLayout ending = findViewById(R.id.ending);
                ending.setVisibility(View.VISIBLE);

                //Set up winner message
                TextView endCard = findViewById(R.id.gameOver);
                endCard.setText("GAME OVER TIE GAME");
                gameOver = true;
            }

        }

    }


    public void playAgain(View view) {

        LinearLayout ending = findViewById(R.id.ending);
        ending.setVisibility(View.INVISIBLE);

        for (int i = 0; i < 9; i++) {
            boardState[i] = 2;
        }

        ImageView reset = findViewById(R.id.tile0);
        reset.setImageResource(0);
        reset = findViewById(R.id.tile1);
        reset.setImageResource(0);
        reset = findViewById(R.id.tile2);
        reset.setImageResource(0);
        reset = findViewById(R.id.tile3);
        reset.setImageResource(0);
        reset = findViewById(R.id.tile4);
        reset.setImageResource(0);
        reset = findViewById(R.id.tile5);
        reset.setImageResource(0);
        reset = findViewById(R.id.tile6);
        reset.setImageResource(0);
        reset = findViewById(R.id.tile7);
        reset.setImageResource(0);
        reset = findViewById(R.id.tile8);
        reset.setImageResource(0);
        gameOver = false;

        //Coin flip for who goes first
        Random ran = new Random();
        int x = ran.nextInt(2);

        if (x == 0) {
            Toast.makeText(this, Html.fromHtml("<big><big><big>Computer goes first</big></big></big>"), Toast.LENGTH_SHORT).show();

            //Computer goes first, always places tile in the middle
            ImageView tile = findViewById(R.id.tile4);
            tile.setTranslationY(-1500f);
            tile.setImageResource(R.drawable.chanka);
            boardState[4] = 1;
            tile.animate().rotationBy(1080f).translationYBy(1500f).setDuration(250);
        }
        else {
            Toast.makeText(this, Html.fromHtml("<big><big><big>Human goes first</big></big></big>"), Toast.LENGTH_SHORT).show();

        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
