package com.example.jennyshangmangame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Blandade ord
    public static final String[] WORDS = {
            "ALFABET", "ANKOR", "SMURFAR", "SNUSMUMRIK", "HALLONKRÄM",
            "ASKUNGEN", "SILVER", "JULKLAPPAR", "ADVENT", "PUMPA",
            "LYFTKRAN", "ALBATROSS",

    };
    public static final Random RANDOM = new Random();

    // Antal fel man får ha innan man förlorar är 8 (0-7)
    public static final int MAX_ERRORS = 7;
    // En string med ordet man skall hitta
    private String wordToFind;
    // Ordet i en char array
    private char[] wordFound;
    // En int som lagrar antal fel man skrivit in
    private int nbErrors;
    // letters already entered by user
    private ArrayList < String > letters = new ArrayList < > ();
    private ImageView bild;
    private TextView wordTv;
    private TextView wordToFindTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bild = findViewById(R.id.bild);
        wordTv = findViewById(R.id.wordTv);
        wordToFindTv = findViewById(R.id.wordToFindTv);
        newGame();
    }

    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
    //    getMenuInflater().inflate(R.menu.main, menu);
    //    return true;
    //}

    //@Override
    //public boolean onOptionsItemSelected(MenuItem item) {
    //   if (item.getItemId() == R.id.new_game) {
    //        newGame();
    //   }
    //    return super.onOptionsItemSelected(item);
    //}



    // Metoden som väljer ord
    private String nextWordToFind() {
        return WORDS[RANDOM.nextInt(WORDS.length)];
    }

    // Här är metoden som startar spelet
    public void newGame() {
        nbErrors = -1;
        letters.clear();
        wordToFind = nextWordToFind();

        // För varje bokstav i orden som valts, så gör vi ett understreck för att visa antal bokstäver
        wordFound = new char[wordToFind.length()];

        for (int i = 0; i < wordFound.length; i++) {
            wordFound[i] = '_';
        }

        updateImg(nbErrors);
        wordTv.setText(wordFoundContent());
        wordToFindTv.setText("");
    }

    // Metod som returnerar sant om man har hittat rätt ord
    public boolean wordFound() {
        return wordToFind.contentEquals(new String(wordFound));
    }

    // Method updating the word found after user entered a character
    private void enter(String c) {
        // we update only if c has not already been entered
        if (!letters.contains(c)) {
            // we check if word to find contains c
            if (wordToFind.contains(c)) {
                // if so, we replace _ by the character c
                int index = wordToFind.indexOf(c);

                while (index >= 0) {
                    wordFound[index] = c.charAt(0);
                    index = wordToFind.indexOf(c, index + 1);
                }
            } else {
                // c not in the word => error
                nbErrors++;
                Toast.makeText(this, R.string.try_an_other, Toast.LENGTH_SHORT).show();
            }

            // c is now a letter entered
            letters.add(c);
        } else {
            Toast.makeText(this, R.string.letter_already_entered, Toast.LENGTH_SHORT).show();
        }
    }

    // Method returning the state of the word found by the user until by now
    private String wordFoundContent() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < wordFound.length; i++) {
            builder.append(wordFound[i]);

            if (i < wordFound.length - 1) {
                builder.append(" ");
            }
        }

        return builder.toString();
    }


    private void updateImg(int play) {
        int resImg = getResources().getIdentifier("hangman_" + play, "drawable",
                getPackageName());
        bild.setImageResource(resImg);
    }


    public void touchLetter(View v) {
        if (nbErrors < MAX_ERRORS
                && !getString(R.string.you_win).equals(wordToFindTv.getText())) {
            String letter = ((Button) v).getText().toString();
            enter(letter);
            wordTv.setText(wordFoundContent());
            updateImg(nbErrors);

            // check if word is found
            if (wordFound()) {
                Toast.makeText(this, R.string.you_win, Toast.LENGTH_SHORT).
                        show();
                wordToFindTv.setText(R.string.you_win);
            } else {
                if (nbErrors >= MAX_ERRORS) {
                    Toast.makeText(this, R.string.you_lose, Toast.LENGTH_SHORT).show();
                    wordToFindTv.setText(getString(R.string.word_to_find).
                            replace("#word#", wordToFind));
                }
            }
        } else {
            Toast.makeText(this, R.string.game_is_ended, Toast.LENGTH_SHORT).show();
        }
    }
    //Metod för att komma till sidan med utvecklarinformation
    public void openDeveloperPagePressed(View view) {
        Intent openDeveloperPage = new Intent(this, DeveloperName.class);
        startActivity(openDeveloperPage);
    }
    //Metod för att start om spelet
    public void openMainMenuPressed(View view) {
        Intent openManinMenuPage = new Intent(this, MainActivity.class);
        startActivity(openManinMenuPage);
    }
}
