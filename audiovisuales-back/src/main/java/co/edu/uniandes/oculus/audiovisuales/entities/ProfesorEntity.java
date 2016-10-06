/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author fa.lopez10
 */
@Entity
public class ProfesorEntity extends BaseEntity implements Serializable{
    private String login;
    private int codigo;
    @OneToMany(mappedBy = "profesor")
    private ArrayList<ReservaEntity> reservas;
}
