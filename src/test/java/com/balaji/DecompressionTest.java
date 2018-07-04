package com.balaji;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DecompressionTest {

  @Test
  public void shouldDecompressEncodedStrings() {
    assertThat(Decompression.decompress("a"), is("a"));
    assertThat(Decompression.decompress("2[a]bcd"), is("aabcd"));
    assertThat(Decompression.decompress("10[a]b"), is("aaaaaaaaaab"));
    assertThat(Decompression.decompress("2[a]c"), is("aac"));
    assertThat(Decompression.decompress("2[ab]c"), is("ababc"));
    assertThat(Decompression.decompress("2[a]2[b]c"), is("aabbc"));
    assertThat(Decompression.decompress("2[a3[f]]c"), is("afffafffc"));
  }
}