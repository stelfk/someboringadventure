package me.stelios.messageBox.messageBoxes.JoeQuestline;

import me.stelios.messageBox.MessageBox;
import me.stelios.messageBox.Type;

public class Joe001 extends MessageBox {
    public Joe001() {
        super(Type.Normal, "JOE001");
        this.text = new String[]{
                "JOE: I love grapes...",
                "JOE: I love grapes... Do you ?"
        };
    }
}
