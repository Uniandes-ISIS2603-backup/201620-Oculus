/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.oculus.audiovisuales.exceptions;

import javax.ejb.ApplicationException;

/**
 *representa las exceptions que se generaran en Audiovisuales
 * @author Sneider Velandia G
 */
@ApplicationException(rollback = true)
public class BusinessLogicException extends Exception
{
    public BusinessLogicException() 
    {
        super();
    }

    public BusinessLogicException(String message) 
    {
        super(message);
    }

    public BusinessLogicException(Throwable cause) 
    {
        super(cause);
    }

    public BusinessLogicException(String message, Throwable cause) 
    {
        super(message, cause);
    }
    
}
