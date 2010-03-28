package br.com.assinchronus.exception;
//erro svn
public class JogadaInvalida extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JogadaInvalida() {
		super();
	}

	public JogadaInvalida(String msg) {
		super(msg);
	}

	public JogadaInvalida(String msg, Throwable t) {
		super(msg, t);
	}
}
