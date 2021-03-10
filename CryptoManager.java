

public class CryptoManager {
	
	private static final char LOWER_BOUND = ' ';
	private static final char UPPER_BOUND = '_';
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_BOUND and UPPER_BOUND characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean stringInBounds (String plainText) {
		boolean withinRange = false; //Boolean var to check if string characters are within the bounds.
		//Loops through each character's ASCII to check if it's less than or equal to the higher bound or grater than or equal to the higher bound
		for (int k = 0; k < plainText.length(); k++)
		{
			if ((int)plainText.charAt(k)  <= UPPER_BOUND && (int)plainText.charAt(k) >= LOWER_BOUND)
			{
				withinRange = true;
			}
			else
			{
				withinRange = false; 
				break;
				
			}
		}
		return withinRange;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String encryptCaesar(String plainText, int key) {
		String encryptedText = ""; //String to hold encrypted text
		int key1 = key; //int clone of the key variable that is passed when called
		
		//Checks if the key is greater than the upper bound, if it is subtract range until it's not.
		if (key1 > (int)UPPER_BOUND)
		{
			do 
			{
				key1 = key1 - RANGE;
			}while(key1 > (int)UPPER_BOUND);
		}
		
		//Creates encrypted message via ceaser's method, checks if the encrypted character is greater than the upper bound, if it is subtract range once
		for (int i = 0; i < plainText.length(); i++)
		{
			int chOld = plainText.charAt(i);
			int chNew = chOld + key1;
			if (chNew > (int)UPPER_BOUND)
			{
				chNew = chOld + (key1 - RANGE);
			}
			encryptedText += (char)chNew;
			
		}
		return encryptedText;
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) {
		String eT = ""; //String var to hold encrypted Text
		String text = plainText;//String var clone of the text to be encrypted
		String key = bellasoStr;//String clone of the encryption keyword
		int offSet = 0; //number to be added to the character Ascii, so the encrypted character will be added to the eT var
		int diff = text.length() - key.length(); // difference of characters between the passed text and the keyword
		
		//Sets the keyword to the same length as the passed String
		if (text.length() != key.length())
		{
			for (int j = 0; j < diff; j++)
			{
				key = key + key.charAt(j);
			}
		}
		
		//Encrypts via the bellaso method, by adding the offSet to the eT string
		for (int g = 0; g < text.length(); g++)
		{
			offSet = (int)text.charAt(g) + (int)key.charAt(g);
			if (offSet > (int)UPPER_BOUND)
			{
				do
				{
					offSet = offSet - RANGE;
				}while(offSet > (int)UPPER_BOUND);
			}
			eT += (char)offSet;
			
		}
		return eT;
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String decryptCaesar(String encryptedText, int key) {
		String originalText = ""; //String to hold the original Text
		int key2 = key; //key clone of the encryption offSet 
		
		//Check if the key is greater than the upper bound, reduce by range until it's not.
		if (key2 > (int)UPPER_BOUND)
		{
			do 
			{
				key2 = key2 - RANGE;
			}while(key2 > (int)UPPER_BOUND);
		}
		
		//decrypt the encrypted text by reversing the encryption method
		for (int l = 0; l < encryptedText.length(); l++)
		{
			int enCh = encryptedText.charAt(l);
			int orCh = enCh - key2;
			if (orCh < (int)LOWER_BOUND)
			{
				orCh = enCh - (key2 - RANGE);
			}
			originalText += (char)orCh;
			
		}
		return originalText;
		
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr) {
		String oT = ""; //String to hold the Original Text
		String enText = encryptedText; //String to hold the encrypted text
		String key3 = bellasoStr;//String to hold the encryption key
		int offSet = 0;//Offset to subtract the encryption key by
		int diff = enText.length() - key3.length();//diff var to hold the difference between characters for the encrypted text and the encryption key
		
		//lengthen the encryption key if necessary
		if (enText.length() != key3.length())
		{
			for (int j = 0; j < diff; j++)
			{
				key3 = key3 + key3.charAt(j);
			}
		}
		
		
		//Reverse the encryption by adding the range to the current ascii of the encryption character. 
		for (int v = 0; v < enText.length(); v++)
		{
			offSet = (int)enText.charAt(v);
			if (offSet < (int)UPPER_BOUND)
			{
				do
				{
					offSet += RANGE;
				}while(offSet < (int)UPPER_BOUND);
			}
			offSet = offSet - (int)key3.charAt(v);
			if (offSet > (int)UPPER_BOUND)
			{
				offSet = offSet - RANGE;
			}
			else if (offSet < (int)LOWER_BOUND)
			{
				offSet = offSet + RANGE;
			}
			oT += (char) offSet;
		}
		return oT;
	}
}