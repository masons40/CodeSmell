public class EnchantmentType {

    public enum Enchantments {
        INK(Types.INK, Category.Adrian),
        PUFFERFISH(Types.PUFFERFISH, Category.Adrian),
        HOLYWATER(Types.HOLYWATER, Category.Adrian),
        URCHIN(Types.URCHIN, Category.Adrian);

        Types eType;
        Category eCat;

        Enchantments(Types eType, EnchantmentType.Category eCat) {
            this.eCat = eCat;
            this.eType = eType;
        }

        Category getCategory() {
            return this.eCat;
        }

        Types getType() {
            return this.eType;
        }

        public enum Types {
            INK, PUFFERFISH, HOLYWATER, URCHIN, SEALORD, DYSPEPSIA, TOXIN, YPHOON, TSUNAMI, SPONGE, PRISTINE, CLEANSE, STARLIGHT, GUST, GRAVITY, DETERMINATION, COURAGE, FOREGIVENESS, TORNADO, REDEMPTION, PEACEFUL, FLEE, VIRTUE, AMBIGUITY, VALERE, SANDSTORM, REKINDLE, THERMALS, APPERTITE;
        }
    }

    Enchantments eType;
    Category eCat;

    public EnchantmentType(Enchantment eType) {
        this.eType = eType.getType();
        this.eCat = eType.getCategory();
    }

    public enum Category {
        Adrian, Zephira, Partial;
    }
}

public interface Hockey extends Sports {
   public void homeGoalScored();
   public void visitingGoalScored();
   public void endOfPeriod(int period);
   public void overtimePeriod(int ot);
}