package game;

import villains.SuperVillain;
import villains.VillainSoldier;
import villains.VillainCommando;
import weapons.WeaponGun;
import weapons.WeaponKnife;
import weapons.WeaponRPG;

public class Story {

    Game game;
    UI ui;
    VisibilityManager vm;
    Player player = new Player();
    SuperVillain villain;

    int silverRing;

    public Story(Game g, UI userInterface, VisibilityManager vManager)
    {
        game = g;
        ui = userInterface;
        vm = vManager;
    }

    public void defaultSetup()
    {
        player.hp = 15;
        ui.hpLabelNumber.setText(""+player.hp);
        
        player.currentWeapon = new WeaponKnife();
        ui.weaponLabelName.setText(player.currentWeapon.name);

        silverRing = 0;
    }

    public void selectPosition(String nextPosition){
        switch(nextPosition)
        {
            case "townGate": townGate(); break;
            case "talkGuard": talkGuard(); break;
            case "attackGuard": attackGuard(); break;
            case "crossRoad": crossRoad(); break;
            case "North": North(); break;
            case "East": East(); break;
            case "West": West(); break;
            case "ending": ending(); break;
            case "toTitle": toTitle(); break;
            case "fight": fight(); break;
            case "playerAttack": playerAttack(); break;
            case "villainAttack": villainAttack(); break;
            case "win": win(); break;
            case "lose": lose(); break;
        }
    }

    public void townGate(){
        ui.mainTextArea.setText("You are at the gate of the town. A Guard is \nstanding in front of you. \n\nWhat do you do?");

        ui.choice1.setText("Talk to the Guard");
        ui.choice2.setText("Attack the Guard");
        ui.choice3.setText("Leave");
        ui.choice4.setText("");

        game.nextPosition1 = "talkGuard";
        game.nextPosition2 = "attackGuard";
        game.nextPosition3 = "crossRoad";
        game.nextPosition4 = "";
    }

    public void talkGuard(){
        ui.mainTextArea.setText("Guard: Hello Stranger!\n\nI have never seen your face.\nI'm sorry but I can't let a stranger enter our\ntown.");

        if (silverRing == 0)
        {
            ui.choice1.setText("<");
            ui.choice2.setText("");
            ui.choice3.setText("");
            ui.choice4.setText("");

            game.nextPosition1 = "townGate";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }

        else if (silverRing == 1)
        {
            ending();
        }
    }

    public void ending()
    {
        ui.mainTextArea.setText("Oh you killed that villain " + villain.name +"!\nThankyou so much!!\nWelcome to our town\n\n<THE END>");

        ui.choice1.setVisible(false);
        ui.choice2.setVisible(false);
        ui.choice3.setVisible(false);
        ui.choice4.setVisible(false);
    }

    public void toTitle(){
        defaultSetup();
        vm.showTitleScreen();
    }

    public void attackGuard(){
        ui.mainTextArea.setText("Guard: Hey! Don't be stupid!\n\nYou are no match for the guard.\nThe Guard inflicted 3 HP Damage.");
        player.hp -= 3;
        ui.hpLabelNumber.setText("" + player.hp);
        ui.choice1.setText("<");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        if (player.hp > 0) {
            game.nextPosition1 = "townGate";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        } else if (player.hp < 1) {
            game.nextPosition1 = "lose";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
    }

    public void crossRoad(){
        ui.mainTextArea.setText("You are at a Crossroad.\nIf you go South, you will go back to the town.\n\nWhere do you want to go?");

        ui.choice1.setText("Go North");
        ui.choice2.setText("Go South");
        ui.choice3.setText("Go East");
        ui.choice4.setText("Go West");

        game.nextPosition1 = "North";
        game.nextPosition2 = "townGate";
        game.nextPosition3 = "East";
        game.nextPosition4 = "West";
    }

    public void North(){
        ui.mainTextArea.setText("There is a River.\nYou drink water and rest by the riverside.\n\n(Your HP is recovered by 2)");
        player.hp += 2;
        ui.hpLabelNumber.setText("" + player.hp);
        ui.choice1.setText("Go South");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "crossRoad";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void East() {
        int i = new java.util.Random().nextInt(100) + 1;
        if (i < 90) {
            player.currentWeapon = new WeaponGun();
        } else {
            player.currentWeapon = new WeaponRPG();
        }
        ui.mainTextArea.setText("You walked into a forest and found a " + player.currentWeapon.name
                + "\n\n(You obtained a " + player.currentWeapon.name + ")");
        ui.weaponLabelName.setText(player.currentWeapon.name);
        ui.hpLabelNumber.setText("" + player.hp);
        ui.choice1.setText("Go West");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "crossRoad";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void West() {

        int i  = new java.util.Random().nextInt(100)+1;
        if(i<90){
            villain = new VillainSoldier();
        }
        else{
            villain = new VillainCommando();
        }
        ui.mainTextArea.setText("You encounter a Villain " + villain.name + "!\n\nWhat do you do?");

        ui.choice1.setText("Fight"); 
        ui.choice2.setText("Run");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "fight";
        game.nextPosition2 = "crossRoad";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void fight() {
        ui.mainTextArea.setText(villain.name + " HP: " + villain.hp + "\n" + villain.name + " Attack: " + villain.attack + "\n\nYour Attack: " + player.currentWeapon.damage + "\n\nWhat do you do?");

        ui.choice1.setText("Attack"); 
        ui.choice2.setText("Run");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "playerAttack";
        game.nextPosition2 = "crossRoad";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void playerAttack() {

        int playerDamage = new java.util.Random().nextInt(player.currentWeapon.damage);


        ui.mainTextArea.setText("You attacked the " + villain.name + " and gave " + playerDamage + " \nDamage!");
        villain.hp -= playerDamage;

        ui.choice1.setText("<"); 
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        if(villain.hp > 0)
        {
            game.nextPosition1 = "villainAttack";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
        else if(villain.hp < 1)
        {
            game.nextPosition1 = "win";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
    }

    public void villainAttack()
    {
        int villainDamage = new java.util.Random().nextInt(villain.attack);

        ui.mainTextArea.setText(villain.name + " attacked you and gave " + villainDamage + " \nDamage!");
        player.hp -= villainDamage;
        ui.hpLabelNumber.setText("" + player.hp);

        ui.choice1.setText("<");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        if (player.hp > 0) {
            game.nextPosition1 = "fight";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        } else if (player.hp < 1) {
            game.nextPosition1 = "lose";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
    }

    public void win()
    {
        ui.mainTextArea.setText("You have defeated the " + villain.name + "!\nThe villain dropped a ring\n\n(You obtained a Silver Ring!");

        silverRing = 1;

        ui.choice1.setText("Go East");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "crossRoad";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void lose()
    {
        ui.mainTextArea.setText("You are dead!\n\n<!GAME OVER!>");

        silverRing = 1;

        ui.choice1.setText("To the Menu");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "toTitle";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    
}
