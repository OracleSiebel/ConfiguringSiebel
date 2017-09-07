package com.oracle.utils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.oracle.constants.CrypterConstants;
import com.oracle.utils.StringUtil;

public class AESCrypter extends AESEncrypter {

	public AESCrypter() {

	}

	public AESCrypter(String algType) {
		super(algType);
	}
	

	public String decrypt(String inputString) throws Exception {// update exception type after integrating error handler

		String decrytedPassword = null;
		if (!StringUtil.isValidString(inputString)) {
			throw(new Exception());// update exception type after integrating error handler
		}

		inputString = inputString.trim();

		try {
			// Apply base 64 decoding
			byte[] afterBase64Decode = Base64.getDecoder().decode(inputString);

			// Divide the key and value
			String decodedOutput = new String(afterBase64Decode, CrypterConstants.CHARSET);
			int length2=decodedOutput.length()-length1;
			String[] outputArray = { decodedOutput.substring(0, length1), decodedOutput.substring(length1, length2),
					decodedOutput.substring(length2) };
			String decodedValue = outputArray[1];
			String decodedSeed = outputArray[0] + outputArray[2];

			// separate password and AlgoType
			int algoLength=decodedValue.length()- CrypterConstants.SECONDLENGTH;
			String[] parts = { decodedValue.substring(0, algoLength), decodedValue.substring(algoLength) };
			decodedValue = parts[0];
			String algoValue = parts[1];

			// decode algo type
			SecretKey rawKey1 = getRawKey(decodedSeed.getBytes());
			algoType = decryptText(algoValue, rawKey1);

			// Decrypt the key using fixed seed
			SecretKey rawKey = getRawKey(CrypterConstants.FIXEDSEED.getBytes());
			String decrptedSeed = decryptText(decodedSeed, rawKey);

			// Decrypt password using decrypted random seed
			byte[] decodedKey = Base64.getDecoder().decode(decrptedSeed);
			SecretKey originalKey = new SecretKeySpec(decodedKey, algoType);
			decrytedPassword = decryptText(decodedValue, originalKey);

			return decrytedPassword;
		} catch (Exception e) {
			throw e;

		}

	}

	private String decryptText(String cipherText, SecretKey secKey) throws Exception {
		byte[] byteCipherText = DatatypeConverter.parseBase64Binary(cipherText);
		Cipher aesCipher = Cipher.getInstance(algoType);
		aesCipher.init(Cipher.DECRYPT_MODE, secKey);
		byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
		return new String(bytePlainText);
	}

}
