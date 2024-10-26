package me.stelios.inventory;

import me.stelios.GamePanel;
import me.stelios.object.SuperObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InventorySystem {

    GamePanel gp;

    public List itemAmmount = new ArrayList<SuperObject>();
    public List items = new ArrayList<Integer>();

    public Actions action = Actions.None;
    public boolean extended = false;
    public int eSelected = 2;

    public boolean toggled = false;
    public int selected = 0;

    private boolean breakEquip = false;

    private String[] eOptions = {
            "Use",
            "Trash",
            "Cancel",
            "Equip/Unequip",
    };


    public InventorySystem(GamePanel gp) {
        this.gp = gp;
        //addItem(new Sword());
    }

    public boolean hasItem(SuperObject o) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) != null) {
                String name = ((SuperObject)items.get(i)).name;
                if (name == o.name) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addItem(SuperObject o, int a) {
        if (hasItem(o)) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i) != null) {
                    String name = ((SuperObject)items.get(i)).name;
                    if (name == o.name) {
                        itemAmmount.set(i, ((int)itemAmmount.get(i)) + a);
                    }
                }
            }
        } else {
            items.add(o);
            itemAmmount.add(a);
        }
    }
    public void addItem(SuperObject o) {
        if (hasItem(o)) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i) != null) {
                    String name = ((SuperObject)items.get(i)).name;
                    if (name == o.name) {
                        itemAmmount.set(i, ((int)itemAmmount.get(i)) + 1);
                    }
                }
            }
        } else {
            items.add(o);
            itemAmmount.add(1);
        }
    }

    public void update() {
        if (selected >= items.size()) {
            selected = 0;
        }
        if(selected < 0) {
            selected = gp.invHandler.items.size() - 1;
        }

        if (eSelected > 3) {
            eSelected = 0;
        }
        if (eSelected < 0) {
            eSelected = 3;
        }
        if (extended == false) eSelected = 0;

        if (action == Actions.Use) {

        } else if (action == Actions.Equip) {
            for (int i = 0; i < gp.playerInv.length; i++) {
                if (gp.hotBar.hasItem(((SuperObject)items.get(selected))) && !breakEquip) {
                    for (int j = 0; j < gp.playerInv.length; j++) {
                        if (gp.playerInv[j] != null) {
                            if (gp.playerInv[j].name == ((SuperObject)items.get(selected)).name) {
                                gp.playerInv[j] = null;
                                action = Actions.None;
                                breakEquip = true;
                                extended = false;
                            }
                        }
                    }
                } else if (gp.playerInv[i] == null) {
                    for (int j = 0; j < gp.oHandler.objects.size(); j++) {
                        if (((SuperObject)items.get(selected)).name == gp.oHandler.objects.get(j).name && !breakEquip) {
                            gp.playerInv[i] = gp.oHandler.objects.get(j);
                            breakEquip = true;
                        }
                    }
                }
            }
            breakEquip = false;
            extended = false;
            action = Actions.None;
        } else if (action == Actions.Trash) {
            itemAmmount.set(selected, (int)itemAmmount.get(selected) - 1);
            extended = false;
            action = Actions.None;
        } else if (action == Actions.Cancel) {
            extended = false;
            action = Actions.None;
        }

        for (int item = 0; item < items.size(); item++) {
            if (items.get(item) != null) {
                if ((int)itemAmmount.get(item) <= 0) {
                    itemAmmount.remove(item);
                    items.remove(item);
                }
            }
        }

    }

    public void render(Graphics2D g) {
        if (toggled) {

            if (!extended) {
                g.setColor(Color.BLACK);
                g.fillRoundRect(75, 75, 250, 400, 5, 5);

                g.setColor(Color.WHITE);
                g.drawRoundRect(75, 75, 250, 400, 5, 5);

                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i) != null && (int) itemAmmount.get(i) > 0) {
                        g.setColor(Color.WHITE);

                        SuperObject obj = (SuperObject) items.get(i);
                        int ammount = (int) itemAmmount.get(i);

                        Font currentFont = g.getFont();
                        Font newFont = currentFont.deriveFont(currentFont.getSize() * 2F);
                        g.setFont(newFont);

                        if (i == selected) {
                            g.fillRoundRect(75, 75 + i * 32, 250, (int) newFont.getSize() + 5, 5, 5);
                            g.setColor(Color.BLACK);
                        } else {
                            g.setColor(Color.WHITE);
                        }

                        String text = obj.name + " <" + ammount + ">";

                        g.drawString(text, 80, 100 + i * 30);

                        g.setFont(currentFont);

                    }
                }
            } else {
                g.setColor(Color.BLACK);
                g.fillRoundRect(75, 75, 175, 400 / eOptions.length + 25, 5, 5);

                g.setColor(Color.WHITE);
                g.drawRoundRect(75, 75, 175, 400 / eOptions.length + 25, 5, 5);

                for (int i = 0; i < eOptions.length; i++) {

                    g.setColor(Color.WHITE);

                    Font currentFont = g.getFont();
                    Font newFont = currentFont.deriveFont(currentFont.getSize() * 2F);
                    g.setFont(newFont);

                    if (i == eSelected) {
                        g.fillRoundRect(75, 75 + i * 32, 175, (int) newFont.getSize() + 5, 5, 5);
                        g.setColor(Color.BLACK);
                    }

                    g.drawString(eOptions[i], 80, 100 + i * 30);

                    g.setFont(currentFont);

                }

            }
        }
    }

}
