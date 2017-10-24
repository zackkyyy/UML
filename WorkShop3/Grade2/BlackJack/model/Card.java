package BlackJack.model;

public class Card {

    private color m_color;
    private value m_value;
    private boolean m_isHidden;
    public Card(color a_color, value a_value) {
        m_value = a_value;
        m_color = a_color;
        m_isHidden = true;
    }

    public color getColor() {
        if (m_isHidden) {
            return color.Hidden;
        }
        return m_color;
    }

    public value getValue() {
        if (m_isHidden) {
            return value.Hidden;
        }
        return m_value;
    }

    public void show(boolean a_show) {
        m_isHidden = !a_show;
    }

    public enum color {
        Hearts,
        Spades,
        Diamonds,
        Clubs,
        Count,
        Hidden
    }

    public enum value {
        Two,
        Three,
        Four,
        Five,
        Six,
        Seven,
        Eight,
        Nine,
        Ten,
        Knight,
        Queen,
        King,
        Ace,
        Count,
        Hidden
    }
}