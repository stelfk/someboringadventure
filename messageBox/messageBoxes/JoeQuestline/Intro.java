package me.stelios.messageBox.messageBoxes.JoeQuestline;

import me.stelios.messageBox.MessageBox;
import me.stelios.messageBox.Type;

public class Intro extends MessageBox {
    public Intro() {
        super(Type.Normal, "INTROBOX");
        this.text = new String[] {
                "System: Welcome to this extremely borind adventure!\n Lets get you started.\n Press V to INTERACT.",
                "System: You can press I to open your INVENTORY and Q to see your QUESTS!\nMoving around is done by using W, A, S, D.\n(NOTE: You can't move, open your inventory or look at your quests while chat boxes are open)",
                "Stel: Good luck 😊"
        };
    }
}
