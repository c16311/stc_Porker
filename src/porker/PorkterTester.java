/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package porker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *　PorkTesterクラス(Mainクラス)
 * @author c16311
 */
public class PorkterTester {
    /**
     * 手札の状態によって役を表示する
     * @param Yakucode　プレイヤークラスのjudgeメソッドで得た3桁のコード*/
    public static void printYaku(int Yakucode){
        switch(Yakucode/100){
            case 2:System.out.println("ワンペア");      break;
            case 3:System.out.println("ツーペア");      break;
            case 4:System.out.println("スリーカード");   break;
            case 5:System.out.println("ストレート");     break;
            case 6:System.out.println("フラッシュ");     break;
            case 7:System.out.println("フルハウス");     break;
            case 8:System.out.println("フォーカード");   break;
            case 9:System.out.println("Sフラッシュ");     break;
            default:System.out.println("役なし");       break;
                
        }
    }
    /**メインメソッド*/
    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in);
        Random rand = new Random();        
        System.out.println("START");
        List<Cards>cards = Cards.newDeck();//トランプ1セット用意
        Collections.shuffle(cards);
        HumanPlayer you = new HumanPlayer("You",100);
        Player computer = new ComputerPlayer("CPU",100);
        int totalCoin=0;        //かけたコインの合計
        int youSetBet;          //プレイヤーが賭けたコインの枚数
        int cpuSetBet;          //プレイヤーが賭けたコインの枚数
        int cardNum;            //手札にあるカードの位置
        int exchangeflag;       //カードを交換するかどうか
        int exchangeCount=0;    //カードを交換した数
        
        //デッキからカードを5枚引く
        for(int i=0;i<5;i++){
            you.drawIn(cards);
            computer.drawIn(cards);
        }
        System.out.println("YOU");
        you.printHand();
        System.out.println("COMPUTER");
        computer.printHand();
        System.out.println();
        
        //コインのベット
        do{
            System.out.print("どれくらい賭ける...>");
            youSetBet=stdin.nextInt();
        }while(you.nowCoin()<youSetBet||youSetBet<=0);
        you.bet(youSetBet);totalCoin+=youSetBet;
        computer.bet((cpuSetBet=(rand.nextInt(5)+1)*10));totalCoin+=cpuSetBet;
        System.out.print("CPUのベット数: ...>"+cpuSetBet);
        System.out.println();
        
        //手札の交換
        System.out.println("手札の入れ替え");
        System.out.println("YOU");
        while(exchangeCount<5){
            do{
                System.out.print("交換しますか?[Yes..1/No..0]>");
                exchangeflag=stdin.nextInt();
            }while(exchangeflag>1||exchangeflag<0);
            if(exchangeflag==0) break;
            do{
                System.out.print("何番目のカードを入れ替えますか>");
                cardNum=stdin.nextInt()-1;
            }while(cardNum>4||cardNum<0);
            you.Exchange(cards,cardNum);
            exchangeCount++;
        }
       
        you.printHand();
        System.out.println("CPU");
        computer.Exchange(cards, 0);
        computer.printHand();
       
        //勝敗の判定
        System.out.print("YOUの結果:"+you.judge()+":");printYaku(you.judge());
        System.out.print("CPUの結果:"+computer.judge()+":");printYaku(computer.judge());
        switch(you.battle(computer)){
            case 1:
                System.out.println("あなたの勝利です。");
                you.getCoin(totalCoin);
                System.out.print("コイン"+totalCoin+"個獲得しました。");
                break;
            case -1:
                System.out.println("あなたの負けです。");
                computer.getCoin(totalCoin);
                System.out.print("コイン"+youSetBet+"個失いました。");
                break;
            case 0:
                System.out.println("引き分けです。");
                break;
        }  
    }
}
