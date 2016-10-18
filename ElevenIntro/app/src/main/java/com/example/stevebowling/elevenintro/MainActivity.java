package com.example.stevebowling.elevenintro;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private GridView gameboard;
    private EditText playerChar;
    private Button checkWinner;

    private int currentPlayer;
    private Player[] players = new Player[2];
    private GameTokenAdapter adapter;
    private ArrayList<Player> gameState = new ArrayList<>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("------", "onCreate");

        gameboard = (GridView) findViewById(R.id.gameboard);
        playerChar = (EditText) findViewById(R.id.playerChar);
        checkWinner = (Button) findViewById(R.id.checkWinner);

        checkWinner.setOnClickListener(gameCheck);
        gameboard.setOnItemClickListener(cellTapped);

        adapter = new GameTokenAdapter(this, R.layout.game_token, gameState);
        gameboard.setAdapter(adapter);

        createGame();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void createGame() {


        {
            currentPlayer = 0;

            playerChar.setText("X");

            players[0] = null;
            players[1] = null;

            gameState.clear();
            for (int i = 0; i < 9; i++) {
                gameState.add(new Player(2, ' '));
            }
        }
    }

    private View.OnClickListener gameCheck = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //initialize all the winning combinations
            int[][] winstate =
                    {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

            Player winner = null;

            for (int[] check : winstate) {

                if (gameState.get(check[0]).id != 2
                        && gameState.get(check[0]).id == gameState.get(check[1]).id
                        && gameState.get(check[0]).id == gameState.get(check[2]).id) {
                    winner = gameState.get(check[0]);
                    break;
                }

            }

            Intent i;
            i = new Intent(MainActivity.this, ResultsActivity.class);
            i.putExtra("char", winner.symbol);

            startActivity(i);
        }
    };

    private AdapterView.OnItemClickListener cellTapped = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (players[currentPlayer] == null) {
                char symbol;
                symbol = playerChar.getText().charAt(0);
                players[currentPlayer] = new Player(currentPlayer, symbol);
            }
            gameState.set(position, players[currentPlayer]);
            adapter.notifyDataSetChanged();

            currentPlayer++;

            if (currentPlayer == 2) {
                currentPlayer = 0;
            }

            if (players[currentPlayer] == null) {
                if (currentPlayer == 0) {
                    playerChar.setText("x");
                } else {
                    playerChar.setText("O");

                }
            } else {
                playerChar.setText(String.valueOf(players[currentPlayer].symbol));
            }
        }


    };


    @Override
    protected void onStart() {
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Log.d("------", "onStart");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    protected void onResume() {
        super.onStart();
        Log.d("------", "onResume");
    }

    @Override
    protected void onPause() {
        super.onStart();
        Log.d("------", "onPause");
    }

    @Override
    protected void onStop() {
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        super.onStart();
        Log.d("------", "onStop");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    @Override
    protected void onDestroy() {
        super.onStart();
        Log.d("------", "onDestroy");
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }
}





