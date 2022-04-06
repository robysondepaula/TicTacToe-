package tictactoe.robysondepaula.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameCode {

        private int [] [] gameBoard;

        private String[]playerNames={"1", "2"};

        private Button playAgainBtn;
        private Button homeBtn;
        private TextView playerTurn;

        private int player = 1;



        GameCode(){
            gameBoard = new int[3] [3];

            for (int row=0; row<3; row++){
                for(int col=0; col<3; col++){
                    gameBoard[row][col] = 0;
                }
            }
        }

        public boolean updateGameBoard(int row, int col){
            if(gameBoard[row - 1 ] [ col -1 ] == 0){
                gameBoard[row - 1 ] [col - 1 ] = player;

                if(player == 1){
                    playerTurn.setText((playerNames[1] + "Turn"));

                }
                else{
                    playerTurn.setText((playerNames[0] + "Turn"));
                }

                return true;

            }else{
                return false;
            }
        }

        public boolean winner() {
            boolean hasWinner = false;

            for (int row = 0; row < 3; row++) {
                if (gameBoard[row][0] == gameBoard[row][1] && gameBoard[row][0] == gameBoard[row][2] && gameBoard[row][0] != 0) {
                    hasWinner = true;

                }
            }

            for (int col = 0; col < 3; col++) {
                if (gameBoard[0][col] == gameBoard[1][col] && gameBoard[2][col] == gameBoard[0][col] && gameBoard[0][col] != 0) {
                    hasWinner = true;
                }
            }

            if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2] && gameBoard[0][0] != 0) {
                hasWinner = true;
            }

            if (gameBoard[2][0] == gameBoard[1][1] && gameBoard[2][0] == gameBoard[0][2] && gameBoard[2][0] != 0) {
                hasWinner = true;
            }

            int allFieldsFilled = 0;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (gameBoard[row][col] != 0) {
                        allFieldsFilled += 1;
                    }
                }
            }


            if (hasWinner) {
                playAgainBtn.setVisibility(View.VISIBLE);
                homeBtn.setVisibility(View.VISIBLE);
                playerTurn.setText((playerNames[player - 1] + "!WINS!"));
                return true;

            }
            else if (allFieldsFilled == 9) {
                playAgainBtn.setVisibility(View.VISIBLE);
                homeBtn.setVisibility(View.VISIBLE);
                playerTurn.setText("!TIE!");
                return true;

            }
            else {
                return false;
            }

        }

        public void resetGame(){
            for (int row=0; row<3; row++){
                for(int col=0; col<3; col++){
                    gameBoard[row][col] = 0;
                }
            }

            player =1;

            playAgainBtn.setVisibility(View.GONE);
            homeBtn.setVisibility(View.GONE);

            playerTurn.setText((playerNames [0] + "plays"));
        }

        public void settingPlayAgainBtn(Button playAgainBtn){
            this.playAgainBtn = playAgainBtn;

        }

        public void settingHomeBtn(Button homeBtn){
            this.homeBtn = homeBtn;
        }
        public void settingPlayerTurn(TextView playerTurn){
            this.playerTurn = playerTurn;
        }

        public void setPlayerNames(String [] playerNames){
            this.playerNames = playerNames;
        }

        public int[][] getGameBoard(){
            return gameBoard;
        }

        public void setPlayer(int player){
            this.player = player;
        }

        public int getPlayer(){
            return player;
        }

}
