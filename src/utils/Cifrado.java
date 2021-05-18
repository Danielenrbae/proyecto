package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Cifrado {
	public static String cifrado(String password) {
		String password_cifrada;
		MessageDigest md = null;
		byte[] digest = null;

		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			digest = md.digest();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		StringBuffer sb;
		sb = new StringBuffer();

		for (byte b : digest) {
			sb.append(String.format("%02x", b));
		}

		password_cifrada = sb.toString();

		return password_cifrada;
	}
	
	// GENERA CADENA ALEATORIA
	public static String generarCodigo() {
		String codigo;
		Random aleatorio;
		String alfa;
		int numero;
		int forma;

		aleatorio = new Random();
		alfa = "ABCDEFGHIJKLMNOPQRSTVWXYZ";
		codigo = "";

		forma = (int) (aleatorio.nextDouble() * alfa.length() - 1 + 0);
		numero = (int) (aleatorio.nextDouble() * 99 + 100);

		codigo = codigo + alfa.charAt(forma) + numero;

		return codigo;
	}
}
