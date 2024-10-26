package me.stelios.quest;

import me.stelios.GamePanel;

public class Quest {

    public String displayText;
    public int objectives;

    public int rewardToGet;

    public Quest(int objectives, String displayText) {
        this.displayText = displayText;
        this.objectives = objectives;
    }

    public int objectivesCompleted(GamePanel gp) { return 0; }
    public void reward(GamePanel gp) {
        // Remember to either clear the quest or replace it!
    }

}
