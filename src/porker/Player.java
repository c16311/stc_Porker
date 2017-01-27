package porker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Playerの処理をまとめたクラス
 * コイン処理はここでここでしている。
 * @author c16311
 */
public class Player {
    private String name;
    private int coins =0;
    private List<Cards>hand;//手札
    
    /**コンスタラクタ
     * プレイヤーを生成するコンストラクタでCardクラスのリスト
     にしたhandクラスも生成する。
     * @param name プレイヤー名
     * @param coins コインの初期値*/
    public Player(String name,int coins){
        this.name = name;
        this.coins = coins;
        hand = new ArrayList<>();
    }
    /**コインの設定
     * @param num セットするコインの数*/
    public void setCoins(int num) {
       this.coins = num;
    }
    /**コインを賭ける
     * playerのコインの枚数から減らします。
     * @param num 賭けるコインの数*/
    public void bet(int num){
        this.coins -= num;
    }
    /**コインを獲得する
     * playerのコインの枚数から増やします。
     * @param num 獲得したコインの数*/
    public void getCoin(int num){
        this.coins += num;
    }
     /**現在コインを取得する
     * playerのコインの枚数から増やします。
     * @return 現在のコインの枚数*/
    public int nowCoin(){
        return this.coins;
    }
    /**カードを引く
     * デッキの一番上からカードを引く。
     * @param deck 山札*/
    public void drawIn(List<Cards> deck){
        hand.add(deck.remove(0));
    }
    /**カードを捨てる
     * @param index　捨てたいカードの位置
     * @return カードを捨てた状態の手札*/
    public Cards drawOut(int index){
        return hand.remove(index);
    }
    /**カードの交換をする
     * カードを捨てた後手札の末尾に新しいカードを加える
     * @param deck　山札
     * @param index　交換したいカードの位置*/
    public void Exchange(List<Cards> deck,int index){
        hand.remove(index);
        hand.add(deck.remove(0));
        
    }
    /**手札の確認*/
    public void printHand(){
        hand.stream().map((hand1) -> {
            System.out.printf("%5s ", Cards.getRank(hand1));
            return hand1;
        }).forEach((hand1) -> {
            System.out.printf("%s\n", Cards.getSuit(hand1));
        });
    }
    
    /**役の内容を取得する
     * @return cardsクラスのjudgeメソッドで得た3桁のコードを返す*/
    public int judge(){
        return  Cards.judge(hand);
        
    }
    /**
     * 役の判定を行う
     * @param player 対戦しているプレイヤー
     * @return あなたが勝利したら1をあなたが敗北したら2を引き分けなら0を返す。
     */
    //役の判定
    public int battle(Player player){
        //互いの3桁のコードを比較する
        if(this.judge()>player.judge()){
            return 1;
        }else if(this.judge()<player.judge()){
            return -1;
        }
        return  0;
        
    }
}
