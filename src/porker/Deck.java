package porker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * カードの山札クラス
 * @author c16311
 */
public class Deck extends ArrayList<Card>{
    public Deck(){
    }
    /*カードを追加する*/
    //deck.add(Card card);
    /*カードを1枚か引く
      もし、枚数以上のカードの場合は最後のカードを返す。
    */
    public Card withDraw(int num){
        if(num>size())
            num=size();
        return remove(num);
    }
    /**山札をシャッフルして順序をバラバラにする*/
    public void shuffle(){
        Collections.shuffle(this);
    }
}
