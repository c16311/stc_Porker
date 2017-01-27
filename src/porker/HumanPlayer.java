/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package porker;

import java.util.Random;
/**
 *　Playerクラスを継承したHumanPlayerクラス
 * @author c16311
 */
public class HumanPlayer extends Player{
    public HumanPlayer(String name,int coins){
        super(name,coins);
    }
    /**現在コインを取得する
     * playerのコインの枚数から増やします。
     * @return 現在のコインの枚数*/
    public int nowCoin(){
        return this.coins;
    }
}
