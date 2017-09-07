package com.oracle.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import com.oracle.constants.CrypterConstants;

public class AESEncrypter {

	protected String algoType = CrypterConstants.AESALGORITHM;
	private int algoKeySize = CrypterConstants.KEYSIZE_256;
	protected int length1 = CrypterConstants.FIRSTLENGTH;

	public AESEncrypter() {

	}

	public AESEncrypter(String algType) {
		this.algoType = algType;

	}


	public String encrypt(String inputString) throws Exception {// update
																// exception
																// type after
																// integrating
																// error handler

		String encodedFinalOutPut = null;
		if (inputString == null || inputString.trim().isEmpty()) {

			throw (new Exception());// update exception type after integrating
									// error handler
		}

		inputString = inputString.trim();

		try {
			// Generate Random key
			SecretKey randomKey = getSeed();

			// Encrypt input String using random key
			String encryptedPassword = encryptText(inputString, randomKey);

			// Encrypt random key using fixed seed value
			SecretKey rawKey = getRawKey(CrypterConstants.FIXEDSEED.getBytes());
			String encodedKey = Base64.getEncoder().encodeToString(randomKey.getEncoded());
			String encryptedSeed = encryptText(encodedKey, rawKey);

			// Encrypt Algo Type
			SecretKey rawKey1 = getRawKey(encryptedSeed.getBytes());
			String encryptedAlgoType = encryptText(algoType, rawKey1);

			// Append EncryptedPassword and Algo type
			encryptedPassword = encryptedPassword + encryptedAlgoType;

			// Append the encrypted value in the middle of encrypted key
			final int mid = encryptedSeed.length() / 2;
			String[] parts = { encryptedSeed.substring(0, mid), encryptedSeed.substring(mid) };
			String appendedString = parts[0] + encryptedPassword + parts[1];

			// Apply base64 encoding
			encodedFinalOutPut = Base64.getEncoder().encodeToString(appendedString.getBytes(CrypterConstants.CHARSET));

			return encodedFinalOutPut;
		} catch (Exception e) {
			throw e;
		}
	}

	protected SecretKey getSeed() throws NoSuchAlgorithmException {
		KeyGenerator generator = KeyGenerator.getInstance(algoType);
		generator.init(algoKeySize);
		SecretKey secKey = generator.generateKey();
		return secKey;
	}

	protected SecretKey getRawKey(byte[] seed) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance(algoType);
		SecureRandom sr = SecureRandom.getInstance(CrypterConstants.SECURERANDOM);
		sr.setSeed(seed);
		kgen.init(algoKeySize, sr);
		SecretKey skey = kgen.generateKey();
		return skey;
	}

	private String encryptText(String plainText, SecretKey secKey) throws Exception {
		Cipher aesCipher = Cipher.getInstance(algoType);
		aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
		byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());
		String out = DatatypeConverter.printBase64Binary(byteCipherText);
		return out;
	}

	public static void main(String args[]) {

		if (args.length != 0) {

			String algoType = CrypterConstants.AESALGORITHM;

			if (args.length == 2) {
				algoType = args[1];
			}

			String inputString = args[0];

			AESEncrypter encrypter = new AESEncrypter(algoType);
			String encryptedString = null;
			try {
				encryptedString = encrypter.encrypt(inputString);
			} catch (Exception e) {

				System.out.println(e.getMessage());

			}
		} else {
			System.out.println("Please enter valid input.");
		}

	}

}
