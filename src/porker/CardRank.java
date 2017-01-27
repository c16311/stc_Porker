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
public enum CardRank {
    A(1),Two(2),Three(3),Four(4),Five(5),Six(6),Seven(7),Eight(8),Nine(9),Ten(10),J(11),Q(12),K(13);
    private final int id;
    private CardRank(final int id){
        this.id = id;
    }
}
