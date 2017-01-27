/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package porker;

/**
 *
 * @author c16311
 */
public enum CardSuit {
    Diamond(1),Spade(2),Clover(3),Heart(4);
    private final int id;
    private CardSuit(int id){
        this.id = id;
    }
}
