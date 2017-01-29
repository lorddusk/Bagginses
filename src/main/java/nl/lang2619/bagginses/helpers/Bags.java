package nl.lang2619.bagginses.helpers;

import net.minecraft.item.Item;
import nl.lang2619.bagginses.items.Bag;

/**
 * Created by Alex on 28/01/2017.
 */
public class Bags {

  private Bag tier1;
  private Bag tier2;
  private Bag tier3;
  private String color;
  private boolean registered;
  private String desc;
  private String serverDesc;

  public Bags(Bag tier1, Bag tier2, Bag tier3, String color, boolean registered) {
      this.tier1 = tier1;
      this.tier2 = tier2;
      this.tier3 = tier3;
      this.color = color;
      this.registered = registered;
  }

  public Bag getTier1() {
    return tier1;
  }

  public Bag getTier2() {
    return tier2;
  }

  public Bag getTier3() {
    return tier3;
  }

  public String getColor() {
    return color;
  }

  public boolean isRegistered() {
    return registered;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getDesc() {
    return this.desc;
  }

  public void setServerDesc(String desc) {
    this.serverDesc = desc;
  }

  public String getServerDesc() {
    return this.serverDesc;
  }

}
