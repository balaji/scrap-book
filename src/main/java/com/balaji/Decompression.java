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
      // Get the full number before the '['
      if (Character.isDigit(c)) {
        int base = 0;
        while (Character.isDigit(c)) {
          repeat = (repeat * base++ * 10) + Character.getNumericValue(c);
          c = charArray[++i];
        }
      }

      // Find the full representation within '[' and ']'
      if (c == '[') {
        StringBuilder temp = new StringBuilder();
        c = charArray[++i];
        // handle empty '[]' case.
        int openBracket = (c == ']') ? 0 : 1;
        while (openBracket != 0) {
          temp.append(c);
          c = charArray[++i];
          if (c == '[') openBracket++;
          if (c == ']') openBracket--;
        }

        //recurse to find inner compressed strings.
        String decompressed = decompress(temp.toString());
        while (repeat-- > 0) {
          result.append(decompressed);
        }
      } else {
        // append individual characters that are not prefixed with numbers.
        result.append(c);
      }
      i++;
    }
    return result.toString();
  }
}
