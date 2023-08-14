package com.akirolabs.token.generatorservice.utils;

import java.util.List;
import java.util.Random;

/**
 * @author: Sandeep Kumar Nagaraj
 * @since: 12-08-2023
 */
public interface TokenGeneratorUtil {
    /**
     * This method perform below logic to generate the new token
     *
     * 1. Loop till the requested token length
     * 2. For every 4th digit append "-" to the output token string
     * 3. Using Randam() generate the random numbers with the numbers ranging from 0 to list.size() -1
     * 4. Pick the numbers from list index based on the Random() generated numbers
     *
     * @param digits - User selected digits
     * @param requestSize - Size of the user inputs
     * @param length - token length
     * @return
     */
    static StringBuilder createCustomSizeToken(
            List<Integer> digits, int requestSize, int length) {
        StringBuilder token = new StringBuilder();
        Random randomNumber = new Random();

        for(int i=0; i<length ; i++) {
            if(i!=0 && i%4 == 0) {
                token.append("-");
            }
            token.append(digits.get(randomNumber.nextInt(requestSize)));
        }
        return token;
    }
}
