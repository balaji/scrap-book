package com.balaji;

class Decompression {

  /**
   * https://techdevguide.withgoogle.com/paths/advanced/compress-decompression/#!
   * @param compressed compressed string.
   * @return decompressed string.
   */
  static String decompress(String compressed) {
    char[] charArray = compressed.toCharArray();
    int repeat = 0;
    StringBuilder result = new StringBuilder();

    int i = 0;
    while (i < charArray.length) {
      char c = charArray[i];
      if (Character.isDigit(c)) {
        int base = 0;
        while (Character.isDigit(c)) {
          repeat = (repeat * base++ * 10) + Character.getNumericValue(c);
          c = charArray[++i];
        }
      }
      if (c == '[') {
        int openBracket = 1;
        StringBuilder temp = new StringBuilder();
        c = charArray[++i];
        if(c == ']') openBracket = 0;
        while (openBracket != 0) {
          temp.append(c);
          c = charArray[++i];
          if (c == '[') openBracket++;
          if (c == ']') openBracket--;
        }
        while (repeat > 0) {
          result.append(decompress(temp.toString()));
          repeat--;
        }
      } else {
        result.append(c);
      }
      i++;
    }
    return result.toString();
  }
}
