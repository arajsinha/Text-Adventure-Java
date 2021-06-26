package game;

import items.ItemNone;
import items.ItemRope;
import items.ItemKey;
import villains.SuperVillain;
import villains.VillainSoldier;
import villains.VillainCommando;
import weapons.WeaponFist;
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
        
        player.currentWeapon = new WeaponFist();
        ui.weaponLabelName.setText(player.currentWeapon.name);

        player.currentItem = new ItemRope();
        ui.itemLabelName.setText(player.currentItem.name);

        player.tryNumber = 0;

        silverRing = 0;
    }

    public void selectPosition(String nextPosition){
        switch(nextPosition)
        {
            case "ending": ending(); break;
            case "toTitle": toTitle(); break;
            case "fight": fight(); break;
            case "playerAttack": playerAttack(); break;
            case "villainAttack": villainAttack(); break;
            case "win": win(); break;
            case "lose": lose(); break;
            case "startRoom": startRoom(); break;
            case "centerRoom": centerRoom(); break;
            case "mirror": mirror(); break;
            case "startDoor": startDoor(); break;
            case "sharpTable": sharpTable(); break;
            case "cutRope": cutRope(); break;
            case "takeKnife": takeKnife(); break;
            case "takeKey": takeKey(); break;
            case "onlyDarkness": onlyDarkness(); break;
            case "openStartDoor": openStartDoor(); break;
            case "hallway1Forward": hallway1Forward(); break;
            case "hallway1Right": hallway1Right(); break;
            case "hallway1Left": hallway1Left(); break;
            case "the3Paths": the3Paths(); break;
            // case "hallway1Left": hallway1Left(); break;
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
    
    public void startRoom(){
        ui.mainTextArea.setText("You wake up, tied to a chair.\nYou can’t remember what happened in the\npast 24 hours.\n\nYou can’t recognize from your \nsurroundings where you are.");

        ui.choice1.setText("CONTINUE");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "centerRoom";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void centerRoom(){

        if(player.currentItem.name != "Rope")
        {
            ui.mainTextArea.setText("In FRONT of you is a mirror. You can see yourself in it.\nTo your RIGHT is a bare brick wall with a\nhuge metallic door\nTo your LEFT is a bare brick wall, and a\nmetallic table with very sharp edges.\nBEHIND you, there is only darkness. You can’t\nmake out what it is\n\nWhat do you do??");

            ui.choice1.setText("Move FORWARD");
            ui.choice2.setText("Move RIGHT");
            ui.choice3.setText("Move LEFT");
            ui.choice4.setText("Move BACKWARD");

            game.nextPosition1 = "mirror";
            game.nextPosition2 = "startDoor";
            game.nextPosition3 = "sharpTable";
            game.nextPosition4 = "onlyDarkness";
        }

        else if (player.currentItem.name == "Rope")
        {
            ui.mainTextArea.setText("In FRONT of you is a mirror. You can see yourself tied up in it.\nTo your RIGHT is a bare brick wall with a\nhuge metallic door\nTo your LEFT is a bare brick wall, and a\nmetallic table with very sharp edges.\nBEHIND you, there is only darkness. You can’t\nmake out what it is\n\nWhat do you do??");

            ui.choice1.setText("Move FORWARD");
            ui.choice2.setText("Move RIGHT");
            ui.choice3.setText("Move LEFT");
            ui.choice4.setText("Move BACKWARD");

            game.nextPosition1 = "mirror";
            game.nextPosition2 = "startDoor";
            game.nextPosition3 = "sharpTable";
            game.nextPosition4 = "onlyDarkness";
        }
        
    }

    public void mirror(){
        ui.mainTextArea.setText("You see yourself in the mirror.\nYou are badly beat up.\nYou don’t have much life left in you.\nYou need to get out of here.\n\nBut how??");

        ui.choice1.setText("Move BACKWARD");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "centerRoom";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void startDoor(){

        if(player.currentItem.name == "Rope" || player.currentItem.name == "None")
        {
            ui.mainTextArea.setText("You see the door more closely.\nIt is metallic, and locked.\nYou can see a keyhole.\nYou can’t open it without a key.");

            ui.choice1.setText("Move LEFT");
            ui.choice2.setText("");
            ui.choice3.setText("");
            ui.choice4.setText("");

            game.nextPosition1 = "centerRoom";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
        else if(player.currentItem.name == "Key")
        {
            ui.mainTextArea.setText("You can see a keyhole.\nYou can use your key to open the door.\n\nWhat do you do?");

            ui.choice1.setText("EXPLORE CURRENT ROOM");
            ui.choice2.setText("OPEN DOOR");
            ui.choice3.setText("");
            ui.choice4.setText("");

            game.nextPosition1 = "centerRoom";
            game.nextPosition2 = "openStartDoor";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
    }

    public void sharpTable(){
    
        if (player.currentItem.name == "Rope")
        {
            ui.mainTextArea.setText("You see the table.\nIt is metallic, and rusted.\nBut also, very sharp.\nYou could use it to get rid of the rope tying\nyour hand.\n\nWhat do you do?");
            
            ui.choice1.setText("MOVE RIGHT");
            ui.choice2.setText("CUT ROPE");
            ui.choice3.setText("");
            ui.choice4.setText("");

            game.nextPosition1 = "centerRoom";
            game.nextPosition2 = "cutRope";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }

        else if (player.currentItem.name == "None")
        {
            if(player.currentWeapon.name != "Knife")
            {
                ui.mainTextArea.setText("You see a rod jutting out of the table.\nYou could break it and use it\nas a Knife.\n\nWhat do you do?");
                ui.choice1.setText("MOVE RIGHT");
                ui.choice2.setText("TAKE WEAPON");
                ui.choice3.setText("");
                ui.choice4.setText("");

                game.nextPosition1 = "centerRoom";
                game.nextPosition2 = "takeKnife";
                game.nextPosition3 = "";
                game.nextPosition4 = "";
            }

            else if(player.currentWeapon.name == "Knife")
            {
                ui.mainTextArea.setText("You have already taken the Weapon\nThere is nothing left here.");
                ui.choice1.setText("MOVE RIGHT");
                ui.choice2.setText("");
                ui.choice3.setText("");
                ui.choice4.setText("");

                game.nextPosition1 = "centerRoom";
                game.nextPosition2 = "";
                game.nextPosition3 = "";
                game.nextPosition4 = "";
            }
        }
    }

    public void cutRope(){
        ui.mainTextArea.setText("You have now cut the Rope!!\nYou are are free now!");
        player.currentItem = new ItemNone();
        ui.itemLabelName.setText(player.currentItem.name);
        ui.choice1.setText("<");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "sharpTable";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void takeKnife(){
        player.currentWeapon = new WeaponKnife();
        ui.mainTextArea.setText("You obtained a " + player.currentWeapon.name + "!\n\n" + player.currentWeapon.name + " Damage: " + player.currentWeapon.damage);
        ui.weaponLabelName.setText(player.currentWeapon.name);
        ui.choice1.setText("<");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "centerRoom";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void onlyDarkness(){
        if(player.currentItem.name != "Rope")
        {
            ui.mainTextArea.setText("There is only darkness around you.\nYou feel the wall.\nIt's mostly brick.\nHowever, there is a tiny piece\nthat feels like metal.\nYou try to pull it.\nIt's a Key!!\n\n What do you do??");
            ui.choice1.setText("MOVE BACKWARD");
            ui.choice2.setText("TAKE KEY");
            ui.choice3.setText("");
            ui.choice4.setText("");

            game.nextPosition1 = "centerRoom";
            game.nextPosition2 = "takeKey";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
        
        if(player.currentItem.name == "Rope")
        {
            ui.mainTextArea.setText("There is only darkness around you.\nYou can't feel the wall, as your\nhands are tied.");
            ui.itemLabelName.setText(player.currentItem.name);
            ui.choice1.setText("MOVE BACKWARD");
            ui.choice2.setText("");
            ui.choice3.setText("");
            ui.choice4.setText("");

            game.nextPosition1 = "centerRoom";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
    }

    public void takeKey(){
        if(player.currentItem.name != "Key")
        {
            player.currentItem = new ItemKey();
            ui.mainTextArea.setText("You have obtained a " + player.currentItem.name);
            ui.itemLabelName.setText(player.currentItem.name);
            ui.choice1.setText("MOVE BACKWARD");
            ui.choice2.setText("");
            ui.choice3.setText("");
            ui.choice4.setText("");

            game.nextPosition1 = "centerRoom";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
        
        else if(player.currentItem.name == "Key")
        {
            player.currentItem = new ItemKey();
            ui.mainTextArea.setText("There is just a dark, bare\nwall here. Nothing else remains.\n");
            ui.itemLabelName.setText(player.currentItem.name);
            ui.choice1.setText("MOVE BACKWARD");
            ui.choice2.setText("");
            ui.choice3.setText("");
            ui.choice4.setText("");

            game.nextPosition1 = "centerRoom";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
    }

    public void openStartDoor(){
        ui.mainTextArea.setText("You have opened the Door.\nThere is wall in FRONT of you.\nTo your LEFT, there is a fountain.\nThere's a wall to your RIGHT.\nThe previous room is BEHIND you.\n\nWhat do you do??");
        ui.choice1.setText("MOVE FORWARD");
        ui.choice2.setText("MOVE RIGHT");
        ui.choice3.setText("MOVE LEFT");
        ui.choice4.setText("MOVE BACKWARDS");

        game.nextPosition1 = "hallway1Forward";
        game.nextPosition2 = "hallway1Right";
        game.nextPosition3 = "hallway1Left";
        game.nextPosition4 = "centerRoom";
    }

    public void hallway1Forward(){
        ui.mainTextArea.setText("There is nothing but a wall here.");
        ui.choice1.setText("MOVE BACKWARD");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "openStartDoor";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
    
    public void hallway1Right(){
        ui.mainTextArea.setText("There is nothing but a wall here.");
        ui.choice1.setText("MOVE LEFT");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "openStartDoor";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void hallway1Left(){
        ui.mainTextArea.setText("There is a Fountain!\nYou drink water and wash your face.\n\nYou have regained 5 Health(HP)!");
        player.hp += 5;
        ui.hpLabelNumber.setText("" + player.hp);
        ui.choice1.setText("CONTINUE");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "the3Paths";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void the3Paths(){
        ui.mainTextArea.setText("You see 3 paths in front of you.\nThey are like tunnels.\nFrom the LEFT most tunnel, you can hear a growl.\nFrom the MIDDLE tunnel, you can see some light.\nFrom the RIGHT most tunnel, you can hear the trickling sound of water.\n\nWhat do you do??");
        ui.choice1.setText("GO TO MIDDLE TUNNEL");
        ui.choice2.setText("GO TO LEFT TUNNEL");
        ui.choice3.setText("GO TO RIGHT TUNNEL");
        ui.choice4.setText("GO BACKWARDS");

        game.nextPosition1 = "middleTunnel";
        game.nextPosition2 = "leftTunnel";
        game.nextPosition3 = "rightTunnel";
        game.nextPosition4 = "openStartDoor";
    }

}
