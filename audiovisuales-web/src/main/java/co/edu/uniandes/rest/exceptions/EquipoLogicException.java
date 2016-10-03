/**
 * Representa las excepciones de la lógica de EquipoLogic 
 */
package co.edu.uniandes.rest.exceptions;

/**
 *
 * @author gc.andrade10
 */
public class EquipoLogicException extends Exception
{
    /**
	 * versión usada en la serialización de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto
	 */
	public EquipoLogicException() {
	}

	/**
	 * Constructor con un mensaje
	 * @param message mensaje de la excepción
	 */
	public EquipoLogicException(String message) {
		super(message);
	}

	/**
	 * Constructor con una causa
	 * @param cause causa de la excepción. Usada para generar la traza.
	 */
	public EquipoLogicException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor con mensaje y causa.
	 * @param message mensaje de la excepción
	 * @param cause causa de la excepción. Usada para generar la traza.
	 */
	public EquipoLogicException(String message, Throwable cause) {
		super(message, cause);
	}

}
