/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author gc.andrade10
 */
public class EquipoEntity extends BaseEntity implements Serializable
{
    
    private TipoEntity tipo;
    private String caracteristicas;
    private ArrayList<ReservaEntity> reservas;
    
    
}
