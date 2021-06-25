package game;

public class VisibilityManager {

    UI ui;    

    public VisibilityManager(UI userInterface){
        ui = userInterface;
    }

    public void showTitleScreen(){
        //Show the Title Screen
        ui.titleNamePanel.setVisible(true);
        ui.startButtonPanel.setVisible(true);

        //Hide the Game Screen
        ui.mainTextPanel.setVisible(false);
        ui.choiceButtonPanel.setVisible(false);
        ui.playerPanel.setVisible(false);
    }

    public void showGameScreen(){
        //HIDE the Title Screen
        ui.titleNamePanel.setVisible(false);
        ui.startButtonPanel.setVisible(false);

        //SHOW the Game Screen
        ui.mainTextPanel.setVisible(true);
        ui.choiceButtonPanel.setVisible(true);
        ui.playerPanel.setVisible(true);
    }
}
