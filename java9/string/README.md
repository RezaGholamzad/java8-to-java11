# Compact String : 

Compact String is one of the performance enhancements introduced in the JVM as part of JDK 9. 

## What is the need of Compact String?

Till JDK 8, Java represent String object as char[] because every character in java is 
of 2 bytes because Java internally uses UTF-16.

If any String contains a word in the English language then the character can be represented 
using a single byte only, we don’t need 2 bytes for each character. Many characters require 
2 bytes to represent them but most of the characters require only 1 byte, which falls under 
LATIN-1 character set. So, there is a scope to improve memory consumption and performance.

Java 9 introduced the concept of compact Strings. The main purpose of the compact string 
is whenever we create a string object and the characters inside the object can be represented 
using 1 byte, which is nothing but LATIN-1 representation, then internally java will create 
one byte[]. In other cases, if any character requires more than 1 byte to represent it then 
each character is stored using 2 bytes i.e. UTF-16 representation.

## how will it distinguish between the LATIN-1 and UTF-16 representations? 

Java developers introduced one final byte variable coder that preserves 
the information about characters representation. The value of coder value can be:

`static final byte LATIN1 = 0;`

`static final byte UTF16 = 1;`

## disable compact string : 

Disables the Compact Strings feature. By default, this option is enabled. When this option is enabled, 
Java Strings containing only single-byte characters are internally represented and stored as 
single-byte-per-character Strings using ISO-8859-1 / Latin-1 encoding. This reduces, by 50%, 
the amount of space required for Strings containing only single-byte characters. 
Disabling the Compact Strings feature forces the use of UTF-16 encoding as 
the internal representation for all Java Strings.

`-XX:-CompactStrings`