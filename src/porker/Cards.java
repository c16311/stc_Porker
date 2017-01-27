/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package porker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Cardの処理をまとめたクラス
 * @author c16311
 */
public class Cards{
    /**
     列挙型Rankで0〜3でダイヤモンド、スペード、クローバー、ハートを宣言
     */
    private enum Suit{
        Diamond(0),Spade(1),Clover(2),Heart(3);
        private final int id;
        private Suit(int id){this.id=id;};
    }
    /**
     列挙型Rankで1〜13の値を宣言
     */
    private enum Rank{
        A(1),Two(2),Three(3),Four(4),Five(5),Six(6),Seven(7),Eight(8),Nine(9),Ten(10),Jack(11),Queen(12),King(13);
        private final int id;
        private Rank(int id){this.id=id;};
    }
    static final List<Cards> deck = new ArrayList<>();
    private final Suit suit;
    private final Rank rank; 
    /**コンストラクタ
     * @param suit:列挙型Suit
     * @param rank:列挙型Rank
    */
    public Cards(Suit suit,Rank rank){ 
        this.suit = suit;
        this.rank = rank;
    }
    static{
        for(Suit suit:Suit.values()){
            for(Rank rank:Rank.values()){
                deck.add(new Cards(suit,rank));
            }
        }
    }
    /**山札を生成する
     　@param　なし
     * @return 生成した山札
    */
    public static ArrayList<Cards> newDeck(){
        return new ArrayList<>(deck);
    }
     /**カードの絵柄を取得する
      @param card　参照するカード
     * @return 取得した役柄
    */
    public static Suit getSuit(Cards card){
        return card.suit;
    }
    /**カードのナンバーを取得する
      @param card　参照するカード
      @return 取得したナンバー
    */
    public static int getRank(Cards card){
        return card.rank.id;
    }
    /**役を判断して3桁の数字で返す
     役番号
     Sフラッシュ :9
     フォーカード :8
     フルハウス  :7 
     フラッシュ  :6
     ストレート  :5
     スリーカード:4
     ツーペア   :3
     ワンペア   :2
     役なし     :1
     * 
     @param hands 手札
     @return 3桁の数字:3桁目に役番号,1〜2桁目に最大重複数字
     例:101,102,203,
     役なしの場合は先頭に1**を返す。
     */
    public static int judge(List<Cards> hands){
        //RankCheckリスト
        List<Integer>rankCheck = Arrays.asList(1,1,1,1,1);
        List<Integer>suitCheck = Arrays.asList(1,1,1,1,1);
        int twopairCount=0;     //ツーペアーかどうかチェックする
        int threecardCount=0;   //スリーペアーかどうかチェックする
        int straightCount=0;    //ストレートかどうかチェックする
        int returnvalue=2;      //1〜2桁目に返す値
        //リストを昇順にソートする
        hands.sort((a,b)->a.rank.id-b.rank.id);
        //Rank(ナンバー)が同じものがあるかどうか
        for(int i=0;i<hands.size();i++){
            for(int j=i+1;j<hands.size();j++){
                if(Cards.getRank(hands.get(i))==Cards.getRank(hands.get(j))){
                    rankCheck.set(i, rankCheck.get(i)+1);
                    //カードのナンバーに1重複してあればそれを最大値にする。
                    if(Cards.getRank(hands.get(i))==1)
                        returnvalue=14;
                    if(returnvalue<Cards.getRank(hands.get(i)))
                        returnvalue=Cards.getRank(hands.get(i));
                }
                if(Cards.getSuit(hands.get(i))==Cards.getSuit(hands.get(j)))
                    suitCheck.set(i, suitCheck.get(i)+1);
            }
        }
        //フォーカードかどうか
       for(int i=0;i<rankCheck.size();i++){
           if(rankCheck.get(i)==4)
               return 8*100+returnvalue;
           if(rankCheck.get(i)==3)
              threecardCount+=1;
           if(rankCheck.get(i)==2)
              twopairCount+=1;
       }
       //フラッシュかどうか
       for(int i=0;i<suitCheck.size();i++){
            if(suitCheck.get(i)==5)
                return 6*100+returnvalue;
        }
       //ストレートかどうか
       for(int i=1;i<hands.size();i++){
           if(Cards.getRank(hands.get(i))==Cards.getRank(hands.get(i-1))+1)
               straightCount++;
           else if(Cards.getRank(hands.get(i))==1&&Cards.getRank(hands.get(i-1))==13)
               straightCount++;
           if(straightCount==4)
               return 5*100+returnvalue; 
           if(straightCount==4&&suitCheck.get(i)==5)
               return 9*100+returnvalue; 
           
       }
       //フルハウス・スリーカード・ツーペアー・ワンペアかどうか
       if(threecardCount==1&&twopairCount==2)
           return 7*100+returnvalue;
       else if(threecardCount==1&&twopairCount==1)
           return 4*100+returnvalue;
       else if(twopairCount==2)
           return 3*100+returnvalue;
       else if(twopairCount==1)
           return 2*100+returnvalue;
       
       return 1;
    }
}
