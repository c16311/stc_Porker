package porker;

/**
 *　カードのメソッドをまとめたクラス
 * @author c16311
 */
public class Card {
    private enum Suit{
        Diamond(1),Spade(2),Clover(3),Heart(4);
        private int id;
        Suit(int id){this.id=id;};
    }
    private enum Rank{
        A(1),Two(2),Three(3),Four(4),Five(5),Six(6),Seven(7),Eight(8),Nine(9),Ten(10),J(11),Q(12),K(13);
        private int id;
        Rank(int id){this.id=id;};
        
    }
    CardSuit suit;  //{0:ダイヤ、1:スペード、2:クローバー、3:ハート}
    CardRank rank;  //{1:A,2〜10,11:J,12:Q,13:K}     
    public Card(CardSuit suit,CardRank rank){
        this.suit = suit;
        this.rank = rank;
    }
    public Card(int suit,int rank){
        ;
    }
}
